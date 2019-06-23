package com.smk.networkprinter.invoice;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.google.gson.Gson;
import com.smk.networkprinter.PrinterManager;
import com.smk.networkprinter.PrinterService;
import com.smk.networkprinter.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class InvoiceActivity extends Activity {

    private ListView lstView;
    private ArrayList<Object> itemList;
    private ListItemAdapter mAdapter;
    private String ipAddress = "192.168.1.87";
    private int port = 9100;
    private int paperWidth = 580;
    private Invoice invoice;
    private PrinterManager printer;
    private PrinterService printerService;
    private ImageView imgLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invoice);
        
        Bundle intent = getIntent().getExtras();
        if(intent != null) {
            this.ipAddress = intent.getString("ipAddress");
            this.port = intent.getInt("port");
            this.paperWidth = intent.getInt("paperWidth");
            this.invoice = new Gson().fromJson(intent.getString("invoice"), Invoice.class);
        }

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        printer = new PrinterManager(ipAddress, port);
        printerService = new PrinterService(printer);
        final ProgressDialog printing = new ProgressDialog(this);
        printing.setMessage("Printing...");
        printing.show();
        if(invoice != null) {
            imgLogo = (ImageView) findViewById(R.id.img_logo);
            Picasso.get()
                    .load("https://www.onhandpos.com/assets/imgs/logo.png")
                    .resize(300, 150)
                    .centerInside()
                    .into(imgLogo, new Callback() {
                        @Override
                        public void onSuccess() {
                            new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    try {
                                        Thread.sleep(300);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    } finally {
                                        Bitmap bitmap = loadBitmapFromView(findViewById(R.id.invoice));
                                        printerService.printImage(resizeBitmap(bitmap, paperWidth));
                                        printerService.lineBreak();
                                        printerService.cutFull();
                                        printing.dismiss();
                                        finish();
                                    }
                                }
                            }).start();
                        }

                        @Override
                        public void onError(Exception e) {

                        }
                    });
            lstView = (ListView) findViewById(R.id.lst_item);
            itemList = new ArrayList<>();
            itemList.addAll(this.invoice.getItems());

            mAdapter = new ListItemAdapter(this, itemList);
            lstView.setAdapter(mAdapter);
            mAdapter.notifyDataSetChanged();
            setListViewHeightBasedOnItems(lstView);

        }


    }

    public static boolean setListViewHeightBasedOnItems(ListView listView) {

        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter != null) {

            int numberOfItems = listAdapter.getCount();

            // Get total height of all items.
            int totalItemsHeight = 0;
            for (int itemPos = 0; itemPos < numberOfItems; itemPos++) {
                View item = listAdapter.getView(itemPos, null, listView);
                float px = 500 * (listView.getResources().getDisplayMetrics().density);
                item.measure(View.MeasureSpec.makeMeasureSpec((int) px, View.MeasureSpec.AT_MOST), View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
                totalItemsHeight += item.getMeasuredHeight();
            }

            // Get total height of all item dividers.
            int totalDividersHeight = listView.getDividerHeight() *
                    (numberOfItems - 1);
            // Get padding
            int totalPadding = listView.getPaddingTop() + listView.getPaddingBottom();

            // Set list height.
            ViewGroup.LayoutParams params = listView.getLayoutParams();
            params.height = totalItemsHeight + totalDividersHeight + totalPadding;
            listView.setLayoutParams(params);
            listView.requestLayout();
            return true;

        } else {
            return false;
        }

    }

    public static Bitmap resizeBitmap(Bitmap source, int maxLength) {
        try {
            int targetWidth = maxLength;

            if (source.getWidth() <= targetWidth) { // if image already smaller than the required height
                return source;
            }

            double aspectRatio = ((double) source.getHeight()) / ((double) source.getWidth());
            int targetHeight = (int) (targetWidth * aspectRatio);

            Bitmap result = Bitmap.createScaledBitmap(source, targetWidth, targetHeight, false);
            if (result != source) {
            }
            return result;
        } catch (Exception e) {
            return source;
        }
    }

    public static Bitmap loadBitmapFromView(View v) {
        Bitmap b = Bitmap.createBitmap(v.getMeasuredWidth(), v.getMeasuredHeight(), Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(b);
        v.layout(v.getLeft(), v.getTop(), v.getRight(), v.getBottom());
        v.draw(c);
        return b;
    }
}
