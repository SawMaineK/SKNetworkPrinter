package com.smk.testnetworkprinter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.gson.Gson;
import com.smk.networkprinter.invoice.Invoice;
import com.smk.networkprinter.invoice.InvoiceActivity;
import com.smk.networkprinter.invoice.Item;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Item> itemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        itemList = new ArrayList<>();
        itemList.add(new Item("1", "Test Product 1 Test Product 1", "300,000", "2", "600,000"));
        itemList.add(new Item("1", "Test Product 1", "3000", "2", "6000"));
        itemList.add(new Item("1", "Test Product 1", "3000", "2", "6000"));
        itemList.add(new Item("1", "Test Product 1", "3000", "2", "6000"));

        Invoice invoice = new Invoice();
        invoice.setItems(itemList);
        invoice.setLogo("https://admin.onhandpos.com/assets/imgs/logo.png");
        invoice.setCompanyName("On Hand POS");
        invoice.setBranchName("အမွတ္ ၃၉ (ဘီ)၊ စပါယ္လမ္း");
        invoice.setAddress("Company Address: No.39 B, Sapal Street, Mingal Taungnyunt");
        invoice.setPhone("+959 777 555 336, 09767 947 154");
        invoice.setDate("10/09/2019 01:23 PM");
        invoice.setCashier("Cashier: Saw K");
        invoice.setCurrency("Currency: MMK");
        invoice.setInvoiceNo("INV. 1000001");
        invoice.setCustomerName("CUST: Saw Maine K");
        invoice.setCustomerPhone("Ph: 09777335336");
        invoice.setSubtotal("20,000");
        invoice.setTaxAmount("20,000");
        invoice.setDiscount("1,000");
        invoice.setGrandTotal("3,000");
        invoice.setPaidAmount("3,000");
        invoice.setChangeAmount("100");
        invoice.setNote("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s");
        invoice.setUdf1("Lorem Ipsum is simply dummy text of the printing and typesetting industry");

        Bundle bundle = new Bundle();
        bundle.putString("ipAddress","192.168.8.87");
        bundle.putInt("port", 9100);
        bundle.putInt("paperWidth", 500); //80mm
        bundle.putString("invoice", new Gson().toJson(invoice));
        startActivity(new Intent(getApplicationContext(), InvoiceActivity.class).putExtras(bundle));
    }
}
