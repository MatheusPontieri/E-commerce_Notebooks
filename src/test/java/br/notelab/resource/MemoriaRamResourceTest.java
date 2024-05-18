package br.notelab.resource;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasItem;

import org.junit.jupiter.api.Test;

import br.notelab.dto.notebook.memoria.MemoriaRamDTO;
import br.notelab.dto.notebook.memoria.MemoriaRamResponseDTO;
import br.notelab.service.notebook.memoria.MemoriaRamService;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.MediaType;

@QuarkusTest
public class MemoriaRamResourceTest { 

    @Inject
    public MemoriaRamService memoriaRamService;
    
    @Test 
    public void findAllTest(){
        given().when().get("/memorias_ram").then().statusCode(200);
    }

    @Test
    public void findByIdTest(){ 
        given().when().get("/memorias_ram/1").then().statusCode(200).body("id", is(1));
    }

    @Test
    public void findByCapacidadeTest(){ 
        given()
        .when()
        .pathParam("capacidade", "4 GB")
        .get("/memorias_ram/search/{capacidade}")
        .then()
        .statusCode(200)
        .body("capacidade", hasItem(is("4 GB")));
    }

    @Test
    public void createTest(){
        MemoriaRamDTO dto = new MemoriaRamDTO("Ram Create", "Ram Limite");

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
    public void updateTest(){
        MemoriaRamResponseDTO response = memoriaRamService.create(new MemoriaRamDTO("Ram Update", "Ram Limite"));
        MemoriaRamDTO dto = new MemoriaRamDTO("Ram Up 2", "Ram Lim 2");

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
    public void deleteTest(){
        MemoriaRamResponseDTO response = memoriaRamService.create(new MemoriaRamDTO("Ram Delete", "Ram Limite"));

        given()
        .when()
        .pathParam("id", response.id())
        .delete("/memorias_ram/{id}")
        .then()
        .statusCode(204);
    }
}