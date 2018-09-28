package com.example.hp_pc.newsapp;

import android.support.test.rule.ActivityTestRule;
import android.view.View;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

public class HomeActivityTest {
    @Rule
    public ActivityTestRule<HomeActivity> testRule=new ActivityTestRule<HomeActivity>(HomeActivity.class);

    public HomeActivity mActivity=null;

    @Before
    public void setUp() throws Exception {
        mActivity=testRule.getActivity();
    }

    @Test
    public void testLaunch(){
        View view=mActivity.findViewById(R.id.header);
        assertNotNull(view);
    }

    @After
    public void tearDown() throws Exception {
        mActivity=null;
    }
}