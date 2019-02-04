package com.example.shivam.justjava;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * IMPORTANT: Make sure you are using the correct package name.
 * This example uses the package name:
 * package com.example.android.justjava
 * If you get an error when copying this code into Android studio, update it to match teh package name found
 * in the project's AndroidManifest.xml file.
 **/


/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {
      int numberOfCoffees=2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        // Calculate the price
        int price = calculatePrice();

        // Figure out if the user wants whipped cream topping
        CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.checkbox_meat);
        boolean hasWhippedCream = whippedCreamCheckBox.isChecked();
        if(hasWhippedCream==true)
            price=price+1;


        // Figure out if the user wants chocolate topping
        CheckBox chocolateCheckBox = (CheckBox) findViewById(R.id.choco_top);
        boolean hasChocolate = chocolateCheckBox.isChecked();
        if(hasChocolate==true)
            price=price+2;

        //Users Name
        EditText name_user= (EditText) findViewById(R.id.name_box);
        String name = name_user.getText().toString();

        // Display the order summary on the screen
        String message = createOrderSummary(name, price, hasWhippedCream, hasChocolate);
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, "Just Java coffee bill for "+name);
        intent.putExtra(Intent.EXTRA_TEXT,message);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
    public void increment(View view) {
       if(numberOfCoffees<100)
           numberOfCoffees++;
       else
           numberOfCoffees=100;
        display(numberOfCoffees);
    }

    /* This method decreases the number of coffees*/
    public void decrement(View view) {
        if(numberOfCoffees>1)
            numberOfCoffees--;
        else {
            Toast.makeText(MainActivity.this,"Coffee peeni hai toh peeyo varna jaao. Backchodi mut pelo", Toast.LENGTH_LONG).show();
            numberOfCoffees = 1;
        }
        display(numberOfCoffees);
    }
    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }
    /**
     * Calculates the price of the order.
     *
     *  quantity is the number of cups of coffee ordered
     */
    private int calculatePrice() {
        int price = numberOfCoffees* 5;
        return price;
    }
    /* This method creates order summary
     */
    private String createOrderSummary(String name, int price, boolean addWhippedCream, boolean addChocolate){
        String summary;
        summary="Name: "+name+"\nQuantity: "+numberOfCoffees+"\nAdd Whipped Cream? "+addWhippedCream+"\nAdd Chocolate? "+addChocolate+"\nTotal: "+price+"" + "\nThank You!";
        return summary;
    }

    }

