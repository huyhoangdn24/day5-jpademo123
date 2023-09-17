package service;

import entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.JpaTransactionManager;
import repository.OrderRepository;

import java.util.List;
import java.util.Optional;


public class OrderService {
    @Autowired
    JpaTransactionManager jpaTransactionManager;

    @Autowired
    static OrderRepository orders;
    public static Order insertorder(Order order){
        return orders.save(order);
    }


    public void save(Order orderService) {
    }

    public Optional<Order> findById(Long id){

        return orders.findById(id);
    }
    public List<Order> findAllOrders() {
        return orders.findAllOrders();
    }
    public List<Order> findAllCurrentMonth(int currentMonth, int currentYear) {
        return orders.findAllCurrentMonth(currentMonth,currentYear);
    }

    public List<Order> findAllByOrderDetailWithProduct(String name) {
        return orders.findAllByOrderDetailWithProduct(name);
    }

    public List<Order> findOrdersByTotalAmountGreaterThan(Double price) {
        return orders.findOrdersByTotalAmountGreaterThan(price);
    }
}
