package xyz.tomd.pommie;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import xyz.tomd.pommie.processors.BasicPomExplorer;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/pom")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.TEXT_PLAIN)
public class PomResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(PomResource.class);

    @Inject
    BasicPomExplorer basicPomExplorer;

    @POST
    @Path("/generate")
    public PomResponse generate(String body,
                             @HeaderParam("coords") String coords) throws Exception {
        LOGGER.info("Received request");

        PomResponse response = new PomResponse();
        response.setResult(basicPomExplorer.getDependencyTree(coords));

        return response;
    }
}
