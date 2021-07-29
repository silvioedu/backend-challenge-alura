package com.alura.aluraflix.api.controller;

import static org.hamcrest.CoreMatchers.equalTo;

import com.alura.aluraflix.api.exceptionhandler.ProblemTypeEnum;
import com.alura.aluraflix.api.input.CategoriaInput;
import com.alura.aluraflix.domain.model.CategoriaExample;

import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(Lifecycle.PER_CLASS)
class CategoriaControllerIT {
    
    @LocalServerPort
	private int port;
	
	@Autowired
	private Flyway flyway;

    @BeforeAll
	void setUp() {
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		RestAssured.port = port;
		RestAssured.basePath = "/categorias";

        flyway.migrate();
    }

    @Test
    void shouldReturn200_WhenReceiveGET_withNoId(){

        RestAssured
            .given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
            .when()
                .get()
            .then()
                .statusCode(HttpStatus.OK.value());

    }

    @Test
    void shouldReturn200_WhenReceiveGET_withValidId(){

        int id = 1;

        RestAssured
            .given()
                .pathParam("id", id)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
            .when()
                .get("/{id}")
            .then()
                .statusCode(HttpStatus.OK.value())
                .body("id", equalTo(id))
                .body("titulo", equalTo("LIVRE"))
                .body("cor", equalTo("#0A3D94"));

    }

    @Test
    void shouldReturn404_WhenReceiveGET_withInvalidId(){

        int id = 99;

        RestAssured
            .given()
                .pathParam("id", id)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
            .when()
                .get("/{id}")
            .then()
                .statusCode(HttpStatus.NOT_FOUND.value())
                .body("status", equalTo(HttpStatus.NOT_FOUND.value()))
                .body("title", equalTo(ProblemTypeEnum.NOT_FOUND.getTitle()))
                .body("detail", equalTo(String.format("Categoria %d não encontrada.", id)));

    }

    @Test
    void shouldReturn201_WhenReceivePOST_withValidInput() {

        CategoriaInput input = CategoriaExample.getInputInstance();

        RestAssured
            .given()
                .body(input)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
            .when()
                .post()
            .then()
                .statusCode(HttpStatus.CREATED.value())
                .body("titulo", equalTo(input.getTitulo()))
                .body("cor", equalTo(input.getCor()));
    }

    @Test
    void shouldReturn400_WhenReceivePOST_withInvalidInput() {

        CategoriaInput input = CategoriaExample.getInputInstance();
        input.setTitulo("");

        RestAssured
            .given()
                .body(input)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
            .when()
                .post()
            .then()
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    void shouldReturn204_WhenReceiveDELETE_withValidId(){

        int id = 5;

        RestAssured
            .given()
                .pathParam("id", id)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
            .when()
                .delete("/{id}")
            .then()
                .statusCode(HttpStatus.NO_CONTENT.value());
    }

    @Test
    void shouldReturn404_WhenReceiveDELETE_withInvalidId(){

        int id = 99;

        RestAssured
            .given()
                .pathParam("id", id)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
            .when()
                .delete("/{id}")
            .then()
                .statusCode(HttpStatus.NOT_FOUND.value())
                .body("status", equalTo(HttpStatus.NOT_FOUND.value()))
                .body("title", equalTo(ProblemTypeEnum.NOT_FOUND.getTitle()))
                .body("detail", equalTo(String.format("Categoria %d não encontrada.", id)));

    }

    @Test
    void shouldReturn409_WhenReceiveDELETE_withInUseId(){

        int id = 1;

        RestAssured
            .given()
                .pathParam("id", id)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
            .when()
                .delete("/{id}")
            .then()
                .statusCode(HttpStatus.CONFLICT.value())
                .body("status", equalTo(HttpStatus.CONFLICT.value()))
                .body("title", equalTo(ProblemTypeEnum.IN_USE.getTitle()))
                .body("detail", equalTo(String.format("Categoria %d em uso.", id)));

    }

    @Test
    void shouldReturn200_WhenReceivePUT_withValidInput() {

        CategoriaInput input = CategoriaExample.getInputInstance();
        int id = 3;

        RestAssured
            .given()
                .pathParam("id", id)
                .body(input)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
            .when()
                .put("/{id}")
            .then()
                .statusCode(HttpStatus.OK.value())
                .body("id", equalTo(id))
                .body("titulo", equalTo(input.getTitulo()))
                .body("cor", equalTo(input.getCor()));
    }

    @Test
    void shouldReturn200_WhenReceivePUT_withInvalidInput() {

        CategoriaInput input = CategoriaExample.getInputInstance();
        input.setTitulo("");
        int id = 4;

        RestAssured
            .given()
                .pathParam("id", id)
                .body(input)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
            .when()
                .put("/{id}")
            .then()
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    void shouldReturn404_WhenReceivePUT_withInvalidId(){

        CategoriaInput input = CategoriaExample.getInputInstance();
        int id = 99;

        RestAssured
            .given()
                .pathParam("id", id)
                .body(input)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
            .when()
                .delete("/{id}")
            .then()
                .statusCode(HttpStatus.NOT_FOUND.value())
                .body("status", equalTo(HttpStatus.NOT_FOUND.value()))
                .body("title", equalTo(ProblemTypeEnum.NOT_FOUND.getTitle()))
                .body("detail", equalTo(String.format("Categoria %d não encontrada.", id)));

    }

}
