package com.security10x.socialdistancing;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class ResourceActivity extends AppCompatActivity {

    ListView pdfListView;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resource);

        toolbar = (Toolbar)findViewById(R.id.toolbar);
        toolbar.setTitle("Covid19 Learning Resource");
        setSupportActionBar(toolbar);

        pdfListView = (ListView) findViewById(R.id.mypdflist);

        String[] pdfFiles = {"परिक्षण की आवश्यकता - कब और  कैसे?",
                            "Covid-19 Testing - When and How",
                            "भारत वापसी लौटने वाले यात्री",
                            "Travellers Returning To India",
                            "नोवल कोरोनावायरस पोस्टर - 1",
                            "Novel Coronavirus Poster - 1",
                            "नोवल कोरोनावायरस पोस्टर - 2",
                            "Novel Coronavirus Poster - 2",
                            "क्या करें और क्या ना करें",
                            "Do's and Don'ts",
                            "Covid-19: Home Care by WHO",
                            "Covid-19: Pregnancy by WHO"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, pdfFiles){
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView myText = (TextView) view.findViewById(android.R.id.text1);
                return view;
            }
        };

        pdfListView.setAdapter(adapter);
        pdfListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String  item = pdfListView.getItemAtPosition(position).toString();
                Intent intent = new Intent(getApplicationContext(), PDFOpener.class);
                intent.putExtra("pdfFileName", item);
                startActivity(intent);
            }
        });

    }
}
