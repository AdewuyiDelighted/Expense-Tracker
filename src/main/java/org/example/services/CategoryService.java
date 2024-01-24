package org.example.services;

import org.example.data.model.Category;
import org.example.data.model.CategoryType;

public interface CategoryService {
    Category addCategory(String categoryName, Long expenseTrackerId, CategoryType categoryType);

}
