package cf.mindaugas.jree27springnodbangular.controller;

import cf.mindaugas.jree27springnodbangular.entity.Product;
import cf.mindaugas.jree27springnodbangular.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
// @CrossOrigin(origins = "http://localhost:4200")
public class ProductController {
    @Autowired
    private ProductRepository productRepository;

    @GetMapping(value={"", "/"}) // GET all products
    public List<Product> getAllProducts(){
        return productRepository.getProducts();
    }

    @GetMapping("/{id}") // GET product by id
    public ResponseEntity<Product> getProductById(@PathVariable Integer id){
        var optionalProduct = productRepository
                .getProducts()
                .stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();

        return optionalProduct
                .map(product -> new ResponseEntity<>(product, HttpStatus.OK)) // 200 OK
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND)); // 404 NOT FOUND
    }

    @PostMapping("") // CREATE product
    public void createProduct(@RequestBody Product product){
        product.setId(productRepository.getProducts().size() + 1);
        productRepository.getProducts().add(product);
    }

    @PutMapping("/{id}") // UPDATE product
    public ResponseEntity<Void> updateProduct(@PathVariable Integer id, @RequestBody Product product){
        var optionalProduct = productRepository
                .getProducts()
                .stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();

        return optionalProduct
                .map(currentProduct -> {
                    currentProduct.setTitle(product.getTitle());
                    currentProduct.setCount(product.getCount());
                    currentProduct.setPrice(product.getPrice());
                    currentProduct.setRating(product.getRating());
                    return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
                })
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}") // DELETE product
    public ResponseEntity<Void> deleteProductById(@PathVariable Integer id){
        var optionalProduct = productRepository
                .getProducts()
                .stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();

        return optionalProduct
                .map(product -> {
                    productRepository.getProducts().remove(product);
                    return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
                })
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}