package com.sumup.sdksampleapp;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.sumup.merchant.api.SumUpAPI;
import com.sumup.merchant.api.SumUpPayment;


import java.util.UUID;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnCheckout = (Button) findViewById(R.id.button_charge);
        btnCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SumUpPayment payment = SumUpPayment.builder()
                        // mandatory parameters
                        // Your affiliate key is bound to the applicationID entered in the SumUp dashboard at https://me.sumup.com/integration-tools
                        .affiliateKey("7ca84f17-84a5-4140-8df6-6ebeed8540fc")
                        .productAmount(1.23)
                        .currency(SumUpPayment.Currency.EUR)
                        // optional: add details
                        .productTitle("Taxi Ride").receiptEmail("customer@mail.com").receiptSMS("+3531234567890")
                        // optional: Add metadata
                        .addAdditionalInfo("AccountId", "taxi0334")
                        .addAdditionalInfo("From", "Paris")
                        .addAdditionalInfo("To", "Berlin")
                        // optional: foreign transaction ID, must be unique!
                        .foreignTransactionId(UUID.randomUUID().toString()) // can not exceed 128 chars
                        .build();

                SumUpAPI.openPaymentActivity(MainActivity.this, ResponseActivity.class, payment);
            }
        });

        Button btnLogout = (Button) findViewById(R.id.button_logout);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SumUpAPI.logout();
            }
        });

        Button btnClearPinPlusSettings = (Button) findViewById(R.id.button_clear_pinplus_settings);
        btnClearPinPlusSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SumUpAPI.clearPinPlusSettings();
            }
        });

    }
}
