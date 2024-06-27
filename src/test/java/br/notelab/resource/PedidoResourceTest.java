package br.notelab.resource;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import br.notelab.dto.pedido.PedidoDTO;
import br.notelab.dto.pedido.PedidoResponseDTO;
import br.notelab.dto.pedido.item_pedido.ItemPedidoDTO;
import br.notelab.service.pedido.PedidoService;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.security.TestSecurity;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.MediaType;

@QuarkusTest
public class PedidoResourceTest {

    @Inject
    public PedidoService pedidoService;

    @Test
    @TestSecurity(user = "test", roles = "Funcionario")
    public void findAllTest(){
        given().when().get("/pedidos").then().statusCode(200);
    }

    @Test
    @TestSecurity(user = "test", roles = "Funcionario")
    public void findByIdTest(){
        given().when().get("/pedidos/1").then().statusCode(200).body("id", is(1));
    }

    /*
    @Test
    @TestSecurity(user = "test", roles = "Cliente")
    public void createTest(){
        PedidoDTO dto = new PedidoDTO(
            1L,
            Arrays.asList(new ItemPedidoDTO(1L, 3500D, 2, 1L))
        );

        given()
        .contentType(MediaType.APPLICATION_JSON)
        .body(dto)
        .when()
        .post("/pedidos")
        .then()
        .statusCode(201)
        .body("cliente.id", is(1));
    }

    @Test
    @TestSecurity(user = "test", roles = "Cliente")
    public void updateTest(){
        PedidoResponseDTO response = pedidoService.create(new PedidoDTO(
            1L,
            Arrays.asList(new ItemPedidoDTO(1L, 3500D, 2, 1L))
        ));

        PedidoDTO dto = new PedidoDTO(
            1L,
            Arrays.asList(new ItemPedidoDTO(1L, 3500D, 1, 1L))
        );

        given()
        .contentType(MediaType.APPLICATION_JSON)
        .body(dto)
        .when()
        .pathParam("id", response.id())
        .put("/pedidos/{id}")
        .then()
        .statusCode(204);
    }

    @Test
    @TestSecurity(user = "test", roles = "Cliente")
    public void deleteTest(){
        PedidoResponseDTO response = pedidoService.create(new PedidoDTO(
            1L,
            Arrays.asList(new ItemPedidoDTO(1L, 3500D, 2, 1L))
        ));

        given()
        .when()
        .pathParam("id", response.id())
        .delete("/pedidos/{id}")
        .then()
        .statusCode(204);
    }
}
    */
}