package com.example.assignment2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity
{
    private DrawerLayout mDrawerLayout;     //DrawerLayout instance varible
    private ActionBarDrawerToggle mToogle;  //ActionBarDrawerToggle instance variable

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDrawerLayout=(DrawerLayout)findViewById(R.id.drawer_layout);
        mToogle=new ActionBarDrawerToggle(this,mDrawerLayout,R.string.open,R.string.close); //Instatiating DrawerLayout in ActionBarDrawerToggle with
                                                                                 //Open and close mechanisms
        mDrawerLayout.addDrawerListener(mToogle);
        mToogle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);         //Enabiling Navigation Drawer as Home in each page
        setNavigationDrawer();
    }

    //Function to set Action to each item of Navigation Drawer
    private void setNavigationDrawer()
    {
        NavigationView navView =(NavigationView)findViewById(R.id.navView); //Instatiating NavigationView
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener()
        {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem)
            {
                Fragment frag=null;
                int itemId=menuItem.getItemId();  //Getting particular menuItem Id
                if(itemId==R.id.navInsert)
                {
                    frag=new InsertFragment();       //Instating particular Fragment based on selected Menu Item Id
                }
                else if(itemId==R.id.navView1)
                {
                    frag=new ViewFragment();
                }
                else if(itemId==R.id.navSearch)
                {
                    frag=new SearchFragment();
                }
                else if(itemId==R.id.navUpdate)
                {
                    frag=new UpdateFragment();
                }
                else if(itemId==R.id.navDelete)
                {
                    frag=new DeleteFragment();
                }
                Toast.makeText(getApplicationContext(),menuItem.getTitle(),Toast.LENGTH_SHORT).show();
                if(frag!=null)
                {
                    //Instantiating FragmentTransaction to start trasaction of Fragment
                    FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.frame,frag);
                    transaction.commit();
                    mDrawerLayout.closeDrawers();
                    return true;

                }
                return false;
            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem)
    {
        if(mToogle.onOptionsItemSelected(menuItem))
        {
            return true;
        }
        return super.onOptionsItemSelected(menuItem);
    }

}
