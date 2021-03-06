package com.robgthai.spoon.hellospoon.google;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.TextView;

import com.google.common.annotations.VisibleForTesting;
import com.robgthai.spoon.hellospoon.R;

import java.util.Random;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Displays "hello world" with a random delay of 2 to 7s after the user clicks on a button. This is
 * used to demonstrate how Espresso can synchronize with any part of your application, which may
 * cause the application state to be unstable (e.g. a network call).
 */
public class SyncActivity extends ActionBarActivity {

  /**
   * A server that returns a hello world string
   */
  public interface HelloWorldServer {
    String getHelloWorld();
  }

  private HelloWorldServer helloWorldServer;
  private TextView statusTextView;

  @Override
  protected void onCreate(Bundle icicle) {
    super.onCreate(icicle);

    setContentView(R.layout.sync_activity);

    statusTextView = checkNotNull(((TextView) findViewById(R.id.status_text)));

    setHelloWorldServer(new HelloWorldServer() {
      @Override
      public String getHelloWorld() {
        Random rand = new Random();
        SystemClock.sleep(rand.nextInt(5000) + 2000);
        return getString(R.string.hello_world);
      }
    });
  }

  public void onRequestButtonClick(@SuppressWarnings("unused") View view) {
    Thread t = new Thread() {
      @Override
      public void run() {
        final String helloworld = helloWorldServer.getHelloWorld();
        runOnUiThread(new Runnable() {
          @Override
          public void run() {
            setStatus(helloworld);
          }
        });
      }
    };
    t.start();
  }

  private void setStatus(String text) {
    statusTextView.setText(text);
  }

  @VisibleForTesting
  public HelloWorldServer getHelloWorldServer() {
    return helloWorldServer;
  }

  @VisibleForTesting
  public void setHelloWorldServer(HelloWorldServer helloWorldServer) {
    this.helloWorldServer = helloWorldServer;
  }
}
