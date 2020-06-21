package com.security10x.socialdistancing;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class HelplineActivity extends AppCompatActivity {
    Toolbar toolbar;
    ListView listNums;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_helpline);

        toolbar = (Toolbar)findViewById(R.id.toolbar);
        toolbar.setTitle("State/Union Territories Helpline");
        setSupportActionBar(toolbar);

        listNums = (ListView) findViewById(R.id.listNums);

        String[] states = {
                "Andhra Pradesh: 08662410978",
                "Arunachal Pradesh: 9436055743",
                "Assam: 104/ 6913347770",
                "Bihar: 104/ 06122217681",
                "Chhattisgarh: 0771282113",
                "Goa: 104",
                "Delhi: 01122307145",
                "Gujarat: 104/ 07923251900",
                "Haryana: 01722545938",
                "Himachal Pradesh: 104/ 0772628940",
                "Jharkhand: 104/ 06512282201",
                "Karnataka: 104/ 08046848600",
                "Kerala: 0471-2552056",
                "Madhya Pradesh: 104/ 07552411180",
                "Maharashtra: 02026127394",
                "Manipur: 3852411668",
                "Meghalaya:	108",
                "Mizoram: 102",
                "Nagaland: 7005539653",
                "Odisha: 9439994859",
                "Puducherry: 104",
                "Punjab: 104",
                "Rajasthan:	01412225624",
                "Sikkim: 104",
                "Tamil Nadu: 04429510500",
                "Telangana:	104",
                "Tripura: 03812315879",
                "Uttarakhand: 104",
                "Uttar Pradesh:	18001805145",
                "West Bengal: 1800313444222, 03323412600"
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, states){
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView myText = (TextView) view.findViewById(android.R.id.text1);
                return view;
            }
        };

        listNums.setAdapter(adapter);
        listNums.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String  item = listNums.getItemAtPosition(position).toString();
                String startSeparator =":";
                String endSeparator = "/";
                int start = item.indexOf(startSeparator);
                int end;
                if (item.indexOf(endSeparator) == -1){
                    end = item.length();
                } else {
                    end = item.indexOf(endSeparator);
                }


                Log.d("HelplineActivity", item.substring(start+1, end).trim());
                String num = item.substring(start+1, end).trim();
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + num.trim()));
                startActivity(intent);
            }
        });

    }
}
