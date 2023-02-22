package com.example.homeworkoutapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;

public class ThirdActivity extends AppCompatActivity {


    String buttonvalue;
    Button startBtn;
    private CountDownTimer countDownTimer;
    TextView mtextview;
    private boolean MTimerunning;
    private long MTimeLeftinmills;
    private InterstitialAd mInterstitialAd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {}
        });
        AdRequest adRequest = new AdRequest.Builder().build();

        InterstitialAd.load(this,"ca-app-pub-3940256099942544/1033173712", adRequest,
                new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                        // The mInterstitialAd reference will be null until
                        // an ad is loaded.
                        mInterstitialAd = interstitialAd;
                        Log.i("add-Test", "onAdLoaded");
                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        // Handle the error
                        Log.i("add-Test", loadAdError.getMessage());
                        mInterstitialAd = null;
                    }
                });

        Intent intent=  getIntent();
        buttonvalue = intent.getStringExtra("value");
        int intvalue= Integer.valueOf(buttonvalue);
        switch (intvalue)
        {
            case 1 :
                setContentView(R.layout.activity_bow);
                break;

            case 2 :
                setContentView(R.layout.activity_bridge);
                break;

            case 3 :
                setContentView(R.layout.activity_chair);
                break;

            case 4 :
                setContentView(R.layout.activity_child);
                break;

            case 5 :
                setContentView(R.layout.activity_cobbler);
                break;

            case 6 :
                setContentView(R.layout.activity_cow);
                break;

            case 7 :
                setContentView(R.layout.activity_playji);
                break;

            case 8 :
                setContentView(R.layout.activity_pauseji);
                break;

            case 9 :
                setContentView(R.layout.activity_plank);
                break;

            case 10 :
                setContentView(R.layout.activity_crunches);
                break;

            case 11 :
                setContentView(R.layout.activity_situp);
                break;

            case 12 :
                setContentView(R.layout.activity_rotation);
                break;

            case 13 :
                setContentView(R.layout.activity_twist);
                break;

            case 14 :
                setContentView(R.layout.activity_windmill);
                break;

            case 15 :
                setContentView(R.layout.activity_legup);
                break;

        }

        startBtn = findViewById(R.id.startbutton);
        mtextview = findViewById(R.id.time);
        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (MTimerunning)
                {

                    stoptimer();
                }
                else {

                    startTimer();
                }
            }
        });

    }
    private void stoptimer()
    {
        countDownTimer.cancel();
        MTimerunning=false;
        startBtn.setText("START");
    }



    private void startTimer()
    {
        final CharSequence value1 =mtextview.getText();
        String num1= value1.toString();
        String num2= num1.substring(0,2);
        String num3= num1.substring(3,5);




        final int number =Integer.valueOf(num2)* 60 + Integer.valueOf(num3);
        MTimeLeftinmills =number*1000;



        countDownTimer = new CountDownTimer(MTimeLeftinmills,1000) {
            @Override
            public void onTick(long millisUntilFinished) {

                MTimeLeftinmills = millisUntilFinished;
                updateTimer();
            }

            @Override
            public void onFinish() {

                int newvalue =Integer.valueOf(buttonvalue)+1;
                if (newvalue<=7)
                {
                    Intent intent=new Intent(ThirdActivity.this,ThirdActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    intent.putExtra("value",String.valueOf(newvalue));
                    startActivity(intent);
                }
                else
                {

                    newvalue=1;
                    Intent intent =new Intent(ThirdActivity.this,ThirdActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    intent.putExtra("value",String.valueOf(newvalue));
                    startActivity(intent);
                }
            }
        }.start();
        startBtn.setText("Pause");
        MTimerunning= true;
    }
    private void updateTimer(){
        int minutes=(int) MTimeLeftinmills/60000;
        int seconds=(int) MTimeLeftinmills%60000/1000;
        String timeLeftText="";
        if(minutes<10)

            timeLeftText="0";
        timeLeftText = timeLeftText+minutes+":";
        if (seconds<10)
            timeLeftText="0";
        timeLeftText+=seconds;
        mtextview.setText(timeLeftText);
    }

    @Override
    public void onBackPressed() {


        Intent intent = new Intent(ThirdActivity.this,SecondActivity.class);
        startActivity(intent);
        finish();

        if (mInterstitialAd != null) {
            mInterstitialAd.show(ThirdActivity.this);
        } else {
            Log.d("TAG", "The interstitial ad wasn't ready yet.");
        }

    }
}