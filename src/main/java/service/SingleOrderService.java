package service;

import database.Client;
import database.Dish;
import database.SingleOrder;
import dto.WSDish;
import dto.WSSingleOrder;
import exceptions.ApplicationException;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Michal on 10.04.2019
 */
@ApplicationScoped
public class SingleOrderService {

    @PersistenceContext
    private EntityManager entityManager;

    @Inject
    private DishService dishService;

    public void createOrder(WSSingleOrder singleOrder) {

        List<Dish> dishList = new ArrayList<>();
        singleOrder.getDishList().stream().forEach(e -> {
            try {
                Dish dish = entityManager.createQuery(
                        "SELECT d FROM Dish d WHERE d.dishName = :dishName", Dish.class)
                        .setParameter("dishName", e.getName())
                        .getSingleResult();

                dishList.add(dish);
            } catch (NoResultException exc) {
                exc.printStackTrace();
            }
        });
        SingleOrder newSingleOrder = new SingleOrder();
        Client client = new Client(singleOrder.getClientFirstName(),
                singleOrder.getClientLastName(),
                singleOrder.getTableNumber());

        newSingleOrder.setOrderedDishes(dishList);
        newSingleOrder.setClient(client);
        newSingleOrder.setTableNumber(singleOrder.getTableNumber());
        entityManager.persist(newSingleOrder);
    }

    public SingleOrder getClientSingleOrder(String firstName, String secondName, Long tableNumber) throws ApplicationException {
        try {
            SingleOrder order = entityManager.createQuery(
                    "SELECT d FROM SingleOrder d WHERE d.tableNumber = :tableNumber AND " +
                            "d.client.firstName = :firstName AND d.client.secondName = :secondName", SingleOrder.class)
                    .setParameter("tableNumber", tableNumber)
                    .setParameter("firstName", firstName)
                    .setParameter("secondName", secondName)
                    .getSingleResult();
            for(Dish dish : order.getOrderedDishes()){
                dish.getIngredients().size();
            }
            return order;
        } catch (NoResultException exc) {
            exc.printStackTrace();
            throw new ApplicationException("There is no such order!");
        }
    }

    public void removeDishFromOrder(Long singleOrderId, Long dishId) throws ApplicationException {
        SingleOrder singleOrder;
        try{
            singleOrder = entityManager.createQuery("SELECT s FROM SingleOrder s WHERE s.id = :singleOrderId", SingleOrder.class)
                    .setParameter("singleOrderId", singleOrderId)
                    .getSingleResult();
        }catch(NonUniqueResultException exc) {
            exc.printStackTrace();
            throw new ApplicationException("There is no such order with id: " + singleOrderId);
        }

        for(Dish dish : singleOrder.getOrderedDishes()){
            if(dish.getId().equals(dishId)){
                singleOrder.removeDish(dish);
                break;
            }
        }
        entityManager.merge(singleOrder);
    }

    public void modifyClientSingleOrder(WSSingleOrder wsSingleOrder) throws ApplicationException {
        SingleOrder singleOrder;
        try{
            singleOrder = entityManager.createQuery("SELECT s FROM SingleOrder s WHERE s.id = :singleOrderId", SingleOrder.class)
                    .setParameter("singleOrderId", wsSingleOrder.getId())
                    .getSingleResult();
        } catch(NoResultException exc){
            exc.printStackTrace();
            throw new ApplicationException("There is no such order with id: " + wsSingleOrder.getId());
        } catch(NonUniqueResultException exc ){
            throw new ApplicationException("Multiple results found for the same id " + wsSingleOrder.getId() + ". Internal Error");
        }

        singleOrder.removeAllDishesFromOrder();
        for(WSDish wsDish : wsSingleOrder.getDishList()){
            Dish dish = dishService.getDishByName(wsDish.getName());
            singleOrder.getOrderedDishes().add(dish);
        }
        entityManager.merge(singleOrder);
    }

}
