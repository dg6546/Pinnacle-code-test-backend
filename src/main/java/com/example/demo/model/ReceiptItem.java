package com.example.demo.model;

import javax.persistence.*;

@Entity
@Table
public class ReceiptItem {

    @Id
    @SequenceGenerator(
            name = "ReceiptItem_sequence",
            sequenceName = "ReceiptItem_sequence",
            allocationSize = 1
    )

    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "ReceiptItem_sequence"
    )
    private long id;

    @ManyToOne
    @JoinColumn(name = "item_id")
    public Good item;

    private int quantity;


    public ReceiptItem() {
    }

    public ReceiptItem(Good item, int quantity) {
        this.item = item;
        this.quantity = quantity;
    }

    public long getId() {
        return id;
    }


    public Good getItem() {
        return item;
    }

    public void setItem(Good item) {
        this.item = item;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "ReceiptItem{" +
                "id=" + id +
                ", item=" + item +
                ", quantity=" + quantity +
                '}';
    }
}
