package com.tinkoff.edu;

import io.restassured.RestAssured;
import io.restassured.authentication.PreemptiveBasicAuthScheme;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static com.tinkoff.edu.Helpers.RandomHelper.*;
import static io.restassured.RestAssured.*;

public class APICountryTests {

    final String METHOD_WITH_ID = "/api/countries/{id}";
    final String METHOD_WITHOUT_ID = "/api/countries";
    final static String TEST_COUNTRY_NAME = randomAlphabetString(2);
    final static int TEST_COUNTRY_ID = randomNumeric(3);
    private static Connection connection;

    @BeforeAll
    public static void setUpAuth() {
        PreemptiveBasicAuthScheme authScheme = new PreemptiveBasicAuthScheme();
        authScheme.setUserName("admin");
        authScheme.setPassword("admin");
        RestAssured.authentication = authScheme;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @BeforeAll
    public static void connectDb() throws SQLException {
        connection = DriverManager.getConnection(
                "jdbc:postgresql://localhost/app-db",
                "app-db-admin",
                "P@ssw0rd"
        );
    }

    @BeforeEach
    public void createDataInDB() throws SQLException {
        PreparedStatement sql = connection.prepareStatement(
                "INSERT INTO COUNTRY(id, country_name) VALUES(?,?)"
        );
        sql.setInt(1, TEST_COUNTRY_ID);
        sql.setString(2, TEST_COUNTRY_NAME);
        sql.executeUpdate();
    }

    @Test
    @DisplayName("Проверка на добавление новой уникальной страны")
    public void addNewUniqueCountry(){
        given()
                .contentType(ContentType.JSON)
                .body("{\n" +
                        "  \"countryName.equals\": \""+ TEST_COUNTRY_NAME +"\"\n" +
                        "}")
                .when()
                .get(METHOD_WITHOUT_ID)
                .then()
                //201 - "created"
                .statusCode(200);
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
        given()
                .contentType(ContentType.JSON)
                .pathParam("id", TEST_COUNTRY_ID)
                .body("{\n" +
                        "  \"id\": \""+ TEST_COUNTRY_ID +"\",\n" +
                        "  \"countryName\": \""+ randomAlphabetString(2) +"\"\n" +
                        "}")
                .when()
                .put(METHOD_WITH_ID)
                .then()
                .statusCode(200);
    }

    @Test
    @DisplayName("Проверка на удаление существующей страны")
    public void deleteCountryWhenItExists(){
        given()
                .contentType(ContentType.JSON)
                .pathParam("id", TEST_COUNTRY_ID)
                .when()
                .delete(METHOD_WITH_ID)
                .then()
                .statusCode(204);
    }

    @AfterEach
    public void deleteDataFromDB() throws SQLException {
        PreparedStatement sql = connection.prepareStatement(
                "DELETE FROM COUNTRY WHERE COUNTRY_NAME=? OR ID=?"
        );
        sql.setString(1, TEST_COUNTRY_NAME);
        sql.setInt(2, TEST_COUNTRY_ID);
        sql.executeUpdate();
    }

    @AfterAll
    static void closeDBConnection() throws SQLException{
        connection.close();
    }
}
