package com.example.hp.justjava;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    int noOfCoffes = 0, wc = 0, ch = 0, p = 10;

    public void submitOrder(View view) {
        display(noOfCoffes);
        toppings();
        int price = calPrice();
        displayPrice(price);
    }

    public void increment(View view) {
        if (noOfCoffes == 100) {
            Toast.makeText(this, "U have had enough !", Toast.LENGTH_SHORT).show();
            return;
        }
        noOfCoffes++;
        display(noOfCoffes);
    }

    public void decrement(View view) {
        if (noOfCoffes == 0) {
            Toast.makeText(this, "What do u mean?", Toast.LENGTH_SHORT).show();
            return;
        }
        noOfCoffes--;
        display(noOfCoffes);
    }

    public int calPrice() {
        return noOfCoffes * p;
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * This method displays the given price on the screen.
     */
    public void toppings() {
        CheckBox wc_cb = (CheckBox) findViewById(R.id.whip_cb);
        if (wc_cb.isChecked() == true) {
            wc = 1;
            p += 2;
        } else wc = 0;
        CheckBox ch_cb = (CheckBox) findViewById(R.id.choco_cb);
        if (ch_cb.isChecked() == true) {
            ch = 1;
            p += 3;
        } else ch = 0;
    }

    private void displayPrice(int number) {
        EditText name = (EditText) findViewById(R.id.name);
        String mes = "Name : " + name.getText().toString();
        mes += "\nQuantity : " + noOfCoffes;
        if (wc == 1)
            mes += "\nWhipped cream added!";
        if (ch == 1)
            mes += "\nChocolate added!";
        mes += "\nThanks, it will be $" + number + "\n Visit again!";
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, "just java order ");
        intent.putExtra(Intent.EXTRA_TEXT, mes);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }

    }
}
