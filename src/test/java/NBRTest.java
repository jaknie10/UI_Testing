import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.junit5.TextReportExtension;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;

@ExtendWith(TextReportExtension.class)
public class NBRTest {

    @BeforeAll
    public static void setBrowser(){
        Configuration.browser = "firefox";
    }

    @BeforeEach
    public void setup(){
        Configuration.baseUrl = "https://natural-born-runners.pl";
        clearBrowserCookies();
    }

    @Test
    public void searchTest() {
        NBRPage nbrPage = new NBRPage();
        nbrPage.open();
        nbrPage.searchFor("buty");
        $$(By.className("product-name")).get(0).shouldHave(Condition.text("Buty"));
    }

    @Test
    public void incorrectLoginTest() {
        NBRPage nbrPage = new NBRPage();
        nbrPage.open();
        nbrPage.login("1234", "1234");
        $(By.xpath("/html/body/div[3]/div/div/div[1]/div/h3")).shouldHave(Condition.text("nie jest poprawne"));
    }

    @Test
    public void addToFavourites() {
        Selenide.open("/product-pol-8441-Buty-Asics-Novablast-3-czarno-pomaranczowe-meskie-AW22.html?country=1143020003&selected_size=67&utm_source=iai_ads&utm_medium=google_shopping");
        $(By.xpath("/html/body/div[3]/div/div/form/div[3]/div[4]/div[1]/select")).click();
        $(By.className("size_58")).click();
        $(By.xpath("/html/body/div[3]/div/div/form/div[3]/div[5]/div[4]/div/a[1]")).click();
        $(By.xpath("/html/body/div[2]/div/span/a")).click();
        $$(By.className("title_54008")).get(0).shouldHave(Condition.text("Asics Novablast 3"));
    }

    @Test
    public void addToCart() {
        Selenide.open("/product-pol-8353-Buty-HOKA-ONE-ONE-CLIFTON-8-niebiesko-pomaranczowe-GBMS-meskie-AW22.html");
        $(By.xpath("/html/body/div[3]/div/div/form/div[3]/div[5]/div[1]/select")).click();
        $(By.className("size_337")).click();
        $(By.xpath("/html/body/div[3]/div/div/form/div[3]/div[6]/div[4]/div/button")).click();
        $(By.xpath("/html/body/div[6]/div/div/div[3]/div/div/div/div[2]/a[1]")).click();
        $$(By.className("productslist_item")).get(0).shouldHave(Condition.text("HOKA ONE ONE CLIFTON 8"));
    }

    @Test
    public void sortByPrice() {
        NBRPage nbrPage = new NBRPage();
        nbrPage.open();
        nbrPage.searchFor("koszulka");
        $(By.xpath("/html/body/div[3]/div/div[1]/div[1]/form[1]/div[1]/div/button")).click();
        $(By.xpath("/html/body/div[3]/div/div[1]/div[1]/form[1]/div[1]/div/ul/li[3]/a")).click();
        $$(By.id("search")).get(0).shouldHave(Condition.text("NATURAL BORN RUNNERS"));
        $$(By.id("search")).get(0).shouldHave(Condition.text("49,00"));
    }
}