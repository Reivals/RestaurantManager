package service;

import database.Dish;
import database.Ingredient;
import dto.WSDish;
import exceptions.ApplicationException;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import java.util.Collections;
import java.util.List;

/**
 * @author Michal on 10.04.2019
 */
@ApplicationScoped
public class DishService {

    @PersistenceContext
    private EntityManager entityManager;

    public Dish getDishByName(String dishName) throws ApplicationException {
        Dish dish;
        try {
            dish = entityManager.createQuery(
                    "SELECT d FROM Dish d WHERE d.dishName = :dishName", Dish.class)
                    .setParameter("dishName", dishName)
                    .getSingleResult();
        } catch (NoResultException exc) {
            throw new ApplicationException("Dish not found");
        } catch (NonUniqueResultException exc){
            exc.printStackTrace();
            throw new ApplicationException("Database is inconsisten. There are multiple dishes with the same name : " + dishName);
        }
        return dish;

    }

    public List<Dish> getDishesByOrder(Long orderId) {
        try {
            return entityManager.createQuery(
                    "SELECT d FROM Dish d WHERE d.singleOrder = :orderId", Dish.class)
                    .setParameter("orderId", orderId)
                    .getResultList();
        } catch (NoResultException exc) {
            return Collections.emptyList();
        } catch (Exception exc){
            exc.printStackTrace();
            return Collections.emptyList();
        }
    }

    public List<Ingredient> getDishIngredients(String dishName) {
        try {
            Dish dish =  entityManager.createQuery(
                    "SELECT d FROM Dish d WHERE d.name = :dishName", Dish.class)
                    .setParameter("dishName", dishName)
                    .getSingleResult();
            if(dish.getIngredients() == null || dish.getIngredients().isEmpty()){
                return Collections.emptyList();
            } else {
                return dish.getIngredients();
            }
        } catch (Exception exc) {
            exc.printStackTrace();
            return Collections.emptyList();
        }
    }

    public List<Dish> getAllDishes() {
        try {
            List<Dish> dishes = entityManager.createQuery(
                    "SELECT d FROM Dish d", Dish.class)
                    .getResultList();
            for(Dish dish : dishes){
                dish.getIngredients().size();
            }
            return dishes;
        } catch (NoResultException exc) {
            return Collections.emptyList();
        } catch (Exception exc){
            exc.printStackTrace();
            return Collections.emptyList();
        }
    }

    public Dish createDish(WSDish wsDish) {
        Dish dish = wsDish.putProperties();
        entityManager.persist(dish);
        return dish;
    }
}
