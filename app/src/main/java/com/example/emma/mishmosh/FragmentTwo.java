package com.example.emma.mishmosh;

import android.app.Fragment;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Chronometer;

import java.util.Timer;

/**
 * Created by Emma on 7/10/17.
 */

public class FragmentTwo extends android.support.v4.app.Fragment {

    private Chronometer chronometer;
    private Button mStartTimer;
    private Button mPauseTimer;
    private Button mResetTimer;
    private long lastTimePaused;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_two_layout, container, false);


  chronometer = (Chronometer)v.findViewById(R.id.my_chronometer);
  mStartTimer = (Button)v.findViewById(R.id.start_timer);
  mPauseTimer = (Button)v.findViewById(R.id.pause_timer);
  mResetTimer = (Button)v.findViewById(R.id.reset_timer);


  mPauseTimer.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {

          lastTimePaused = SystemClock.elapsedRealtime();
          chronometer.stop();
          mStartTimer.setEnabled(true);
          mPauseTimer.setEnabled(false);

      }
  });

mStartTimer.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {

        // see if the lasttimepaused is equal to 0; if it isn't, start increasing time

        if (lastTimePaused!=0){

            chronometer.setBase(chronometer.getBase() + SystemClock.elapsedRealtime() - lastTimePaused);
            chronometer.start();
        }

        else {
          // don't start at base, since there isn't one

            chronometer.setBase(SystemClock.elapsedRealtime());
            chronometer.start();
        }


        // set the button to not be enabled once the user hits it
        mStartTimer.setEnabled(false);
        mPauseTimer.setEnabled(true);
    }
});

mResetTimer.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        chronometer.stop();
        // set lastpause to 0
        lastTimePaused = 0;
        chronometer.setBase(SystemClock.elapsedRealtime());

        // enable the start button
        mStartTimer.setEnabled(true);
        // disable the stop button, since there's nothing to stop anymore since it's back to 0
        mPauseTimer.setEnabled(false);
    }
});






return v;
    }
}
