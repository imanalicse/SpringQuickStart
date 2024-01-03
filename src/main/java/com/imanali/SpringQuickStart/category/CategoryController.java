package com.imanali.SpringQuickStart.category;

import com.imanali.SpringQuickStart.api.response.DataResponseModel;
import com.imanali.SpringQuickStart.api.response.ResponseHandler;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping(path = "api/v1/categories")
@PreAuthorize("hasRole('ADMIN')")
public class CategoryController {
    private CategoryService categoryService;

    @GetMapping
    public ResponseEntity<DataResponseModel> getCategories() {
        List<Category>  categories = categoryService.getCategories();
        Map<String, Object> responseData = new HashMap<>();
        responseData.put("categories", categories);
        return ResponseHandler.oKResponse(responseData);
    }
}
