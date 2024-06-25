package br.notelab.resource;

import org.jboss.logging.Logger;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;

import br.notelab.dto.notebook.NotebookDTO;
import br.notelab.form.ImageForm;
import br.notelab.service.file.FileService;
import br.notelab.service.notebook.NotebookFileServiceImpl;
import br.notelab.service.notebook.NotebookService;
import jakarta.annotation.security.RolesAllowed;
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

    private static final Logger LOG = Logger.getLogger(NotebookResource.class);

    @Inject
    public NotebookService notebookService;

    @Inject
    public NotebookFileServiceImpl fileService;

    @GET
    // @RolesAllowed({"Funcionario"})
    public Response findAll(){
        LOG.info("Buscando todos os notebooks");
        return Response.ok(notebookService.findAll()).build();
    }

    @GET
    @RolesAllowed({"Funcionario"})
    @Path("/{id}")
    public Response findById(@PathParam("id") Long id){
        LOG.infof("Buscando notebook com id %d", id);
        return Response.ok(notebookService.findById(id)).build();
    }

    @GET
    @Path("/search/descricao/{descricao}")
    public Response findByDescricao(@PathParam("descricao") String descricao){
        LOG.infof("Buscando notebooks com descrição %s", descricao);
        return Response.ok(notebookService.findByDescricao(descricao)).build();
    }

    @POST
    @RolesAllowed({"Funcionario"})
    public Response create(NotebookDTO dto){
        LOG.info("Criando notebook");
        return Response
            .status(201)
            .entity(notebookService.create(dto))
            .build();
    }

    @PUT
    @RolesAllowed({"Funcionario"})
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, NotebookDTO dto){
        LOG.infof("Atualizando notebook com id %d", id);
        notebookService.update(id, dto);
        return Response.status(204).build();
    }

    @DELETE
    @RolesAllowed({"Funcionario"})
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id){
        LOG.infof("Excluindo notebook com id %d", id);
        notebookService.delete(id);
        return Response.status(204).build();
    }

    @GET
    @Path("/search/preco_min/{preco}")
    public Response findByPrecoMin(@PathParam("preco") Double preco){
        LOG.infof("Buscando notebooks com preço mínimo %.2f", preco);
        return Response.ok(notebookService.findByPrecoMin(preco)).build();
    }

    @GET
    @Path("/search/preco_max/{preco}")
    public Response findByPrecoMax(@PathParam("preco") Double preco){
        LOG.infof("Buscando notebooks com preço máximo %.2f", preco);
        return Response.ok(notebookService.findByPrecoMax(preco)).build();
    }

    @GET
    @Path("/search/preco/{min}/{max}")
    public Response findByPrecoMinMax(@PathParam("min") Double min, @PathParam("max") Double max){
        LOG.infof("Buscando notebooks com preço entre %.2f e %.2f", min, max);
        return Response.ok(notebookService.findByPrecoMinMax(min, max)).build();
    }

    @GET
    @Path("/search/sistema_operacional/{sistema}")
    public Response findBySistemaOperacional(@PathParam("sistema") String sistema){
        LOG.infof("Buscando notebooks com sistema operacional %s", sistema);
        return Response.ok(notebookService.findBySistemaOperacional(sistema)).build();
    }

    @GET
    @Path("/search/gamer/{isGamer}")
    public Response findByGamer(@PathParam("isGamer") Boolean isGamer){
        LOG.infof("Buscando notebooks para gamer: %b", isGamer);
        return Response.ok(notebookService.findByGamer(isGamer)).build();
    }

    @GET
    @Path("/search/recurso/{recurso}")
    public Response findByRecurso(@PathParam("recurso") String recurso){
        LOG.infof("Buscando notebooks com recurso %s", recurso);
        return Response.ok(notebookService.findByRecurso(recurso)).build();
    }

    @GET
    @Path("/search/placa_video/{placa}")
    public Response findByPlacaVideo(@PathParam("placa") String placaVideo){
        LOG.infof("Buscando notebooks com placa de vídeo %s", placaVideo);
        return Response.ok(notebookService.findByPlacaVideo(placaVideo)).build();
    }

    @GET
    @Path("/search/processador/{processador}")
    public Response findByProcessador(@PathParam("processador") String processador){
        LOG.infof("Buscando notebooks com processador %s", processador);
        return Response.ok(notebookService.findByProcessador(processador)).build();
    }

    @GET
    @Path("/search/nome_armazenamento/{nome}")
    public Response findByNomeArmazenamento(@PathParam("nome") String nome){
        LOG.infof("Buscando notebooks com nome de armazenamento %s", nome);
        return Response.ok(notebookService.findByNomeArmazenamento(nome)).build();
    }

    @GET
    @Path("/search/capacidade_armazenamento/{capacidade}")
    public Response findByCapacidadeArmazenamento(@PathParam("capacidade") String capacidade){
        LOG.infof("Buscando notebooks com capacidade de armazenamento %s", capacidade);
        return Response.ok(notebookService.findByCapacidadeArmazenamento(capacidade)).build();
    }

    @GET
    @Path("/search/capacidade_memoria_ram/{capacidade}")
    public Response findByCapacidadeMemoriaRam(@PathParam("capacidade") String capacidade){
        LOG.infof("Buscando notebooks com capacidade de memória RAM %s", capacidade);
        return Response.ok(notebookService.findByCapacidadeMemoriaRam(capacidade)).build();
    }

    @GET
    @Path("/search/taxa_atualizacao/{taxa}")
    public Response findByTaxaAtualizacao(@PathParam("taxa") String taxa){
        LOG.infof("Buscando notebooks com taxa de atualização %s", taxa);
        return Response.ok(notebookService.findByTaxaAtualizacao(taxa)).build();
    }
    
    @GET
    @Path("/search/conexao_sem_fio/{conexao}")
    public Response findByConexaoSemFio(@PathParam("conexao") String conexao){
        LOG.infof("Buscando notebooks com conexão sem fio %s", conexao);
        return Response.ok(notebookService.findByConexaoSemFio(conexao)).build();
    }

    @GET
    @Path("/search/entrada_saida/{entradaSaida}")
    public Response findByEntradaSaida(@PathParam("entradaSaida") String entradaSaida){
        LOG.infof("Buscando notebooks com entrada/saida %s", entradaSaida);
        return Response.ok(notebookService.findByEntradaSaida(entradaSaida)).build();
    }

    @PATCH
    @Path("/{id}/image/upload")
    @RolesAllowed({"Funcionario"})
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response upload(@PathParam("id") Long id, @MultipartForm ImageForm form){
        LOG.infof("Upload de imagem para notebook com id %d", id);
        fileService.upload(id, form.getNomeImagem(), form.getImagem());

        return Response.status(Status.NO_CONTENT).build();
    }

    @GET
    @Path("/image/download/{nomeImagem}")
    @RolesAllowed({"Funcionario"})
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response download(@PathParam("nomeImagem") String nomeImagem){
        LOG.infof("Download da imagem %s", nomeImagem);

        ResponseBuilder response = Response.ok(fileService.download(nomeImagem));
        response.header("Content-Disposition", "attachement: filename=" + nomeImagem);
        
        return response.build();
    }
}