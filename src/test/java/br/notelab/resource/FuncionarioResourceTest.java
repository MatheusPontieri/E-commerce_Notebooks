package br.notelab.resource;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasItem;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import br.notelab.dto.endereco.EnderecoDTO;
import br.notelab.dto.pessoa.funcionario.FuncionarioDTO;
import br.notelab.dto.pessoa.funcionario.FuncionarioResponseDTO;
import br.notelab.dto.pessoa.telefone.TelefoneDTO;
import br.notelab.repository.CidadeRepository;
import br.notelab.service.pessoa.funcionario.FuncionarioService;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.MediaType;

@QuarkusTest
public class FuncionarioResourceTest {

    @Inject
    public FuncionarioService funcionarioService;

    @Inject
    public CidadeRepository cidadeRepository;

    @Test
    // @Order
    // @TestSecurity
    public void findAllTest(){
        given().when().get("/funcionarios").then().statusCode(200);
    }

    @Test
    public void findByIdTest(){
        given().when().get("/funcionarios/1").then().statusCode(200).body("id", is(1));
    }

    @Test
    public void findByNomeTest(){
        given()
        .when()
        .pathParam("nome", "Maria")
        .get("/funcionarios/search/nome/{nome}")
        .then()
        .statusCode(200)
        .body("nome", hasItem("Maria"));
    }

    @Test
    public void findByCpfTest(){
        given()
        .when()
        .pathParam("cpf", "581.019.600")
        .get("/funcionarios/search/cpf/{cpf}")
        .then()
        .statusCode(200)
        .body("cpf", hasItem("581.019.600-40"));
    }

    @Test
    public void createTest(){
        FuncionarioDTO dto = new FuncionarioDTO(
            LocalDate.parse("2022-04-26"),
            2500d, 
            "Lucas Rafael",
            LocalDate.parse("1980-04-26"),
            "626.163.140-96",
            "lucasrafael@gmail.com",
            "lucas12345",
            1,
            Arrays.asList(new EnderecoDTO("77040-456", "Quadra 5", "204 Norte", 12, "Perto da ponte", 2L)),
            List.of(new TelefoneDTO("63", "8429-0319"))
        );
        
        given()
        .contentType(MediaType.APPLICATION_JSON)
        .body(dto)
        .when()
        .post("/funcionarios")
        .then()
        .statusCode(201)
        .body("nome", is("Lucas Rafael"))
        .body("cpf", is("626.163.140-96"));
    }

    @Test
    public void updateTest(){
        FuncionarioDTO dto = new FuncionarioDTO(
            LocalDate.parse("2020-02-15"),
            3200d,
            "João Antonio",
            LocalDate.parse("1970-04-26"),
            "590.662.790-10",
            "joaoantonio10@gmail.com",
            "joaoantonio12345",
            1,
            Arrays.asList(new EnderecoDTO("77021-458", "Quadra 2", "207 Sul", 5, null, 2L)),
            List.of(new TelefoneDTO("35", "8404-0319"))
        );
        FuncionarioResponseDTO response = funcionarioService.create(dto);

        FuncionarioDTO dtoUpdate = new FuncionarioDTO(
            LocalDate.parse("2020-02-22"),
            2500d,
            "João Pedro",
            LocalDate.parse("2005-04-26"),
            "314.453.590-73",
            "joaoantonio10@gmail.com",
            "joaoantonio12345",
            1,
            Arrays.asList(new EnderecoDTO("77021-456", "Quadra 3", "204 Sul", 0, null, 1L)),
            List.of(new TelefoneDTO("35", "8404-0319"))
        );

        given()
        .contentType(MediaType.APPLICATION_JSON)
        .body(dtoUpdate)
        .when()
        .pathParam("id", response.id())
        .put("/funcionarios/{id}")
        .then()
        .statusCode(204);
    }

    @Test
    public void deleteTest(){
        FuncionarioDTO dto = new FuncionarioDTO(
            LocalDate.parse("2005-04-26"),
            1900d,
            "João Silva",
            LocalDate.parse("2005-04-26"),
            "881.260.930-99",
            "joaosilva@gmail.com",
            "joaoSilva12345",
            1,
            Arrays.asList(new EnderecoDTO("77021-221", "Quadra 10", "209 Norte", 12, null, 1L)),
            List.of(new TelefoneDTO("63", "8421-5837"))
        );

        FuncionarioResponseDTO response = funcionarioService.create(dto);

        given()
        .when()
        .pathParam("id", response.id())
        .delete("/funcionarios/{id}")
        .then()
        .statusCode(204);
    }
}
