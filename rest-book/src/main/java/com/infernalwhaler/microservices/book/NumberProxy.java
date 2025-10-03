package com.infernalwhaler.microservices.book;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

/**
 * @author Sdeseure
 * @project rest-book
 * @date 3/10/2025
 */

@RegisterRestClient(configKey = "number.proxy")
@Path("/api/numbers")
public interface NumberProxy {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public IsbnThirteen generateIsbnNumbers();
}
