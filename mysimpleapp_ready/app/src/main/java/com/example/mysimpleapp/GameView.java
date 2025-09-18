package com.example.mysimpleapp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

public class GameView extends View {

    private float cx, cy;
    private float radius = 80f;
    private Paint paint;
    private int score = 0;
    private OnScoreChangeListener listener;

    public GameView(Context context) {
        super(context);
        init();
    }

    public GameView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(0xFF2196F3);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (cx == 0 && cy == 0) {
            cx = w / 2f;
            cy = h / 2f;
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(cx, cy, radius, paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            float x = event.getX();
            float y = event.getY();
            float dx = x - cx;
            float dy = y - cy;
            if (dx*dx + dy*dy <= radius*radius) {
                score++;
                if (listener != null) listener.onScoreChanged(score);
            } else {
                cx = x;
                cy = y;
                invalidate();
            }
            return true;
        }
        return super.onTouchEvent(event);
    }

    public void resetGame() {
        score = 0;
        if (listener != null) listener.onScoreChanged(score);
        cx = getWidth() / 2f;
        cy = getHeight() / 2f;
        invalidate();
    }

    public void setOnScoreChangeListener(OnScoreChangeListener l) {
        this.listener = l;
    }

    public interface OnScoreChangeListener {
        void onScoreChanged(int newScore);
    }
}
