package br.notelab.resource;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.everyItem;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasItem;

import java.util.List;

import org.junit.jupiter.api.Test;

import br.notelab.dto.notebook.NotebookDTO;
import br.notelab.dto.notebook.NotebookResponseDTO;
import br.notelab.dto.notebook.especificacao.EspecificacaoDTO;
import br.notelab.dto.notebook.tela.TelaDTO;
import br.notelab.service.notebook.NotebookService;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.MediaType;

@QuarkusTest
public class NotebookResourceTest { 

    @Inject
    public NotebookService notebookService;
    
    @Test 
    public void findAllTest(){
        given().when().get("/notebooks").then().statusCode(200);
    }

    @Test
    public void findByIdTest(){ 
        given().when().get("/notebooks/1").then().statusCode(200).body("id", is(1));
    }


    @Test
    public void findByDescricaoTest(){ 
        given()
        .when()
        .pathParam("descricao", "Dell G15")
        .get("/notebooks/search/descricao/{descricao}")
        .then()
        .statusCode(200)
        .body("descricao", hasItem(is("Dell G15")));
    }

    @Test
    public void findByPrecoMinTest(){ 
        given()
        .when()
        .pathParam("preco", 3500d)
        .get("/notebooks/search/preco_min/{preco}")
        .then()
        .statusCode(200);
    }

    @Test
    public void findByPrecoMaxTest(){ 
        given()
        .when()
        .pathParam("preco", 8000d)
        .get("/notebooks/search/preco_max/{preco}")
        .then()
        .statusCode(200);
    }

    @Test
    public void findByPrecoMinMaxTest(){ 
        given()
        .when()
        .pathParam("min", 4500d)
        .pathParam("max",6500d)
        .get("/notebooks/search/preco/{min}/{max}")
        .then()
        .statusCode(200);
    }

    @Test
    public void findBySistemaOperacionalTest(){ 
        given()
        .when()
        .pathParam("sistema", "Windows")
        .get("/notebooks/search/sistema_operacional/{sistema}")
        .then()
        .statusCode(200)
        .body("sistemaOperacional", hasItem(is("Windows 11")));
    }

    @Test
    public void findByGamerTest(){ 
        given()
        .when()
        .pathParam("isGamer", true)
        .get("/notebooks/search/gamer/{isGamer}")
        .then()
        .statusCode(200)
        .body("isGamer", hasItem(is(true)));
    }

    @Test
    public void findByRecursoTest(){ 
        given()
        .when()
        .pathParam("recurso", "Tela Anti-reflexiva")
        .get("/notebooks/search/recurso/{recurso}")
        .then()
        .statusCode(200)
        .body("listaRecurso.nome", everyItem(hasItem("Tela Anti-reflexiva")));
    }

    @Test
    public void findByPlacaVideoTest(){ 
        given()
        .when()
        .pathParam("placa", "RTX 3050")
        .get("/notebooks/search/placa_video/{placa}")
        .then()
        .statusCode(200)
        .body("placaVideo.modelo", hasItem(is("RTX 3050")));
    }

    @Test
    public void findByProcessadorTest(){ 
        given()
        .when()
        .pathParam("processador", "Intel I5 13600KF")
        .get("/notebooks/search/processador/{processador}")
        .then()
        .statusCode(200)
        .body("processador.modelo", hasItem(is("Intel I5 13600KF")));
    }

    @Test
    public void findByNomeArmazenamentoTest(){ 
        given()
        .when()
        .pathParam("nome", "SSD")
        .get("/notebooks/search/nome_armazenamento/{nome}")
        .then()
        .statusCode(200)
        .body("listaArmazenamento.nome", everyItem(hasItem("SSD")));
    }

    @Test
    public void findByCapacidadeArmazenamentoTest(){ 
        given()
        .when()
        .pathParam("capacidade", "512 GB")
        .get("/notebooks/search/capacidade_armazenamento/{capacidade}")
        .then()
        .statusCode(200)
        .body("listaArmazenamento.capacidade", everyItem(hasItem("512 GB")));
    }

    @Test
    public void findByCapacidadeMemoriaRamTest(){ 
        given()
        .when()
        .pathParam("capacidade", "4 GB")
        .get("/notebooks/search/capacidade_memoria_ram/{capacidade}")
        .then()
        .statusCode(200)
        .body("listaMemoriaRam.capacidade", everyItem(hasItem("4 GB")));
    }

