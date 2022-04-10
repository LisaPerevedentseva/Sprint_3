import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.Before;
import org.junit.Test;
import ru.yandex.scooter.client.OrderApiClient;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import java.util.List;

public class GetOrdersTest {

    OrderApiClient orderApi = new OrderApiClient();

    @Before
    public void setUp() {
        // задаем базовую часть адреса
        RestAssured.baseURI = "http://qa-scooter.praktikum-services.ru/";

    }


    @Test
    @DisplayName("Get list of orders")
    @Description("Проверяем, что можно получить список заказов, в ответе - не пустой массив объектов")
    public void getListOfOrdersWithoutParametersSuccess(){

        ValidatableResponse response = orderApi.getListOfOrders();
        // проверяем код ответа
        response.statusCode(200);
        // создаем список объектов в ответе
        List<Object> orders = response.extract().jsonPath().getList("orders");
        // определяем колчество записей, проверяем, что оно не равно 0
        int ordersSize = orders.size();
        assertThat(ordersSize, not(equalTo(0)));

    }

}
