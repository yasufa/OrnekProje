package Step;

import com.microsoft.playwright.Page;

import java.util.Arrays;

public class InputHelper {

    public static void setInputValue(Page page, String selector, String value) {
        page.evaluate("([selector, value]) => {" +
                "  const el = document.querySelector(selector);" +
                "  if (el) {" +
                "    el.value = value;" +
                "    el.dispatchEvent(new Event('input', { bubbles: true }));" +
                "    el.dispatchEvent(new Event('change', { bubbles: true }));" +
                "  }" +
                "}", Arrays.asList(selector, value));
    }
}
