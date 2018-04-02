package hshare.base.component.view.viewpager;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.animation.DecelerateInterpolator;

import java.lang.reflect.Field;

import hshare.base.component.view.viewpager.base.FixedSpeedScroller;

public class ScrollerViewPager extends ViewPager {

    private int duration = 700;

    public ScrollerViewPager(Context context) {
        this(context,null);
    }

    public ScrollerViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        fixScrollSpeed();
    }


    public void fixScrollSpeed() {
        fixScrollSpeed(duration);
    }

    public void fixScrollSpeed(int duration) {
        this.duration = duration;
        setScrollSpeedUsingRefection(duration);
    }


    private void setScrollSpeedUsingRefection(int duration) {
        try {
            Field localField = ViewPager.class.getDeclaredField("mScroller");
            localField.setAccessible(true);
            FixedSpeedScroller scroller = new FixedSpeedScroller(getContext(), new DecelerateInterpolator(1.5F));
            scroller.setDuration(duration);
            localField.set(this, scroller);
            return;
        } catch (IllegalAccessException localIllegalAccessException) {
        } catch (IllegalArgumentException localIllegalArgumentException) {
        } catch (NoSuchFieldException localNoSuchFieldException) {
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        try {
            return super.onInterceptTouchEvent(ev);
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}
