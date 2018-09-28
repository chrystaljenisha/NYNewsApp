package com.example.hp_pc.newsapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hp_pc.newsapp.Adapter.ViewPagerAdapter;
import com.example.hp_pc.newsapp.Utils.FetchDataAsyncTask;
import com.example.hp_pc.newsapp.Fragments.ArtsFragment;
import com.example.hp_pc.newsapp.Fragments.MostViewedFragment;
import com.example.hp_pc.newsapp.Fragments.SportsFragment;
import com.example.hp_pc.newsapp.Fragments.TopStoriesFragment;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    private TabLayout tabLayout;
    private Toolbar mtoolbar;
    private ViewPager fragmentContainer;
    private DrawerLayout mDrawerLayout;

    @Override
    public void onBackPressed() {
        String fragmentTag = makeFragmentName(fragmentContainer.getId(), 1);
        MostViewedFragment fragment = (MostViewedFragment) getSupportFragmentManager().findFragmentByTag(fragmentTag);
        if (fragment instanceof MostViewedFragment) {
               boolean flag=fragment.goBack();
               if(!flag)
                   super.onBackPressed();
            }
            else {
            super.onBackPressed();
        }
    }

    private static String makeFragmentName(int viewId, long id) {
        return "android:switcher:" + viewId + ":" + id;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUpToolBarMenu();
        setUpNavigationMenu();
        tabLayout = (TabLayout) findViewById(R.id.tabId);
        fragmentContainer = (ViewPager) findViewById(R.id.fragmentContainer);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragments(new TopStoriesFragment(), "Top Stories");
        adapter.addFragments(new MostViewedFragment(), "Most Viewed");
        adapter.addFragments(new SportsFragment(), "Sports");
        adapter.addFragments(new ArtsFragment(), "Art");
        fragmentContainer.setAdapter(adapter);
        tabLayout.setupWithViewPager(fragmentContainer);
    }

    private void setUpNavigationMenu() {
        NavigationView navView = findViewById(R.id.nav_view);
        navView.setNavigationItemSelectedListener(this);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_id);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, mtoolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }

    private void setUpToolBarMenu() {
        mtoolbar = (Toolbar) findViewById(R.id.toolBar);
        mtoolbar.setTitle(R.string.nav);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int id = menuItem.getItemId();
        Fragment fragment = null;
        if (id == R.id.nav_mostViewed) {
           fragmentContainer.setCurrentItem(1);
        } else if (id == R.id.nav_arts) {
            fragmentContainer.setCurrentItem(3);
        } else if (id == R.id.nav_sports) {
            fragmentContainer.setCurrentItem(2);
        } else if (id == R.id.nav_topStories) {
            fragmentContainer.setCurrentItem(0);
        }
       /* if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.fragmentContainer, fragment);
            ft.commit();
        }*/
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_id);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
