import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class NBRPage {
    public NBRPage open() {
        Selenide.open("/");
        return this;
    }

    public NBRPage searchFor(String text) {
        $(By.id("menu_search_text")).setValue(text).pressEnter();
        return this;
    }

    public NBRPage login(String email, String password) {
        Selenide.open("/signin.php");
        $(By.id("signin_login_input")).setValue(email);
        $(By.id("signin_pass_input")).setValue(password).pressEnter();
        return this;
    }
}
