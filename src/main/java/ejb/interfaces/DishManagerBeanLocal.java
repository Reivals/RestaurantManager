package ejb.interfaces;

import database.Dish;
import database.Ingredient;
import exceptions.ApplicationException;

import javax.ejb.Local;
import java.util.List;

/**
 * @author Michal on 23.03.2019
 */
@Local
public interface DishManagerBeanLocal {

    Dish getDishByName(String dishId) throws ApplicationException;
    List<Dish> getDishesByOrder(Long orderId) throws ApplicationException;
    List<Ingredient> getDishIngredients(String dishName) throws ApplicationException;

}
