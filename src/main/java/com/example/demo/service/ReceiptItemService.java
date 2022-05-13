package com.example.demo.service;

import com.example.demo.model.ReceiptItem;
import com.example.demo.repository.ReceiptItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReceiptItemService {
    private final ReceiptItemRepository receiptItemRepository;

    @Autowired
    public ReceiptItemService(ReceiptItemRepository receiptItemRepository) {
        this.receiptItemRepository = receiptItemRepository;
    }

    public ReceiptItem createNewReceiptItem(ReceiptItem receiptItem){
        return receiptItemRepository.save(receiptItem);
    }

}
