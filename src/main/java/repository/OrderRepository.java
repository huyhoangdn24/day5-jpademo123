package repository;

import entity.Order;
import entity.OrderDetails;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public  interface   OrderRepository extends CrudRepository<Order, Integer> {
    <S extends Order> S save(S entity);
    void save(OrderDetails orderDetail);
    @Query("FROM Orders o WHERE FUNCTION('MONTH',o.orderDate) = :month")
    Optional<Order> findById(Long id);
    List<Order> findAllOrders();
    List<Order> findAllByOrderDate_Month(Integer month);

    List<Order> findAllByOrderDetailProductName(String name);

}
