package kabopok.server.services;

import kabopok.server.entities.Product;
import kabopok.server.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductService {

  private final ProductRepository productRepository;
  public void save(Product product) {
    UUID productID = UUID.randomUUID();
    product.setProductID(productID);
    productRepository.save(product);
  }

}
