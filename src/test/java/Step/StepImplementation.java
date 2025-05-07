package Step;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.WaitForSelectorState;
import com.thoughtworks.gauge.Step;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import utilities.ElementUtils;
import utilities.PropertyUtils;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class StepImplementation extends BaseTest {


    LocatorReader locaterReader;


    @Step("<key> li elementlerden <text> degerine esit olana tikla")
    public void clickElementByTxt(String key, String text){
            page.waitForSelector(LocatorReader.getLocaterString(key));
        try {Locator elements = LocatorReader.getLocator(page, key);
            int count = elements.count();

            for (int i = 0; i < count; i++) {
                String elementText = elements.nth(i).innerText();
                if (text.startsWith("Key_")){
                    if (elementText.equals(PropertyUtils.getProperty(text))){
                        elements.nth(i).click();
                        break;
                    }
                } else
                    if (elementText.equals(text)){
                        elements.nth(i).click();
                        break;
                    }


            }} catch (Exception e){
            System.out.println(text + " elementi bulunamadı.");
        }
    }

    @Step("<key> li elementlerden <text> degerini içerene tikla")
    public void clickElementByContainsTxt(String key, String text){
        page.waitForSelector(LocatorReader.getLocaterString(key));
        try {Locator elements = LocatorReader.getLocator(page, key);
            int count = elements.count();

            for (int i = 0; i < count; i++) {
                String elementText = elements.nth(i).innerText();
                if (text.startsWith("Key_")){
                    if (elementText.contains(PropertyUtils.getProperty(text))){
                        elements.nth(i).click();
                        break;
                    }
                } else
                if (elementText.contains(text)){
                    elements.nth(i).click();
                    break;
                }


            }} catch (Exception e){
            System.out.println(text + " elementi bulunamadı.");
        }
    }

    @Step("<key> li elementin <attr> attributesi <attrtext> mi")
    public void findElementWithTextAndCheckAttributeText(String key, String attr, String attrtext){
        try {
            page.waitForSelector(LocatorReader.getLocaterString(key));
            Locator element = LocatorReader.getLocator(page, key);
            String attrValue = element.getAttribute(attr);
            if (!(attrValue.equals(attrtext))){
                Assert.fail("\nBeklenen Text: "+attrtext + " \nElement Text: "+attrValue);
            }
        } catch (Exception e){
            System.out.println(attrtext + " attr bulunamadı.");
        }
    }

    @Step("<key> li elementin value attributesi <attrtext> yaz")
    public void findElementWithTextAndCheckAttributeTextwrite(String key, String attrtext){
        try {
            page.waitForSelector(LocatorReader.getLocaterString(key));
            Locator element = LocatorReader.getLocator(page, key);
            if (attrtext.startsWith("Key_")){
                InputHelper.setInputValue(page,LocatorReader.getLocaterString(key),PropertyUtils.getProperty(attrtext));
            } else
                InputHelper.setInputValue(page,LocatorReader.getLocaterString(key),attrtext);

            System.out.println("Value attribute "+ attrtext+ "yazıldı.");
        } catch (Exception e){
            System.out.println(attrtext + " attr yazılamadı.");
        }
    }


    @Step("<key> elementi <text> iceriyor mu?")
    public void chechTextContains(String key, String text) throws IOException {
        page.waitForSelector(LocatorReader.getLocaterString(key));
        Locator element = LocatorReader.getLocator(page, key);
        System.out.println("Element: "+element.innerText());

        if (text.startsWith("Key_")){
            if (!(element.innerText().contains(PropertyUtils.getProperty(text)))){
                Assert.fail("\nBeklenen Text: "+PropertyUtils.getProperty(text) + " \nElement Text: "+element.innerText());
            }
        }else
        if (!(element.innerText().contains(text))){
            Assert.fail("\nBeklenen Text: "+text + " \nElement Text: "+element.innerText());
        }
    }

    @Step("<key> elementi Provizyon No iceriyor mu?")
    public void chechTextContainsProvizyonNo(String key) throws IOException {
        page.waitForSelector(LocatorReader.getLocaterString(key));
        Locator element = LocatorReader.getLocator(page, key);
        System.out.println("Element: "+element.innerText());

        if (provizyonNo3.startsWith("Key_")){
            if (!(element.innerText().contains(PropertyUtils.getProperty(provizyonNo3)))){
                Assert.fail("\nBeklenen Text: "+PropertyUtils.getProperty(provizyonNo3) + " \nElement Text: "+element.innerText());
            }
        }else
        if (!(element.innerText().contains(provizyonNo3))){
            Assert.fail("\nBeklenen Text: "+provizyonNo3 + " \nElement Text: "+element.innerText());
        }
    }

    @Step("<key> elemente <text> yaz")
    public void typeText(String key, String text) throws IOException {
        try {
            page.waitForSelector(LocatorReader.getLocaterString(key));
            Locator selector = LocatorReader.getLocator(page, key);
            selector.clear();

            if (text.startsWith("Key_")){
                selector.fill(PropertyUtils.getProperty(text));
            } else {
                selector.click();
                selector.fill(text);
            }
        } catch (Exception e){
            System.out.println(e);
        }
    }

    @Step({"Send keys to element text <text>(robot)",
            "Elemente <text> degerini yaz(robot)"})
    public void sendKey(String text) throws AWTException {

        StringSelection ss = new StringSelection(text);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss,null);

        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);

    }

    String provizyonNo3;
    @Step("<key> li elementin textini <text> olarak kaydet")
    public void textKaydet(String key, String text) throws IOException {
        page.waitForSelector(LocatorReader.getLocaterString(key));
        Locator element = LocatorReader.getLocator(page, key);
        System.out.println("element.getText() = " + element.innerText());
        provizyonNo3= element.innerText();
    }

    @Step("<key> elemente provizyonNo yaz")
    public void typeTextProvizyonNo(String key) throws IOException {
        try {
            page.waitForSelector(LocatorReader.getLocaterString(key));
            Locator selector = LocatorReader.getLocator(page, key);
            selector.clear();

            if (provizyonNo3.startsWith("Key_")){
                selector.fill(PropertyUtils.getProperty(provizyonNo3));
            } else {
                selector.click();
                selector.fill(provizyonNo3);
            }
        } catch (Exception e){
            System.out.println(e);
        }
    }


    @Step("<key> li elementleri bul ve <text> degerine esit olanın yanındaki checkbox a tıkla")
    public void clickCheckboxNextToIt(String key, String text){
        page.waitForSelector(LocatorReader.getLocaterString(key));
        try {Locator elements = LocatorReader.getLocator(page, key);
            int count = elements.count();
            Locator locaterElement = LocatorReader.getLocator(page, key + "../td/input");

            for (int i = 0; i < count; i++) {
                String elementText = elements.nth(i).innerText();
                if (elementText.equals(text)){
                    locaterElement.nth(i).click();
                    break;
                }
            }} catch (Exception e){
            System.out.println(text + " elementi bulunamadı.");
        }
    }





    @Step("<url> urle git")
    public void goToUrl(String url) {
        page.navigate(url);
    }

    @Step("<second> sn bekle")
    public void sleep(int second) {
        try {
            Thread.sleep(second * 1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Step("<key> elemente tıkla")
    public void clickElement(String key) {
        page.waitForSelector(LocatorReader.getLocaterString(key));
        Locator element = LocatorReader.getLocator(page, key);
        element.click();
    }

    @Step("<key> elementi var mı?")
    public void checkElement(String key) {
        try {
            page.waitForSelector(LocatorReader.getLocaterString(key));
            Locator element = LocatorReader.getLocator(page, key);
        } catch (Exception e) {
            Assert.assertTrue("Element bulunamadı", false);
        }


    }

    @Step("<key> elementi görünüyor mu?")
    public void checkElementVisible(String key) {
        try {
            page.waitForSelector(LocatorReader.getLocaterString(key));
            Locator element = LocatorReader.getLocator(page, key);
            if (!element.isVisible()){
                Assert.fail("Element görünür değil.");
            }
        } catch (Exception e){
            Assert.assertTrue("Element görünür değil.", false);
        }

    }



    public void clearInputWithBackspace(Page page, String selector) {
        Locator inputField = page.locator(selector);
        inputField.click();
        inputField.selectText();
        inputField.press("Backspace");
    }

    @Step("<key> elementini temizle")
    public void clearInputText(String key){
        try {
            page.waitForSelector(LocatorReader.getLocaterString(key));
            Locator selector = LocatorReader.getLocator(page, key);
            selector.click();
            selector.selectText();
            selector.press("Backspace");

        } catch (Exception e){
            System.out.println(e);
        }
    }

    @Step("<key> elemente <text> yazDT")
    public void typeTextDT(String key, String text) throws IOException {
        try {
            page.waitForSelector(LocatorReader.getLocaterString(key));
            Locator selector = LocatorReader.getLocator(page, key);
            selector.clear();

            if (text.startsWith("Key_")){
                selector.fill(PropertyUtils.getProperty(text));
                page.evaluate("document.querySelector('#birthDate').dispatchEvent(new Event('input', { bubbles: true }))");
            } else {
                selector.click();
                selector.fill(text);
                page.evaluate("document.querySelector('#birthDate').dispatchEvent(new Event('input', { bubbles: true }))");
            }
        } catch (Exception e){
            System.out.println(e);
        }
    }

    @Step("Enter tusuna tıklanır")
    public void pressEnterKey() {
        page.keyboard().press("Enter");
    }

    @Step("Sayfayı asagı kaydır")
    public void sayfayiAsagiKaydir(){
        page.evaluate("window.scrollBy(0, window.innerHeight)");
        page.evaluate("caches.keys().then(keys => { keys.forEach(key => caches.delete(key)); });");
    }

    @Step("Sayfayı yenile")
    public void sayfayiYenile(){
        page.reload();
    }


    @Step("Giriş başarılı olmalıdır")
    public void verifyLoginSuccess() {
        Assertions.assertTrue(LocatorReader.getLocator(page, "logoutButton").isVisible());
    }

    @Step("<key> elementine <file> dosyasını yükle")
    public void fileUpload(String key, String file){
        page.waitForSelector(LocatorReader.getLocaterString(key));
        Locator selector = LocatorReader.getLocator(page, key);

        String filePath = "C:\\Users\\" + System.getProperty("user.name") + "\\" + "Desktop" + "\\" + file;
        page.locator(key).setInputFiles(Paths.get(filePath),new Locator.SetInputFilesOptions());


        selector.setInputFiles(Paths.get(filePath));
    }

    @Step("<file> dosyasını yükle")
    public void fileUploadRobot(String file) throws AWTException {

        String filePath = "C:\\Users\\" + System.getProperty("user.name") + "\\" + "Desktop" + "\\" + file;
        StringSelection selection = new StringSelection(filePath);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, null);

        Robot robot = new Robot();
        robot.delay(1000);
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.delay(500);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);


    }

    @Step("<key> li elementi invisible olana kadar bekle")
    public void waitInvisible(String key){
        Locator selector = LocatorReader.getLocator(page, key);
        page.waitForSelector(String.valueOf(selector), new Page.WaitForSelectorOptions().setState(WaitForSelectorState.HIDDEN));
    }

    @Step("<key> li elementin tarihine <text> gün ekle")
    public void addDate(String key, String text) {
        page.waitForSelector(LocatorReader.getLocaterString(key));
        Locator selector = LocatorReader.getLocator(page, key);
        Calendar cal = Calendar.getInstance();
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        cal.add(Calendar.DATE, Integer.parseInt(text));
        selector.click();
        selector.fill(dateFormat.format(cal.getTime()));
    }

    @Step("<key> li elemente bugünün tarihini yaz")
    public void todayCalenderSendKey(String key){

        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1; // Ay 0-11 arasında indekslenir.
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        String today = day + "." + month + "." + year;

        page.waitForSelector(LocatorReader.getLocaterString(key));
        Locator selector = LocatorReader.getLocator(page, key);
        selector.clear();
        selector.fill(today);

    }

    @Step("<filename> isimli dosya indi mi?")
    public void checkDownloadFileAndDelete(String filename){
        String home = System.getProperty("user.home");
        File file = new File(home+"/Downloads/" + filename);
        if (file.delete())
            System.out.println("file deleted");
    }

    @Step("<key> elementin <text> den küçük olduğunu kontrol et")
        public void checkAttributeedeesaass(String key, String text) {
        page.waitForSelector(LocatorReader.getLocaterString(key));
        try {Locator elements = LocatorReader.getLocator(page, key);
            int count = elements.count()-1;
            for (int i = 0; i < count; i++) {
                String elementText = elements.nth(i).innerText();
                System.out.println(elementText);
                if (Double.parseDouble(elements.nth(i).innerText().replace(",","."))>Double.parseDouble(text)){
                    Assert.assertTrue(text + " ten yüksek tutar var",false);
                    break;
                    }


            }} catch (Exception e){
            System.out.println(text + " elementi bulunamadı.");
        }
    }

    @Step("Yeni açılan sayfaya geç")
    public void navigateWindow(){
        List<Page> pages = page.context().pages();
        Page newTab = pages.get(pages.size() - 1);
        newTab.bringToFront();
        System.out.println("Yeni sekmeye geçildi: "+ newTab.url());
    }

    @Step("Sayfanın url i <url> mı?")
    public void checkUrl(String url){
        try {
            String currentUrl = page.url();
            if (currentUrl.equals(url)){
                System.out.println("Sayfanın url i doğru");
            }

        } catch (Exception e) {
            Assert.assertTrue("Sayfanın url i " + url + " değil. Url: " + page.url(), false);
        }
    }

    @Step("<key> li element tıklanabilir değil mi?")
    public void checkNotClicable(String key) {
        page.waitForSelector(LocatorReader.getLocaterString(key));
        Locator selector = LocatorReader.getLocator(page, key);

        if (ElementUtils.isNotClickable(selector)) {
            System.out.println("Buton şu an tıklanamaz durumda.");
        } else {
            Assert.assertTrue("Element tıklanabilir.", false);
        }
    }

    }




