package resources;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import database.Dish;
import dto.WSDish;
import ejb.interfaces.DishManagerBeanLocal;
import exceptions.ApplicationException;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

/**
 * @author Michal on 07.04.2019
 */
@Path("/dictionaryApi")
public class RestDictionaryController {

    @EJB
    private DishManagerBeanLocal dishManagerBeanLocal;

    private ObjectMapper objectMapper = new ObjectMapper();

    @POST
    @Path("/createDish")
    public Response createNewDish(WSDish wsDish){
        try {
           Dish dish = dishManagerBeanLocal.createDish(wsDish);
           return Response.ok(objectMapper.writeValueAsString(new WSDish().fillProperties(dish))).build();
        } catch (ApplicationException | JsonProcessingException e) {
            e.printStackTrace();
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

    }

    @DELETE
    @Path("/deleteDish/{dishId}")
    public Response deleteDish(@PathParam("dishId") Long dishId){
        try {
            dishManagerBeanLocal.removeDish(dishId);
        } catch (ApplicationException e) {
            e.printStackTrace();
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.ok().build();
    }

    @PUT
    @Path("/modifyDish")
    public Response modifyDish(WSDish wsDish){
        try {
            Dish dish = dishManagerBeanLocal.modifyDish(wsDish);
            return Response.ok(objectMapper.writeValueAsString(new WSDish().fillProperties(dish))).build();
        } catch (ApplicationException | JsonProcessingException e) {
            e.printStackTrace();
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

}
