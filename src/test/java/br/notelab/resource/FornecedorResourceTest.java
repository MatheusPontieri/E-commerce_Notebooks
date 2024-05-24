package br.notelab.resource;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasItem;

import java.util.List;

import org.junit.jupiter.api.Test;

import br.notelab.dto.pessoa.fornecedor.FornecedorDTO;
import br.notelab.dto.pessoa.fornecedor.FornecedorResponseDTO;
import br.notelab.dto.pessoa.telefone.TelefoneDTO;
import br.notelab.service.pessoa.fornecedor.FornecedorService;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.MediaType;

@QuarkusTest
public class FornecedorResourceTest { 

    @Inject
    public FornecedorService fornecedorService;
    
    @Test 
    public void findAllTest(){
        given().when().get("/fornecedores").then().statusCode(200);
    }

    @Test
    public void findByIdTest(){ 
        given().when().get("/fornecedores/1").then().statusCode(200).body("id", is(1));
    }

    @Test
    public void findByNomeTest(){ 
        given()
        .when()
        .pathParam("nome", "Dell")
        .get("/fornecedores/search/{nome}")
        .then()
        .statusCode(200)
        .body("nome", hasItem("Dell Enterprises"));
    }

    @Test
    public void createTest(){
        FornecedorDTO dto = new FornecedorDTO(
            "Fornecedor Create", 
            "create@gmail.com", 
            "93.818.452/0001-66",
            List.of(new TelefoneDTO("60", "9219-4088"))
        );

        given()
        .contentType(MediaType.APPLICATION_JSON)
        .body(dto)
        .when()
        .post("/fornecedores")
        .then()
        .statusCode(201)
        .body("nome", is("Fornecedor Create"))
        .body("cnpj", is("93.818.452/0001-66"));
    }

    @Test
    public void updateTest(){
        FornecedorResponseDTO response = fornecedorService.create(new FornecedorDTO(
            "Fornecedor Update", 
            "update@gmail.com", 
            "84.052.332/0001-00",
            List.of(new TelefoneDTO("64", "9220-4088")))
        );

        FornecedorDTO dto = new FornecedorDTO(
            "Fornecedor Update 2", 
            "update2@gmail.com", 
            "84.052.332/0001-00",
            List.of(new TelefoneDTO("63", "9240-4095"))
        );

        given()
        .contentType(MediaType.APPLICATION_JSON)
        .body(dto)
        .when()
        .pathParam("id", response.id())
        .put("/fornecedores/{id}")
        .then()
        .statusCode(204);
    }

    @Test
    public void deleteTest(){
        FornecedorResponseDTO response = fornecedorService.create(new FornecedorDTO(
        "Fornecedor Delete", 
        "delete@gmail.com", 
        "01.304.006/0001-15",
        List.of(new TelefoneDTO("62", "9245-4088")))
        );

        given()
        .when()
        .pathParam("id", response.id())
        .delete("/fornecedores/{id}")
        .then()
        .statusCode(204);
    }
}