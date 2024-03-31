package ru.aydar.tests;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static io.appium.java_client.AppiumBy.accessibilityId;
import static io.appium.java_client.AppiumBy.id;
import static io.qameta.allure.Allure.step;

@DisplayName("Тесты Android-приложения сайта Wikipedia")
public class WikipediaTest extends TestBase {
    @Test
    @DisplayName("Проверка работы поиска")
    void successfulSearchTest() {
        step("Открываем поиск", () -> {
            $(accessibilityId("Search Wikipedia")).click();
        });
        step("Вводим текст в строку поиска", () -> {
            $(id("org.wikipedia.alpha:id/search_src_text")).sendKeys("selenide");
        });
        step("Проверяем, что нашлись статьи", () ->
                $$(id("org.wikipedia.alpha:id/page_list_item_title"))
                        .shouldHave(sizeGreaterThan(0)));
        step("Проверяем название первой статьи в списке", () ->
                $(id("org.wikipedia.alpha:id/page_list_item_title")).shouldHave(text("Selenide")));
    }

    @Disabled("Статьи не открываются в той версии Википедии, которая используется в автоматизации на BrowserStack")
    @Test
    @DisplayName("Проверка перехода на первую статью из результатов поиска")
    void openArticleFromSearchTest() {
        step("Открываем поиск и вводим текст", () -> {
            $(accessibilityId("Search Wikipedia")).click();
            $(id("org.wikipedia.alpha:id/search_src_text")).sendKeys("ufa");
        });
        step("Кликаем по первому результату поиска", () ->
                $(id("org.wikipedia.alpha:id/page_list_item_title")).click());
        step("Проверяем текст в подзаголовке открывшейся статьи", () ->
                $(id("org.wikipedia.alpha:id/pcs-edit-section-title-description")).shouldHave(text("Capital of Bashkortostan, Russia")));
    }

    @Test
    @DisplayName("Проверка текста первых двух заголовков на главной странице")
    void checkMainPageHeadersTest() {
        step("Проверяем текст в заголовке раздела новостей", () ->
                $$(id("org.wikipedia.alpha:id/view_card_header_title")).get(0).shouldHave(text("In the news")));
        step("Проверяем текст в заголовке раздела статьи дня", () ->
                $$(id("org.wikipedia.alpha:id/view_card_header_title")).get(1).shouldHave(text("Featured article")));
    }
}
