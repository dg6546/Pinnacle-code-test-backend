package com.example.demo.service;

import com.example.demo.model.Good;
import com.example.demo.model.Location;
import com.example.demo.model.Receipt;
import com.example.demo.model.ReceiptItem;
import com.example.demo.repository.ReceiptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReceiptService {
    private final ReceiptRepository receiptRepository;
    private final ReceiptItemService receiptItemService;
    private final LocationService locationService;
    private final GoodService goodService;

    @Autowired
    public ReceiptService(ReceiptRepository receiptRepository, ReceiptItemService receiptItemService, LocationService locationService, GoodService goodService) {
        this.receiptRepository = receiptRepository;
        this.receiptItemService = receiptItemService;
        this.locationService = locationService;
        this.goodService = goodService;
    }
    public List<Receipt> getReceipts() {
        return receiptRepository.findAll();
    }

    public Receipt createNewReceipt(Receipt receipt) throws Exception {
        Receipt newReceipt = new Receipt();

        //set location of the new receipt
        Location receiptLocation = locationService.getLocationById(receipt.getLocation().getId());
        newReceipt.setLocation(receiptLocation);

        //set list of item
        List<ReceiptItem> receiptItemList = receipt.getListOfItem(); //get list of item in the response body
        List<ReceiptItem> newReceiptItemList = new ArrayList<>();


        for(ReceiptItem item: receiptItemList){
            Optional<Good> optGood = goodService.getGoodById(item.getItem().getId());
            if(!optGood.isPresent()){ throw new Exception("Good not found"); }
            Good good = optGood.get();
            ReceiptItem newReceiptItem = new ReceiptItem(good, item.getQuantity());
            newReceiptItemList.add(receiptItemService.createNewReceiptItem(newReceiptItem));
        }

        newReceipt.setListOfItem(newReceiptItemList);
        receiptRepository.save(newReceipt);
        return newReceipt;
    }

    public Optional<Receipt> getReceiptByID(Long id) {
        return receiptRepository.findById(id);
    }
}
