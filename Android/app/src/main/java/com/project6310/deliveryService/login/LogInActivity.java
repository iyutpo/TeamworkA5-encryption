package com.project6310.deliveryService.login;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LogInActivity extends AppCompatActivity {

    Button LogInButton, RegisterButton ;
    EditText account, password;
    String accountHolder, passwordHolder;
    Boolean EditTextEmptyHolder;
    SQLiteDatabase sqLiteDatabaseObj;
    SQLiteHelper sqLiteHelper;
    Cursor cursor;
    String tempPassword = "NOT_FOUND" ;
    public static final String userEmail = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LogInButton = (Button)findViewById(R.id.buttonLogin);

        RegisterButton = (Button)findViewById(R.id.buttonRegister);

        account = (EditText)findViewById(R.id.editEmail);
        password = (EditText)findViewById(R.id.editPassword);

        sqLiteHelper = new SQLiteHelper(this);

        LogInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CheckEditTextStatus();
                LoginFunction();
            }
        });

        RegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LogInActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

    }

    public void LoginFunction(){

        if(EditTextEmptyHolder) {

            sqLiteDatabaseObj = sqLiteHelper.getWritableDatabase();

            cursor = sqLiteDatabaseObj.query(SQLiteHelper.TABLE_NAME, null, " " + SQLiteHelper.Account + "=?", new String[]{accountHolder}, null, null, null);

            while (cursor.moveToNext()) {

                if (cursor.isFirst()) {

                    cursor.moveToFirst();

                    tempPassword = cursor.getString(cursor.getColumnIndex(SQLiteHelper.Password));

                    cursor.close();
                }
            }

            CheckFinalResult();

        }
        else {

            Toast.makeText(LogInActivity.this,"Please Enter Account or Password.",Toast.LENGTH_LONG).show();

        }

    }

    public void CheckEditTextStatus(){

        accountHolder = account.getText().toString();
        passwordHolder = password.getText().toString();

        EditTextEmptyHolder = !TextUtils.isEmpty(accountHolder) && !TextUtils.isEmpty(passwordHolder);
    }

    public void CheckFinalResult(){

        if(tempPassword.equalsIgnoreCase(passwordHolder))
        {

            Toast.makeText(LogInActivity.this,"Login Successful",Toast.LENGTH_LONG).show();

            Intent intent = new Intent(LogInActivity.this, CustomerDashboardActivity.class);

            intent.putExtra(userEmail, accountHolder);

            startActivity(intent);


        }
        else {

            Toast.makeText(LogInActivity.this,"Account or Password is Wrong, Please Try Again.",Toast.LENGTH_LONG).show();

        }
        tempPassword = "NOT_FOUND" ;

    }

}