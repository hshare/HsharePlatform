package hshare.base.component.view.indicator.spring;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.OvershootInterpolator;

import hshare.base.component.view.R;


public class SpringView extends View {

    private Paint paint;
    private Path path;

    private Point headPoint;
    private Point footPoint;

    private static Bitmap bitmap;

    public SpringView(Context context) {
        this(context, null);
    }

    public SpringView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SpringView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
        if (bitmap == null) {
            bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.base_component_guide_point);
        }
    }

    private void init() {
        setAlpha(0);

        headPoint = new Point();
        footPoint = new Point();

        path = new Path();

        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setStrokeWidth(1);
    }

    private void makePath() {
        float headOffsetX = (float) (headPoint.getRadius() * Math.sin(Math.atan((footPoint.getY() - headPoint.getY()) / (footPoint.getX() - headPoint.getX()))));
        float headOffsetY = (float) (headPoint.getRadius() * Math.cos(Math.atan((footPoint.getY() - headPoint.getY()) / (footPoint.getX() - headPoint.getX()))));

        float footOffsetX = (float) (footPoint.getRadius() * Math.sin(Math.atan((footPoint.getY() - headPoint.getY()) / (footPoint.getX() - headPoint.getX()))));
        float footOffsetY = (float) (footPoint.getRadius() * Math.cos(Math.atan((footPoint.getY() - headPoint.getY()) / (footPoint.getX() - headPoint.getX()))));

        float x1 = headPoint.getX() - headOffsetX;
        float y1 = headPoint.getY() + headOffsetY;

        float x2 = headPoint.getX() + headOffsetX;
        float y2 = headPoint.getY() - headOffsetY;

        float x3 = footPoint.getX() - footOffsetX;
        float y3 = footPoint.getY() + footOffsetY;

        float x4 = footPoint.getX() + footOffsetX;
        float y4 = footPoint.getY() - footOffsetY;

        float anchorX = (footPoint.getX() + headPoint.getX()) / 2;
        float anchorY = (footPoint.getY() + headPoint.getY()) / 2;

        path.reset();
        path.moveTo(x1, y1);
        path.quadTo(anchorX, anchorY, x3, y3);
        path.lineTo(x4, y4);
        path.quadTo(anchorX, anchorY, x2, y2);
        path.lineTo(x1, y1);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        makePath();
        canvas.drawPath(path, paint);
        canvas.drawCircle(headPoint.getX(), headPoint.getY(), headPoint.getRadius(), paint);
        canvas.drawCircle(footPoint.getX(), footPoint.getY(), footPoint.getRadius(), paint);
//        canvas.drawBitmap(bitmap,headPoint.getX()-bitmap.getWidth()/2,headPoint.getY()-bitmap.getHeight()/2, paint);
//        canvas.drawBitmap(bitmap,footPoint.getX(), footPoint.getY(),paint);
//        canvas.drawBitmap(bitmap,footPoint.getX()-bitmap.getWidth()/2,footPoint.getY()-bitmap.getHeight()/2, paint);
        super.onDraw(canvas);
    }

    public void animCreate() {
        setPivotX(getHeadPoint().getX());
        setPivotY(getFootPoint().getY());
        AnimatorSet animatorSet = new AnimatorSet();
        ObjectAnimator oaX = ObjectAnimator.ofFloat(this, "scaleX", 0.3f, 1f);
        ObjectAnimator oaY = ObjectAnimator.ofFloat(this, "scaleY", 0.3f, 1f);
        ObjectAnimator oaA = ObjectAnimator.ofFloat(this, "alpha", 0.0f, 1f);
        animatorSet.play(oaX).with(oaY).with(oaA);
        animatorSet.setDuration(500);
        animatorSet.setInterpolator(new OvershootInterpolator());
        animatorSet.setStartDelay(300);
        animatorSet.start();
    }

    public Point getHeadPoint() {
        return headPoint;
    }

    public Point getFootPoint() {
        return footPoint;
    }

    public void setIndicatorColor(int color) {
        color = 0xffffa128;
        paint.setColor(color);
    }

    public int getIndicatorColor() {
        return paint.getColor();
    }
}
