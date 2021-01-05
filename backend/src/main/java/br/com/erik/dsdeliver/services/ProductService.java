package br.com.erik.dsdeliver.services;

import br.com.erik.dsdeliver.dto.ProductDTO;
import br.com.erik.dsdeliver.entities.Product;
import br.com.erik.dsdeliver.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    @Transactional(readOnly = true)
    public List<ProductDTO> findAll(){
        List<Product> list = repository.findAllByOrderByNameAsc();
        return list.stream().map(ProductDTO::new).collect(Collectors.toList());
    }

}
