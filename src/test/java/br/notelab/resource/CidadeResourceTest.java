package br.notelab.resource;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasItem;

import org.junit.jupiter.api.Test;

import br.notelab.dto.endereco.cidade.CidadeDTO;
import br.notelab.dto.endereco.cidade.CidadeResponseDTO;
import br.notelab.service.endereco.cidade.CidadeService;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.security.TestSecurity;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.MediaType;

@QuarkusTest
public class CidadeResourceTest {

    @Inject
    public CidadeService cidadeService;

    @Test
    @TestSecurity(user = "test", roles = "Funcionario")
    public void findAllTest(){
        given().when().get("/cidades").then().statusCode(200);
    }

    @Test
    public void findByIdTest(){
        given().when().get("/cidades/1").then().statusCode(200).body("id", is(1));
    }

    @Test
    public void findByNomeTest(){
        given()
        .when()
        .pathParam("nome", "Palmas")
        .get("/cidades/search/nome_cidade/{nome}")
        .then()
        .statusCode(200)
        .body("nome", hasItem("Palmas"));
    }

    @Test
    public void findByNomeEstadoTest(){
        given()
        .when()
        .pathParam("nome", "Tocantins")
        .get("/cidades/search/nome_estado/{nome}")
        .then()
        .statusCode(200)
        .body("nome", hasItem("Palmas"));
    }

    @Test
    public void createTest(){
        CidadeDTO dto = new CidadeDTO("Cidade Create", 1L);
        
        given()
        .contentType(MediaType.APPLICATION_JSON)
        .body(dto)
        .when()
        .post("/cidades")
        .then()
        .statusCode(201)
        .body("nome", is("Cidade Create"))
        .body("estado.id", is(1));
    }

    @Test
    public void updateTest(){
        CidadeResponseDTO response = cidadeService.create(new CidadeDTO("Cidade Update", 2L));
        CidadeDTO dto = new CidadeDTO("Cidade Update 2", 1L);

        given()
        .contentType(MediaType.APPLICATION_JSON)
        .body(dto)
        .when()
        .pathParam("id", response.id())
        .put("/cidades/{id}")
        .then()
        .statusCode(204);
    }

    @Test
    public void deleteTest(){
        CidadeResponseDTO response = cidadeService.create(new CidadeDTO("Cidade Delete", 1L));

        given()
        .when()
        .pathParam("id", response.id())
        .delete("/cidades/{id}")
        .then()
        .statusCode(204);
    }
}
