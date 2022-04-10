package ru.yandex.scooter.steps;

import io.qameta.allure.Step;
import ru.yandex.scooter.client.CourierApiClient;
import ru.yandex.scooter.model.Courier;

public class CourierStep {

    CourierApiClient courierApiClient = new CourierApiClient();

   // метод для получения id курьера
    @Step("Получение id курьера")
    public int getCourierId(Courier courier){
        int courierId;
        return courierId = courierApiClient.loginCourier(courier.getLogin(), courier.getPassword()).extract().path("id");
    }

}