    @Test
    public void findByTaxaAtualizacaoTest(){ 
        given()
        .when()
        .pathParam("taxa", "120 Hz")
        .get("/notebooks/search/taxa_atualizacao/{taxa}")
        .then()
        .statusCode(200)
        .body("tela.taxaAtualizacao", hasItem(is("120 Hz")));
    }

    @Test
    public void findByConexaoSemFioTest(){ 
        given()
        .when()
        .pathParam("conexao", "Bluetooth")
        .get("/notebooks/search/conexao_sem_fio/{conexao}")
        .then()
        .statusCode(200)
        .body("listaConexaoSemFio.nome", everyItem(hasItem("Bluetooth")));
    }

    @Test
    public void findByEntradaSaidaTest(){ 
        given()
        .when()
        .pathParam("entradaSaida", "Ethernet (RJ-45)")
        .get("/notebooks/search/entrada_saida/{entradaSaida}")
        .then()
        .statusCode(200)
        .body("listaEntradaSaida.nome", everyItem(hasItem("Ethernet (RJ-45)")));
    }

    @Test
    public void createTest(){
        NotebookDTO dto = new NotebookDTO(
            "Lenovo IdeaPad L340",
            "IdeaPad",
            "L340",
            3419D,
            "81tr0002br",
            "Windows 11 Pro",
            true,
            4,
            "8 GB",
            10,
            2l,
            List.of(1l,2l,3l),
            1l,
            1l,
            List.of(1l, 2l),
            List.of(2l),
            new TelaDTO("12'", "1920x1080", "60 Hz"),
            new EspecificacaoDTO("25 cm", "10 cm", "12 cm", "2 kg"),
            List.of(1l,2l),
            List.of(1l,2l),
            2
        );

        given()
        .contentType(MediaType.APPLICATION_JSON)
        .body(dto)
        .when()
        .post("/notebooks")
        .then()
        .statusCode(201)
        .body("descricao", is("Lenovo IdeaPad L340"))
        .body("modelo", is("81tr0002br"));
    }

    @Test
    public void updateTest(){
        NotebookResponseDTO response = notebookService.create(
            new NotebookDTO(
                "Lenovo IdeaPad L340",
                "IdeaPad",
                "L340",
                3419D,
                "81tr0002br2",
                "Windows 11 Pro",
                true,
                4,
                "16 GB",
                10,
                2l,
                List.of(1l,2l,3l),
                1l,
                1l,
                List.of(1l, 2l),
                List.of(2l),
                new TelaDTO("13'", "1920x1080", "60 Hz"),
                new EspecificacaoDTO("28 cm", "10 cm", "12 cm", "2 kg"),
                List.of(1l,2l),
                List.of(1l,2l),
                2
        ));
        
        NotebookDTO dto = new NotebookDTO(
            "Lenovo IdeaPad L345",
            "Idea",
            "L234",
            3500D,
            "81tr0002br4",
            "Windows 11",
            true,
            4,
            "16 GB",
            10,
            2l,
            List.of(1l,2l,3l),
            1l,
            1l,
            List.of(1l, 2l),
            List.of(2l),
            new TelaDTO("13'", "1920x1080", "60 Hz"),
            new EspecificacaoDTO("28 cm", "10 cm", "12 cm", "2 kg"),
            List.of(1l,2l),
            List.of(1l,2l),
            2
        );

        given()
        .contentType(MediaType.APPLICATION_JSON)
        .body(dto)
        .when()
        .pathParam("id", response.id())
        .put("/notebooks/{id}")
        .then()
        .statusCode(204);
    }

    @Test
    public void deleteTest(){
        NotebookResponseDTO dto = notebookService.create(new NotebookDTO(
            "Lenovo IdeaPad L345",
            "Idea",
            "L543",
            3500D,
            "81tr0002br5",
            "Windows 11",
            true,
            4,
            "16 GB",
            10,
            2l,
            List.of(1l,2l,3l),
            1l,
            1l,
            List.of(1l, 2l),
            List.of(2l),
            new TelaDTO("10'", "1920x1080", "60 Hz"),
            new EspecificacaoDTO("22 cm", "10 cm", "12 cm", "2 kg"),
            List.of(1l,2l),
            List.of(1l,2l),
            2
    ));
        given()
        .when()
        .pathParam("id", dto.id())
        .delete("/notebooks/{id}")
        .then()
        .statusCode(204);
    }
}