package com.smk.networkprinter.invoice;

public class Item {
    private int id;
    private String name;
    private int price;
    private int qty;
    private int amount;

    public Item(int id, String name, int price, int qty, int amount) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.qty = qty;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
