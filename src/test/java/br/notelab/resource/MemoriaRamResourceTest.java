package br.notelab.resource;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasItem;

import org.junit.jupiter.api.Test;

import br.notelab.dto.notebook.memoria.MemoriaRamDTO;
import br.notelab.dto.notebook.memoria.MemoriaRamResponseDTO;
import br.notelab.service.notebook.memoria.MemoriaRamService;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.security.TestSecurity;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.MediaType;

@QuarkusTest
public class MemoriaRamResourceTest { 

    @Inject
    public MemoriaRamService memoriaRamService;
    
    @Test 
    @TestSecurity(user = "test", roles = "Funcionario")
    public void findAllTest(){
        given().when().get("/memorias_ram").then().statusCode(200);
    }

    @Test
    @TestSecurity(user = "test", roles = "Funcionario")
    public void findByIdTest(){ 
        given().when().get("/memorias_ram/1").then().statusCode(200).body("id", is(1));
    }

    @Test
    @TestSecurity(user = "test", roles = "Funcionario")
    public void findByCapacidadeTest(){ 
        given()
        .when()
        .pathParam("capacidade", "4 GB")
        .get("/memorias_ram/search/{capacidade}")
        .then()
        .statusCode(200)
        .body("capacidade", hasItem("4 GB"));
    }

    @Test
    @TestSecurity(user = "test", roles = "Funcionario")
    public void createTest(){
        MemoriaRamDTO dto = new MemoriaRamDTO("Ram Create");

        given()
        .contentType(MediaType.APPLICATION_JSON)
        .body(dto)
        .when()
        .post("/memorias_ram")
        .then()
        .statusCode(201)
        .body("capacidade", is("Ram Create"));
    }

    @Test
    @TestSecurity(user = "test", roles = "Funcionario")
    public void updateTest(){
        MemoriaRamResponseDTO response = memoriaRamService.create(new MemoriaRamDTO("Ram Update"));
        MemoriaRamDTO dto = new MemoriaRamDTO("Ram Up 2");

        given()
        .contentType(MediaType.APPLICATION_JSON)
        .body(dto)
        .when()
        .pathParam("id", response.id())
        .put("/memorias_ram/{id}")
        .then()
        .statusCode(204);
    }

    @Test
    @TestSecurity(user = "test", roles = "Funcionario")
    public void deleteTest(){
        MemoriaRamResponseDTO response = memoriaRamService.create(new MemoriaRamDTO("Ram Delete"));

        given()
        .when()
        .pathParam("id", response.id())
        .delete("/memorias_ram/{id}")
        .then()
        .statusCode(204);
    }
}