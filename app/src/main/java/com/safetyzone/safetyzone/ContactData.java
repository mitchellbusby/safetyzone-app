package com.safetyzone.safetyzone;

import android.database.Cursor;
import android.provider.BaseColumns;

import java.io.Serializable;

/**
 * Created by Zak on 8/10/2015.
 */
public class ContactData implements Serializable {
    public static final String TABLE = "contact";
    public static final String COLUMN_ID = BaseColumns._ID;
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_NUMBER = "number"; //number?
    public static final String COLUMN_CONTACT_SINCE = "contact_since";
    public static final String COLUMN_DESIGNATED = "contact_since";
    public static final String[] COLUMNS = {COLUMN_ID, COLUMN_NAME, COLUMN_NUMBER, COLUMN_CONTACT_SINCE, COLUMN_DESIGNATED};

    private int mId;
    private String mName;
    private String mNumber;
    private long mContactSince;
    private int mDesignated;//cant do int

    public ContactData(int id, String name, String number, long contactSince, int designated) {
        mId = id;
        mName = name;
        mNumber = number;
        mContactSince = contactSince;
        mDesignated = designated;
    }

    /**
     * Constructor to create a contact using cursor
     */
    public ContactData(Cursor cursor) {
        mId = cursor.getInt(cursor.getColumnIndex(COLUMN_ID));
        mName = cursor.getString(cursor.getColumnIndex(COLUMN_NAME));
        mNumber = cursor.getString(cursor.getColumnIndex(COLUMN_NUMBER));
        mContactSince = cursor.getLong(cursor.getColumnIndex(COLUMN_CONTACT_SINCE));
        mDesignated = cursor.getInt(cursor.getColumnIndex(COLUMN_DESIGNATED));
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public String getmNumber() {
        return "0" + mNumber;
    }

    public void setmNumber(String mNumber) {
        this.mNumber = mNumber;
    }

    public long getmContactSince() {
        return mContactSince;
    }

    public void setmContactSince(long mContactSince) {
        this.mContactSince = mContactSince;
    }

//    public void setDesignated() {
//        mDesignated=0;
//    }

    public int isDesignated() {
        return mDesignated;
    }


}
