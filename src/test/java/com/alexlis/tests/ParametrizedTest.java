package com.alexlis.tests;

import com.alexlis.tests.domain.MenuItem;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.stream.Stream;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class ParametrizedTest extends Config {

    @Test
    void firstSimpleTest() {
        open("https://www.google.ru/");
        $(".gLFyf").setValue("Собака").pressEnter();
        $("#search").shouldHave(text("Собака"));
    }

    @ValueSource(strings = {
            "selenide",
            "selenium",
            "junit"
    })

    @ParameterizedTest(name = "Search by text: {0}")
    void searchValueSourceTest(String i) {
        open("https://www.google.ru/");
        $(".gLFyf").setValue(i).pressEnter();
        $("#search").shouldHave(text(i));
    }

    @CsvSource({
            "город, Москва",
            "деревня, Зуево"
    })

    @ParameterizedTest(name = "Search by text: {0}")
    void searchCsvSourceTest(String searchQuery, String testName) {
        open("https://www.google.ru/");
        $(".gLFyf").setValue(searchQuery + " " + testName).pressEnter();
        $("#search").shouldHave(text(searchQuery + " " + testName));
    }

    @EnumSource(MenuItem.class)

    @ParameterizedTest()
    void searchEnumSourceTest(MenuItem menuItem) {
        open("https://www.google.ru/");
        $(".gLFyf").setValue(menuItem.getDescription()).pressEnter();
        $("#search").shouldHave(text(menuItem.getDescription()));
    }

    static Stream<Arguments> testWithMethodSource() {
        return Stream.of(
                Arguments.of(
                        "Lexus", "IS", "250"
                ),
                Arguments.of(
                        "Toyota", "Chaser", "JZX100"
                ),
                Arguments.of(
                        "Infiniti", "FX", "350"
                )
        );
    }

    @MethodSource("testWithMethodSource")
    @ParameterizedTest(name = "TestForm: {0}")
    void searchWithMethodSourceTest(String mark, String model, String index) {
        open("https://www.google.ru/");
        $(".gLFyf").setValue(mark + " " + model + " " + index).pressEnter();
        $("#rso").shouldHave(text(mark + " " + model + " " + index));
        $("#rso").shouldHave(text("Картинки по запросу"));
    }
}