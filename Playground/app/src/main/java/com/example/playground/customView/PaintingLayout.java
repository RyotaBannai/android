package com.example.playground.customView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class PaintingLayout extends View {
    /*
     * The main call to onSizeChanged() is done after the construction of your view but before the drawing.
     * At this time the system will calculate the size of your view and notify you by calling onSizeChanged()
     *
     * onSizeChanged() is (at least sometimes) called from within layout().
     * In that case it's usually called before any OnLayoutChangeListeners
     *
     * onMeasure() is called automatically right after a call to measure()
     * */

    private int status;
    private Paint paint;
    private Rect rect;

    public PaintingLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        Init();
    }

    private void Init() {
        status = 0;
        paint = new Paint();
        paint.setColor(getColor());
        rect = new Rect();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        rect.left = w / 10;
        rect.top = h / 10;
        rect.right = w * 9 / 10;
        rect.bottom = h * 9 / 10;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawRect(rect, paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            status = (status >= 2) ? 0 : status + 1;
            paint.setColor(getColor());
            invalidate();
        }
        return true;
    }

    private int getColor() {
        int color;

        switch (this.status) {
            case 0:
                color = Color.RED;
                break;
            case 1:
                color = Color.GREEN;
                break;
            case 2:
                color = Color.BLUE;
                break;
            default:
                color = Color.BLACK;
        }
        return color;
    }


}
