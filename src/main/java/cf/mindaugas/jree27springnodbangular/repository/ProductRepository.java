package cf.mindaugas.jree27springnodbangular.repository;

import cf.mindaugas.jree27springnodbangular.entity.Product;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductRepository {
    private final List<Product> products = new ArrayList<>()
    {{
        add(new Product(1, "Snowboard", 100, 9.99, 3.75));
        add(new Product(2, "Kittens", 999, 19.99, 4.85));
        add(new Product(3, "Small dogs", 999, 19.99, 4.85));
        add(new Product(4, "Tesla P100D", 999, 19.99, 4.85));
    }};

    public List<Product> getProducts(){
        return products;
    }
}
