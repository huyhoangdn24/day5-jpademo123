package entity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Entity
@Table (name ="order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "orderDate")
    private Date orderDate;
    @Column(name ="customerName")
    private String customerName;
    @Column(name = "customerAddress")
    private String customerAddress;
    @OneToMany(mappedBy = "order" , fetch = FetchType.EAGER)
    List<OrderDetails> orderDetail;

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", orderDate=" + orderDate +
                ", customerName='" + customerName + '\'' +
                ", customerAddress='" + customerAddress + '\'' +
                '}';
    }
    public int getId() {

        return id;
    }

    public void setId(int id) {

        this.id = id;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date oderDate) {

        this.orderDate = oderDate;
    }

    public String getCustomerName() {

        return customerName;
    }

    public void setCustomerName(String customerName) {

        this.customerName = customerName;
    }

    public String getCustomerAddress() {

        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {

        this.customerAddress = customerAddress;
    }


    public List<OrderDetails> getOrderDetail() {
        return orderDetail;
    }

    public void setOrderDetail(List<OrderDetails> orderDetail) {
        this.orderDetail = orderDetail;
    }
}
