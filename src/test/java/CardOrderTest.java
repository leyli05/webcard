import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.cssClass;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class CardOrderTest {
    @Test
    void shouldTestForm() {
        open("http://localhost:9999/");
        SelenideElement form = $("form");
        $("[data-test-id=name] input").setValue("Иванова Татьяна");
        $("[data-test-id=phone] input").setValue("+79001234567");
        $("[data-test-id=agreement]").click();
        $("button").click();
        $("[data-test-id=order-success]").shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test
    void shouldTestFormDoubleName() {
        open("http://localhost:9999/");
        SelenideElement form = $("form");
        $("[data-test-id=name] input").setValue("Иванова-Петрова Татьяна");
        $("[data-test-id=phone] input").setValue("+79001234567");
        $("[data-test-id=agreement]").click();
        $("button").click();
        $("[data-test-id=order-success]").shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test
    void shouldNotTestName() {
        open("http://localhost:9999/");
        SelenideElement form = $("form");
        $("[data-test-id=name] input").setValue("Ivanova Татьяна");
        $("[data-test-id=phone] input").setValue("+79001234567");
        $("[data-test-id=agreement]").click();
        $("button").click();
        $("[data-test-id=name].input_invalid .input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));

    }

    @Test
    void shouldNotTestPhone() {
        open("http://localhost:9999/");
        SelenideElement form = $("form");
        $("[data-test-id=name] input").setValue("Иванова Татьяна");
        $("[data-test-id=phone] input").setValue("791234567");
        $("[data-test-id=agreement]").click();
        $("button").click();
        $("[data-test-id=phone].input_invalid .input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void shouldNotTestAgreement() {
        open("http://localhost:9999/");
        SelenideElement form = $("form");
        $("[data-test-id=name] input").setValue("Иванова Татьяна");
        $("[data-test-id=phone] input").setValue("+79001234567");
        $("button").click();
        $("[data-test-id=agreement]").shouldHave(cssClass("input_invalid"));

    }

    @Test
    void shouldNotTestEmptyName() {
        open("http://localhost:9999/");
        SelenideElement form = $("form");
        $("[data-test-id=name] input").setValue("");
        $("[data-test-id=phone] input").setValue("+79001234567");
        $("[data-test-id=agreement]").click();
        $("button").click();
        $("[data-test-id=name].input_invalid .input__sub ").shouldHave(exactText("Поле обязательно для заполнения"));
    }

    @Test
    void shouldNotTestEmptyPhone() {
        open("http://localhost:9999/");
        SelenideElement form = $("form");
        $("[data-test-id=name] input").setValue("Иванова Татьяна");
        $("[data-test-id=phone] input").setValue("");
        $("[data-test-id=agreement]").click();
        $("button").click();
        $("[data-test-id=phone].input_invalid .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
    }
}
