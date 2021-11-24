package com.example.version3_355app.ui.settings;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.version3_355app.R;

import java.util.ArrayList;

public class Settings extends AppCompatActivity{
    ArrayList<String> settingsList = new ArrayList<String>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_settings);

        ListView listView = (ListView) findViewById(R.id.listview); // list in your XML

        settingsList.add("Account");
        settingsList.add("Notifications");
        settingsList.add("Theme");

    }
}

