package br.notelab.resource;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasItem;

import org.junit.jupiter.api.Test;

import br.notelab.dto.notebook.conexao.EntradaSaidaDTO;
import br.notelab.dto.notebook.conexao.EntradaSaidaResponseDTO;
import br.notelab.service.notebook.conexao.EntradaSaidaService;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.MediaType;

@QuarkusTest
public class EntradaSaidaResourceTest { 

    @Inject
    public EntradaSaidaService entradaSaidaService;
    
    @Test 
    public void findAllTest(){
        given().when().get("/entradas_saidas").then().statusCode(200);
    }
    
    @Test
    public void findByIdTest(){ 
        given().when().get("/entradas_saidas/1").then().statusCode(200).body("id", is(1));
    }

    @Test
    public void findByNomeTest(){ 
        given()
        .when()
        .pathParam("nome", "Ethernet (RJ-45)")
        .get("/entradas_saidas/search/{nome}")
        .then()
        .statusCode(200)
        .body("nome", hasItem(is("Ethernet (RJ-45)")));
    }

    @Test
    public void createTest(){
        EntradaSaidaDTO dto = new EntradaSaidaDTO("EntradaSaida Create");

        given()
        .contentType(MediaType.APPLICATION_JSON)
        .body(dto)
        .when()
        .post("/entradas_saidas")
        .then()
        .statusCode(201)
        .body("nome", is("EntradaSaida Create"));
    }

    @Test
    public void updateTest(){
        EntradaSaidaResponseDTO response = entradaSaidaService.create(new EntradaSaidaDTO("EntradaSaida Update"));
        EntradaSaidaDTO dto = new EntradaSaidaDTO("EntradaSaida Update 2");

        given()
        .contentType(MediaType.APPLICATION_JSON)
        .body(dto)
        .when()
        .pathParam("id", response.id())
        .put("/entradas_saidas/{id}")
        .then()
        .statusCode(204);
    }

    @Test
    public void deleteTest(){
        EntradaSaidaResponseDTO response = entradaSaidaService.create(new EntradaSaidaDTO("EntradaSaida Delete"));

        given()
        .when()
        .pathParam("id", response.id())
        .delete("/entradas_saidas/{id}")
        .then()
        .statusCode(204);
    }
}