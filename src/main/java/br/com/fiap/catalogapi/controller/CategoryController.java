package br.com.fiap.catalogapi.controller;

import br.com.fiap.catalogapi.config.Constants;
import br.com.fiap.catalogapi.exception.NotFoundException;
import br.com.fiap.catalogapi.model.dto.CategoryDTO;
import br.com.fiap.catalogapi.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = Constants.VERSION + "/category")
public class CategoryController {

    private CategoryService service;

    @Autowired
    public CategoryController(CategoryService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<Object> list() {
        return ResponseEntity.ok(service.list());
    }

    @PostMapping
    public ResponseEntity<Object> insert(@RequestBody @Valid CategoryDTO categoryDTO) {
        return new ResponseEntity<>(service.insert(categoryDTO), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable Long id) throws NotFoundException {
        return ResponseEntity.ok(service.getById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteById(@PathVariable Long id) throws NotFoundException {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
