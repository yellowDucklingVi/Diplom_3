package ru.yandex.praktikum.api;

import org.apache.commons.lang3.RandomStringUtils;

public class UserGenerator {
    public static User getRandomUser() {
        String email = RandomStringUtils.randomAlphanumeric(8) + "@yandex.ru";
        String password = RandomStringUtils.randomAlphanumeric(8);
        String name = RandomStringUtils.randomAlphanumeric(6);
        return new User(email, password, name);
    }
}