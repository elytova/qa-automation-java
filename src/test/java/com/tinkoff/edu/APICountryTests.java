package com.tinkoff.edu;

import io.restassured.RestAssured;
import io.restassured.authentication.PreemptiveBasicAuthScheme;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.tinkoff.edu.Helpers.RandomStringHelper.*;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class APICountryTests {

    final String METHOD_WITH_ID = "/api/countries/{id}";
    final String METHOD_WITHOUT_ID = "/api/countries";

    @BeforeAll
    public static void setUpAuth() {
        PreemptiveBasicAuthScheme authScheme = new PreemptiveBasicAuthScheme();
        authScheme.setUserName("admin");
        authScheme.setPassword("admin");
        RestAssured.authentication = authScheme;
    }

    @BeforeAll
    public static void setUpErrorLogging() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Test
    @DisplayName("Проверка на добавление новой уникальной страны")
    public void addNewUniqueCountry(){
        String newRandomCountry = randomAlphabetString(2);
        given()
                .contentType(ContentType.JSON)
                .body("{\n" +
                        "  \"countryName\": \""+newRandomCountry+"\"\n" +
                        "}")
                .when()
                .post(METHOD_WITHOUT_ID)
                .then()
                //201 - "created"
                .statusCode(201)
                .body("id", not(empty()),
                        "countryName", is(newRandomCountry)
                );
    }

    @Test
    @DisplayName("Проверка на валидацию страны: размер > 2символов")
    public void getErrorWhenIncorrectCountryValue(){
        String newRandomCountry = randomAlphabetString(3);
        given()
                .contentType(ContentType.JSON)
                .body("{\n" +
                        "  \"countryName\": \""+newRandomCountry+"\"\n" +
                        "}")
                .when()
                .post(METHOD_WITHOUT_ID)
                .then()
                .statusCode(400);
    }

    @Test
    @DisplayName("Проверка на изменение существующей страны")
    public void changeCountryWhenItExists(){
        String testCountry = get("/api/countries").then().extract().response().path("id[0]").toString();
        String newRandomCountry = randomAlphabetString(2);
        given()
                .contentType(ContentType.JSON)
                .pathParam("id", testCountry)
                .body("{\n" +
                        "  \"id\": \""+testCountry+"\",\n" +
                        "  \"countryName\": \""+newRandomCountry+"\"\n" +
                        "}")
                .when()
                .put(METHOD_WITH_ID)
                .then()
//                .root("find {it.type.name == '%s'}.status")
                .statusCode(200);
    }

    @Test
    @DisplayName("Проверка на удаление существующей страны")
    public void deleteCountryWhenItExists(){
        String testCountry = get("/api/countries").then().extract().response().path("id[0]").toString();
        given()
                .contentType(ContentType.JSON)
                .pathParam("id", testCountry)
                .when()
                .delete(METHOD_WITH_ID)
                .then()
                .statusCode(204);
    }
}
