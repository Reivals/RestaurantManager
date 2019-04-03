package ejb.interfaces;

import dto.WSSingleOrder;

import javax.ejb.Local;


@Local
public interface OrderManagerBeanLocal {

    void createOrder(WSSingleOrder singleOrder);
    void removeOrder();

}
