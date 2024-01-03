package com.imanali.SpringQuickStart.category;

import com.imanali.SpringQuickStart.model.Category;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
@Slf4j
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }
}
