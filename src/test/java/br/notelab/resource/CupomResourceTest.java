package br.notelab.resource;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasItem;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import br.notelab.dto.pedido.cupom.CupomDTO;
import br.notelab.dto.pedido.cupom.CupomResponseDTO;
import br.notelab.service.pedido.cupom.CupomService;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.security.TestSecurity;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.MediaType;

@QuarkusTest
public class CupomResourceTest {

    @Inject
    public CupomService cupomService;

    @Test
    @TestSecurity(user = "test", roles = "Funcionario")
    public void findAllTest(){
        given().when().get("/cupons").then().statusCode(200);
    }

    @Test
    @TestSecurity(user = "test", roles = "Funcionario")
    public void findByIdTest(){
        given().when().get("/cupons/1").then().statusCode(200).body("id", is(1));
    }

    @Test
    @TestSecurity(user = "test", roles = "Funcionario")
    public void findByCodigoTest(){
        given()
        .when()
        .pathParam("codigo", "LENOVO")
        .get("/cupons/search/codigo/{codigo}")
        .then()
        .statusCode(200)
        .body("codigo", hasItem("LENOVO20OFF"));
    }

    @Test
    @TestSecurity(user = "test", roles = "Funcionario")
    public void findByFornecedorTest(){
        given()
        .when()
        .pathParam("id-fornecedor", 2L)
        .get("/cupons/search/fornecedor/{id-fornecedor}")
        .then()
        .statusCode(200)
        .body("codigo", hasItem(is("LENOVO20OFF")));
    }

    @Test
    @TestSecurity(user = "test", roles = "Funcionario")
    public void createTest(){
        CupomDTO dto = new CupomDTO(
            "CREATE20OFF",
            0.2f,
            LocalDateTime.now().plusDays(15),
            1l);
        
        given()
        .contentType(MediaType.APPLICATION_JSON)
        .body(dto)
        .when()
        .post("/cupons")
        .then()
        .statusCode(201)
        .body("codigo", is("CREATE20OFF"))
        .body("fornecedor.id", is(1));
    }

    @Test
    @TestSecurity(user = "test", roles = "Funcionario")
    public void updateTest(){
        CupomResponseDTO response = cupomService.create(new CupomDTO(
            "UPDATE20OFF",
            0.2f,
            LocalDateTime.now().plusDays(15),
            1l
        ));

        CupomDTO dto = new CupomDTO(
            "UPDATE25OFF",
            0.25f,
            LocalDateTime.now().plusDays(15),
            1l
        );


        given()
        .contentType(MediaType.APPLICATION_JSON)
        .body(dto)
        .when()
        .pathParam("id", response.id())
        .put("/cupons/{id}")
        .then()
        .statusCode(204);
    }

    @Test
    @TestSecurity(user = "test", roles = "Funcionario")
    public void deleteTest(){
        CupomResponseDTO response = cupomService.create(new CupomDTO(
            "DELETE15OFF",
            0.15f,
            LocalDateTime.now().plusDays(15),
            1l
        ));

        given()
        .when()
        .pathParam("id", response.id())
        .delete("/cupons/{id}")
        .then()
        .statusCode(204);
    }
}
