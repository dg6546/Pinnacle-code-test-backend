package com.example.demo.service;

import com.example.demo.model.Good;
import com.example.demo.repository.GoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GoodService {
    private final GoodRepository goodRepository;

    @Autowired
    public GoodService(GoodRepository goodRepository) {
        this.goodRepository = goodRepository;
    }

    public List<Good> getGoods(){
        return goodRepository.findAll();
    }

    public Optional<Good> getGoodById(Long id){
        return goodRepository.findById(id);
    }
}
