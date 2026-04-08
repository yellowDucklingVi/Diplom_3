package ru.yandex.praktikum.api;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class UserClient {
    private static final String BASE_URL = "https://stellarburgers.education-services.ru";
    private static final String REGISTER_ENDPOINT = "/api/auth/register";
    private static final String LOGIN_ENDPOINT = "/api/auth/login";
    private static final String DELETE_ENDPOINT = "/api/auth/user";

    @Step("Создание пользователя: {user.email}")
    public Response createUser(User user) {
        return given()
                .contentType(ContentType.JSON)
                .baseUri(BASE_URL)
                .body(user)
                .when()
                .post(REGISTER_ENDPOINT);
    }

    @Step("Логин пользователя: {user.email}")
    public Response loginUser(User user) {
        return given()
                .contentType(ContentType.JSON)
                .baseUri(BASE_URL)
                .body(user)
                .when()
                .post(LOGIN_ENDPOINT);
    }

    @Step("Удаление пользователя")
    public Response deleteUser(String accessToken) {
        return given()
                .contentType(ContentType.JSON)
                .baseUri(BASE_URL)
                .header("Authorization", accessToken)
                .when()
                .delete(DELETE_ENDPOINT);
    }
}