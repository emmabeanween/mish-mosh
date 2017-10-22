package com.example.emma.mishmosh;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.text.DecimalFormat;
import java.util.stream.IntStream;

/**
 * Created by Emma on 7/10/17.
 */

public class FragmentThree extends android.support.v4.app.Fragment {

    private EditText mWeightEntry;
    private Spinner mHeightSpinner;
    private Button mgetBmiButton;
    private double height;
    private double weight;
    private double BMI;
    private String BMIcategory;
    private TextView BMITextView;
    private TextView BMICategoryTextView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_three_layout, container, false);


        BMITextView = (TextView)v.findViewById(R.id.BMI_text_view);
        BMICategoryTextView = (TextView)v.findViewById(R.id.BMI_category_text_view);


// all heights between 2 foot and 8 foot
String [] heights = {"24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36",
                     "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50",
                      "51", "52", "53", "54", "55", "56", "57", "58", "59", "60", "61", "62", "63", "64",
                       "65", "66", "67", "68", "69", "70", "71", "72", "73", "74", "75", "76", "77", "78", "79",
                         "80", "81", "82", "83", "84", "85", "86", "87", "88", "89", "90", "91", "92", "93"
                        , "94", "95", "96"};
     mHeightSpinner = (Spinner)v.findViewById(R.id.height_spinner);
        ArrayAdapter<String> mAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item,
                heights);


                //new ArrayAdapter<Integer>(getContext(), android.R.layout.simple_spinner_item, heights);
        mAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mHeightSpinner.setAdapter(mAdapter);

        mHeightSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                height = Double.parseDouble(adapterView.getItemAtPosition(i).toString());

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                height = 0.0;

            }
        });




     mWeightEntry = (EditText)v.findViewById(R.id.weight_edit_text);
     mWeightEntry.addTextChangedListener(new TextWatcher() {
         @Override
         public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

         }

         @Override
         public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

             try {
                 weight = new Double(mWeightEntry.getText().toString());
             } catch (NumberFormatException e) {
                 weight = 0.0; // your default value
             }

         }
         @Override
         public void afterTextChanged(Editable editable) {

         }
     });



     mgetBmiButton = (Button)v.findViewById(R.id.get_bmi_button);
     mgetBmiButton.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
              if (height == 0.0 | weight == 0.0){
                   // user didn't enter height and/or weight
                  Toast.makeText(getContext(), "Please enter your height and/or weight.", Toast.LENGTH_SHORT).show();

              }

              else {

                  //calculate BMI



                  double pounds = weight * 0.45;
                  double heightininches = height * 0.025;
                  double squaredanswer = heightininches * heightininches;

                  DecimalFormat oneDigit = new DecimalFormat("#,##0.0");

                  BMI = Double.valueOf(oneDigit.format(pounds /squaredanswer));


                  // establish the user's BMI category

                  if (BMI <= 18.5){

                      BMIcategory = "Underweight. Please eat a cookie on us.";
                  }
                 else if (BMI > 18.5 && BMI  <= 25.0)
                  BMIcategory = "Normal weight. Congrats to you!";

                  else if (BMI >25.0 &&  BMI <= 30){
                        BMIcategory = "Overweight. You're pushing it, tubby.";

                  }

                  else {

                      BMIcategory = "Obese. It's okay, a good portion of America is.";
                  }

                  // make both textviews visible

                 BMITextView.setVisibility(View.VISIBLE);
                 BMICategoryTextView.setVisibility(View.VISIBLE);

                  // set text on both

                  BMITextView.setText(String.valueOf(BMI));
                  BMICategoryTextView.setText(BMIcategory);



              }




         }
     });

// set up spinner
        // get value of height and inches
        // when user hits SHOWBMI button, do an if/else statement for empty values
        // if empty: Toast
        // else:
        // make textview that shows bmi invisible
        // make textview that shows weight category invisible


























        return v;
    }
}
