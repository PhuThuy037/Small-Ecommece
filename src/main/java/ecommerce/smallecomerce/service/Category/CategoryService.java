package ecommerce.smallecomerce.service.Category;

import ecommerce.smallecomerce.dto.CategoryDto;
import ecommerce.smallecomerce.dto.Response;

public interface CategoryService {
    Response createCategory(CategoryDto categoryRequest);
    Response updateCategory(Long categoryId, CategoryDto categoryRequest);
    Response getAllCategories();
    Response getCategoryById(Long categoryId);
    Response deleteCategory(Long categoryId);
}
