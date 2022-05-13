package com.example.demo.config;

import com.example.demo.model.Good;
import com.example.demo.model.Location;
import com.example.demo.model.Receipt;
import com.example.demo.model.ReceiptItem;
import com.example.demo.repository.GoodRepository;
import com.example.demo.repository.LocationRepository;
import com.example.demo.repository.ReceiptItemRepository;
import com.example.demo.repository.ReceiptRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Configuration
public class ReceiptItemConfig {
    @Bean
    CommandLineRunner ReceiptCommandLineRunner(ReceiptItemRepository receiptItemRepository,
                                               GoodRepository goodRepository,
                                               ReceiptRepository receiptRepository,
                                               LocationRepository locationRepository){
        return args -> {
            List<Good> listOfGoods = new ArrayList<>();

            Optional<Location> ony = locationRepository.findLocationByName("New York");
            if (!ony.isPresent()){
                throw new Error("not found");
            }
            Optional<Location> oca = locationRepository.findLocationByName("California");
            if (!oca.isPresent()){
                throw new Error("not found");
            }
            Optional<Good> obook = goodRepository.findGoodByName("book");
            if (!obook.isPresent()){
                throw new Error("not found");
            }
            Optional<Good> opencil = goodRepository.findGoodByName("pencil");
            if (!opencil.isPresent()){
                throw new Error("not found");
            }
            Optional<Good> opotatoChips = goodRepository.findGoodByName("potato chips");
            if (!opotatoChips.isPresent()){
                throw new Error("not found");
            }
            Optional<Good> oshirt = goodRepository.findGoodByName("shirt");
            if (!oshirt.isPresent()){
                throw new Error("not found");
            }
            Location ny = ony.get();
            Location ca = oca.get();
            Good book = obook.get();
            Good pencil = opencil.get();
            Good potatoChips = opotatoChips.get();
            Good shirt = oshirt.get();

            //receipt1
            List<ReceiptItem> listOfReceiptItem1 = new ArrayList<>();
            listOfReceiptItem1.add(new ReceiptItem(book, 1));
            listOfReceiptItem1.add(new ReceiptItem(potatoChips, 1));

            receiptItemRepository.saveAll(listOfReceiptItem1);
            Receipt receipt1 = new Receipt(ca, listOfReceiptItem1);
            receiptRepository.save(receipt1);

            //receipt2
            List<ReceiptItem> listOfReceiptItem2 = new ArrayList<>();
            listOfReceiptItem2.add(new ReceiptItem(book, 1));
            listOfReceiptItem2.add(new ReceiptItem(pencil, 3));

            receiptItemRepository.saveAll(listOfReceiptItem2);
            Receipt receipt2 = new Receipt(ny, listOfReceiptItem2);
            receiptRepository.save(receipt2);

            //receipt3
            List<ReceiptItem> listOfReceiptItem3 = new ArrayList<>();
            listOfReceiptItem3.add(new ReceiptItem(pencil, 2));
            listOfReceiptItem3.add(new ReceiptItem(shirt, 1));

            receiptItemRepository.saveAll(listOfReceiptItem3);
            Receipt receipt3 = new Receipt(ny, listOfReceiptItem3);
            receiptRepository.save(receipt3);

        };
    }

}
