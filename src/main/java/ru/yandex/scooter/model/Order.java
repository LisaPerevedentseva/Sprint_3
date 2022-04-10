package ru.yandex.scooter.model;

import io.qameta.allure.Step;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Order {

    public String firstName;
    public String lastName;
    public String address;
    public String metroStation;
    public String phone;
    public int rentTime;
    public String deliveryDate;
    public String comment;
    public String[] color;

    public Order (){}

    public Order (String firstName, String lastName, String address, String metroStation, String phone, int rentTime, String deliveryDate, String comment, String[] color){
        this.firstName=firstName;
        this.lastName=lastName;
        this.address=address;
        this.metroStation=metroStation;
        this.phone=phone;
        this.rentTime=rentTime;
        this.deliveryDate=deliveryDate;
        this.comment=comment;
        this.color=color;
    }

    @Step ("Создаем рандомный заказ с цветом самоката {color}")
    public Order getRandomOrder (String[] color){
        String firstName= RandomStringUtils.randomAlphabetic(8);
        String lastName=RandomStringUtils.randomAlphabetic(8);
        String address=RandomStringUtils.randomAlphabetic(8) +"," + RandomStringUtils.randomNumeric(3);
        String metroStation=RandomStringUtils.randomNumeric(1);
        String phone="+7" + RandomStringUtils.randomNumeric(10);
        int rentTime= RandomUtils.nextInt(1, 7);
        String deliveryDate= DateTimeFormatter.ofPattern("yyyy-MM-dd").format(LocalDateTime.now());
        String comment=RandomStringUtils.randomAlphabetic(8);
        String[] colors=color;
        return new Order (firstName, lastName, address, metroStation, phone, rentTime, deliveryDate, comment, colors);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMetroStation() {
        return metroStation;
    }

    public void setMetroStation(String metroStation) {
        this.metroStation = metroStation;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getRentTime() {
        return rentTime;
    }

    public void setRentTime(int rentTime) {
        this.rentTime = rentTime;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String[] getColor() {
        return color;
    }

    public void setColor(String[] color) {
        this.color = color;
    }




}
