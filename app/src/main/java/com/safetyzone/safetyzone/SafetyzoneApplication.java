package com.safetyzone.safetyzone;

import android.app.Application;
import android.widget.Button;
import android.widget.ListView;

/**
 * Created by Zak on 25/10/2015.
 */
public class SafetyzoneApplication extends Application{

    //ContactData contactData;
    private ListView mContactListView;
    public static final int CONTACTLIMIT = 5;

    public static boolean isAddnew() {
        return addnew;
    }

    public static void setAddnew(boolean addnew) {
        SafetyzoneApplication.addnew = addnew;




    }

    public static boolean addnew = false;



    public static int getContactlimit()
    {
        return CONTACTLIMIT;
    }


}