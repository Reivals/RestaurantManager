package ejb.interfaces;

import database.Dish;
import database.Ingredient;
import dto.WSDish;
import exceptions.ApplicationException;
import exceptions.ValidationException;
import org.glassfish.jersey.jaxb.internal.XmlJaxbElementProvider;

import javax.ejb.Local;
import java.util.List;

/**
 * @author Michal on 23.03.2019
 */
@Local
public interface DishManagerBeanLocal {

    Dish getDishByName(String dishId) throws ValidationException, ApplicationException;
    List<Dish> getDishesByOrder(Long orderId) throws ValidationException;
    List<Ingredient> getDishIngredients(String dishName) throws ApplicationException;
    List<Dish> getAllDishes();
    Dish createDish(WSDish wsDish) throws ApplicationException;
    void removeDish(Long dishId) throws ApplicationException;
    Dish modifyDish(WSDish wsDish) throws ApplicationException;
}
