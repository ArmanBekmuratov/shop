package kz.arman.ejb;


import kz.arman.*;

import kz.arman.domain.Order;
import kz.arman.domain.Product;
import kz.arman.domain.ProductInOrder;

import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@LocalBean
public class OrdersManagerBean {

    @PersistenceContext(unitName = "MyPU")
    private EntityManager entityManager;

    public Order createOrder(){
        Order order = new Order();
        entityManager.persist(order);

        return order;
    }

    public boolean addInOrder(long productId, long orderId, int quantity) {
        Product product = entityManager.find(Product.class, productId);
        if(product == null) {
            return false;
        }

        Order order = entityManager.find(Order.class, orderId);
        if(order == null) {
            return false;
        }

        ProductInOrder productInOrder = new ProductInOrder();
        productInOrder.setOrder(order);
        productInOrder.setProduct(product);
        entityManager.persist(productInOrder);

        return true;
    }

    public List<Product> getProductInOrder(long orderId) {
        Order order = entityManager.find(Order.class, orderId);
        if(order == null) {
            return Collections.emptyList();
        }

       List<ProductInOrder> productInOrders = order.getProductInOrders();
        List<Product> result = new ArrayList<>();
        for(ProductInOrder productInOrder : productInOrders) {
            result.add(productInOrder.getProduct()  );
        }
        return result;
    }
}
