package com.robgthai.spoon.hellospoon.google;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.PopupMenu.OnMenuItemClickListener;
import android.widget.TextView;

import com.robgthai.spoon.hellospoon.R;

/**
 * Shows MenuActivity with Options menu, Context menu and Popup menu. Click on a menu item changes
 * text of R.id.textMenuResult.
 */
public class MenuActivity extends ActionBarActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.menu_activity);
    registerForContextMenu(findViewById(R.id.text_context_menu));
  }

  @Override
  public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
    super.onCreateContextMenu(menu, v, menuInfo);
    MenuInflater inflater = getMenuInflater();
    inflater.inflate(R.menu.contextmenu, menu);
  }



  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    TextView text = (TextView) findViewById(R.id.text_menu_result);
    text.setText(item.getTitle());
    return true;
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    MenuInflater inflater = getMenuInflater();
    inflater.inflate(R.menu.optionsmenu, menu);
    return true;
  }

  @Override
  public boolean onContextItemSelected(MenuItem item) {
    TextView text = (TextView) findViewById(R.id.text_menu_result);
    text.setText(item.getTitle());
    return true;
  }

  public void showPopup(View view) {
    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB) {
      TextView text = (TextView) findViewById(R.id.text_menu_result);
      text.setText("Not supported in API " + Build.VERSION.SDK_INT);
    } else {
      PopupMenu popup = new PopupMenu(this, view);
      popup.setOnMenuItemClickListener(new PopupMenuListener());
      popup.getMenuInflater().inflate(R.menu.popupmenu, popup.getMenu());
      popup.show();
    }
  }

  private class PopupMenuListener implements OnMenuItemClickListener {
    @Override
    public boolean onMenuItemClick(MenuItem item) {
      TextView text = (TextView) findViewById(R.id.text_menu_result);
      text.setText(item.getTitle());
      return true;
    }
  }
}
