package br.notelab.resource;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasItem;

import org.junit.jupiter.api.Test;

import br.notelab.dto.notebook.gpu.PlacaVideoDTO;
import br.notelab.dto.notebook.gpu.PlacaVideoResponseDTO;
import br.notelab.service.notebook.gpu.PlacaVideoService;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.security.TestSecurity;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.MediaType;

@QuarkusTest
public class PlacaVideoResourceTest { 

    @Inject
    public PlacaVideoService placaVideoService;
    
    @Test 
    @TestSecurity(user = "test", roles = "Funcionario")
    public void findAllTest(){
        given().when().get("/placas-video").then().statusCode(200);
    }

    @Test
    @TestSecurity(user = "test", roles = "Funcionario")
    public void findByIdTest(){ 
        given().when().get("/placas-video/1").then().statusCode(200).body("id", is(1));
    }

    @Test
    @TestSecurity(user = "test", roles = "Funcionario")
    public void findByModeloTest(){ 
        given()
        .when()
        .pathParam("modelo", "RTX 3050")
        .get("/placas-video/search/modelo/{modelo}")
        .then()
        .statusCode(200)
        .body("modelo", hasItem(is("RTX 3050")));
    }

    @Test
    @TestSecurity(user = "test", roles = "Funcionario")
    public void findByMemoriaVideoTest(){ 
        given()
        .when()
        .pathParam("memoria", "4 GB")
        .get("/placas-video/search/memoria/{memoria}")
        .then()
        .statusCode(200)
        .body("memoriaVideo", hasItem(is("4 GB")));
    }

    @Test
    @TestSecurity(user = "test", roles = "Funcionario")
    public void createTest(){
        PlacaVideoDTO dto = new PlacaVideoDTO("Placa Create", "Memoria C");

        given()
        .contentType(MediaType.APPLICATION_JSON)
        .body(dto)
        .when()
        .post("/placas-video")
        .then()
        .statusCode(201)
        .body("modelo", is("Placa Create"));
    }

    @Test
    @TestSecurity(user = "test", roles = "Funcionario")
    public void updateTest(){
        PlacaVideoResponseDTO response = placaVideoService.create(new PlacaVideoDTO("Placa Update", "Memoria U"));
        PlacaVideoDTO dto = new PlacaVideoDTO("Placa Up 2", "Memoria C2");

        given()
        .contentType(MediaType.APPLICATION_JSON)
        .body(dto)
        .when()
        .pathParam("id", response.id())
        .put("/placas-video/{id}")
        .then()
        .statusCode(204);
    }

    @Test
    @TestSecurity(user = "test", roles = "Funcionario")
    public void deleteTest(){
        PlacaVideoResponseDTO response = placaVideoService.create(new PlacaVideoDTO("Placa Delete", "Memoria U"));

        given()
        .when()
        .pathParam("id", response.id())
        .delete("/placas-video/{id}")
        .then()
        .statusCode(204);
    }
}