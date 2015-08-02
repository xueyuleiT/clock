package com.example.circleview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.util.AttributeSet;
import android.view.View;

public class DasLine extends View {

	private Paint mPaint;
	private Path path;
	private PathEffect effect;
	private int mColor;

	public DasLine(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);

		mPaint = new Paint();
		path = new Path();
		effect = new DashPathEffect(new float[] { 5, 5, 5, 5 }, 5);
		TypedArray arr = context.obtainStyledAttributes(attrs,
				R.styleable.DashLine);
		mColor = arr.getColor(R.styleable.DashLine_lineColor, Color.GRAY);
		arr.recycle();
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);

		mPaint.setStyle(Paint.Style.STROKE);

		mPaint.setStrokeWidth(getWidth());

		mPaint.setColor(mColor);

		path.moveTo(0, 0);

		path.lineTo(0, getHeight());

		mPaint.setPathEffect(effect);

		canvas.drawPath(path, mPaint);

	}

	public DasLine(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public DasLine(Context context) {
		super(context);
	}

}
