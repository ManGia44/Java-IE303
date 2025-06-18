package com.shoe_btth4.shoe_btth4.controller;

import com.shoe_btth4.shoe_btth4.model.Shoe;
import com.shoe_btth4.shoe_btth4.repository.ShoeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/shoes")
public class ShoeController {

    @Autowired
    private ShoeRepository shoeRepository;

    @GetMapping
    public List<Shoe> getAllShoes() {
        return shoeRepository.findAll();
    }
}
