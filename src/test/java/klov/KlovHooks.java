package klov;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentKlovReporter;
import com.thoughtworks.gauge.*;
import utilities.KlovUtils;

import java.io.IOException;

public class KlovHooks {


    private static ExtentReports extent;
    private static ExtentTest suite;
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    static {
        extent = new ExtentReports();

        ExtentKlovReporter klov = null;
        try {
            klov = new ExtentKlovReporter(KlovUtils.getProperty("klovUrl"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            klov.initMongoDbConnection(KlovUtils.getProperty("klovMongoHost"), KlovUtils.getPropertyInt("klovMongoPort")); // Uzak sunucu ise IP yaz
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            klov.setProjectName(KlovUtils.getProperty("klovProjectName"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            klov.setReportName(KlovUtils.getProperty("klovProjectName"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        extent.attachReporter(klov);
    }

    @BeforeScenario
    public void beforeScenario(ExecutionContext context) throws IOException {
        if (KlovUtils.getProperty("useKlov").equals("true")){
            this.suite = this.extent.createTest(context.getCurrentScenario().getName());
            if (context.getCurrentScenario().getIsFailing()){
                suite.fail(context.getCurrentScenario().getName());
            } else {
                suite.pass(context.getCurrentScenario().getName());
            }
        }


    }

    @AfterStep
    public void afterStep(ExecutionContext context) throws IOException {
        if (KlovUtils.getProperty("useKlov").equals("true")){
            String stepText = context.getCurrentStep().getDynamicText();
            ExtentTest step = this.suite.createNode(stepText);
            if (context.getCurrentStep().getIsFailing()){
                step.fail(context.getCurrentStep().getDynamicText());
            }else {
                step.pass(context.getCurrentStep().getDynamicText());
            }
            this.test.set(step);
        }

    }

    @AfterScenario
    public void afterScenario() throws IOException {
        if (KlovUtils.getProperty("useKlov").equals("true")) {
            this.extent.flush();
        }

    }
}
