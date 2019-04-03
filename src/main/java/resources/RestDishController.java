package resources;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import database.Dish;
import dto.WSDish;
import dto.WSSingleOrder;
import ejb.interfaces.DishManagerBeanLocal;
import ejb.interfaces.OrderManagerBeanLocal;
import exceptions.ApplicationException;
import exceptions.ErrorMessage;
import org.apache.commons.lang3.StringUtils;

import javax.ejb.EJB;
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
        } catch (ApplicationException e) {
            return Response.status(204).build();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return Response.status(500).build();
        }
    }

    @GET
    @Path("/getDishesByOrder/{id}")
    public Response getDishByOrder(Long orderId){
        try {
            List<Dish> dish = dishManagerBean.getDishesByOrder(orderId);
            return Response.ok(objectMapper.writeValueAsString(dish)).build();
        } catch (ApplicationException e) {
            return Response.status(204).build();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return Response.status(500).build();
        }
    }

    @GET
    @Path("/getAllDishes")
    public Response getAllDishes(){
        try {
            List<Dish> dish = dishManagerBean.getAllDishes();
            List<WSDish> respone = new ArrayList<>();
            dish.stream().forEach( d -> respone.add(new WSDish().fillProperties(d)));
            return Response.ok(objectMapper.writeValueAsString(respone)).build();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return Response.status(500).build();
        }
    }

    @POST
    @Path("/createNewOrder")
    public Response createNewOrder(WSSingleOrder singleOrder){
        orderManagerBeanLocal.createOrder(singleOrder);
        return Response.ok().build();
    }

}
