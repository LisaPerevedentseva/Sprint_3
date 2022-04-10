package ru.yandex.scooter.client;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import ru.yandex.scooter.model.Courier;

import static io.restassured.RestAssured.given;

public class CourierApiClient {

    private final String JSON = "application/json";
    private final String CREATE_COURIER_PATH = "api/v1/courier";
    private final String LOGIN_COURIER_PATH = "api/v1/courier/login";
    private final String DELETE_COURIER_PATH = "api/v1/courier/";



    @Step ("Создание курьера")
    public ValidatableResponse createCourier (Courier courier) {
       return given().
               header("Content-type", JSON).
               body(courier).
               post(CREATE_COURIER_PATH).
               then();
    }

    @Step ("Авторизация курьера")
    public ValidatableResponse loginCourier (String login, String pass){
       return given().
                header("Content-type", "application/json").
                body("{\"login\":\"" + login + "\"," + "\"password\":\"" + pass + "\"}").
                post(LOGIN_COURIER_PATH).
                then();
    }

    @Step ("Удаление курьера")
    public ValidatableResponse deleteCourier (int id){
        return given().
                header("Content-type", "application/json").
                pathParam("id", id).
                delete(DELETE_COURIER_PATH + "{id}").
                then();
    }

}
