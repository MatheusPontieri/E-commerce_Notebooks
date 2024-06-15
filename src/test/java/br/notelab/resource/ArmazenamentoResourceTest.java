package br.notelab.resource;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasItem;

import org.junit.jupiter.api.Test;

import br.notelab.dto.notebook.memoria.ArmazenamentoDTO;
import br.notelab.dto.notebook.memoria.ArmazenamentoResponseDTO;
import br.notelab.service.notebook.memoria.ArmazenamentoService;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.security.TestSecurity;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.MediaType;

@QuarkusTest
public class ArmazenamentoResourceTest {
    
    @Inject
    public ArmazenamentoService armazenamentoService;

    @Test
    @TestSecurity(user = "test", roles = "Funcionario")
    public void findAllTest(){
        given().when().get("/armazenamentos").then().statusCode(200);
    }

    @Test
    @TestSecurity(user = "test", roles = "Funcionario")
    public void findByIdTest(){
        given().when().get("/armazenamentos/1").then().statusCode(200).body("id", is(1));
    }

    @Test
    @TestSecurity(user = "test", roles = "Funcionario")
    public void findByNomeTest(){
        given()
        .when()
        .pathParam("nome", "SSD")
        .get("/armazenamentos/search/nome/{nome}")
        .then()
        .statusCode(200)
        .body("nome", hasItem(is("SSD")));
    }

    @Test
    @TestSecurity(user = "test", roles = "Funcionario")
    public void findByCapacidadeTest(){
        given()
        .when()
        .pathParam("capacidade", "256 GB")
        .get("/armazenamentos/search/capacidade/{capacidade}")
        .then()
        .statusCode(200)
        .body("capacidade", hasItem(is("256 GB")));
    }

    @Test
    @TestSecurity(user = "test", roles = "Funcionario")
    public void createTest(){
        ArmazenamentoDTO dto = new ArmazenamentoDTO("SSD Create", "3 TB");
        
        given()
        .contentType(MediaType.APPLICATION_JSON)
        .body(dto)
        .when()
        .post("/armazenamentos")
        .then()
        .statusCode(201)
        .body("nome", is("SSD Create"))
        .body("capacidade", is("3 TB"));
    }

    @Test
    @TestSecurity(user = "test", roles = "Funcionario")
    public void updateTest(){
        ArmazenamentoResponseDTO response = armazenamentoService.create(new ArmazenamentoDTO("SSD Update", "2 TB"));
        ArmazenamentoDTO dto = new ArmazenamentoDTO("SSD Update", "1 TB");

        given()
        .contentType(MediaType.APPLICATION_JSON)
        .body(dto)
        .when()
        .pathParam("id", response.id())
        .put("/armazenamentos/{id}")
        .then()
        .statusCode(204);
    }

    @Test
    @TestSecurity(user = "test", roles = "Funcionario")
    public void deleteTest(){
        ArmazenamentoResponseDTO response = armazenamentoService.create(new ArmazenamentoDTO("SSD Delete", "1 TB"));

        given()
        .when()
        .pathParam("id", response.id())
        .delete("/armazenamentos/{id}")
        .then()
        .statusCode(204);
    }
}