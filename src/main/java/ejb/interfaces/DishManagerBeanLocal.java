package ejb.interfaces;

import database.Client;
import database.Dish;

import javax.ejb.Local;
import java.util.List;

/**
 * @author Michal on 23.03.2019
 */
@Local
public interface DishManagerBeanLocal {

    List<Client> getAllDishes(Long clientId);
    Client getDishById(Long dishId);
    List<Dish> getDishesByOrder(Long orderId);

}
