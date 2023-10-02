package main;

import configuration.JPAConfig;
import entity.Order;
import entity.OrderDetails;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import repository.OrderRepository;
import service.OrderService;

import java.time.LocalDate;
import java.util.*;

public class MainOrder {
    static ApplicationContext context = new AnnotationConfigApplicationContext(JPAConfig.class);
    static OrderRepository orderService =context .getBean(OrderRepository.class);



    public static void main(String[] args) {
//        insertorder();
        //        createNewOrdersEntry();
//        findAll();

        findAllWithCurrentMonth();
//        findAllByProductName("huy hoang");
//        findAllByPriceGreaterThan(1000);
    }
    private static  void  insertorder(){
        Order order = new Order();
        order.setOrderDate(new Date());
        order.setCustomerName("Customer 1");
        order.setCustomerAddress("Address 1");
        orderService.save(order);

    }

    private static void findAllOrders() {
        List<Order> orders = orderService.findAllOrders();
        if (!orders.isEmpty()) {
            for (Order order : orders) {
                System.out.println("Order: " + order);
                List<OrderDetails> orderDetails = order.getOrderDetail();
                for (OrderDetails detail : orderDetails) {
                    System.out.println("Detail: " + detail);
                }
            }
        } else {
            System.out.println("Order not found");
        }
    }
    private static void  findAllById(Long id){
        Optional<Order> orders = orderService.findById(id);
        if (orders.isPresent()) {
            Order order = orders.get();
            System.out.println(order);
            order.getOrderDetail().forEach(System.out::println);
        } else {
            System.out.println("Not found any order with id is " + id);
        }
    }
    private static void findAllWithCurrentMonth(){
        LocalDate currentDate = LocalDate.now();
        Integer month = currentDate.getMonthValue();
        List<Order> orders = OrderService.findAllByOrderDate_Month(month);
        if(!orders.isEmpty()){
            System.out.println("List all the orders in the current month "+month);
            for(Order order:orders){
                System.out.println(order);
                order.getOrderDetail();
            }
        } else {
            System.out.println("Not found order in the current month "+month);
        }
    }
    private static void  findOrdersByTotalAmountGreaterThan(Double price){
        List<Order> ordersEntityList = OrderService.findAll();
        Map<Order,Double> map=new HashMap<>();
        for (Order order: ordersEntityList){
            List<OrderDetails> orderDetails = (List<OrderDetails>) order.getOrderDetail();
            double total= orderDetails.stream().mapToDouble(orderDetail->orderDetail.getUnitPrice()*orderDetail.getQuantity()).sum();
            if(total>price) map.put(order,total);
        }
        if(!map.isEmpty()){
            System.out.println("List all orders which have total amount more than 1,000USD");
            map.forEach((order,total)->
                    System.out.println(order + " with total "+ total));
        } else {
            System.out.println("Not found list all orders which have total amount more than 1,000USD");
        }
    }
    public static void findAllByProductName(String name){
        List<Order> orders=OrderService.findAllByOrderDetailProductName(name);
        if(!orders.isEmpty()){
            System.out.println(String.format("List all orders buy %s book", name));
            System.out.println(orders);
        } else {
            System.out.println(String.format("Not found list all orders buy %s book", name));
        }
    }


}






