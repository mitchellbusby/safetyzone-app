package com.safetyzone.safetyzone;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class GeneralActivity extends AppCompatActivity{
    private android.support.v7.widget.Toolbar toolbar;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private List<String> mDrawerList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_general);
        setUpToolbar();
        initialiseNavigationDrawer();
        changeFragment(mDrawerList.get(0));

    }

    public void setUpToolbar() {
        // Sets the toolbar for older APIs that don't support the tool bar such as ICS
        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.mainToolbar);
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setLogo(R.drawable.ic_launcher_24dp);
        setSupportActionBar(toolbar);
    }

    public void initialiseNavigationDrawer() {
        initialiseNavigationLinks();
        mDrawerLayout = (DrawerLayout) findViewById(R.id.activity_contacts);
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.app_name, R.string.app_name) {
            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
            }

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
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
        mDrawerList = new ArrayList<String>();

        // This should be dynamically generated somehow or based on something other than code
        mDrawerList.add("Home");
        mDrawerList.add("Follow Me");
        mDrawerList.add("Information");
        mDrawerList.add("Contacts");

        ListView mDrawerListView = (ListView) findViewById(R.id.nav_list);
        ListAdapter drawerListAdapter = new GeneralAdapter<String>(getBaseContext(), R.layout.list_style_navigation, R.id.list_navigation_item, mDrawerList);

        
        mDrawerListView.setAdapter(drawerListAdapter);
        mDrawerListView.setOnItemClickListener(new DrawerItemClickListener());

    }
    public void addContactsFragment() {
        android.support.v4.app.FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_placeholder, new ContactsFragment());
        ft.commit();
    }

    public void changeFragment(String fragmentName) {
        Fragment chosenFragment = null;
        switch (fragmentName) {
            case "Home": chosenFragment = new HomeFragment();
                break;
            case "Contacts": chosenFragment = new ContactsFragment();
                break;
            case "Follow Me": chosenFragment = new FollowMeFragment(); //
                break;
            case "Information": chosenFragment = new safetytipFragment();
                break;
            default:
                break;
        }
        if (chosenFragment!=null) {
            android.support.v4.app.FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.fragment_placeholder, chosenFragment);
            ft.commit();
            changeToolbarTitleText(fragmentName);
        }
        // Close drawers at the end tbh
        mDrawerLayout.closeDrawers();
    }
    public void changeToolbarTitleText(String newTitle) {
        getSupportActionBar().setTitle(newTitle);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Pass the event to ActionBarDrawerToggle, if it returns
        // true, then it has handled the app icon touch event
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        switch (item.getItemId()) {
            case R.id.action_settings:
                // User chose the "Settings" item, show the app settings UI...
                return true;

            case R.id.action_favorite:
                Fragment chosenFragment = new HomeFragment();
                android.support.v4.app.FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.fragment_placeholder, chosenFragment);
                ft.commit();
                changeToolbarTitleText("Home");
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }

    }

    private class DrawerItemClickListener implements AdapterView.OnItemClickListener {
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            changeFragment(mDrawerList.get(position));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }



}
