package br.notelab.validation;

import org.hibernate.exception.ConstraintViolationException;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
@ApplicationScoped
public class ConstraintViolationExceptionMapper implements ExceptionMapper<ConstraintViolationException> {

    @Override
    public Response toResponse(ConstraintViolationException exception) {

        ConstraintViolationError violationError = new ConstraintViolationError("400", "Violação na integridade do campo");
        String excecao = exception.getErrorMessage();
        
        int startIndexError = excecao.indexOf("ERRO");
        int endIndexError = excecao.indexOf("\n", startIndexError);
        String message = excecao.substring(startIndexError + 6, endIndexError);

        int startIndexDetail = excecao.indexOf("Detalhe");
        int endIndexDetail = excecao.indexOf(".]", startIndexDetail);
        String detail = excecao.substring(startIndexDetail + 9, endIndexDetail);

        violationError.setError(message);
        violationError.setDetail(detail);

        return Response.status(Response.Status.BAD_REQUEST).entity(violationError).build();
    }
}