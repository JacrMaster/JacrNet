package com.JacrMaster.presentation.controllers;

import com.JacrMaster.presentation.dto.ProductDTO;
import com.JacrMaster.services.product.IProductServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
    @Autowired
    private IProductServiceImpl productService;

    @PostMapping("/add")
    public ResponseEntity<ProductDTO> addProduct(@RequestBody @Valid ProductDTO productDTO){
        return new ResponseEntity<>(productService.addProduct(productDTO), HttpStatus.CREATED);
    }
    @DeleteMapping("/product/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id){
        return new ResponseEntity<>(productService.deleteProduct(id), HttpStatus.OK);
    }
    @GetMapping("/all")
    public ResponseEntity<List<ProductDTO>> findAll(){
        return  new ResponseEntity<>(productService.findAll(), HttpStatus.OK);
    }
    @PutMapping("/product/{id}")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable Long id, @RequestBody @Valid ProductDTO productDTO){
        return new ResponseEntity<>(productService.updateProduct(id, productDTO), HttpStatus.OK);
    }
}
