package resources;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import database.Ingredient;
import dto.IngredientType;
import ejb.TestManagerBean;
import ejb.interfaces.TestManagerBeanLocal;
import jdk.nashorn.internal.objects.annotations.Getter;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Michal on 21.03.2019
 */

@Path("/api")
public class RestController {
    @EJB
    private TestManagerBeanLocal testManagerBean;

    private ObjectMapper objectMapper = new ObjectMapper();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getIngredientType")
    public Response sayPlainTextHello() throws JsonProcessingException {
        //testManagerBean.doNothing();
        //TODO FIX MOCK
        List<IngredientType> list = new ArrayList<>();
        for(int i=0;i<10;i++){
            list.add(new IngredientType("Owoce " + i));
        }


        return Response.ok(objectMapper.writeValueAsString(list)).build();
    }


}
