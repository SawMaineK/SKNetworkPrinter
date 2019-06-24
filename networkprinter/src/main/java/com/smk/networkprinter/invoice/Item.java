package com.smk.networkprinter.invoice;

public class Item {
    private String id;
    private String name;
    private String price;
    private String qty;
    private String amount;

    public Item(String id, String name, String price, String qty, String amount) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.qty = qty;
        this.amount = amount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
