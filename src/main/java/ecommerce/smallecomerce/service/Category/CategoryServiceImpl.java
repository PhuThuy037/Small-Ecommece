package ecommerce.smallecomerce.service.Category;

import ecommerce.smallecomerce.dto.CategoryDto;
import ecommerce.smallecomerce.dto.Response;
import ecommerce.smallecomerce.entity.Category;
import ecommerce.smallecomerce.exception.NotFoundException;
import ecommerce.smallecomerce.mapper.EntityDtoMapper;
import ecommerce.smallecomerce.repository.CategoryRepo;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepo categoryRepo;
    private final EntityDtoMapper entityDtoMapper;

    @Override
    public Response createCategory(CategoryDto categoryRequest) {
        Category category = new Category();
        category.setName(categoryRequest.getName());
        categoryRepo.save(category);

        return Response.builder()
                .status(200)
                .message("Category created")
                .build();
    }

    @Override
    public Response updateCategory(Long categoryId, CategoryDto categoryRequest) {

        Category category = categoryRepo.findById(categoryId).orElseThrow(()->new NotFoundException("Category Not Found"));
        category.setName(categoryRequest.getName());
        categoryRepo.save(category);

        return Response.builder()
                .status(200)
                .message("Category updated")
                .build();
    }

    @Override
    public Response getAllCategories() {
        List<CategoryDto> categories = categoryRepo.findAll().stream()
                .map(entityDtoMapper::mapCategoryToDtoBasic)
                .toList();

        return Response.builder()
                .status(200)
                .categoryList(categories)
                .build();
    }

    @Override
    public Response getCategoryById(Long categoryId) {
        Category category = categoryRepo.findById(categoryId).orElseThrow(()->new NotFoundException("Category Not Found"));
        CategoryDto categoryDto = entityDtoMapper.mapCategoryToDtoBasic(category);

        return Response.builder()
                .status(200)
                .category(categoryDto)
                .build();
    }

    @Override
    public Response deleteCategory(Long categoryId) {
        Category category = categoryRepo.findById(categoryId).orElseThrow(()->new NotFoundException("Category Not Found"));
        categoryRepo.delete(category);
        return Response.builder()
                .status(200)
                .message("Category deleted")
                .build();
    }
}
