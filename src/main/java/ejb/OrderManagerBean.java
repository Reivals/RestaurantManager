package ejb;

import database.Client;
import database.Dish;
import database.SingleOrder;
import dto.WSSingleOrder;
import ejb.interfaces.OrderManagerBeanLocal;
import exceptions.ApplicationException;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Michal on 26.03.2019
 */

@Stateless
@TransactionAttribute
public class OrderManagerBean implements OrderManagerBeanLocal {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void createOrder(WSSingleOrder singleOrder) {
        SingleOrder newSingleOrder = new SingleOrder();
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
        Client client = new Client();
        client.setFirstName(singleOrder.getClientFirstName());
        client.setSecondName(singleOrder.getClientLastName());
        client.setSingleOrder(newSingleOrder);
        client.setTableNumber(singleOrder.getTableNumber());
        newSingleOrder.setClient(client);
        newSingleOrder.setOrderedDishes(dishList);
        newSingleOrder.setTableNumber(singleOrder.getTableNumber());
        entityManager.persist(client);
        entityManager.persist(newSingleOrder);

    }

    @Override
    public void removeOrder() {

    }

    public SingleOrder getClientSingleOrder(String firstName, String secondName, Long tableNumber){
        try {
             SingleOrder order = entityManager.createQuery(
                    "SELECT d FROM SingleOrder d WHERE d.tableNumber = :tableNumber AND " +
                            "d.client.firstName = :firstName AND d.client.secondName = :secondName", SingleOrder.class)
                    .setParameter("tableNumber", tableNumber)
                    .setParameter("firstName", firstName)
                    .setParameter("secondName", secondName)
                    .getSingleResult();
            order.getClient();

            order.getOrderedDishes().size();
            return order;
        } catch (NoResultException exc) {
            exc.printStackTrace();
            return null;
        }
    }
}
