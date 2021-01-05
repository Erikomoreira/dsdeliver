package br.com.erik.dsdeliver.controllers;

import br.com.erik.dsdeliver.dto.ProductDTO;
import br.com.erik.dsdeliver.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/products")
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping
    public ResponseEntity<List<ProductDTO>> findAll(){
        List<ProductDTO> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }
}
