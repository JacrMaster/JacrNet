package com.JacrMaster.services.product;

import com.JacrMaster.presentation.dto.ProductDTO;

import java.util.List;

public interface IProductServiceImpl {
    ProductDTO addProduct(ProductDTO productDTO);
    String deleteProduct(Long id);
    List<ProductDTO> findAll();
    ProductDTO updateProduct(Long id, ProductDTO productDTO);

}
