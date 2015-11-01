package com.safetyzone.safetyzone;

import android.app.Application;
import android.content.Context;
import android.location.Location;
import android.widget.Button;
import android.widget.ListView;

/**
 * Created by Zak on 25/10/2015.
 */
public class SafetyzoneApplication extends Application{


    public static SafetyzoneApplication instance;

    public SafetyzoneApplication()
    {
        super();
    }

    public static synchronized SafetyzoneApplication get()
    {
        if (instance == null) {
            instance = new SafetyzoneApplication();
        }
        return instance;
    }



    //ContactData contactData;
    Location lastlocation;

    private ListView mContactListView;
    public static final int CONTACTLIMIT = 5;

    public static boolean isAddnew() {
        return addnew;
    }

    public static void setAddnew(boolean addnew) {
        SafetyzoneApplication.addnew = addnew;




    }

    public static boolean addnew = false;

    public Location getLastlocation() {
        return lastlocation;
    }

    public void setLastlocation(Location lastlocation) {
        this.lastlocation = lastlocation;
    }



    public static int getContactlimit()
    {
        return CONTACTLIMIT;
    }


}
