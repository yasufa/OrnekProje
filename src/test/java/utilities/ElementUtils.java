package utilities;

import com.microsoft.playwright.Locator;

public class ElementUtils {

    public static boolean isNotClickable(Locator locator) {
        try {
            // 1. Element görünür mü?
            if (!locator.isVisible()) {
                return true;
            }

            // 2. Element etkin mi? (örneğin disabled mı?)
            if (!locator.isEnabled()) {
                return true;
            }


            // Eğer hepsi sağlanıyorsa, tıklanabilir demektir
            return false;
        } catch (Exception e) {
            // Hata varsa da tıklanamaz demektir
            return true;
        }
    }
}
