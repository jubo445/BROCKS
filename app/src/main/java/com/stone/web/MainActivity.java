 package com.stone.web;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.graphics.Bitmap;
 import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

 public class MainActivity extends AppCompatActivity {

    WebView webView;
    ProgressBar progressBar;
    Toolbar toolbar;
    ImageView icon;
    TextView urlText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        progressBar=findViewById(R.id.ProgressBar);

        webView=findViewById(R.id.webView);
        icon=findViewById(R.id.icon);
        urlText=findViewById(R.id.urlText);



        webView.getSettings().setJavaScriptEnabled(true);

        webView.loadUrl("https://www.bing.com/");
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                Toast.makeText(MainActivity.this, "Page Loading Started", Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(view.VISIBLE);
                urlText.setText(url);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                progressBar.setVisibility(view.GONE);


                Toast.makeText(MainActivity.this, "Loading Finished", Toast.LENGTH_SHORT).show();
            }
        });
        webView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onReceivedIcon(WebView view, Bitmap icons) {
                super.onReceivedIcon(view, icons);
                icon.setImageBitmap(icons);
            }

            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                progressBar.setProgress(newProgress);
            }
        });

    }

     @Override
     public boolean onCreateOptionsMenu(Menu menu) {

         MenuInflater menuInflater=getMenuInflater();
         menuInflater.inflate(R.menu.top_menu,menu);


         return true;
     }

     @Override
     public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){

            case R.id.back:
                if(webView.canGoBack()){
                    webView.goBack();
                }else{
                    Toast.makeText(this, "NO HISTORY FOUND", Toast.LENGTH_SHORT).show();
                }



                break;


            case R.id.forward:
                if(webView.canGoForward()){
                    webView.goForward();
                }else{
                    Toast.makeText(this, "NO PAGE FOUND", Toast.LENGTH_SHORT).show();
                }



                break;


            case R.id.refesh:

                webView.reload();
                break;



        }



         return true;
     }

     @Override
     public void onBackPressed() {
         if (webView.canGoBack()) {

             webView.goBack();

         } else {

             super.onBackPressed();
         }

     }
 }