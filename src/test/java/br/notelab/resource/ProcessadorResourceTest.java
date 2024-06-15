package br.notelab.resource;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasItem;

import org.junit.jupiter.api.Test;

import br.notelab.dto.notebook.processador.ProcessadorDTO;
import br.notelab.dto.notebook.processador.ProcessadorResponseDTO;
import br.notelab.service.notebook.processador.ProcessadorService;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.security.TestSecurity;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.MediaType;

@QuarkusTest
public class ProcessadorResourceTest { 

    @Inject
    public ProcessadorService ProcessadorService;
    
    @Test 
    @TestSecurity(user = "test", roles = "Funcionario")
    public void findAllTest(){
        given().when().get("/processadores").then().statusCode(200);
    }

    @Test
    @TestSecurity(user = "test", roles = "Funcionario")
    public void findByIdTest(){ 
        given().when().get("/processadores/1").then().statusCode(200).body("id", is(1));
    }

    @Test
    @TestSecurity(user = "test", roles = "Funcionario")
    public void findByModeloTest(){ 
        given()
        .when()
        .pathParam("modelo", "Intel I5 13600KF")
        .get("/processadores/search/{modelo}")
        .then()
        .statusCode(200)
        .body("modelo", hasItem(is("Intel I5 13600KF")));
    }

    @Test
    @TestSecurity(user = "test", roles = "Funcionario")
    public void createTest(){
        ProcessadorDTO dto = new ProcessadorDTO("Processador Create", "Velocid. C", "M. C.");

        given()
        .contentType(MediaType.APPLICATION_JSON)
        .body(dto)
        .when()
        .post("/processadores")
        .then()
        .statusCode(201)
        .body("modelo", is("Processador Create"));
    }

    @Test
    @TestSecurity(user = "test", roles = "Funcionario")
    public void updateTest(){
        ProcessadorResponseDTO response = ProcessadorService.create(new ProcessadorDTO("Processador Update", "Veloc. U", "Mm. U"));
        ProcessadorDTO dto = new ProcessadorDTO("Processador Update 2", "Velocid. C", "M. C.");

        given()
        .contentType(MediaType.APPLICATION_JSON)
        .body(dto)
        .when()
        .pathParam("id", response.id())
        .put("/processadores/{id}")
        .then()
        .statusCode(204);
    }

    @Test
    @TestSecurity(user = "test", roles = "Funcionario")
    public void deleteTest(){
        ProcessadorResponseDTO response = ProcessadorService.create(new ProcessadorDTO("Processador Delete", "Memoria D", "P. D"));

        given()
        .when()
        .pathParam("id", response.id())
        .delete("/processadores/{id}")
        .then()
        .statusCode(204);
    }
}