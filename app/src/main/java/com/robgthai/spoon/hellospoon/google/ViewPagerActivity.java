package com.robgthai.spoon.hellospoon.google;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;

import com.robgthai.spoon.hellospoon.R;

/**
 * Activity to demonstrate actions on a {@link android.support.v4.view.ViewPager}.
 */
public class ViewPagerActivity extends ActionBarActivity {

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.pager_activity);

    final ViewPager pager = (ViewPager) findViewById(R.id.pager_layout);
    pager.setAdapter(new SimplePagerAdapter());
  }

}

