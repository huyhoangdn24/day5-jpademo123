package repository;

import entity.CategoryEntity;
import entity.Order;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public  interface   OrderRepository extends CrudRepository<Order, Integer> {
    Optional<Order> findById(Long id);
    List<Order> findAllOrders();
    List<Order> findAllCurrentMonth(int currentMonth, int currentYear);
    List<Order>findOrdersByTotalAmountGreaterThan(Double price);
    List<Order>findAllByOrderDetailWithProduct(String name);

}
