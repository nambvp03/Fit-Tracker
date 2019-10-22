/* Copyright 2017 The TensorFlow Authors. All Rights Reserved.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
==============================================================================*/

package com.codingminds.fittracker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import io.paperdb.Paper;

/** Main {@code Activity} class for the Camera app. */
public class AddEntryActivity extends Activity {

  ImageView foodImageView;
  TextView foodTitleTextView;

  SeekBar seekBar;
  TextView curTextView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_addentry);

    foodImageView = findViewById(R.id.imageViewFood);
    foodTitleTextView = findViewById(R.id.textViewTitle);

    seekBar = findViewById(R.id.seekBar);
    curTextView = findViewById(R.id.textViewCurrent);

    foodImageView.setImageBitmap(FoodResult.foodImage);
    foodTitleTextView.setText(FoodResult.foodLabel);

    seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
      @Override
      public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
        curTextView.setText("Serving: " + i);
      }

      @Override
      public void onStartTrackingTouch(SeekBar seekBar) {

      }

      @Override
      public void onStopTrackingTouch(SeekBar seekBar) {

      }
    });

    Button buttonAdd = findViewById(R.id.buttonAdd);
    buttonAdd.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        FoodEntry foodEntry = new FoodEntry();
        foodEntry.setName(FoodResult.foodLabel);
        foodEntry.setServing(seekBar.getProgress());
        foodEntry.setTimestamp(System.currentTimeMillis());

        List<FoodEntry> foodList = Paper.book().read("foodlist", new ArrayList<FoodEntry>());
        foodList.add(foodEntry);
        Paper.book().write("foodlist", foodList);

        Intent intent = new Intent(AddEntryActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
      }
    });

    Button buttonCancel = findViewById(R.id.buttonCancel);
    buttonCancel.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Intent intent = new Intent(AddEntryActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
      }
    });
  }
}
