package ejb.interfaces;

import database.SingleOrder;
import dto.WSSingleOrder;
import exceptions.ApplicationException;

import javax.ejb.Local;


@Local
public interface OrderManagerBeanLocal {

    void createOrder(WSSingleOrder singleOrder) throws ApplicationException;
    SingleOrder getClientSingleOrder(String firstName, String secondName, Long tableNumber) throws ApplicationException;
    void removeDishFromOrder(Long singleOrderId, Long dishId) throws ApplicationException;
    void modifyClientSingleOrder(WSSingleOrder singleOrder) throws ApplicationException;
}
