package br.com.stoom.store.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.stoom.store.model.Category;
import br.com.stoom.store.request.CategoryRequest;
import br.com.stoom.store.response.CategoryResponse;
import br.com.stoom.store.response.Response;
import br.com.stoom.store.services.CategoryService;

@Controller
@RequestMapping("/category")
public class CategoryController {
    
    @Autowired
    private CategoryService categoryService;


    @PostMapping
    public ResponseEntity<Response<CategoryResponse>> create(@RequestBody CategoryRequest request) {
        
        Response<CategoryResponse> response = new Response<>();
        try {
            Optional<Category> category = categoryService.create(request);
            response.setData(category.get().toCategoryResponse());
            return ResponseEntity.ok(response);
        } catch(Exception e) {
            response.addError(e);
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response<CategoryResponse>> update(@PathVariable("id") Long id, @RequestBody CategoryRequest request) {
        Response<CategoryResponse> response = new Response<>();
        try {
            Optional<Category> category =  categoryService.update(id, request);
            response.setData(category.get().toCategoryResponse());
            return ResponseEntity.ok(response);
        } catch(Exception e) {
            response.addError(e);
            return ResponseEntity.badRequest().body(response);
        }
    }  

    @GetMapping("/all")
    public ResponseEntity<Response<List<CategoryResponse>>> findAll(@RequestParam(name = "active", required = false) Boolean active) {
        Response<List<CategoryResponse>> response = new Response<>();
        try {
            List<CategoryResponse> dados = categoryService.findAll(active).stream()
            .map(e -> e.toCategoryResponse())
            .collect(Collectors.toList());
            response.setData(dados);
            return ResponseEntity.ok(response);
        } catch(Exception ex) {
            response.addError(ex);
            return ResponseEntity.badRequest().body(response);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response<CategoryResponse>> findById(@PathVariable("id") Long id) {
        Response<CategoryResponse> response = new Response<>();
        try {
            Optional<Category> category = categoryService.findById(id);
            if (category.isPresent()) {
                response.setData(category.get().toCategoryResponse());
            }
            return ResponseEntity.ok(response);
        } catch(Exception ex) {
            response.addError(ex);
            return ResponseEntity.badRequest().body(response);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response<Boolean>> delete(@PathVariable("id") Long id) {
        Boolean delete = categoryService.delete(id);
        return ResponseEntity.ok(new Response<>(delete));
    }
}
