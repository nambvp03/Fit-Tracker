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
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.paperdb.Paper;

/** Main {@code Activity} class for the Camera app. */
public class MainActivity extends Activity {

  ListView historyListView;
  TextView statusDetailsTextView;
  TextView todayStatusDetailsTextView;

  FoodListListAdapter foodListListAdapter;

  @Override
  public <T extends View> T findViewById(int id) {
    return super.findViewById(id);
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    historyListView = findViewById(R.id.listViewHistory);
    statusDetailsTextView = findViewById(R.id.textViewStatusDetails);
    todayStatusDetailsTextView = findViewById(R.id.textViewTodayStatusDetails);

    List<FoodEntry> foodList = Paper.book().read("foodlist", new ArrayList<FoodEntry>());
    Log.i("TEST", foodList.toString());
    foodListListAdapter = new FoodListListAdapter(this, R.layout.listview_history_item, foodList);
    historyListView.setAdapter(foodListListAdapter);

    int calTotal = 0;
    for (int i = 0; i < foodList.size(); i++) {
      calTotal += foodList.get(i).getCalTotal();
    }
    int calAvg = foodList.size() == 0 ? 0: calTotal / foodList.size();
    if (calAvg > 500)
      statusDetailsTextView.setText("Be careful!");
    else if (calAvg < 100)
      statusDetailsTextView.setText("Eat more!");
    else
      statusDetailsTextView.setText("Good Job!");

    SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
    int calTotalToday = 0;
    for (int i = 0; i < foodList.size(); i++) {
      Date today = new Date(System.currentTimeMillis());
      Date date = new Date(foodList.get(i).getTimestamp());
      sf.format(today);
      sf.format(date);
      if (today.equals(date))
        calTotalToday += foodList.get(i).getCalTotal();
    }
    if (calTotalToday > 3000)
      todayStatusDetailsTextView.setText("Be careful!");
    else if (calTotalToday < 1000)
      todayStatusDetailsTextView.setText("Eat more!");
    else
      todayStatusDetailsTextView.setText("Good Job!");

    Button button = findViewById(R.id.buttonAddEntry);
    button.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Intent intent = new Intent(MainActivity.this, CameraActivity.class);
        startActivity(intent);
      }
    });
  }
}
