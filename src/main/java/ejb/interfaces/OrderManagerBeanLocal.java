package ejb.interfaces;

import javax.ejb.Local;


@Local
public interface OrderManagerBeanLocal {

    void createOrder();
    void removeOrder();

}
