package br.notelab.resource;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasItem;

import org.junit.jupiter.api.Test;

import br.notelab.dto.notebook.conexao.ConexaoSemFioDTO;
import br.notelab.dto.notebook.conexao.ConexaoSemFioResponseDTO;
import br.notelab.service.notebook.conexao.ConexaoSemFioService;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.MediaType;

@QuarkusTest
public class ConexaoSemFioResourceTest { 

    @Inject
    public ConexaoSemFioService conexaoSemFioService;
    
    @Test 
    public void findAllTest(){
        given().when().get("/conexoes").then().statusCode(200);
    }

    @Test
    public void findByIdTest(){ 
        given().when().get("/conexoes/1").then().statusCode(200).body("id", is(1));
    }

    @Test
    public void findByNomeTest(){ 
        given()
        .when()
        .pathParam("nome", "Bluetooth")
        .get("/conexoes/search/{nome}")
        .then()
        .statusCode(200)
        .body("nome", hasItem(is("Bluetooth")));
    }

    @Test
    public void createTest(){
        ConexaoSemFioDTO dto = new ConexaoSemFioDTO("Conexão Create");

        given()
        .contentType(MediaType.APPLICATION_JSON)
        .body(dto)
        .when()
        .post("/conexoes")
        .then()
        .statusCode(201)
        .body("nome", is("Conexão Create"));
    }

    @Test
    public void updateTest(){
        ConexaoSemFioResponseDTO response = conexaoSemFioService.create(new ConexaoSemFioDTO("Conexao Update"));
        ConexaoSemFioDTO dto = new ConexaoSemFioDTO("Conexao Update 2");

        given()
        .contentType(MediaType.APPLICATION_JSON)
        .body(dto)
        .when()
        .pathParam("id", response.id())
        .put("/conexoes/{id}")
        .then()
        .statusCode(204);
    }

    @Test
    public void deleteTest(){
        ConexaoSemFioResponseDTO response = conexaoSemFioService.create(new ConexaoSemFioDTO("Conexao Delete"));

        given()
        .when()
        .pathParam("id", response.id())
        .delete("/conexoes/{id}")
        .then()
        .statusCode(204);
    }
}