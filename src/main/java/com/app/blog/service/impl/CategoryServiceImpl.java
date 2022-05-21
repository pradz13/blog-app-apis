package com.app.blog.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import com.app.blog.entity.Category;
import com.app.blog.exceptions.ResourceNotFoundException;
import com.app.blog.payloads.CategoryDto;
import com.app.blog.repository.CategoryRepository;
import com.app.blog.service.CategoryService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category category = this.modelMapper.map(categoryDto, Category.class);
        Category addedCategory = this.categoryRepository.save(category);
        return this.modelMapper.map(addedCategory, CategoryDto.class);
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
        Category categoryToBeUpdated = this.categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "Category Id", categoryId));

        categoryToBeUpdated.setCategoryTitle(categoryDto.getCategoryTitle());
        categoryToBeUpdated.setCategoryDescription(categoryDto.getCategoryDescription());
        Category updatedCategory = this.categoryRepository.save(categoryToBeUpdated);
        return this.modelMapper.map(updatedCategory, CategoryDto.class);
    }

    @Override
    public void deleteCategory(Integer categoryId) {
        Category categoryToBeDeleted = this.categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "Category Id", categoryId));

        this.categoryRepository.delete(categoryToBeDeleted);
    }

    @Override
    public CategoryDto getCategory(Integer categoryId) {
        Category category = this.categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "Category Id", categoryId));

        return this.modelMapper.map(category, CategoryDto.class);
    }

    @Override
    public List<CategoryDto> getAllCategories() {
        List<Category> categories = this.categoryRepository.findAll();
        return categories
                .stream()
                .map(category -> this.modelMapper.map(category, CategoryDto.class))
                .collect(Collectors.toList());

    }

}
