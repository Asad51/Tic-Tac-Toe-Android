package com.github.asad51.tictactoe;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class DrawableView extends View {
    private Paint paint;
    private int shape = Constant.Shape.DEFAULT;
    private int size = 15, divisor = 4;
    private float width, height, w, h;

    public DrawableView(final Context context, final AttributeSet attributeSet) {
        super(context, attributeSet);

        paint = new Paint();
    }

    public void drawShape(int shape) {
        this.shape = shape;
    }

    public void drawShape(int shape, int divisor, int size){
        this.shape = shape;
        this.divisor = divisor;
        this.size = size;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        width = getWidth();
        height = getHeight();
        w = width / divisor;
        h = height / divisor;

        paint.setAntiAlias(true);
        paint.setStrokeWidth(size);
        paint.setStyle(Paint.Style.STROKE);

        switch (shape) {
            case Constant.Shape.O:
                paint.setColor(Color.parseColor(Constant.Color.O));
                canvas.drawCircle(width / 2, height / 2, height / 2 - h, paint);
                break;
            case Constant.Shape.X:
                paint.setColor(Color.parseColor(Constant.Color.X));
                canvas.drawLine(w, h, width - w, height - h, paint);
                canvas.drawLine(height - w, h, w, height - h, paint);
                break;
            default:
                break;
        }
    }
}
