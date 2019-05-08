package ejb;

import database.Dish;
import database.Ingredient;
import dto.WSDish;
import ejb.interfaces.DishManagerBeanLocal;
import exceptions.ApplicationException;
import exceptions.ValidationException;
import org.apache.commons.lang3.StringUtils;
import service.DishService;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.inject.Inject;
import javax.persistence.NoResultException;
import java.util.List;

/**
 * @author Michal on 23.03.2019
 */

@Stateless
@TransactionAttribute
public class DishManagerBean implements DishManagerBeanLocal {

    @Inject
    private DishService dishService;

    @Override
    public Dish getDishByName(String dishName) throws ValidationException, ApplicationException {
        if (StringUtils.isBlank(dishName)) {
            throw new ValidationException("Invalid parameter: dishId");
        }
        return dishService.getDishByName(dishName);
    }

    @Override
    public List<Dish> getDishesByOrder(Long orderId) throws ValidationException {
        if (orderId == null) {
            throw new ValidationException("Invalid parameter: orderId");
        }
        return dishService.getDishesByOrder(orderId);
    }

    @Override
    public List<Ingredient> getDishIngredients(String dishName) throws ApplicationException {
        if (StringUtils.isBlank(dishName)) {
            throw new ApplicationException("Invalid dishId");
        }
        return dishService.getDishIngredients(dishName);
    }

    @Override
    public List<Dish> getAllDishes() {
        return dishService.getAllDishes();
    }

    @Override
    public Dish createDish(WSDish wsDish) throws ApplicationException {
        try{
            validateDish(wsDish);
        } catch(NoResultException e){
            return dishService.createDish(wsDish);
        }
        throw new ApplicationException("There is already meal with such name!");
    }

    @Override
    public void removeDish(Long dishId) throws ApplicationException {
        dishService.removeDish(dishId);
    }

    @Override
    public Dish modifyDish(WSDish wsDish) throws ApplicationException {
        validateDish(wsDish);
        return dishService.modifyDish(wsDish);
    }

    private void validateDish(WSDish wsDish) throws ApplicationException {
        if (!wsDish.validateWSDish()) {
            throw new ApplicationException("Incorrect data passed!");
        }
        try {
            Dish dish = getDishByName(wsDish.getName());
            if (dish != null && wsDish.getIngredients().size() == dish.getIngredients().size() &&
                    dish.getType() != wsDish.getType() && dish.getCostInZlotys() != wsDish.getCost()) {
                throw new ApplicationException("There is already such a meal!");
            }
        } catch (ApplicationException ex) {
            if (!"Invalid parameter: dishId".equals(ex.getMessage()))
                throw ex;
        } catch (ValidationException e) {
            return;
        }
    }

}
