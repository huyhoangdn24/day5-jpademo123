package service;

import entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.OrderRepository;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    @Autowired
    static OrderRepository orderRepository;
    public static List<Order> findAllByOrderDate_Month(Integer month) {
        return orderRepository.findAllByOrderDate_Month(month);
    }

    public static List<Order> findAllByOrderDetailProductName(String name) {
       return orderRepository.findAllByOrderDetailProductName(name);
    }
    public static Optional<Order> findAllById(Long id){
        return orderRepository.findById(id);
    }
    public static List<Order> findAllOrders(){
        return orderRepository.findAllOrders();
    }

    public static List<Order> findAll() {
        return (List<Order>) orderRepository.findAll();
    }
}
