package resources;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import database.Dish;
import database.Ingredient;
import ejb.interfaces.DishManagerBeanLocal;
import exceptions.ApplicationException;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
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

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getDish/{name}")
    public Response getDishById(@PathParam("name") String name){
        try {
           Dish dish = testManagerBean.getDishByName(name);
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
