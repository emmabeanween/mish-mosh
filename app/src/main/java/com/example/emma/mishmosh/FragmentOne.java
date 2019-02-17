package com.example.emma.mishmosh;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;
import com.bumptech.glide.request.target.Target;
import com.firebase.ui.database.FirebaseListAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import net.danlew.android.joda.JodaTimeAndroid;

import org.joda.time.DateTimeUtils;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Emma on 7/10/17.
 */

public class FragmentOne extends android.support.v4.app.Fragment {

    private Button mAddCityButton;
    private int REQUEST_CODE = 1;
    private String DIALOG_SEARCH = "Dialog_Search";
    private FirebaseListAdapter<City> mAdapter;
    private TextView mCityTitleView;
    private Button mCityTimeButton;
    private ListView mCityListView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        JodaTimeAndroid.init(getContext());
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode!=REQUEST_CODE){

            return;

        }

        if (resultCode!= Activity.RESULT_OK){

            return;
        }

        Log.e("TAG", "in the result");
       
        // get the city selected
        String city_selected = SearchFragment.getCity(data);
        City mCity = new City();
        mCity.setTitle(city_selected);


        DatabaseReference mReference  = FirebaseDatabase.getInstance().getReference("cities");
        String id = mReference.push().getKey();




        // set value
        mReference.child(id).setValue(mCity).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {


                if (task.isSuccessful()){


                    Log.i("TAG", "Task went well");

                }

                else {

                    Log.i("TAG", "Task failed");
                }


            }
        });





    }




    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_one_layout, container, false);



        // set up firebase adapter

        DatabaseReference mReference = FirebaseDatabase.getInstance().
                getReferenceFromUrl("https://emmas-projects.firebaseio.com/cities");

         mAdapter = new FirebaseListAdapter<City>(getActivity(), City.class,R.layout.custom_layout, mReference ) {
             @Override
             protected void populateView(View v, City model, int position) {


                     mCityTitleView = (TextView)v.findViewById(R.id.city_title_view);
                     mCityTimeButton = (Button)v.findViewById(R.id.time_view);

                     // Set their text

                     mCityTitleView.setText(model.getTitle());
                     // get time based on each model's title, using joda-time
                     String currentZone = "America/" + model.getTitle();
                  //  String currentZone = "America" + "/" + model.getTitle();



                     DateTimeZone dz = DateTimeZone.forID(currentZone);
                     String tzid = dz.getShortName(DateTimeUtils.currentTimeMillis());
                     // format that with minute, second, and am/pm
                     SimpleDateFormat dateFormat = new SimpleDateFormat(" hh:mm  aa");
                     String formattedTime = dateFormat.format(tzid);
                     mCityTimeButton.setText(formattedTime);














             }
         };



   mCityListView = (ListView)v.findViewById(R.id.this_list_view);
   mCityListView.setAdapter(mAdapter);




        // gif of clock
        ImageView iView = (ImageView) v.findViewById(R.id.imageView2);
        GlideDrawableImageViewTarget imageViewPreview = new GlideDrawableImageViewTarget(iView);
        Glide

                .with(this)
                .load("https://media.giphy.com/media/uUNskMnf4LdSg/giphy.gif")
                .listener(new RequestListener<String, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        return false;
                    }
                })
                .into(imageViewPreview);



        mAddCityButton = (Button) v.findViewById(R.id.button);
        mAddCityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                android.support.v4.app.FragmentManager manager = getActivity().getSupportFragmentManager();
                SearchFragment searchFragment = new SearchFragment();
                searchFragment.setTargetFragment(FragmentOne.this, REQUEST_CODE);
                searchFragment.show(manager, DIALOG_SEARCH);


                // go to Search Fragment



            }

        } );




    return v;






    }















}





















