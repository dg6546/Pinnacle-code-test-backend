package com.example.demo.controller;

import com.example.demo.model.Good;
import com.example.demo.service.GoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(path = "api/v1/good")
public class GoodController {
    private final GoodService goodService;

    @Autowired
    public GoodController(GoodService goodService) {
        this.goodService = goodService;
    }

    @GetMapping
    public List<Good> getGoods(){
        return goodService.getGoods();
    }
}
