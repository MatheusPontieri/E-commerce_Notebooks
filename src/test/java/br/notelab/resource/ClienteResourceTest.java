package br.notelab.resource;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasItem;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import br.notelab.dto.endereco.EnderecoDTO;
import br.notelab.dto.pessoa.cliente.ClienteDTO;
import br.notelab.dto.pessoa.cliente.ClienteResponseDTO;
import br.notelab.dto.pessoa.telefone.TelefoneDTO;
import br.notelab.service.pessoa.cliente.ClienteService;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.security.TestSecurity;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.MediaType;


@QuarkusTest
public class ClienteResourceTest {

    @Inject
    public ClienteService clienteService;

    @Test
    @TestSecurity(user = "test", roles = "Funcionario")
    public void findAllTest(){
        given().when().get("/clientes").then().statusCode(200);
    }

    @Test
    @TestSecurity(user = "test", roles = "Funcionario")
    public void findByIdTest(){
        given().when().get("/clientes/1").then().statusCode(200).body("id", is(1));
    }

    @Test
    @TestSecurity(user = "test", roles = "Funcionario")
    public void findByNomeTest(){
        given()
        .when()
        .pathParam("nome", "Rafael")
        .get("/clientes/search/nome/{nome}")
        .then()
        .statusCode(200)
        .body("nome", hasItem("Rafael"));
    }

    @Test
    @TestSecurity(user = "test", roles = "Funcionario")
    public void findByCpfTest(){
        given()
        .when()
        .pathParam("cpf", "955.514")
        .get("/clientes/search/cpf/{cpf}")
        .then()
        .statusCode(200)
        .body("cpf", hasItem("955.514.170-34"));
    }

    @Test
    public void createTest(){
        ClienteDTO dto = new ClienteDTO(
            "Pedro Cavalcante",
            LocalDate.parse("2000-04-26"),
            true,
            "574.790.350-04",
            "pedro@gmail.com",
            "pedro12345",
            1,
            Arrays.asList(new EnderecoDTO("77021-456", "Quadra 3", "204 Sul", 0, null, 1L)),
            List.of(new TelefoneDTO("77", "8429-0303"))
        );
        
        given()
        .contentType(MediaType.APPLICATION_JSON)
        .body(dto)
        .when()
        .post("/clientes")
        .then()
        .statusCode(201)
        .body("nome", is("Pedro Cavalcante"))
        .body("cpf", is("574.790.350-04"));
    }

    @Test
    @TestSecurity(user = "test", roles = "Cliente")
    public void updateTest(){
        ClienteDTO dto = new ClienteDTO(
            "João Silva",
            LocalDate.parse("2005-04-26"),
            true,
            "449.373.820-04",
            "joao@gmail.com",
            "joao12345",
            1,
            Arrays.asList(new EnderecoDTO("77021-456", "Quadra 3", "204 Sul", 0, null, 1L)),
            List.of(new TelefoneDTO("63", "8425-0319"))
        );
        ClienteResponseDTO response = clienteService.create(dto);

        ClienteDTO dtoUpdate = new ClienteDTO(
            "João Pedro",
            LocalDate.parse("2005-04-26"),
            false,
            "449.373.820-04",
            "joao@gmail.com",
            "joao12345",
            1,
            Arrays.asList(new EnderecoDTO("77021-456", "Quadra 3", "204 Sul", 0, null, 1L)),
            List.of(new TelefoneDTO("62", "8425-0318"))
        );

        given()
        .contentType(MediaType.APPLICATION_JSON)
        .body(dtoUpdate)
        .when()
        .pathParam("id", response.id())
        .put("/clientes/{id}")
        .then()
        .statusCode(204);
    }

    @Test
    @TestSecurity(user = "test", roles = "Cliente")
    public void deleteTest(){
       ClienteDTO dto = new ClienteDTO(
            "João Pedro",
            LocalDate.parse("2005-04-26"),
            false,
            "644.683.080-79",
            "joaopedro@gmail.com",
            "joao12345",
            1,
            Arrays.asList(new EnderecoDTO("77021-456", "Quadra 3", "204 Sul", 0, null, 1L)),
            List.of(new TelefoneDTO("63", "8421-0319"))
        );

        ClienteResponseDTO response = clienteService.create(dto);

        given()
        .when()
        .pathParam("id", response.id())
        .delete("/clientes/{id}")
        .then()
        .statusCode(204);
    }
}
