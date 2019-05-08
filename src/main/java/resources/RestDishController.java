package resources;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import database.Dish;
import database.SingleOrder;
import dto.WSDish;
import dto.WSError;
import dto.WSSingleOrder;
import ejb.interfaces.DishManagerBeanLocal;
import ejb.interfaces.OrderManagerBeanLocal;
import exceptions.ApplicationException;
import exceptions.ErrorMessage;
import exceptions.ValidationException;
import org.apache.commons.lang3.StringUtils;

import javax.ejb.EJB;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Michal on 21.03.2019
 */

@Path("/api")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class RestDishController {
    @EJB
    private DishManagerBeanLocal dishManagerBean;

    @EJB
    private OrderManagerBeanLocal orderManagerBeanLocal;

    private ObjectMapper objectMapper = new ObjectMapper();

    @POST
    @Path("/getDishByName")
    public Response getDishByName(WSDish WSDish){
        if(WSDish == null || StringUtils.isBlank(WSDish.getName())){
            return Response.status(400).entity(new ErrorMessage("Invalid parameters passed")).build();
        }
        try {
           Dish dish = dishManagerBean.getDishByName(WSDish.getName());
           return Response.ok(objectMapper.writeValueAsString(new WSDish().fillProperties(dish))).build();
        } catch (NoResultException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } catch (ValidationException e){
            return Response.status(Response.Status.BAD_REQUEST).entity(WSError.create(e.getMessage())).build();
        } catch (JsonProcessingException | ApplicationException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GET
    @Path("/getDishesByOrder/{id}")
    public Response getDishByOrder(Long orderId){
        try {
            List<Dish> dish = dishManagerBean.getDishesByOrder(orderId);
            return Response.ok(objectMapper.writeValueAsString(dish)).build();
        } catch (ValidationException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(WSError.create(e.getMessage())).build();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(WSError.create("Couldn't serialize object. Fatal error")).build();
        }
    }

    @GET
    @Path("/getAllDishes")
    public Response getAllDishes(){
        try {
            List<Dish> dish = dishManagerBean.getAllDishes();
            List<WSDish> response = new ArrayList<>();
            dish.stream().forEach( d -> response.add(new WSDish().fillProperties(d)));
            return Response.ok(objectMapper.writeValueAsString(response)).build();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(WSError.create("Couldn't serialize object. Fatal error")).build();
        }
    }

    @POST
    @Path("/createNewOrder")
    public Response createNewOrder(WSSingleOrder singleOrder){
        try {
            orderManagerBeanLocal.createOrder(singleOrder);
        } catch (ApplicationException e) {
            e.printStackTrace();
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.ok().build();
    }

    @GET
    @Path("/getClientOrder/{clientName}/{clientSurname}/{tableNumber}")
    public Response getClientOrder(@PathParam("clientName") String clientName,
                                   @PathParam("clientSurname") String clientSurname,
                                   @PathParam("tableNumber") Long tableNumber){

        SingleOrder singleOrder;
        try {
            singleOrder = orderManagerBeanLocal.getClientSingleOrder(clientName, clientSurname, tableNumber);
        } catch (ApplicationException e) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        if(singleOrder != null){
            try {
                return Response.ok(objectMapper.writeValueAsString(new WSSingleOrder().fillProperties(singleOrder))).build();
            } catch (JsonProcessingException e) {
                e.printStackTrace();
                return Response.serverError().build();
            }
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }

    @DELETE
    @Path("/removeDishFromOrder/{singleOrderId}/{dishId}")
    public Response removeDishFromOrder(@PathParam("singleOrderId") Long singleOrderId,
                                        @PathParam("dishId") Long dishId){
        try {
            orderManagerBeanLocal.removeDishFromOrder(singleOrderId, dishId);
        } catch(NoResultException e){
            return Response.status(Response.Status.NO_CONTENT).entity(WSError.create(e.getMessage())).build();
        } catch(ApplicationException e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(WSError.create(e.getMessage())).build();
        }
        return Response.ok().build();
    }

    @PUT
    @Path("/modifySingleOrder")
    public Response modifySingleOrder(WSSingleOrder singleOrder){
        try {
            orderManagerBeanLocal.modifyClientSingleOrder(singleOrder);
        } catch (NonUniqueResultException e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(WSError.create(e.getMessage())).build();
        } catch (ApplicationException e) {
            e.printStackTrace();
            return Response.status(Response.Status.NO_CONTENT).entity(WSError.create(e.getMessage())).build();
        }
        return Response.ok().build();
    }

}
