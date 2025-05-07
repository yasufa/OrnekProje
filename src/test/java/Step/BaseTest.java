package Step;

import com.microsoft.playwright.*;
import com.thoughtworks.gauge.AfterScenario;
import com.thoughtworks.gauge.AfterSuite;
import com.thoughtworks.gauge.BeforeScenario;
import com.thoughtworks.gauge.BeforeSuite;
import utilities.PropertyUtils;

import java.io.IOException;
import java.util.Arrays;

public class BaseTest {

    static Playwright playwright;
    static Browser browser;
    static BrowserContext context;
    static Page page;

    @BeforeScenario
    public static void setUp() throws IOException {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions()
                .setHeadless(false)
                .setArgs(Arrays.asList(new String[]{"--start-maximized"}))

        );
        context = browser.newContext(new Browser.NewContextOptions().setViewportSize(null).setIgnoreHTTPSErrors(true));
        page = context.newPage();
        page.navigate(PropertyUtils.getProperty("url"));
    }

    @AfterScenario
    public static void tearDown() {
        context.close();
        browser.close();
        playwright.close();
    }
}
