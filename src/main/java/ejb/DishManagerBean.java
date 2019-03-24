package ejb;

import database.Dish;
import database.Ingredient;
import ejb.interfaces.DishManagerBeanLocal;
import exceptions.ApplicationException;
import org.apache.commons.lang3.StringUtils;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.Collections;
import java.util.List;

/**
 * @author Michal on 23.03.2019
 */

@Stateless
@TransactionAttribute
public class DishManagerBean implements DishManagerBeanLocal {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Dish getDishByName(String dishName) throws ApplicationException {
        Dish dish;
        if (dishName == null) {
            throw new ApplicationException("Invalid dishId");
        }
        try {
            dish = entityManager.createQuery(
                    "SELECT d FROM Dish d WHERE d.dishName = :dishName", Dish.class)
                    .setParameter("dishName", dishName)
                    .getSingleResult();
        } catch (NoResultException exc) {
            throw new ApplicationException("Dish not found");
        }
        dish.getIngredients().size();
        return dish;

    }

    @Override
    public List<Dish> getDishesByOrder(Long orderId) throws ApplicationException {
        List<Dish> listOfDishes;
        if (orderId == null) {
            throw new ApplicationException("Invalid orderId");
        }
        try {
            listOfDishes = entityManager.createQuery(
                    "SELECT d FROM Dish d WHERE d.singleOrder = :orderId", Dish.class)
                    .setParameter("orderId", orderId)
                    .getResultList();
        } catch (NoResultException exc) {
            return Collections.emptyList();
        }
        return listOfDishes;
    }

    public List<Ingredient> getDishIngredients(String dishName) throws ApplicationException {
        Dish dish;
        if (StringUtils.isBlank(dishName)) {
            throw new ApplicationException("Invalid dishId");
        }
        try {
            dish = entityManager.createQuery(
                    "SELECT d FROM Dish d WHERE d.name = :dishName", Dish.class)
                    .setParameter("dishName", dishName)
                    .getSingleResult();
        } catch (NoResultException exc) {
            throw new ApplicationException("No dish found");
        }
        dish.getIngredients().size();
        return dish.getIngredients();
    }

    public List<Dish> getAllDishes() {
        try {
            return entityManager.createQuery(
                    "SELECT d FROM Dish d", Dish.class)
                    .getResultList();
        } catch (NoResultException exc) {
            return Collections.emptyList();
        }
    }

}
