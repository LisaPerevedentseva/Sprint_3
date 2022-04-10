package ru.yandex.scooter.steps;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import ru.yandex.scooter.client.OrderApiClient;


public class OrderStep {

    OrderApiClient orderApi = new OrderApiClient();


    @Step ("Получение id заказа")
    public int getOrderTrack (ValidatableResponse response){
        int track;
        return track=response.extract().path("track");
    }


}
