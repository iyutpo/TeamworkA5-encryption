package com.project6310.deliveryService.login;

/**
 * Created by Shashank on 14-Feb-18.
 */
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class CustomerDashboardActivity extends AppCompatActivity {

    String AccountHolder;
    TextView Account;
    Button LogOUT ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        Account = (TextView)findViewById(R.id.textView1);
        LogOUT = (Button)findViewById(R.id.button1);

        Intent intent = getIntent();

        // Receiving User Email Send By MainActivity.
        AccountHolder = intent.getStringExtra(LogInActivity.userEmail);

        // Setting up received email to TextView.
        Account.setText(Account.getText().toString()+ AccountHolder);

        // Adding click listener to Log Out button.
        LogOUT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Finishing current DashBoard activity on button click.
                finish();

                Toast.makeText(CustomerDashboardActivity.this,"Log Out Successful", Toast.LENGTH_LONG).show();

            }
        });

    }
}