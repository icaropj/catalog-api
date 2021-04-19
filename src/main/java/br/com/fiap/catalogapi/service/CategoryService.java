package br.com.fiap.catalogapi.service;

import br.com.fiap.catalogapi.exception.NotFoundException;
import br.com.fiap.catalogapi.model.Category;
import br.com.fiap.catalogapi.model.dto.CategoryDTO;
import br.com.fiap.catalogapi.repository.CategoryRepository;
import com.googlecode.jmapper.JMapper;
import com.googlecode.jmapper.api.JMapperAPI;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CategoryService {

    private static final String CATEGORY_NOT_FOUND_FOR_ID = "Category not found for id: ";
    private CategoryRepository repository;
    private JMapperAPI mapper;

    @Autowired
    public CategoryService(CategoryRepository repository) {
        this.repository = repository;
        mapper = new JMapperAPI();
    }

    public List<CategoryDTO> list() {
        log.info("Listing all categories");
        return repository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public Category insert(CategoryDTO categoryDTO) {
        log.info("insert category: {}", categoryDTO);
        Category category = mapToEntity(categoryDTO);
        return repository.save(category);
    }

    public Category getById(Long id) throws NotFoundException {
        log.info("get category by id: {}", id);
        return repository.findById(id).orElseThrow(() -> new NotFoundException(CATEGORY_NOT_FOUND_FOR_ID + id));
    }

    public void deleteById(Long id) throws NotFoundException {
        log.info("delete category: {}", id);
        if (!repository.findById(id).isPresent())
            throw new NotFoundException(CATEGORY_NOT_FOUND_FOR_ID + id);
        repository.deleteById(id);
    }

    private Category mapToEntity(CategoryDTO categoryDTO) {
        JMapper<Category, CategoryDTO> categoryMapper =
                new JMapper<>(Category.class, CategoryDTO.class, mapper);
        return categoryMapper.getDestination(categoryDTO);
    }

    private CategoryDTO mapToDTO(Category category) {
        JMapper<CategoryDTO, Category> categoryMapper =
                new JMapper<>(CategoryDTO.class, Category.class, mapper);
        return categoryMapper.getDestination(category);
    }

}
