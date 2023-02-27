package sg.edu.nus.iss.day29workshop.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.PayloadApplicationEvent;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import sg.edu.nus.iss.day29workshop.model.Order;
import sg.edu.nus.iss.day29workshop.service.OrderService;

@RestController
@RequestMapping(path={"", "index.html"}, produces= MediaType.APPLICATION_JSON_VALUE)
public class OrderController {

    @Autowired
    OrderService orderSvc;

    @PostMapping("/api/order")
    public ResponseEntity<Order> postOrder(@RequestBody String payload) throws IOException{
        System.out.println(payload);
        Order o = Order.createOrder(payload);
		String orderId = orderSvc.addOrder(o);
		System.out.printf(">>>> orderId: %s\n", orderId);

		return new ResponseEntity<>(o, HttpStatus.OK);
    }
}
