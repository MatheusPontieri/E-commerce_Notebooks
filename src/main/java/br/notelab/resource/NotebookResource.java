package br.notelab.resource;

import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;

import br.notelab.dto.notebook.NotebookDTO;
import br.notelab.form.ImageForm;
import br.notelab.service.FileService;
import br.notelab.service.notebook.NotebookService;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.ResponseBuilder;
import jakarta.ws.rs.core.Response.Status;

@Path("/notebooks")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class NotebookResource {

    @Inject
    public NotebookService notebookService;

    @Inject
    public FileService fileService;

    @GET
    public Response findAll(){
        return Response.ok(notebookService.findAll()).build();
    }

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Long id){
        return Response.ok(notebookService.findById(id)).build();
    }

    @GET
    @Path("/search/descricao/{descricao}")
    public Response findByDescricao(@PathParam("descricao") String descricao){
        return Response.ok(notebookService.findByDescricao(descricao)).build();
    }

    @POST
    public Response create(NotebookDTO dto){
        return Response
            .status(201)
            .entity(notebookService.create(dto))
            .build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, NotebookDTO dto){
        notebookService.update(id, dto);
        return Response.status(204).build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id){
        notebookService.delete(id);
        return Response.status(204).build();
    }

    @GET
    @Path("/search/preco_min/{preco}")
    public Response findByPrecoMin(@PathParam("preco") Double preco){
        return Response.ok(notebookService.findByPrecoMin(preco)).build();
    }

    @GET
    @Path("/search/preco_max/{preco}")
    public Response findByPrecoMax(@PathParam("preco") Double preco){
        return Response.ok(notebookService.findByPrecoMax(preco)).build();
    }

    @GET
    @Path("/search/preco/{min}/{max}")
    public Response findByPrecoMinMax(@PathParam("min") Double min, @PathParam("max") Double max){
        return Response.ok(notebookService.findByPrecoMinMax(min, max)).build();
    }

    @GET
    @Path("/search/sistema_operacional/{sistema}")
    public Response findBySistemaOperacional(@PathParam("sistema") String sistema){
        return Response.ok(notebookService.findBySistemaOperacional(sistema)).build();
    }

    @GET
    @Path("/search/gamer/{isGamer}")
    public Response findByGamer(@PathParam("isGamer") Boolean isGamer){
        return Response.ok(notebookService.findByGamer(isGamer)).build();
    }

    @GET
    @Path("/search/recurso/{recurso}")
    public Response findByRecurso(@PathParam("recurso") String recurso){
        return Response.ok(notebookService.findByRecurso(recurso)).build();
    }

    @GET
    @Path("/search/placa_video/{placa}")
    public Response findByPlacaVideo(@PathParam("placa") String placaVideo){
        return Response.ok(notebookService.findByPlacaVideo(placaVideo)).build();
    }

    @GET
    @Path("/search/processador/{processador}")
    public Response findByProcessador(@PathParam("processador") String processador){
        return Response.ok(notebookService.findByProcessador(processador)).build();
    }

    @GET
    @Path("/search/nome_armazenamento/{nome}")
    public Response findByNomeArmazenamento(@PathParam("nome") String nome){
        return Response.ok(notebookService.findByNomeArmazenamento(nome)).build();
    }

    @GET
    @Path("/search/capacidade_armazenamento/{capacidade}")
    public Response findByCapacidadeArmazenamento(@PathParam("capacidade") String capacidade){
        return Response.ok(notebookService.findByCapacidadeArmazenamento(capacidade)).build();
    }

    @GET
    @Path("/search/capacidade_memoria_ram/{capacidade}")
    public Response findByCapacidadeMemoriaRam(@PathParam("capacidade") String capacidade){
        return Response.ok(notebookService.findByCapacidadeMemoriaRam(capacidade)).build();
    }

    @GET
    @Path("/search/taxa_atualizacao/{taxa}")
    public Response findByTaxaAtualizacao(@PathParam("taxa") String taxa){
        return Response.ok(notebookService.findByTaxaAtualizacao(taxa)).build();
    }
    
    @GET
    @Path("/search/conexao_sem_fio/{conexao}")
    public Response findByConexaoSemFio(@PathParam("conexao") String conexao){
        return Response.ok(notebookService.findByConexaoSemFio(conexao)).build();
    }

    @GET
    @Path("/search/entrada_saida/{entradaSaida}")
    public Response findByEntradaSaida(@PathParam("entradaSaida") String entradaSaida){
        return Response.ok(notebookService.findByEntradaSaida(entradaSaida)).build();
    }

    @PATCH
    @Path("/{id}/image/upload")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response upload(@PathParam("id") Long id, @MultipartForm ImageForm form){
        fileService.upload(id, form.getNomeImagem(), form.getImagem());

        return Response.status(Status.NO_CONTENT).build();
    }

    @GET
    @Path("/image/download/{nomeImagem}")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response download(@PathParam("nomeImagem") String nomeImagem){
        ResponseBuilder response = Response.ok(fileService.download(nomeImagem));
        response.header("Content-Disposition", "attachement: filename=" + nomeImagem);
        
        return response.build();
    }
}