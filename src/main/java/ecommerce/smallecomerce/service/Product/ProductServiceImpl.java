package ecommerce.smallecomerce.service.Product;

import ecommerce.smallecomerce.dto.ProductDto;
import ecommerce.smallecomerce.dto.Response;
import ecommerce.smallecomerce.entity.Category;
import ecommerce.smallecomerce.entity.Product;
import ecommerce.smallecomerce.exception.NotFoundException;
import ecommerce.smallecomerce.mapper.EntityDtoMapper;
import ecommerce.smallecomerce.repository.CategoryRepo;
import ecommerce.smallecomerce.repository.ProductRepo;
import jakarta.persistence.EntityManager;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final CategoryRepo categoryRepo;
    private final ProductRepo productRepo;
    private final EntityDtoMapper entityDtoMapper;


    @Override
    public Response createProduct(Long categoryId,  String name, String description, BigDecimal price) {

        Category category = categoryRepo.findById(categoryId).orElseThrow(()-> new NotFoundException("Category not found"));

        Product product = new Product();
        product.setName(name);
        product.setDescription(description);
        product.setPrice(price);
        product.setCategory(category);
        productRepo.save(product);


        return Response.builder()
                .status(200)
                .message("Product created successfully")
                .build();
    }

    @Override
    public Response updateProduct(Long productId, Long categoryId,  String name, String description, BigDecimal price) {
        Product product = productRepo.findById(productId).orElseThrow(()-> new NotFoundException("Product not found"));
        Category category = null;
        if(categoryId != null){
            category =  categoryRepo.findById(categoryId).orElseThrow(()-> new NotFoundException("Category not found"));
        }

        if (category != null) product.setCategory(category);
        if (name != null) product.setName(name);
        if (price != null) product.setPrice(price);
        if (description != null) product.setDescription(description);
        productRepo.save(product);

        return Response.builder()
                .status(200)
                .message("Product updated successfully")
                .build();
    }

    @Override
    public Response deleteProduct(Long productId) {
        Product product = productRepo.findById(productId).orElseThrow(()-> new NotFoundException("Product Not Found"));
        productRepo.delete(product);

        return Response.builder()
                .status(200)
                .message("Product deleted successfully")
                .build();

    }

    @Override
    public Response getProductById(Long productId) {
        Product product = productRepo.findById(productId).orElseThrow(()-> new NotFoundException("Product Not Found"));
        ProductDto productDto = entityDtoMapper.mapProductToDtoBasic(product);

        return Response.builder()
                .status(200)
                .product(productDto)
                .build();

    }

    @Override
    public Response getAllProducts() {

        List<ProductDto> productList = productRepo.findAll(Sort.by(Sort.Direction.DESC, "id")).stream()
                .map(entityDtoMapper::mapProductToDtoBasic)
                .toList();


        return Response.builder()
                .status(200)
                .productList(productList)
                .build();
    }

    @Override
    public Response getProductsByCategory(Long categoryId) {
        List<Product> products = productRepo.findByCategoryId(categoryId);
        if(products.isEmpty()){
            throw new NotFoundException("No Products found for this category");
        }
        List<ProductDto> productDtoList = products.stream()
                .map(entityDtoMapper::mapProductToDtoBasic)
                .collect(Collectors.toList());

        return Response.builder()
                .status(200)
                .productList(productDtoList)
                .build();
    }

    @Override
    public Response searchProduct(String searchValue) {
        List<Product> products = productRepo.findByNameContainingOrDescriptionContaining(searchValue, searchValue);

        if (products.isEmpty()){
            throw new NotFoundException("No Products Found");
        }
        List<ProductDto> productDtoList = products.stream()
                .map(entityDtoMapper::mapProductToDtoBasic)
                .collect(Collectors.toList());


        return Response.builder()
                .status(200)
                .productList(productDtoList)
                .build();
    }
}
