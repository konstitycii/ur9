package quru.qa;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.stream.Stream;

import static com.codeborne.selenide.Selenide.*;

public class TextBoxTest {

    @BeforeAll
    public static void configureSelenide() {
        Configuration.browser = "chrome";
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.pageLoadStrategy = "eager";
        Configuration.browserSize = "1920x1080";
    }

    @DisplayName("Тест с использованием @CsvSource")
    @ParameterizedTest(name = "Проверка Text Box с данными: {0}, {1}, {2}, {3}")
    @CsvSource({
            "Vlad Klimin, klimas777@ya.ru, Penza Mira 77, Zarechny Zelenay 29A",
            "Andrey Pronkin, andrey12v@mail.ru, Zarechny Lenina 66, Zarechny Lenina 66",
            "Pavel Rudakov, rudak@gmail.com, Zarechny Ozerskay 2, Zarechny Ozerskay 2"
    })
    public void textBoxTestWithCsvSource(String fullName, String email, String currentAddress, String permanentAddress) {
        open("/text-box");
        $("#userName").setValue(fullName);
        $("#userEmail").setValue(email);
        $("#currentAddress").setValue(currentAddress);
        $("#permanentAddress").setValue(permanentAddress);
        $("#submit").scrollTo().click();
        $("#output #name").shouldHave(Condition.text("Name:" + fullName));
        $("#output #email").shouldHave(Condition.text("Email:" + email));
        $("#output #currentAddress").shouldHave(Condition.text("Current Address :" + currentAddress));
        $("#output #permanentAddress").shouldHave(Condition.text("Permananet Address :" + permanentAddress));
    }

    @DisplayName("Тест с использованием @ValueSource")
    @ParameterizedTest(name = "Проверка только имени: {0}")
    @ValueSource(strings = {
            "Vlad Klimin",
            "Andrey Pronkin",
            "Pavel Rudakov"
    })
    public void textBoxTestWithValueSource(String fullName) {
        open("/text-box");
        $("#userName").setValue(fullName);
        $("#userEmail").setValue("test@example.com");
        $("#currentAddress").setValue("Default Address");
        $("#permanentAddress").setValue("Default Address");
        $("#submit").scrollTo().click();
        $("#output #name").shouldHave(Condition.text("Name:" + fullName));
    }

    @DisplayName("Тест с использованием @MethodSource")
    @ParameterizedTest(name = "Проверка Text Box с данными: {0}, {1}, {2}, {3}")
    @MethodSource("provideUserData")
    public void textBoxTestWithMethodSource(String fullName, String email, String currentAddress, String permanentAddress) {
        open("/text-box");
        $("#userName").setValue(fullName);
        $("#userEmail").setValue(email);
        $("#currentAddress").setValue(currentAddress);
        $("#permanentAddress").setValue(permanentAddress);
        $("#submit").scrollTo().click();
        $("#output #name").shouldHave(Condition.text("Name:" + fullName));
        $("#output #email").shouldHave(Condition.text("Email:" + email));
        $("#output #currentAddress").shouldHave(Condition.text("Current Address :" + currentAddress));
        $("#output #permanentAddress").shouldHave(Condition.text("Permananet Address :" + permanentAddress));
    }

    static Stream<Arguments> provideUserData() {
        return Stream.of(
                Arguments.of("Vlad Klimin", "klimas777@ya.ru", "Penza Mira 77", "Zarechny Zelenay 29A"),
                Arguments.of("Andrey Pronkin", "andrey12v@mail.ru", "Zarechny Lenina 66", "Zarechny Lenina 66"),
                Arguments.of("Pavel Rudakov", "rudak@gmail.com", "Zarechny Ozerskay 2", "Zarechny Ozerskay 2")
        );
    }

    @DisplayName("Тест с использованием Enum")
    @ParameterizedTest(name = "Проверка Text Box с данными из Enum: {0}")
    @EnumSource(User.class)
    public void textBoxTestWithEnumSource(User user) {
        open("/text-box");
        $("#userName").setValue(user.getFullName());
        $("#userEmail").setValue(user.getEmail());
        $("#currentAddress").setValue(user.getCurrentAddress());
        $("#permanentAddress").setValue(user.getPermanentAddress());
        $("#submit").scrollTo().click();
        $("#output #name").shouldHave(Condition.text("Name:" + user.getFullName()));
        $("#output #email").shouldHave(Condition.text("Email:" + user.getEmail()));
        $("#output #currentAddress").shouldHave(Condition.text("Current Address :" + user.getCurrentAddress()));
        $("#output #permanentAddress").shouldHave(Condition.text("Permananet Address :" + user.getPermanentAddress()));
    }
}
