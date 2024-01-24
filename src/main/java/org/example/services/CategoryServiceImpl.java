package org.example.services;

import org.example.data.model.Category;
import org.example.data.model.CategoryType;
import org.example.data.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public Category addCategory(String categoryName, Long expenseTrackerId, CategoryType categoryType) {
        Category category = categoryExist(categoryName, expenseTrackerId);
        if (category == null) {
            Category newCategory = new Category();
            newCategory.setName(categoryName.toUpperCase());
            newCategory.setExpensesTrackerId(expenseTrackerId);
            newCategory.setCategoryType(categoryType);
            categoryRepository.save(newCategory);
            return newCategory;
        }
        return category;
    }

    private Category categoryExist(String categoryName, Long expenseTrackerId) {
        for (Category category : categoryRepository.findAll()) {
            if (category.getName().equals(categoryName) && category.getExpensesTrackerId().equals(expenseTrackerId)) {
                return category;
            }
            break;
        }
        return null;
    }
}
