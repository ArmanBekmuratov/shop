package kz.arman.domain;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "ORders")
public class Order {
    @Id
    @GeneratedValue
    private long id;

    @OneToMany(mappedBy = "order")
    private List<ProductInOrder> productInOrders;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<ProductInOrder> getProductInOrders() {
        return productInOrders;
    }

    public void setProductInOrders(List<ProductInOrder> productInOrders) {
        this.productInOrders = productInOrders;
    }
}
