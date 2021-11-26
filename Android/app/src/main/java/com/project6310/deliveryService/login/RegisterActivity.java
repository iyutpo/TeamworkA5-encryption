package com.project6310.deliveryService.login;

/**
 * Created by Shashank on 14-Feb-18.
 */

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class
RegisterActivity extends AppCompatActivity {

    EditText account, firstName, lastName, phoneNumber, password, rating, credit ;
    Button Register;
    String accountHolder, firstNameHolder, lastNameHolder, phoneNumberHolder, passwordHolder, ratingHolder, creditHolder;
    Boolean EditTextEmptyHolder;
    SQLiteDatabase sqLiteDatabaseObj;
    String SQLiteDataBaseQueryHolder ;
    SQLiteHelper sqLiteHelper;
    Cursor cursor;
    String F_Result = "Not_Found";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Register = (Button)findViewById(R.id.buttonRegister);

        account= findViewById(R.id.editAccount);
        firstName= findViewById(R.id.editFirstName);
        lastName=findViewById(R.id.editLastName);
        phoneNumber= findViewById(R.id.editPhoneNumber);
        password=findViewById(R.id.editPassword);
        rating= findViewById(R.id.editRating);
        credit=findViewById(R.id.editCredit);

        sqLiteHelper = new SQLiteHelper(this);

        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SQLiteDataBaseBuild();

                SQLiteTableBuild();

                CheckEditTextStatus();

                CheckingEmailAlreadyExistsOrNot();

                EmptyEditTextAfterDataInsert();


            }
        });

    }

    public void SQLiteDataBaseBuild(){

        sqLiteDatabaseObj = openOrCreateDatabase(SQLiteHelper.DATABASE_NAME, Context.MODE_PRIVATE, null);

    }

    public void SQLiteTableBuild() {

        sqLiteDatabaseObj.execSQL(
                "CREATE TABLE IF NOT EXISTS "
                        + SQLiteHelper.TABLE_NAME
                        + "(" + SQLiteHelper.Id + " PRIMARY KEY AUTOINCREMENT NOT NULL, "
                        + SQLiteHelper.Account + "VARCHAR, "
                        + SQLiteHelper.First_Name + " VARCHAR, "
                        + SQLiteHelper.Last_Name + " VARCHAR, "
                        + SQLiteHelper.Phone_Number + " VARCHAR, "
                        + SQLiteHelper.Password + " VARCHAR, "
                        + SQLiteHelper.Rating + " VARCHAR, "
                        + SQLiteHelper.Credit + " VARCHAR);"
        );
    }

    public void InsertDataIntoSQLiteDatabase(){

        if(EditTextEmptyHolder)
        {

            SQLiteDataBaseQueryHolder = "INSERT INTO "+SQLiteHelper.TABLE_NAME+" (account, firstName, lastName, phoneNumber, password, rating, credit) VALUES('"+accountHolder+"', '"+firstNameHolder+"', '"+lastNameHolder+"','"+phoneNumberHolder+"','"+passwordHolder+"','"+ratingHolder+"','"+creditHolder+"');";

            sqLiteDatabaseObj.execSQL(SQLiteDataBaseQueryHolder);

            sqLiteDatabaseObj.close();

            Toast.makeText(RegisterActivity.this,"User Registered Successfully", Toast.LENGTH_LONG).show();

        }
        else {

            Toast.makeText(RegisterActivity.this,"Please Fill All The Required Fields.", Toast.LENGTH_LONG).show();
        }

    }

    public void EmptyEditTextAfterDataInsert(){

        account.getText().clear();
        firstName.getText().clear();
        lastName.getText().clear();
        phoneNumber.getText().clear();
        password.getText().clear();
        rating.getText().clear();
        credit.getText().clear();
    }

    public void CheckEditTextStatus(){

        // Getting value from All EditText and storing into String Variables.
        accountHolder = account.getText().toString();
        firstNameHolder = firstName.getText().toString();
        lastNameHolder = lastName.getText().toString();
        phoneNumberHolder = phoneNumber.getText().toString();
        passwordHolder = password.getText().toString();
        ratingHolder = rating.getText().toString();
        creditHolder = credit.getText().toString();

        EditTextEmptyHolder = !TextUtils.isEmpty(accountHolder) && !TextUtils.isEmpty(firstNameHolder) && !TextUtils.isEmpty(lastNameHolder) && !TextUtils.isEmpty(phoneNumberHolder) && !TextUtils.isEmpty(passwordHolder) && !TextUtils.isEmpty(ratingHolder) && !TextUtils.isEmpty(creditHolder);
    }

    public void CheckingEmailAlreadyExistsOrNot(){

        sqLiteDatabaseObj = sqLiteHelper.getWritableDatabase();

        cursor = sqLiteDatabaseObj.query(SQLiteHelper.TABLE_NAME, null, " " + SQLiteHelper.Account + "=?", new String[]{accountHolder}, null, null, null);

        while (cursor.moveToNext()) {

            if (cursor.isFirst()) {

                cursor.moveToFirst();

                F_Result = "Account Found";

                cursor.close();
            }
        }

        CheckFinalResult();

    }


    public void CheckFinalResult(){

        if(F_Result.equalsIgnoreCase("Account Found"))
        {

            Toast.makeText(RegisterActivity.this,"Account Already Exists",Toast.LENGTH_LONG).show();

        }
        else {

            InsertDataIntoSQLiteDatabase();

        }

        F_Result = "Not_Found" ;

    }

}