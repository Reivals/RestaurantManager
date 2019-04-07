package ejb.interfaces;

import database.Dish;
import database.Ingredient;
import dto.WSDish;
import exceptions.ApplicationException;
import org.glassfish.jersey.jaxb.internal.XmlJaxbElementProvider;

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
    List<Dish> getAllDishes();
    Dish createDish(WSDish wsDish) throws ApplicationException;
    Dish removeDish(Long dishId) throws ApplicationException;
    Dish modifyDish(WSDish wsDish) throws ApplicationException;
}
