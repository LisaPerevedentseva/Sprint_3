import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.yandex.scooter.client.OrderApiClient;
import ru.yandex.scooter.model.Order;
import ru.yandex.scooter.steps.OrderStep;

import static org.hamcrest.Matchers.*;

@RunWith(Parameterized.class)
public class CreateOrderParameterizedTest {

    @Before
    public void setUp() {
        // задаем базовую часть адреса
        RestAssured.baseURI = "http://qa-scooter.praktikum-services.ru/";
    }

    @After
    public void clearData(){
        if (track!=0){
            orderApi.cancelOrder(track);
        }
    }

OrderApiClient orderApi = new OrderApiClient();
OrderStep step = new OrderStep();

int track;

private final Order order;
private final int expectedStatusCode;

public CreateOrderParameterizedTest (Order order, int expectedStatusCode){
    this.order=order;
    this.expectedStatusCode=expectedStatusCode;
}


@Parameterized.Parameters
public static Object[] getDataForTest (){
    return new Object [][]{
            {new Order().getRandomOrder(new String[]{"BLACK"}), 201},
            {new Order().getRandomOrder(new String[]{"GREY"}), 201},
            {new Order().getRandomOrder(new String[]{"BLACK", "GREY"}), 201},
            {new Order().getRandomOrder(new String[]{}), 201}
    };
}

@Test
@DisplayName ("Create order with different color")
@Description("Проверяем, что можно создать заказ с различными комбинациями цветов GREY и BLACK, а также вообще без цвета. В ответе приходит не пустое значение track")
    public void createOrderTestSuccess (){

    ValidatableResponse response = orderApi.createOrder(order);
    // сохраняем номер заказа для отмены
    track = step.getOrderTrack(response);
    response.statusCode(expectedStatusCode).and().body("track", notNullValue());
}
}
