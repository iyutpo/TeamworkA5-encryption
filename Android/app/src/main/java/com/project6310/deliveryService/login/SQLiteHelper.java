package com.project6310.deliveryService.login;

/**
 * Created by Shashank on 14-Feb-18.
 */

import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;

public class SQLiteHelper extends SQLiteOpenHelper {

    static String DATABASE_NAME="DeliveryService";

    public static final String TABLE_NAME="CustomerTable";

    public static final String Id ="id";

    public static final String Account = "account";

    public static final String First_Name ="firstName";

    public static final String Last_Name ="lastName";

    public static final String Phone_Number ="phoneNumber";

    public static final String Password = "password";

    public static final String Rating ="rating";

    public static final String Credit ="credit";

    public SQLiteHelper(Context context) {

        super(context, DATABASE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase database) {

        String CREATE_TABLE="CREATE TABLE IF NOT EXISTS "+TABLE_NAME+" " +
                "("
                + Id +" INTEGER PRIMARY KEY, "
                + Account +" VARCHAR, "
                + First_Name +" VARCHAR, "
                + Last_Name +" VARCHAR, "
                + Phone_Number +" VARCHAR, "
                + Password +" VARCHAR, "
                + Rating +" VARCHAR, "
                + Credit +" VARCHAR"
                + ")";
        database.execSQL(CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);

    }

}