package ecommerce.smallecomerce.service.Product;

import ecommerce.smallecomerce.dto.Response;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;

public interface ProductService {
    Response createProduct(Long categoryId,  String name, String description, BigDecimal price);
    Response updateProduct(Long productId, Long categoryId,  String name, String description, BigDecimal price);
    Response deleteProduct(Long productId);
    Response getProductById(Long productId);
    Response getAllProducts();
    Response getProductsByCategory(Long categoryId);
    Response searchProduct(String searchValue);
}
