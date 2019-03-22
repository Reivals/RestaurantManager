package ejb;

import ejb.interfaces.TestManagerBeanLocal;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author Michal on 21.03.2019
 */

@Stateless
@TransactionAttribute
public class TestManagerBean implements TestManagerBeanLocal {

    @PersistenceContext
    public EntityManager em;

    @Override
    public void doNothing() {
        System.out.println("xDDDDDDDDDDDDDDDDDDd");
        return;
    }
}
