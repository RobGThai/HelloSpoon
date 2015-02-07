package com.robgthai.spoon.hellospoon.google;


import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.TextView;

import com.robgthai.spoon.hellospoon.R;

/**
 * Simple activity used to display data received from another activity.
 */
public class DisplayActivity extends ActionBarActivity {

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.display_activity);
    TextView textView = (TextView) findViewById(R.id.display_data);
    textView.setText(getIntent().getStringExtra(SendActivity.EXTRA_DATA));
  }
}
