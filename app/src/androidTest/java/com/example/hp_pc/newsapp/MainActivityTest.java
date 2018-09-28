package com.example.hp_pc.newsapp;

import android.app.Instrumentation;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.AdapterView;

import com.example.hp_pc.newsapp.Fragments.MostViewedFragment;

import org.hamcrest.Matcher;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.core.AllOf.allOf;
import static org.junit.Assert.*;

public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> testRule=new ActivityTestRule<MainActivity>(MainActivity.class);

    public MainActivity mActivity=null;


    @Before
    public void setUp() throws Exception {
        mActivity=testRule.getActivity();
    }


    @Test
    public void testLaunch(){
        View view=mActivity.findViewById(R.id.nav_view);
        assertNotNull(view);

    }
    @Test
    public void testFragmentLoading(){
        onView(allOf(
                withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE),
                withId(R.id.fragmentContainer)))
                .check(matches(isDisplayed()));
    }


    @After
    public void tearDown() throws Exception {
        mActivity=null;
    }
}