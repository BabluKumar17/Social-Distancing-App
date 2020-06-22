package com.security10x.socialdistancing;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

public class AboutActivity extends AppCompatActivity {

    TextView about;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        about = (TextView) findViewById(R.id.aboutText);

        String text = "<div>\n" +
                "   <div>\n" +
                "       This app turns on your Bluetooth and scans other devices in the range of 2 meters or less. If it finds one, it will alert you immediately to maintain your distance. Please accept all the permissions the app requires for it to work.\n" +
                "   </div>\n" +
                "   <div>\n" +
                "       This is an open-source project. If you're a developer and would like to contribute to the project, I welcome you to submit your changes. <br><br> Github URL: https://github.com/BabluKumar17/Social-Distancing-App/" +
                "   <div>\n" +
                "</div>";
        about.setText(Html.fromHtml(text));
    }
}
