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

import br.com.stoom.store.model.Brand;
import br.com.stoom.store.request.BrandRequest;
import br.com.stoom.store.response.BrandResponse;
import br.com.stoom.store.response.Response;
import br.com.stoom.store.services.BrandService;

@Controller
@RequestMapping("/brand")
public class BrandController {
    
    @Autowired
    private BrandService brandService;


    @PostMapping
    public ResponseEntity<Response<BrandResponse>> create(@RequestBody BrandRequest request) {
        
        Response<BrandResponse> response = new Response<>();
        try {
            Optional<Brand> brand = brandService.create(request);
            response.setData(brand.get().toBrandResponse());
            return ResponseEntity.ok(response);
        } catch(Exception e) {
            response.addError(e);
            return ResponseEntity.badRequest().body(response);
        }
    } 

    @PutMapping("/{id}")
    public ResponseEntity<Response<BrandResponse>> update(@PathVariable("id") Long id, @RequestBody BrandRequest request) {
        Response<BrandResponse> response = new Response<>();
        try {
            Optional<Brand> brand =  brandService.update(id, request);
            response.setData(brand.get().toBrandResponse());
            return ResponseEntity.ok(response);
        } catch(Exception e) {
            response.addError(e);
            return ResponseEntity.badRequest().body(response);
        }
    } 

    @GetMapping("/all")
    public ResponseEntity<Response<List<BrandResponse>>> findAll(@RequestParam(name = "active", required = false) Boolean active) {
        Response<List<BrandResponse>> response = new Response<>();
        try {
            List<BrandResponse> dados = brandService.findAll(active).stream()
            .map(e -> e.toBrandResponse())
            .collect(Collectors.toList());
            response.setData(dados);
            return ResponseEntity.ok(response);
        } catch(Exception ex) {
            response.addError(ex);
            return ResponseEntity.badRequest().body(response);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response<BrandResponse>> findById(@PathVariable("id") Long id) {
        Response<BrandResponse> response = new Response<>();
        try {
            Optional<Brand> brand = brandService.findById(id);
            if (brand.isPresent()) {
                response.setData(brand.get().toBrandResponse());
            }
            return ResponseEntity.ok(response);
        } catch(Exception ex) {
            response.addError(ex);
            return ResponseEntity.badRequest().body(response);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response<Boolean>> delete(@PathVariable("id") Long id) {
        Boolean delete = brandService.delete(id);
        return ResponseEntity.ok(new Response<>(delete));
    }
}
