package com.alura.aluraflix.api.controller;

import static org.hamcrest.CoreMatchers.equalTo;

import com.alura.aluraflix.api.exceptionhandler.ProblemTypeEnum;

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
class CategoriaVideoControllerIT {
    
    @LocalServerPort
	private int port;
	
	@Autowired
	private Flyway flyway;

    @BeforeAll
	void setUp() {
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		RestAssured.port = port;
		RestAssured.basePath = "/categorias/{id}/videos";

        flyway.migrate();
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
                .get()
            .then()
                .statusCode(HttpStatus.OK.value());
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
                .get()
            .then()
                .statusCode(HttpStatus.NOT_FOUND.value())
                .body("status", equalTo(HttpStatus.NOT_FOUND.value()))
                .body("title", equalTo(ProblemTypeEnum.NOT_FOUND.getTitle()))
                .body("detail", equalTo(String.format("Categoria %d não encontrada.", id)));

    }
}
