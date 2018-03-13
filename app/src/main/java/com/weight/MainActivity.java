package com.weight;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.TextView;


public class MainActivity extends Activity {

    private Context context;
    private TextView weight_value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        weight_value = (TextView) findViewById(R.id.weight_value);
        final ObservableHorizontalScrollView scrollView = (ObservableHorizontalScrollView) findViewById(R.id.weight_scrollview);
        scrollView.setOnTouchListener(new OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    scrollView.startScrollerTask();
                }
                return false;
            }
        });
        scrollView.setOnScrollStopListner(new ObservableHorizontalScrollView.OnScrollStopListner() {
            public void onScrollChange(int index) {
                if (index == 0) {
                    weight_value.setText("25");
                } else {
                    int value = px2dip(context, index);
                    weight_value.setText((value / 7 + 25) + "");
                }
            }
        });
    }

    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }
}
