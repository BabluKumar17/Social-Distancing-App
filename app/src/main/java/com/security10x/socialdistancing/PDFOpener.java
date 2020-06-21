package com.security10x.socialdistancing;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnRenderListener;

public class PDFOpener extends AppCompatActivity {

    PDFView pdfViewer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdfopener);

        pdfViewer = (PDFView) findViewById(R.id.pdfViewer);
        String getItem = getIntent().getStringExtra("pdfFileName");

        if (getItem.equals("परिक्षण की आवश्यकता - कब और  कैसे?")){
            pdfViewer.fromAsset("FINAL_14_03_2020_Hindi.pdf")
                    .onRender(new OnRenderListener() {
                        @Override
                        public void onInitiallyRendered(int pages, float pageWidth, float pageHeight) {
                            pdfViewer.fitToWidth();
                        }
                    })
                    .load();
        }
        if (getItem.equals("Covid-19 Testing - When and How")){
            pdfViewer.fromAsset("FINAL_14_03_2020_ENg.pdf")
                    .onRender(new OnRenderListener() {
                        @Override
                        public void onInitiallyRendered(int pages, float pageWidth, float pageHeight) {
                            pdfViewer.fitToWidth();
                        }
                    })
                    .load();
        }
        if (getItem.equals("भारत वापसी लौटने वाले यात्री")){
            pdfViewer.fromAsset("PosterTravellerHindi.pdf")
                    .onRender(new OnRenderListener() {
                        @Override
                        public void onInitiallyRendered(int pages, float pageWidth, float pageHeight) {
                            pdfViewer.fitToWidth();
                        }
                    })
                    .load();
        }
        if (getItem.equals("Travellers Returning To India")){
            pdfViewer.fromAsset("PostrerEnglishtraveller.pdf")
                    .onRender(new OnRenderListener() {
                        @Override
                        public void onInitiallyRendered(int pages, float pageWidth, float pageHeight) {
                            pdfViewer.fitToWidth();
                        }
                    })
                    .load();
        }
        if (getItem.equals("नोवल कोरोनावायरस पोस्टर - 1")){
            pdfViewer.fromAsset("ProtectivemeasuresHin.pdf")
                    .onRender(new OnRenderListener() {
                        @Override
                        public void onInitiallyRendered(int pages, float pageWidth, float pageHeight) {
                            pdfViewer.fitToWidth();
                        }
                    })
                    .load();
        }
        if (getItem.equals("Novel Coronavirus Poster - 1")){
            pdfViewer.fromAsset("ProtectivemeasuresEng.pdf")
                    .onRender(new OnRenderListener() {
                        @Override
                        public void onInitiallyRendered(int pages, float pageWidth, float pageHeight) {
                            pdfViewer.fitToWidth();
                        }
                    })
                    .load();
        }
        if (getItem.equals("नोवल कोरोनावायरस पोस्टर - 2")){
            pdfViewer.fromAsset("socialdistancingHindi.pdf")
                    .onRender(new OnRenderListener() {
                        @Override
                        public void onInitiallyRendered(int pages, float pageWidth, float pageHeight) {
                            pdfViewer.fitToWidth();
                        }
                    })
                    .load();
        }
        if (getItem.equals("Novel Coronavirus Poster - 2")){
            pdfViewer.fromAsset("socialdistancingEnglish.pdf")
                    .onRender(new OnRenderListener() {
                        @Override
                        public void onInitiallyRendered(int pages, float pageWidth, float pageHeight) {
                            pdfViewer.fitToWidth();
                        }
                    })
                    .load();
        }
        if (getItem.equals("क्या करें और क्या ना करें")){
            pdfViewer.fromAsset("Poster_Corona_ad_Hin.pdf")
                    .onRender(new OnRenderListener() {
                        @Override
                        public void onInitiallyRendered(int pages, float pageWidth, float pageHeight) {
                            pdfViewer.fitToWidth();
                        }
                    })
                    .load();
        }
        if (getItem.equals("Do's and Don'ts")){
            pdfViewer.fromAsset("Poster_Corona_ad_Eng.pdf")
                    .onRender(new OnRenderListener() {
                        @Override
                        public void onInitiallyRendered(int pages, float pageWidth, float pageHeight) {
                            pdfViewer.fitToWidth();
                        }
                    })
                    .load();
        }
        if (getItem.equals("Covid-19: Home Care by WHO")){
            pdfViewer.fromAsset("Covid19_Home_Care.pdf")
                    .onRender(new OnRenderListener() {
                        @Override
                        public void onInitiallyRendered(int pages, float pageWidth, float pageHeight) {
                            pdfViewer.fitToWidth();
                        }
                    })
                    .load();
        }
        if (getItem.equals("Covid-19: Pregnancy by WHO")){
            pdfViewer.fromAsset("COVID_19_Pregnancy.pdf")
                    .onRender(new OnRenderListener() {
                        @Override
                        public void onInitiallyRendered(int pages, float pageWidth, float pageHeight) {
                            pdfViewer.fitToWidth();
                        }
                    })
                    .load();
        }

    }
}
