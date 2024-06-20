package br.notelab.validation;

import java.io.IOException;

import org.jboss.logging.Logger;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.ext.Provider;

@Provider   
public class CorsFilterRequest implements ContainerRequestFilter {

  private static final Logger LOG = Logger.getLogger(CorsFilter.class);

  @Context
  private HttpServletRequest request;

  @Override
  public void filter(ContainerRequestContext requestContext) throws IOException {
    LOG.infov("REQUISICAO {0} {1} do IP {2}", requestContext.getMethod(), requestContext.getUriInfo().getPath(), request.getRemoteAddr());
  }
}