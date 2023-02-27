package sg.edu.nus.iss.day29workshop.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.nus.iss.day29workshop.Repository.OrderRepo;
import sg.edu.nus.iss.day29workshop.model.Order;

@Service
public class OrderService {
    
    @Autowired
    OrderRepo orderRepo;

    public String addOrder(Order o) {
        String orderId = UUID.randomUUID().toString().substring(0, 8);
        o.setOrderId(orderId);
        orderRepo.insertOrder(o);
        return orderId;
    }


}