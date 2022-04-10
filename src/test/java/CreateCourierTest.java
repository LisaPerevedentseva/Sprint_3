import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.yandex.scooter.client.CourierApiClient;
import ru.yandex.scooter.model.Courier;
import ru.yandex.scooter.steps.CourierStep;

import static org.hamcrest.Matchers.*;


public class CreateCourierTest {

    CourierApiClient courierApi = new CourierApiClient();

    CourierStep step = new CourierStep();

    ValidatableResponse response;

    int id;

    Courier courier;

    @Before
    public void setUp() {
        // задаем базовую часть адреса
        RestAssured.baseURI = "http://qa-scooter.praktikum-services.ru/";

        // создаем модель курьера с рандомными данными
        courier = Courier.getRandomCourier();
    }

    @After
    public void clearData(){
        if (courier.getLogin()!=null&&courier.getPassword()!=null&&id!=0){
            {
            courierApi.deleteCourier(id);
            }
        }
    }

    @Test
    @DisplayName("Create unique courier")
    @Description("Проверяем код ответа 201 и значение параметра ok = true для корректного запроса на создание курьера")
    public void createUniqueCourierSuccess () {

        // выполняем запрос на создание курьера
        response = courierApi.createCourier(courier);
        // сохраняем id созданного курьера
        id = step.getCourierId(courier);
        // проверяем код ответа и текст сообщения
        response.statusCode(201).and().assertThat().body("ok", equalTo(true));
    }

    @Test
    @DisplayName("Create duplicate courier")
    @Description ("Проверяем, что при попытке создать курьера с одинаковыми login и password код ответа 409, текст сообщения Этот логин уже используется")
    public void createDuplicateCourierFailure () {

        // выполняем запрос на создание курьера
        courierApi.createCourier(courier);
        // сохраняем id созданного курьера для последующего удаления
        id = step.getCourierId(courier);
        // повторно выполняем запрос на создание курьера
        response = courierApi.createCourier(courier);
        // проверяем код ответа и сообщение
        response.statusCode(409).and().assertThat().body("message", equalTo("Этот логин уже используется"));

    }

    @Test
    @DisplayName("Create courier without login")
    @Description ("Проверяем, что нельзя создать курьера без логина, код ответа 400, текст сообщения Недостаточно данных для создания учетной записи")
    public void createCourierWithoutLoginFailure () {

        // устанавливаем пустой логин
        courier.setLogin(null);
        // выполняем запрос на создание курьера
        response = courierApi.createCourier(courier);
        // проверяем код ответа и сообщение
        response.statusCode(400).and().assertThat().body("message", equalTo("Недостаточно данных для создания учетной записи"));

    }

    @Test
    @DisplayName("Create courier without password")
    @Description ("Проверяем, что нельзя создать курьера без пароля, код ответа 400, текст сообщения Недостаточно данных для создания учетной записи")
    public void createCourierWithoutPasswordFailure () {

        // устанавливаем пустой пароль
        courier.setPassword(null);
        // выполняем запрос на создание курьера
        response = courierApi.createCourier(courier);
        // проверяем код ответа и сообщение
        response.statusCode(400).and().assertThat().body("message", equalTo("Недостаточно данных для создания учетной записи"));
    }

    @Test
    @Description ("Проверяем, что при попытке создать курьера с существующим login код ответа 409, текст сообщения Этот логин уже используется")
    @DisplayName("Create courier with existing login")
    public void createCourierWithExistingLoginFailure () {

        // выполняем запрос на создание курьера
        courierApi.createCourier(courier);
        // сохраняем id созданного курьера
        id = step.getCourierId(courier);
        // устанавливаем курьеру рандомный пароль
        courier.setPassword(RandomStringUtils.randomAlphabetic(10));
        // повторно регистрируем курьера с таким же логином
        response = courierApi.createCourier(courier);
        // проверяем код ответа и сообщение
        response.statusCode(409).and().assertThat().body("message", equalTo("Этот логин уже используется"));
    }

}
