package com.alura.aluraflix.api.controller;

import static org.hamcrest.CoreMatchers.equalTo;

import com.alura.aluraflix.api.exceptionhandler.ProblemTypeEnum;
import com.alura.aluraflix.api.input.VideoInput;
import com.alura.aluraflix.domain.model.VideoExample;

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
class VideoControllerIT {
    
    @LocalServerPort
	private int port;
	
	@Autowired
	private Flyway flyway;

    @BeforeAll
	void setUp() {
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		RestAssured.port = port;
		RestAssured.basePath = "/videos";

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
                .body("titulo", equalTo("Um sonho de liberdade"))
                .body("descricao", equalTo("Condenado por assassinato, o banqueiro Andy Dufresne vai para a prisão, desenvolve uma forte amizade com um preso mais velho e aprende a navegar o duro clima da prisão."))
                .body("url", equalTo("https://www.netflix.com/title/70005379"))
                .body("categoriaId", equalTo(2));
                
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
                .body("detail", equalTo(String.format("Vídeo %d não encontrado.", id)));

    }

    @Test
    void shouldReturn201_WhenReceivePOST_withValidInput() {

        VideoInput input = VideoExample.getInputInstance();

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
                .body("descricao", equalTo(input.getDescricao()))
                .body("url", equalTo(input.getUrl()));
    }

    @Test
    void shouldReturn400_WhenReceivePOST_withInvalidInput() {

        VideoInput input = VideoExample.getInputInstance();
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

        int id = 2;

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
                .body("detail", equalTo(String.format("Vídeo %d não encontrado.", id)));

    }

    @Test
    void shouldReturn200_WhenReceivePUT_withValidInput() {

        VideoInput input = VideoExample.getInputInstance();
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
                .body("descricao", equalTo(input.getDescricao()))
                .body("url", equalTo(input.getUrl()));
    }

    @Test
    void shouldReturn200_WhenReceivePUT_withInvalidInput() {

        VideoInput input = VideoExample.getInputInstance();
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

        VideoInput input = VideoExample.getInputInstance();
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
                .body("detail", equalTo(String.format("Vídeo %d não encontrado.", id)));

    }

    @Test
    void shouldReturn200_WhenReceiveGET_withSearchParam(){

        String search = "002";

        RestAssured
            .given()
                .queryParam("search", search)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
            .when()
                .get()
            .then()
                .statusCode(HttpStatus.OK.value());

    }


}
