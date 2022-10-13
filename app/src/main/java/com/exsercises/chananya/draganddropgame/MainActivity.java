package com.exsercises.chananya.draganddropgame;

import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ViewGroup mainLayout;
    private View placementC;
    private View placementS;
    private ImageView imageC1;
    private ImageView imageC2;
    private ImageView imageC3;
    private ImageView imageC4;
    private ImageView imageC5;
    private ImageView imageS1;
    private ImageView imageS2;
    private ImageView imageS3;
    private ImageView imageS4;
    private ImageView imageS5;
    ArrayList<ImageView> C = new ArrayList<ImageView>();
    ArrayList<ImageView> S = new ArrayList<ImageView>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainLayout = (ViewGroup) findViewById(R.id.mainLayout);
        placementC = findViewById(R.id.placementCircle);
        placementS = findViewById(R.id.placementSquare);
        imageC1 = (ImageView) findViewById(R.id.C1);
        imageC2 = (ImageView) findViewById(R.id.C2);
        imageC3 = (ImageView) findViewById(R.id.C3);
        imageC4 = (ImageView) findViewById(R.id.C4);
        imageC5 = (ImageView) findViewById(R.id.C5);
        imageS1 = (ImageView) findViewById(R.id.S1);
        imageS2 = (ImageView) findViewById(R.id.S2);
        imageS3 = (ImageView) findViewById(R.id.S3);
        imageS4 = (ImageView) findViewById(R.id.S4);
        imageS5 = (ImageView) findViewById(R.id.S5);

        imageC1.setOnTouchListener(onTouchListener);
        imageC2.setOnTouchListener(onTouchListener);
        imageC3.setOnTouchListener(onTouchListener);
        imageC4.setOnTouchListener(onTouchListener);
        imageC5.setOnTouchListener(onTouchListener);
        imageS1.setOnTouchListener(onTouchListener);
        imageS2.setOnTouchListener(onTouchListener);
        imageS3.setOnTouchListener(onTouchListener);
        imageS4.setOnTouchListener(onTouchListener);
        imageS5.setOnTouchListener(onTouchListener);

        C.add(imageC1);
        C.add(imageC2);
        C.add(imageC3);
        C.add(imageC4);
        C.add(imageC5);
        S.add(imageS1);
        S.add(imageS2);
        S.add(imageS3);
        S.add(imageS4);
        S.add(imageS5);
    }
    private View.OnTouchListener onTouchListener = new View.OnTouchListener() {

        private int xStart;
        private int yStart;

        @Override
        public boolean onTouch(View view, MotionEvent event) {

            final int x = (int) event.getRawX();
            final int y = (int) event.getRawY();

            switch (event.getAction() & MotionEvent.ACTION_MASK) {

                case MotionEvent.ACTION_DOWN:
                    FrameLayout.LayoutParams lParams = (FrameLayout.LayoutParams)
                            view.getLayoutParams();

                    xStart = x - lParams.leftMargin;
                    yStart = y - lParams.topMargin;

                    break;

                case MotionEvent.ACTION_UP:
                    checkPlacement(view);
                    break;

                case MotionEvent.ACTION_MOVE:
                    FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) view
                            .getLayoutParams();
                    layoutParams.leftMargin = x - xStart;
                    layoutParams.topMargin = y - yStart;

                    view.setLayoutParams(layoutParams);
                    break;
            }
            mainLayout.invalidate();
            return true;
        }
    };
    private void checkPlacement(View v) {
        Rect imageViewRect = new Rect(), placementSRect = new Rect(), placementCRect = new Rect();

        ((ImageView)v).getGlobalVisibleRect(imageViewRect);
        placementS.getGlobalVisibleRect(placementSRect);
        placementC.getGlobalVisibleRect(placementCRect);

        for (ImageView ivc : C) {
            ivc.getGlobalVisibleRect(imageViewRect);
            if (!placementCRect.contains(imageViewRect))
                return;
        }
        for (ImageView ivs : S) {
            ivs.getGlobalVisibleRect(imageViewRect);
            if (!placementSRect.contains(imageViewRect))
                return;
        }
        Toast.makeText(MainActivity.this,
                "Success!", Toast.LENGTH_SHORT)
                .show();
    }
}
