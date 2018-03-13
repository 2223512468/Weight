package com.weight;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.HorizontalScrollView;

public class ObservableHorizontalScrollView extends HorizontalScrollView {
	private Runnable scrollerTask;
	private int intitPosition;
	private int newCheck = 100;
	private OnScrollStopListner onScrollstopListner;

	public ObservableHorizontalScrollView(Context context, AttributeSet attrs) {
		super(context, attrs);
		scrollerTask = new Runnable() {
			@Override
			public void run() {
				int newPosition = getScrollX();
				if (intitPosition - newPosition == 0) {
					if (onScrollstopListner == null) {
						return;
					}
					onScrollstopListner.onScrollChange(getScrollX());
				} else {
					intitPosition = getScrollX();
					postDelayed(scrollerTask, newCheck);
				}
			}
		};
	}

	public ObservableHorizontalScrollView(Context context, AttributeSet attrs,
			int defStyle) {
		super(context, attrs, defStyle);
	}

	public ObservableHorizontalScrollView(Context context) {
		super(context);
		
	}

	public interface OnScrollStopListner {
		void onScrollChange(int index);
	}

	public void setOnScrollStopListner(OnScrollStopListner listner) {
		onScrollstopListner = listner;
	}

	public void startScrollerTask() {
		intitPosition = getScrollX();
		postDelayed(scrollerTask, newCheck);
	}
}
