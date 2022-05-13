package com.example.demo.controller;

import com.example.demo.common.ApiResponse;
import com.example.demo.model.Location;
import com.example.demo.model.Receipt;
import com.example.demo.model.ReceiptItem;
import com.example.demo.service.ReceiptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController

public class ReceiptController {
    private final ReceiptService receiptService;

    @Autowired
    public ReceiptController(ReceiptService receiptService) {
        this.receiptService = receiptService;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping(path = "api/v1/receipt/new")
    @PostMapping
    public ResponseEntity<ApiResponse> createReceipt(@RequestBody Receipt receipt) {
        try {
            Receipt newReceipt = receiptService.createNewReceipt(receipt);
            return new ResponseEntity<>(new ApiResponse(true, newReceipt.toString()), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping(path = "api/v1/receipt")
    @GetMapping
    public ResponseEntity getReceipt(@RequestBody Map<String, Object> request){
        Long id = Long.parseLong(request.get("id").toString());
        Optional<Receipt> receipt = receiptService.getReceiptByID(id);
        if (receipt.isPresent()) {
            return ResponseEntity
                    .status(HttpStatus.ACCEPTED)
                    .body(receipt);
        }
        return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Receipt not found");
    }
}