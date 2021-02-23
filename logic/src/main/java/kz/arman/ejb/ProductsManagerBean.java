package kz.arman.ejb;

import kz.arman.domain.Product;
import org.graalvm.compiler.phases.common.inlining.policy.InlineEverythingPolicy;

import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@LocalBean
public class ProductsManagerBean {
    @PersistenceContext(unitName = "MyPU")
    private EntityManager entityManager;

    public Product createProduct(String name, int price){
        Product product = new Product();
        product.setName(name);
        product.setPrice(price);
        entityManager.persist(product);

        return product;
    }

    public List<Product> getProducts(){
      TypedQuery<Product> query = entityManager.createQuery("select c from Product c", Product.class);
      return query.getResultList();
    }
}
