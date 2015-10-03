package com.safetyzone.safetyzone;

import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class GeneralActivity extends AppCompatActivity {
    android.support.v7.widget.Toolbar toolbar;
    DrawerLayout mDrawerLayout;
    ActionBarDrawerToggle mDrawerToggle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general);
        onCreateCustom();
        addContactsFragment();
        initialiseNavigationLinks();
        initialiseNavigationDrawer();
    }
    public void onCreateCustom() {
        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.mainToolbar);
        setSupportActionBar(toolbar);
        try {
            getSupportActionBar().setTitle("Blah");
        } catch (NullPointerException e) {

        }

        //toolbar.setTitle("Blah");
    }
    public void initialiseNavigationDrawer() {
        mDrawerLayout = (DrawerLayout) findViewById(R.id.activity_contacts);
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.app_name, R.string.app_name) {
            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                //getSupportActionBar().setTitle("Closed");
            }

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getSupportActionBar().setTitle("Open");
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);
    }
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }
    public void initialiseNavigationLinks() {
        List<String> mDrawerList = new ArrayList<String>();
        mDrawerList.add("Link 1");
        ListView mDrawerListView = (ListView) findViewById(R.id.nav_list);
        ListAdapter drawerListAdapter = new ArrayAdapter<String>(getBaseContext(), R.layout.list_navigation, R.id.list_navigation_item, mDrawerList);
        mDrawerListView.setAdapter(drawerListAdapter);
        //mDrawerListView.setOnItemClickListener(new DrawerItemClickListener());

    }
    public void addContactsFragment() {
        android.support.v4.app.FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_placeholder, new ContactsFragment());
        ft.commit();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Pass the event to ActionBarDrawerToggle, if it returns
        // true, then it has handled the app icon touch event
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        // Handle your other action bar items...

        return super.onOptionsItemSelected(item);
    }
    /*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_contacts, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);

    }*/
}
