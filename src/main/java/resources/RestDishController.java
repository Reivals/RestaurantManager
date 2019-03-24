package resources;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import database.Dish;
import database.Ingredient;
import dto.DishRequest;
import ejb.interfaces.DishManagerBeanLocal;
import exceptions.ApplicationException;
import exceptions.ErrorMessage;
import org.apache.commons.lang3.StringUtils;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collections;
import java.util.List;

/**
 * @author Michal on 21.03.2019
 */

@Path("/api")
public class RestDishController {
    @EJB
    private DishManagerBeanLocal testManagerBean;

    private ObjectMapper objectMapper = new ObjectMapper();

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/getDish")
    public Response getDishById(DishRequest dishRequest){
        if(dishRequest == null || StringUtils.isBlank(dishRequest.getName())){
            return Response.status(400).entity(new ErrorMessage("Invalid parameters passed")).build();
        }
        try {
           Dish dish = testManagerBean.getDishByName(dishRequest.getName());
           return Response.ok(objectMapper.writeValueAsString(dish)).build();
        } catch (ApplicationException e) {
            return Response.status(204).build();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return Response.status(500).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getDishByOrder/{id}")
    public Response getDishByOrder(Long orderId){
        try {
            List<Dish> dish = testManagerBean.getDishesByOrder(orderId);
            return Response.ok(objectMapper.writeValueAsString(dish)).build();
        } catch (ApplicationException e) {
            return Response.status(204).build();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return Response.status(500).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getDishByIngredients/{dishName}")
    public Response getDishByIngredients(@PathParam("dishName") String dishName) throws JsonProcessingException {
        try {
            List<Ingredient> dish = testManagerBean.getDishIngredients(dishName);
            return Response.ok(objectMapper.writeValueAsString(dish)).build();
        } catch (ApplicationException e) {
            return Response.ok(objectMapper.writeValueAsString(Collections.emptyList())).build();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return Response.status(500).build();
        }
    }

}
