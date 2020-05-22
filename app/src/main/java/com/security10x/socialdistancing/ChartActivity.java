package com.security10x.socialdistancing;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.gson.Gson;
import com.security10x.socialdistancing.Adapter.FeedAdapter;
import com.security10x.socialdistancing.Common.HTTPDataHandler;
import com.security10x.socialdistancing.Model.RSSObject;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ChartActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    //RSS link
//    private final String RSS_link="https://services.india.gov.in/feed/rss?cat_id=5&ln=en";

    private static final String TAG = "ChartActivity";
    Spinner spinner;
    int newcases, active, critical, recovered, total, newDeaths, totalDeaths;
    String cases[] = {"New", "Active", "Critical", "Recovered", "Total"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);

        new InternetCheck(new InternetCheck.Consumer() {
            @Override
            public void accept(Boolean internet) {
                if (!internet) {
                    Toast.makeText(getApplicationContext(), "No Internet Connection. Please connect to the internet.", Toast.LENGTH_SHORT).show();
                }
                else{
                    spinner = (Spinner) findViewById(R.id.spinner);

                    // Spinner click listener
                    spinner.setOnItemSelectedListener(ChartActivity.this);

                    // Spinner Drop down elements
                    List<String> categories = new ArrayList<String>();
                    categories.add("India");

                    categories.add("Afghanistan");
                    categories.add("Albania");
                    categories.add("Algeria");
                    categories.add("Andorra");
                    categories.add("Angola");
                    categories.add("Antigua-and-Barbuda");
                    categories.add("Argentina");
                    categories.add("Armenia");
                    categories.add("Aruba");
                    categories.add("Australia");
                    categories.add("Austria");
                    categories.add("Azerbaijan");
                    categories.add("Bahamas");
                    categories.add("Bahrain");
                    categories.add("Bangladesh");
                    categories.add("Barbados");
                    categories.add("Belarus");
                    categories.add("Belgium");
                    categories.add("Benin");
                    categories.add("Bermuda");
                    categories.add("Bhutan");
                    categories.add("Bolivia");
                    categories.add("Bosnia-and-Herzegovina");
                    categories.add("Brazil");
                    categories.add("Brunei-");
                    categories.add("Bulgaria");
                    categories.add("Burkina-Faso");
                    categories.add("Cabo-Verde");
                    categories.add("Cambodia");
                    categories.add("Cameroon");
                    categories.add("Canada");
                    categories.add("CAR");
                    categories.add("Cayman-Islands");
                    categories.add("Chad");
                    categories.add("Channel-Islands");
                    categories.add("Chile");
                    categories.add("China");
                    categories.add("Colombia");
                    categories.add("Congo");
                    categories.add("Costa-Rica");
                    categories.add("Croatia");
                    categories.add("Cuba");
                    categories.add("Cura&ccedil;ao");
                    categories.add("Cyprus");
                    categories.add("Czechia");
                    categories.add("Denmark");
                    categories.add("Diamond-Princess-");
                    categories.add("Djibouti");
                    categories.add("Dominican-Republic");
                    categories.add("DRC");
                    categories.add("Ecuador");
                    categories.add("Egypt");
                    categories.add("El-Salvador");
                    categories.add("Equatorial-Guinea");
                    categories.add("Eritrea");
                    categories.add("Estonia");
                    categories.add("Eswatini");
                    categories.add("Ethiopia");
                    categories.add("Faeroe-Islands");
                    categories.add("Fiji");
                    categories.add("Finland");
                    categories.add("France");
                    categories.add("French-Guiana");
                    categories.add("French-Polynesia");
                    categories.add("Gabon");
                    categories.add("Gambia");
                    categories.add("Georgia");
                    categories.add("Germany");
                    categories.add("Ghana");
                    categories.add("Gibraltar");
                    categories.add("Greece");
                    categories.add("Greenland");
                    categories.add("Guadeloupe");
                    categories.add("Guam");
                    categories.add("Guatemala");
                    categories.add("Guinea");
                    categories.add("Guyana");
                    categories.add("Haiti");
                    categories.add("Honduras");
                    categories.add("Hong-Kong");
                    categories.add("Hungary");
                    categories.add("Iceland");
                    categories.add("India");
                    categories.add("Indonesia");
                    categories.add("Iran");
                    categories.add("Iraq");
                    categories.add("Ireland");
                    categories.add("Isle-of-Man");
                    categories.add("Israel");
                    categories.add("Italy");
                    categories.add("Ivory-Coast");
                    categories.add("Jamaica");
                    categories.add("Japan");
                    categories.add("Jordan");
                    categories.add("Kazakhstan");
                    categories.add("Kenya");
                    categories.add("Kuwait");
                    categories.add("Kyrgyzstan");
                    categories.add("Latvia");
                    categories.add("Lebanon");
                    categories.add("Liberia");
                    categories.add("Liechtenstein");
                    categories.add("Lithuania");
                    categories.add("Luxembourg");
                    categories.add("Macao");
                    categories.add("Madagascar");
                    categories.add("Malaysia");
                    categories.add("Maldives");
                    categories.add("Malta");
                    categories.add("Martinique");
                    categories.add("Mauritania");
                    categories.add("Mauritius");
                    categories.add("Mayotte");
                    categories.add("Mexico");
                    categories.add("Moldova");
                    categories.add("Monaco");
                    categories.add("Mongolia");
                    categories.add("Montenegro");
                    categories.add("Montserrat");
                    categories.add("Morocco");
                    categories.add("Namibia");
                    categories.add("Nepal");
                    categories.add("Netherlands");
                    categories.add("New-Caledonia");
                    categories.add("New-Zealand");
                    categories.add("Nicaragua");
                    categories.add("Niger");
                    categories.add("Nigeria");
                    categories.add("North-Macedonia");
                    categories.add("Norway");
                    categories.add("Oman");
                    categories.add("Pakistan");
                    categories.add("Palestine");
                    categories.add("Panama");
                    categories.add("Papua-New-Guinea");
                    categories.add("Paraguay");
                    categories.add("Peru");
                    categories.add("Philippines");
                    categories.add("Poland");
                    categories.add("Portugal");
                    categories.add("Puerto-Rico");
                    categories.add("Qatar");
                    categories.add("R&eacute;union");
                    categories.add("Romania");
                    categories.add("Russia");
                    categories.add("Rwanda");
                    categories.add("S.-Korea");
                    categories.add("Saint-Lucia");
                    categories.add("Saint-Martin");
                    categories.add("San-Marino");
                    categories.add("Saudi-Arabia");
                    categories.add("Senegal");
                    categories.add("Serbia");
                    categories.add("Seychelles");
                    categories.add("Singapore");
                    categories.add("Sint-Maarten");
                    categories.add("Slovakia");
                    categories.add("Slovenia");
                    categories.add("Somalia");
                    categories.add("South-Africa");
                    categories.add("Spain");
                    categories.add("Sri-Lanka");
                    categories.add("St.-Barth");
                    categories.add("St.-Vincent-Grenadines");
                    categories.add("Sudan");
                    categories.add("Suriname");
                    categories.add("Sweden");
                    categories.add("Switzerland");
                    categories.add("Taiwan");
                    categories.add("Tanzania");
                    categories.add("Thailand");
                    categories.add("Timor-Leste");
                    categories.add("Togo");
                    categories.add("Trinidad-and-Tobago");
                    categories.add("Tunisia");
                    categories.add("Turkey");
                    categories.add("U.S.-Virgin-Islands");
                    categories.add("UAE");
                    categories.add("Uganda");
                    categories.add("UK");
                    categories.add("Ukraine");
                    categories.add("Uruguay");
                    categories.add("USA");
                    categories.add("Uzbekistan");
                    categories.add("Vatican-City");
                    categories.add("Venezuela");
                    categories.add("Vietnam");
                    categories.add("Zambia");
                    categories.add("Zimbabwe");

                    // Creating adapter for spinner
                    ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(ChartActivity.this, android.R.layout.simple_spinner_item, categories);

                    // Drop down layout style - list view with radio button
                    dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                    // attaching data adapter to spinner
                    spinner.setAdapter(dataAdapter);
                }
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        ((TextView) parent.getChildAt(0)).setTextColor(Color.BLACK);
        ((TextView) parent.getChildAt(0)).setTextSize(18);

        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();
        api_call(item);
        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private void api_call(final String Country){
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://covid-193.p.rapidapi.com/statistics?country=" + Country)
                .get()
                .addHeader("x-rapidapi-host", "covid-193.p.rapidapi.com")
                .addHeader("x-rapidapi-key", "") // please your own api-key
                .build();
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        try {
            Response response = client.newCall(request).execute();
            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(@NotNull Call call, @NotNull IOException e) {

                }

                @Override
                public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {

                    if(response.isSuccessful()){
                        String responseData = response.body().string();
                        Log.d("API Response", responseData);

                        try {
                            JSONObject json = new JSONObject(responseData);
//                            Log.d("JSON Response", String.valueOf(json));

                            JSONArray responseArray = json.getJSONArray("response");
                            Log.d("responseArray", String.valueOf(json));

                            JSONObject object = responseArray.getJSONObject(0);

                            String country = object.getString("country");
                            Log.d(TAG, country);

                            // Cases
                            JSONObject cases = object.getJSONObject("cases");
                            String newCases = cases.getString("new");

                            String newActive = cases.getString("active");

                            String newCritical = cases.getString("critical");

                            String newRecovered = cases.getString("recovered");

                            String totalCases = cases.getString("total");

                            try {
                                newcases = Integer.parseInt(newCases.toString());
                            } catch (NumberFormatException e){
                                newcases = 0;
                            }

//                        Log.d(TAG, String.valueOf(newcases));

                            try {
                                active = Integer.parseInt(newActive.toString());
                            } catch (NumberFormatException e){
                                active = 0;
                            }
//                        Log.d(TAG, String.valueOf(active));

                            try {
                                critical = Integer.parseInt(newCritical.toString());
                            } catch (NumberFormatException e){
                                critical = 0;
                            }
//                        Log.d(TAG, String.valueOf(critical));

                            try {
                                recovered =  Integer.parseInt(newRecovered.toString());
                            } catch (NumberFormatException e){
                                recovered = 0;
                            }
//                        Log.d(TAG, String.valueOf(recovered));

                            try {
                                total = Integer.parseInt(totalCases.toString());
                            } catch (NumberFormatException e){
                                total = 0;
                            }
//                        Log.d(TAG, String.valueOf(total));

                            // Deaths
                            JSONObject deaths = object.getJSONObject("deaths");
                            String newDeath = deaths.getString("new");
//                            newDeaths = Integer.parseInt(newDeath.toString());;

                            String totalDeath = deaths.getString("total");
//                        Log.d(TAG, totalDeaths);
//                            totalDeaths = Integer.parseInt(totalDeath.toString());;

                            final int[] casesArray  = {newcases, active, critical, recovered, total};
                            ChartActivity.this.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    setupPieChart(Country, casesArray);
                                }
                            });

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setupPieChart(String country, int[] caseValues) {

        Log.d("setupPieChart", String.valueOf(cases[3]));

        // Populating a list of pieEntries
        List<PieEntry> pieEntries = new ArrayList<>();
        for (int i = 0; i < cases.length; i++) {
//            Log.d(TAG, String.valueOf(caseValues[i]));
            pieEntries.add(new PieEntry(caseValues[i], cases[i]));
//            Log.d("setupPieChart", String.valueOf(caseValues[i]));
        }

        PieDataSet dataset = new PieDataSet(pieEntries, "");
        dataset.setColors(MyColorTemplate.MATERIAL_COLORS);
        PieData data = new PieData(dataset);

        // Get the chart
        PieChart chart = (PieChart) findViewById(R.id.chart);
        chart.setData(data);
//        chart.setNoDataText("Loading the chart...");
        chart.getDescription().setText("");
        chart.getLegend().setTextColor(Color.BLACK);
        chart.setCenterText(String.format("Covid-19 Update for " + country));
//        chart.getDescription().setPosition(1f, 250f);
        chart.animateY(1000);
        chart.invalidate();
    }
}

class MyColorTemplate {
    public static final int[] MATERIAL_COLORS = {
            rgb("#d17915"), rgb("#f1c40f"), rgb("#e74c3c"), rgb("#2fd072"), rgb("#3498db")
    };

    public static final int[] JOYFUL_COLORS = {
            Color.rgb(217, 80, 138), Color.rgb(254, 149, 7), Color.rgb(254, 247, 120),
            Color.rgb(106, 167, 134), Color.rgb(53, 194, 209)
    };

    public static int rgb(String hex) {
        int color = (int) Long.parseLong(hex.replace("#", ""), 16);
        int r = (color >> 16) & 0xFF;
        int g = (color >> 8) & 0xFF;
        int b = (color >> 0) & 0xFF;
        return Color.rgb(r, g, b);
    }
}
