package ejb.interfaces;

import database.SingleOrder;
import dto.WSSingleOrder;

import javax.ejb.Local;


@Local
public interface OrderManagerBeanLocal {

    void createOrder(WSSingleOrder singleOrder);
    void removeOrder();
    SingleOrder getClientSingleOrder(String firstName, String secondName, Long tableNumber);

}
