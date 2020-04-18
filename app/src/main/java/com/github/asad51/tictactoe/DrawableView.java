package com.github.asad51.tictactoe;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class DrawableView extends View {
    private String color;
    private float size;
    private Paint paint;
    private int shape = Constant.Shape.DEFAULT;
    private float width, height, w, h;

    public DrawableView(final Context context, final AttributeSet attributeSet) {
        super(context, attributeSet);

        paint = new Paint();
        color = "#545454";
        size = 15;
    }

    public void drawShape(int shape) {
        this.shape = shape;
    }

    public void drawShape(int shape, String color) {
        this.shape = shape;
        this.color = color;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        width = getWidth();
        height = getHeight();
        w = width / 4;
        h = height / 4;

        paint.setColor(Color.parseColor(color));
        paint.setAntiAlias(true);
        paint.setStrokeWidth(size);
        paint.setStyle(Paint.Style.STROKE);
        Log.d("TAG", "onDraw: draw  w " + width + " h " + height);
        switch (shape) {
            case Constant.Shape.O:
                Log.d("TAG", "onDraw: w " + width + " h " + height);
                canvas.drawCircle(width / 2, height / 2, w, paint);
                break;
            case Constant.Shape.X:
                canvas.drawLine(w, h, width - w, height - h, paint);
                canvas.drawLine(width - w, h, w, height - h, paint);
                break;
            default:
                break;
        }
    }
}
