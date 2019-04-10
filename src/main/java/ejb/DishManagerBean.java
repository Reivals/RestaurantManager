package ejb;

import database.Dish;
import database.Ingredient;
import dto.WSDish;
import dto.WSIngredient;
import ejb.interfaces.DishManagerBeanLocal;
import exceptions.ApplicationException;
import org.apache.commons.lang3.StringUtils;
import service.DishService;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.Collections;
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
    public Dish getDishByName(String dishName) throws ApplicationException {
        if (StringUtils.isBlank(dishName)) {
            throw new ApplicationException("Invalid dishId");
        }
        return dishService.getDishByName(dishName);
    }

    @Override
    public List<Dish> getDishesByOrder(Long orderId) throws ApplicationException {
        if (orderId == null) {
            throw new ApplicationException("Invalid orderId");
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
        if (!wsDish.validateWSDish()) {
            throw new ApplicationException("Incorrect data passed!");
        }
        try {
            if(getDishByName(wsDish.getName())!=null){
                throw new ApplicationException("There is already such meal!");
            }
        } catch (ApplicationException ex) {
            if (!"Dish not found".equals(ex.getMessage()))
                throw ex;
        }
        return dishService.createDish(wsDish);
    }

    @Override
    public Dish removeDish(Long dishId) {
        //TODO
        return null;
    }

    @Override
    public Dish modifyDish(WSDish wsDish) {
        //TODO
        return null;
    }



}
