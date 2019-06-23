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
        itemList.add(new Item(1, "Medical Tester 1", 3000, 2, 6000));
        itemList.add(new Item(1, "Medical Tester 1", 3000, 2, 6000));
        itemList.add(new Item(1, "Medical Tester 1", 3000, 2, 6000));
        itemList.add(new Item(1, "Medical Tester 1", 3000, 2, 6000));
        itemList.add(new Item(1, "Medical Tester 1", 3000, 2, 6000));
        itemList.add(new Item(1, "Medical Tester 1", 3000, 2, 6000));

        Invoice invoice = new Invoice();
        invoice.setItems(itemList);

        Bundle bundle = new Bundle();
        bundle.putString("ipAddress","192.168.1.87");
        bundle.putInt("port", 9100);
        bundle.putInt("paperWidth", 580); //80mm
        bundle.putString("invoice", new Gson().toJson(invoice));
        startActivity(new Intent(getApplicationContext(), InvoiceActivity.class).putExtras(bundle));
    }
}
