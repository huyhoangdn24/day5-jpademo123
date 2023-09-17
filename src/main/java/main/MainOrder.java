package main;

import configuration.JPAConfig;
import entity.Order;
import entity.OrderDetails;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.domain.Sort;
import repository.BookRepository;
import repository.CategoryRepository;
import repository.OrderDetailsRepository;
import repository.OrderRepository;
import service.OrderDetailsService;
import service.OrderService;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;


import java.util.Optional;
import java.util.Scanner;

public class MainOrder {
    static ApplicationContext context = new AnnotationConfigApplicationContext(JPAConfig.class);
    static OrderService orderService =context .getBean(OrderService.class);
    static OrderDetailsService orderDetailsService = (OrderDetailsService) context.getBean("OrderDetailsService");


    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        while (true) {
            System.out.println("1. Insert Order");
            System.out.println("2. List All Orders");
            System.out.println("3. Get an order and orderDetails by order id");
            System.out.println("4.List all the orders in the current month");
            System.out.println("5. List Orders with Total Amount > 1000");
            System.out.println("6. List Orders with Java Book");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    insertorder();
                    break;

                case 2:
                    findAllOrders();
                    break;
                case 3:
                    System.out.println("Enter id of order That you want to find!");
                    Long id=sc.nextLong();
                    sc.nextLine();
                    findAllById( id);
                    break;
                case 4:
                    findAllCurrentMonth();
                    break;
                case 5:
                    findOrdersByTotalAmountGreaterThan(1000.0);
                    break;
                case 6 :
                    findAllByOrderDetailWithProduct("Java Book");
                    break;
                case 7:
                    System.out.println("Exiting...");
                    sc.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
    public static  void  insertorder(){
        Order order = new Order();
        order.setOrderDate(new Date());
        order.setCustomerName("Customer 1");
        order.setCustomerAddress("Address 1");
        orderService.save(order);
        // oder deatails
        OrderDetails details = new OrderDetails();
        details.setProductName("Product A");
        details.setQuantity(5);
        details.setUnitPrice(10.0);
        orderDetailsService.save(details);
    }

    public static void findAllOrders() {
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
    public static void  findAllById(Long id){
        Optional<Order> orders = orderService.findById(id);
        if (orders.isPresent()) {
            Order order = orders.get();
            System.out.println(order);
            order.getOrderDetail().forEach(System.out::println);
        } else {
            System.out.println("Not found any order with id is " + id);
        }
    }
    public static void findAllCurrentMonth(){
        LocalDate currentDate = LocalDate.now();
        int currentMonth = currentDate.getMonthValue();
        int currentYear = currentDate.getYear();
        List<Order> currentMonthOrders = orderService.findAllCurrentMonth(currentMonth, currentYear);
        if (!currentMonthOrders.isEmpty()) {
            for (Order order : currentMonthOrders) {
                System.out.println("Order: " + order);
                List<OrderDetails> orderDetails = order.getOrderDetail();
                for (OrderDetails detail : orderDetails) {
                    System.out.println("Detail: " + detail);
                }
            }
        } else {
            System.out.println("No orders found for the current month.");
        }
    }
    public static void  findOrdersByTotalAmountGreaterThan(Double price){
        List<Order> ordersWithTotalAmountGreaterThan = orderService.findOrdersByTotalAmountGreaterThan(price);

        if (ordersWithTotalAmountGreaterThan.isEmpty()) {
            System.out.println("No orders with a greater total value were found " + price + " USD.");
        } else {
            System.out.println("List of orders with a larger total value " + price + " USD:");
            for (Order order : ordersWithTotalAmountGreaterThan) {
                System.out.println("Order: " + order);
            }
        }

    }
    public static void  findAllByOrderDetailWithProduct( String name ){
        List<Order> orders=orderService.findAllByOrderDetailWithProduct(name);
        if(!orders.isEmpty()){
            System.out.println(String.format("List all orders buy %s book", name));
            System.out.println(orders);
        } else {
            System.out.println(String.format("Not found list all orders buy %s book", name));
        }
    }


}






