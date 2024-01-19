package org.example.services;

import org.example.data.model.Category;

public interface CategoryService {
    Category addCategory(String categoryName, Long expenseTrackerId);

}
