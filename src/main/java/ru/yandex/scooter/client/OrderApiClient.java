package ru.yandex.scooter.client;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import ru.yandex.scooter.model.Order;

import static io.restassured.RestAssured.given;

public class OrderApiClient {

    private final String JSON = "application/json";
    private final String CREATE_ORDER_PATH="api/v1/orders";
    private final String CANCEL_ORDER_PATH="api/v1/orders/cancel";
    private final String LIST_OF_ORDERS_PATH="api/v1/orders";


    @Step ("Создание заказа")
    public ValidatableResponse createOrder(Order order){
        return given().
                header("Content-type", JSON).
                body(order).
                when().
                post(CREATE_ORDER_PATH).
                then();

    }


    @Step("Отмена заказа")
    public ValidatableResponse cancelOrder(int track){
        return given().
                header("Content-type", JSON).
                body("{\"track\":" + "\"" + track + "\"}").
                when().
                put(CANCEL_ORDER_PATH).
                then();
    }

    @Step("Получение списка заказов")
    public ValidatableResponse getListOfOrders (){
        return given().
                header("Content-type", JSON).
                when().
                get(LIST_OF_ORDERS_PATH).
                then();
    }


}
