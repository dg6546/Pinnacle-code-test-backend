package com.example.demo.model;

import javax.persistence.*;
import java.text.DecimalFormat;
import java.util.List;

@Entity
@Table
public class Receipt {

    @Id
    @SequenceGenerator(
            name = "Receipt_sequence",
            sequenceName = "Receipt_sequence",
            allocationSize = 1
    )

    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "Receipt_sequence"
    )

    private long id;

    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location location;

    @OneToMany
    @JoinColumn(name = "receipt_id")
    private List<ReceiptItem> listOfItem;

    @Transient
    private double subtotal;

    @Transient
    private double tax;

    @Transient
    private double total;

    public Receipt() {
    }

    public Receipt(Location location, List<ReceiptItem> listOfItem) {
        this.location = location;
        this.listOfItem = listOfItem;
    }

    public Receipt(Location tempLocation) {
        this.location = tempLocation;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public List<ReceiptItem> getListOfItem() {
        return listOfItem;
    }

    public void setListOfItem(List<ReceiptItem> listOfItem) {
        this.listOfItem = listOfItem;
    }

    public double getSubtotal() {
        double subtotal = 0;
        for(ReceiptItem item: listOfItem){
            subtotal += item.getItem().getPrice() * item.getQuantity();
        }
        return Math.round(subtotal * 100.0) / 100.0 ;
    }

    public void setSubtotal(int subtotal) {
        this.subtotal = subtotal;
    }

    public String getTax() {
        Location location = this.location;
        double taxRate = location.getTax();
        DecimalFormat df = new DecimalFormat("0.00");
        List<String> exemption = location.getExemption();
        double tax = 0.00;
        for(ReceiptItem item: listOfItem){
            if (!exemption.contains(item.getItem().getType())){
                tax += item.getItem().getPrice() * (taxRate/100);
            }
        }
        tax = Math.round(tax * 10.0) / 10.0;
        return String.format("%.2f", tax);
    }

    public void setTax(int tax) {
        this.tax = tax;
    }

    public double getTotal() {
        return this.getSubtotal() + Double.parseDouble(this.getTax());
    }

    public void setTotal(int total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "Receipt{" +
                "id=" + id +
                ", location=" + location +
                ", listOfItem=" + listOfItem +
                ", subtotal=" + subtotal +
                ", tax=" + tax +
                ", total=" + total +
                '}';
    }
}
