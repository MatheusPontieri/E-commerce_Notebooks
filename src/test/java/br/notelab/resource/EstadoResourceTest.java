package br.notelab.resource;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasItem;

import org.junit.jupiter.api.Test;

import br.notelab.dto.endereco.estado.EstadoDTO;
import br.notelab.dto.endereco.estado.EstadoResponseDTO;
import br.notelab.service.endereco.estado.EstadoService;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.security.TestSecurity;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.MediaType;

@QuarkusTest
public class EstadoResourceTest { 

    @Inject
    public EstadoService estadoService;
    
    @Test 
    @TestSecurity(user = "test", roles = "Funcionario")
    public void findAllTest(){
        given().when().get("/estados").then().statusCode(200);
    }

    @Test
    @TestSecurity(user = "test", roles = "Funcionario")
    public void findByIdTest(){ 
        given().when().get("/estados/1").then().statusCode(200).body("id", is(1));
    }

    @Test
    @TestSecurity(user = "test", roles = "Funcionario")
    public void findByNomeTest(){ 
        given()
        .when()
        .pathParam("nome", "Tocantins")
        .get("/estados/search/nome/{nome}")
        .then()
        .statusCode(200)
        .body("nome", hasItem(is("Tocantins")));
    }

    @Test
    @TestSecurity(user = "test", roles = "Funcionario")
    public void createTest(){
        EstadoDTO dto = new EstadoDTO("Estado Create", "EC");

        given()
        .contentType(MediaType.APPLICATION_JSON)
        .body(dto)
        .when()
        .post("/estados")
        .then()
        .statusCode(201)
        .body("nome", is("Estado Create"))
        .body("sigla", is("EC"));
    }

    @Test
    @TestSecurity(user = "test", roles = "Funcionario")
    public void updateTest(){
        EstadoResponseDTO response = estadoService.create(new EstadoDTO("Estado Update", "EU"));
        EstadoDTO dto = new EstadoDTO("Estado Update 2", "E2");

        given()
        .contentType(MediaType.APPLICATION_JSON)
        .body(dto)
        .when()
        .pathParam("id", response.id())
        .put("/estados/{id}")
        .then()
        .statusCode(204);
    }

    @Test
    @TestSecurity(user = "test", roles = "Funcionario")
    public void deleteTest(){
        EstadoResponseDTO response = estadoService.create(new EstadoDTO("Estado Delete", "ED"));

        given()
        .when()
        .pathParam("id", response.id())
        .delete("/estados/{id}")
        .then()
        .statusCode(204);
    }
}