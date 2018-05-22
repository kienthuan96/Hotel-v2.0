package com.example.thuan.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ListView;

public class SearchActivity extends AppCompatActivity {
    private ListView listView;
    private AutoCompleteTextView auto;
    private Button btnClick;
    private ArrayAdapter<Integer> adapter;
    Integer tuyenXe[] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        auto = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView);
        adapter = new ArrayAdapter<Integer>(this, android.R.layout.simple_dropdown_item_1line, tuyenXe);
        auto.setAdapter(adapter);
        auto.setDropDownHeight(400);

        listView = (ListView) findViewById(R.id.list_view);
        adapter = new ArrayAdapter<Integer>(this, android.R.layout.simple_list_item_1, tuyenXe);
        listView.setAdapter(adapter);
    }
}
