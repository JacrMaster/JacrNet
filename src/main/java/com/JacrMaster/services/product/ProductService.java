package com.JacrMaster.services.product;

import com.JacrMaster.exception.exception.ResourceNotFoundException;
import com.JacrMaster.models.Product;
import com.JacrMaster.presentation.dto.ProductDTO;
import com.JacrMaster.repositories.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService implements IProductServiceImpl{
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ProductDTO addProduct(ProductDTO productDTO) {
        Product product = modelMapper.map(productDTO, Product.class);
        Product productSaved = productRepository.save(product);
        return modelMapper.map(productSaved, ProductDTO.class);
    }

    @Override
    public String deleteProduct(Long id) {
        productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No existe producto"));
        productRepository.deleteById(id);
        return "Eliminado con exito";
    }

    @Override
    public List<ProductDTO> findAll() {
        List<Product> products = (List<Product>) productRepository.findAll();
        return products.stream().map(product -> modelMapper.map(product, ProductDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public ProductDTO updateProduct(Long id, ProductDTO productDTO) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product no existe"));
        product.setId(id);
        product.setName(productDTO.getName());
        product.setProfile(productDTO.getProfile());
        product.setLink(productDTO.getLink());

        Product productUpdate = productRepository.save(product);
        return modelMapper.map(productUpdate, ProductDTO.class);
    }
}
