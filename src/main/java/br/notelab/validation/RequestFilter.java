package br.notelab.validation;

import java.io.IOException;

import org.jboss.logging.Logger;
import org.jboss.resteasy.spi.HttpRequest;

import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.ext.Provider;

@Provider   
public class RequestFilter implements ContainerRequestFilter {

  private static final Logger LOG = Logger.getLogger(RequestFilter.class);

  @Context
  private HttpRequest request;

  @Override
  public void filter(ContainerRequestContext requestContext) throws IOException {
    LOG.infov("REQUISICAO {0} {1} do IP {2}", requestContext.getMethod(), requestContext.getUriInfo().getPath(), request.getRemoteAddress());
  }
}