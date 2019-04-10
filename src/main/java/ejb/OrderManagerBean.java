package ejb;

import database.Client;
import database.Dish;
import database.SingleOrder;
import dto.WSDish;
import dto.WSSingleOrder;
import ejb.interfaces.DishManagerBeanLocal;
import ejb.interfaces.OrderManagerBeanLocal;
import exceptions.ApplicationException;
import javafx.application.Application;
import service.DishService;
import service.SingleOrderService;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Michal on 26.03.2019
 */

@Stateless
@TransactionAttribute
public class OrderManagerBean implements OrderManagerBeanLocal {

    @Inject
    private DishService dishService;

    @Inject
    private SingleOrderService singleOrderService;

    @Override
    public void createOrder(WSSingleOrder singleOrder) throws ApplicationException {
        singleOrder.validateOrder();
        singleOrderService.createOrder(singleOrder);
    }


    @Override
    public SingleOrder getClientSingleOrder(String firstName, String secondName, Long tableNumber) throws ApplicationException {
        return singleOrderService.getClientSingleOrder(firstName,secondName,tableNumber);
    }

    @Override
    public void removeDishFromOrder(Long singleOrderId, Long dishId) throws ApplicationException {
        singleOrderService.removeDishFromOrder(singleOrderId, dishId);
    }

    @Override
    public void modifyClientSingleOrder(WSSingleOrder wsSingleOrder) throws ApplicationException {
        singleOrderService.modifyClientSingleOrder(wsSingleOrder);
    }


}
