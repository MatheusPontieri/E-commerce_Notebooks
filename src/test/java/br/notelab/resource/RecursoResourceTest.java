package br.notelab.resource;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasItem;

import org.junit.jupiter.api.Test;

import br.notelab.dto.notebook.recurso.RecursoDTO;
import br.notelab.dto.notebook.recurso.RecursoResponseDTO;
import br.notelab.service.notebook.recurso.RecursoService;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.MediaType;

@QuarkusTest
public class RecursoResourceTest { 

    @Inject
    public RecursoService RecursoService;
    
    @Test 
    public void findAllTest(){
        given().when().get("/recursos").then().statusCode(200);
    }

    @Test
    public void findByIdTest(){ 
        given().when().get("/recursos/1").then().statusCode(200).body("id", is(1));
    }

    @Test
    public void findByNomeTest(){ 
        given()
        .when()
        .pathParam("nome", "Teclado Numérico")
        .get("/recursos/search/{nome}")
        .then()
        .statusCode(200)
        .body("nome", hasItem(is("Teclado Numérico")));
    }

    @Test
    public void createTest(){
        RecursoDTO dto = new RecursoDTO("Recurso Create");

        given()
        .contentType(MediaType.APPLICATION_JSON)
        .body(dto)
        .when()
        .post("/recursos")
        .then()
        .statusCode(201)
        .body("nome", is("Recurso Create"));
    }

    @Test
    public void updateTest(){
        RecursoResponseDTO response = RecursoService.create(new RecursoDTO("Recurso Update"));
        RecursoDTO dto = new RecursoDTO("Recurso Update 2");

        given()
        .contentType(MediaType.APPLICATION_JSON)
        .body(dto)
        .when()
        .pathParam("id", response.id())
        .put("/recursos/{id}")
        .then()
        .statusCode(204);
    }

    @Test
    public void deleteTest(){
        RecursoResponseDTO response = RecursoService.create(new RecursoDTO("Recurso Delete"));

        given()
        .when()
        .pathParam("id", response.id())
        .delete("/recursos/{id}")
        .then()
        .statusCode(204);
    }
}