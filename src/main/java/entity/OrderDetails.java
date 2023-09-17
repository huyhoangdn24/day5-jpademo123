package entity;

import javax.persistence.*;

@Entity
@Table(name ="order")
public class OrderDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @ManyToOne
    @JoinColumn(name = "orderId", nullable = false, referencedColumnName = "id")
    Order order;
    @Column (name = "oderId")
    private int  oderId;
    @Column (name = "productName")
    private String productName;
    @Column (name ="quantity")
    private int quantity;
    @Column  (name = "unitPrice")
    private double unitPrice;
    @Override
    public String toString() {
        return "OrderDetail{" +
                "id=" + id +
                ", productName='" + productName + '\'' +
                ", quantity=" + quantity +
                ", unitPrice=" + unitPrice +
                '}';
    }
    public int getId() {

        return id;
    }

    public void setId(int id) {

        this.id = id;
    }

    public int getOderId() {

        return oderId;
    }

    public void setOderId(int oderId) {

        this.oderId = oderId;
    }

    public String getProductName() {

        return productName;
    }

    public void setProductName(String productName) {

        this.productName = productName;
    }

    public int getQuantity() {

        return quantity;
    }

    public void setQuantity(int quantity) {

        this.quantity = quantity;
    }

    public double getUnitPrice() {

        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {

        this.unitPrice = unitPrice;
    }
}
