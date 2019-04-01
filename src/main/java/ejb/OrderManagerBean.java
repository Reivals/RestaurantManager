package ejb;

import ejb.interfaces.OrderManagerBeanLocal;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author Michal on 26.03.2019
 */

@Stateless
@TransactionAttribute
public class OrderManagerBean implements OrderManagerBeanLocal {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void createOrder() {

    }

    @Override
    public void removeOrder() {

    }
}
