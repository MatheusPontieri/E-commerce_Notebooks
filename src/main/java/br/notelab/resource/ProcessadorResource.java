package br.notelab.resource;

import java.util.List;

import br.notelab.dto.ProcessadorDTO;
import br.notelab.dto.ProcessadorResponseDTO;
import br.notelab.model.Processador;
import br.notelab.repository.ProcessadorRepository;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/processadores")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ProcessadorResource {
    @Inject
    public ProcessadorRepository processadorRepository;

    @GET
    @Path("/{id}")
    public ProcessadorResponseDTO findById(@PathParam("id") Long id){
        return ProcessadorResponseDTO.valueof(processadorRepository.findById(id));
    }

    @GET
    public List<ProcessadorResponseDTO> findAll(){
        return processadorRepository
        .listAll()
        .stream()
        .map(p -> ProcessadorResponseDTO.valueof(p))
        .toList();
    }

    @GET 
    @Path("/search/modelo/{modelo}")
    public List<ProcessadorResponseDTO> findByModelo(@PathParam("modelo") String modelo){
        return processadorRepository
        .findByModelo(modelo)
        .stream()
        .map(p -> ProcessadorResponseDTO.valueof(p))
        .toList();
    }

    @POST
    @Transactional
    public ProcessadorResponseDTO create(ProcessadorDTO dto){
        Processador p = new Processador();

        p.setModelo(dto.modelo());
        p.setGeracao(dto.geracao());
        p.setVelocidade(dto.velocidade());
        p.setNucleos(dto.nucleos());
        p.setThreads(dto.threads());
        p.setMemoriaCache(dto.memoriaCache());

        processadorRepository.persist(p);
        return ProcessadorResponseDTO.valueof(p);
    }

    @PUT
    @Transactional
    @Path("/{id}")
    public void update(@PathParam("id") Long id, ProcessadorDTO dto){
        Processador p = processadorRepository.findById(id);

        p.setModelo(dto.modelo());
        p.setGeracao(dto.geracao());
        p.setVelocidade(dto.velocidade());
        p.setNucleos(dto.nucleos());
        p.setThreads(dto.threads());
        p.setMemoriaCache(dto.memoriaCache());

        processadorRepository.persist(p);
    }

    @DELETE
    @Transactional
    @Path("/{id}")
    public void delete(@PathParam("id") Long id){
        processadorRepository.deleteById(id);
    }
}