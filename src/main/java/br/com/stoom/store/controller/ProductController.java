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

import br.com.stoom.store.model.Product;
import br.com.stoom.store.request.ProductRequest;
import br.com.stoom.store.response.ProductResponse;
import br.com.stoom.store.response.Response;
import br.com.stoom.store.services.ProductService;

@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<Response<ProductResponse>> create(@RequestBody ProductRequest request) {
        
        Response<ProductResponse> response = new Response<>();
        try {
            Optional<Product> product = productService.create(request);
            response.setData(product.get().toProductResponse());
            return ResponseEntity.ok(response);
        } catch(Exception e) {
            response.addError(e);
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response<ProductResponse>> update(@PathVariable("id") Long id, @RequestBody ProductRequest request) {
        Response<ProductResponse> response = new Response<>();
        try {
            Optional<Product> product =  productService.update(id, request);
            response.setData(product.get().toProductResponse());
            return ResponseEntity.ok(response);
        } catch(Exception e) {
            response.addError(e);
            return ResponseEntity.badRequest().body(response);
        }
    }  

    @GetMapping("/all")
    public ResponseEntity<Response<List<ProductResponse>>> findAll(@RequestParam(name = "active", required = false) Boolean active) {
        Response<List<ProductResponse>> response = new Response<>();
        try {
            List<ProductResponse> dados = productService.findAll(active).stream()
            .map(e -> e.toProductResponse())
            .collect(Collectors.toList());
            response.setData(dados);
            return ResponseEntity.ok(response);
        } catch(Exception ex) {
            response.addError(ex);
            return ResponseEntity.badRequest().body(response);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response<ProductResponse>> findById(@PathVariable("id") Long id) {
        Response<ProductResponse> response = new Response<>();
        try {
            Optional<Product> product = productService.findById(id);
            if (product.isPresent()) {
                response.setData(product.get().toProductResponse());
            }
            return ResponseEntity.ok(response);
        } catch(Exception ex) {
            response.addError(ex);
            return ResponseEntity.badRequest().body(response);
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Response<Boolean>> delete(@PathVariable("id") Long id) {
        Boolean delete = productService.delete(id);
        return ResponseEntity.ok(new Response<>(delete));
    }


    @GetMapping("/by-brand/{brand}")
    public ResponseEntity<Response<List<ProductResponse>>> findAllByBrand(@PathVariable("brand") String brand) {
        Response<List<ProductResponse>> response = new Response<>();
        try {
            List<ProductResponse> dados = productService.findAllByBrand(brand).stream()
            .map(e -> e.toProductResponse())
            .collect(Collectors.toList());
            response.setData(dados);
            return ResponseEntity.ok(response);
        } catch(Exception ex) {
            response.addError(ex);
            return ResponseEntity.badRequest().body(response);
        }
    }

    @GetMapping("/by-category/{category}")
    public ResponseEntity<Response<List<ProductResponse>>> findAllByCategory(@PathVariable("category") String category) {
        Response<List<ProductResponse>> response = new Response<>();
        try {
            List<ProductResponse> dados = productService.findAllByCategory(category).stream()
            .map(e -> e.toProductResponse())
            .collect(Collectors.toList());
            response.setData(dados);
            return ResponseEntity.ok(response);
        } catch(Exception ex) {
            response.addError(ex);
            return ResponseEntity.badRequest().body(response);
        }
    }
}
