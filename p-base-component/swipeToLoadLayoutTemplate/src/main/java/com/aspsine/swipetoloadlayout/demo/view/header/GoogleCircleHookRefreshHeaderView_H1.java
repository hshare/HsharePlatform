package com.aspsine.swipetoloadlayout.demo.view.header;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import com.aspsine.swipetoloadlayout.SwipeRefreshTrigger;
import com.aspsine.swipetoloadlayout.SwipeTrigger;
import com.aspsine.swipetoloadlayout.demo.view.GoogleCircleProgressView;
import com.aspsine.swipetoloadlayout.template.R;

public class GoogleCircleHookRefreshHeaderView_H1 extends GoogleCircleHookRefreshHeaderView {

    private SwipeTrigger onSwipeTrigger;

    public void setOnSwipeTrigger(SwipeTrigger onSwipeTrigger) {
        this.onSwipeTrigger = onSwipeTrigger;
    }

    public GoogleCircleHookRefreshHeaderView_H1(Context context) {
        this(context, null);
    }

    public GoogleCircleHookRefreshHeaderView_H1(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public GoogleCircleHookRefreshHeaderView_H1(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void onPrepare() {
        super.onPrepare();
        if (onSwipeTrigger != null) {
            onSwipeTrigger.onPrepare();
        }
    }

    @Override
    public void onMove(int y, boolean isComplete, boolean automatic) {
        super.onMove(y, isComplete, automatic);
        if (onSwipeTrigger != null) {
            onSwipeTrigger.onMove(y, isComplete, automatic);
        }
    }

    @Override
    public void onRelease() {
        super.onRelease();
        if (onSwipeTrigger != null) {
            onSwipeTrigger.onRelease();
        }
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR1)
    @Override
    public void onComplete() {
        super.onComplete();
        if (onSwipeTrigger != null) {
            onSwipeTrigger.onComplete();
        }
    }

    @Override
    public void onReset() {
        super.onReset();
        if (onSwipeTrigger != null) {
            onSwipeTrigger.onReset();
        }
    }

}
