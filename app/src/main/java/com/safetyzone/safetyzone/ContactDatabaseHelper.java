package com.safetyzone.safetyzone;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteCursorDriver;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQuery;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zak on 8/10/2015.
 */
public class ContactDatabaseHelper extends SQLiteOpenHelper {

    //the database version
    private static final int DATABASE_VERSION = 1;

    // the filename / database name referd to
    private static final String DATABASE_NAME = "contact.db";

    // Singleton instance of this helper
    private static ContactDatabaseHelper instance;

    private ContactDatabaseHelper(Context context)
    {
        super(context, DATABASE_NAME, new ContactCursorFactory(), DATABASE_VERSION);
    }

    public static synchronized ContactDatabaseHelper get(Context context)
    {
        if (instance == null) {
            instance = new ContactDatabaseHelper(context.getApplicationContext());
        }
        return instance;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String createContactSql = "create table " + ContactData.TABLE + " ("
                + ContactData.COLUMN_ID + " integer primary key autoincrement, "
                + ContactData.COLUMN_NAME + " text not null, "
                + ContactData.COLUMN_NUMBER + " integer not null, "
                + ContactData.COLUMN_CONTACT_SINCE + " integer not null)";
        db.execSQL(createContactSql);

        //addContact(new ContactData(1, "zak", 4583, 8080808));
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        /*
        will be called if something calls a version of the database
        that is higher than current version
         */
    }

    public void addContact(ContactData contactData)
    {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(ContactData.COLUMN_NAME, contactData.getmName());
        values.put(ContactData.COLUMN_NUMBER, contactData.getmNumber());
        values.put(ContactData.COLUMN_CONTACT_SINCE, contactData.getmContactSince());
        db.insertOrThrow(ContactData.TABLE, null, values);
    }

    public void removeContact(ContactData contactData)
    {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(ContactData.TABLE, ContactData.COLUMN_ID + " = ?", new String[]{String.valueOf(contactData.getmId())});
    }

    public List<ContactData> getContactDataList(String name)
    {
        List<ContactData> list = new ArrayList<>();

        ContactCursor cursor = getContactCursor(name);
        if (cursor.moveToFirst()) {
            do {
                list.add(cursor.getContactData());
            } while (cursor.moveToNext());
        }
        cursor.close();

        return list;
    }

    public ContactCursor getContactCursor(String name)
    {
        // Default to showing all contact data
        String where = null; //sql string
        String[] whereArgs = null;

        // If a name is specified, filter the results
        if (name != null) {
            where = ContactData.COLUMN_NAME + " = ?";
            whereArgs = new String[]{name};
        }

        return (ContactCursor) getReadableDatabase().query(ContactData.TABLE, ContactData.COLUMNS, where, whereArgs, null, null, null);
    }

    /**
     * A custom cursor factory which creates instances of the cursor.
     */
    private static class ContactCursorFactory implements SQLiteDatabase.CursorFactory {

        @Override
        public Cursor newCursor(SQLiteDatabase db, SQLiteCursorDriver masterQuery, String editTable, SQLiteQuery query)
        {
            return new ContactCursor(masterQuery, editTable, query);
        }
    }


    /**
     * A custom cursor, makes getcontactData() method to return the current row
     */
    public static class ContactCursor extends SQLiteCursor {
        public ContactCursor(SQLiteCursorDriver driver, String editTable, SQLiteQuery query)
        {
            super(driver, editTable, query);
        }

        public ContactData getContactData()
        {
            return new ContactData(this);
        }
    }


}
