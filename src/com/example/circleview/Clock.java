package com.example.circleview;

import java.util.Calendar;

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

public class Clock extends View {
	/**
	 * 画笔对象的引用
	 */
	private Paint paint;

	/**
	 * 圆环的颜色
	 */
	private int roundColor;

	/**
	 * 圆环进度的颜色
	 */
	private int roundProgressColor;

	/**
	 * 圆环的宽度
	 */
	private float roundWidth;
	/**
	 * 最大进度
	 */
	private int max;

	/**
	 * 当前进度
	 */
	private Context con;

	private boolean isInit = false;
	private int progress = 20;
	private Path path;
	private PathEffect effect;

	public Clock(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		this.con = context;
		init(context, attrs);

	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		drawOutCycle(canvas, getWidth(), getWidth() / 2);
		drawHander(canvas);
	}

	private void init(Context context, AttributeSet attrs) {
		paint = new Paint();
		path = new Path();
		effect = new DashPathEffect(new float[] { 5, 5, 5, 5 }, 5);
		TypedArray mTypedArray = context.obtainStyledAttributes(attrs,
				R.styleable.RoundProgressBar);

		// 获取自定义属性和默认值
		roundColor = mTypedArray.getColor(
				R.styleable.RoundProgressBar_roundColor, Color.GRAY);
		roundProgressColor = mTypedArray.getColor(
				R.styleable.RoundProgressBar_roundProgressColor, Color.GREEN);
		roundWidth = mTypedArray.getDimension(
				R.styleable.RoundProgressBar_roundWidth, 10);
		max = mTypedArray.getInteger(R.styleable.RoundProgressBar_max, 100);

		mTypedArray.recycle();
	}

	int center;
	int Line1 = 10;
	int padding;

	/**
	 * 画最外层的大圆环
	 */
	private void drawOutCycle(Canvas canvas, int centre, int radius) {
		isInit = true;
		padding = (int) (roundWidth);// 边框大小
		this.center = centre / 2;
		paint.setColor(roundColor); // 设置圆环的颜色
		paint.setStyle(Paint.Style.STROKE); // 设置空心
		paint.setStrokeWidth(2); // 设置圆环的宽度
		paint.setAntiAlias(true); // 消除锯齿
		// canvas.drawCircle(centre / 2, centre / 2, radius - 10, paint); //
		// 画出圆环

		for (int i = 0; i < 120; i++) {

			if (i % 10 == 0) {
				if (i % 30 == 0) {
					Line1 = 10;
				} else {
					Line1 = 15;
				}
			} else {
				Line1 = 20;
			}
			Path path = new Path();
			path.moveTo(centre / 2, padding);
			path.lineTo(centre / 2, centre / Line1);
			canvas.drawPath(path, paint);
			canvas.rotate(3, centre / 2, centre / 2);
			canvas.save();
		}

	}

	public Clock(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.con = context;
		init(context, attrs);
	}

	public Clock(Context context) {
		super(context);
		this.con = context;
		init(context, null);

	}

	public int getProgress() {
		return progress;
	}

	public void setProgress(int progress) {
		this.progress = progress;
	}

	public void drawHander(Canvas canvas) {
		final Calendar calendar = Calendar.getInstance();
		int hour = calendar.get(Calendar.HOUR);
		int minute = calendar.get(Calendar.MINUTE);
		int second = calendar.get(Calendar.SECOND);

		float h = ((hour + (float) minute / 60) / 12) * 360;
		float m = ((minute + (float) second / 60) / 60) * 360;
		float s = (float) second * 6;
		paint.setStyle(Paint.Style.STROKE); // 设置空心
		paint.setAntiAlias(true); // 消除锯齿
		// 时针
		paint.setColor(Color.GRAY);
		paint.setStrokeWidth(8);
		canvas.save(); // 线锁定画布
		canvas.rotate(h, center, center); // 旋转画布
		Path path1 = new Path();
		path1.moveTo(center, center); // 开始的基点
		path1.lineTo(center, center / 4); // 最后的基点
		canvas.drawPath(path1, paint);
		canvas.restore();

		// 分针
		paint.setColor(Color.BLUE);
		paint.setStrokeWidth(6);
		canvas.save(); // 线锁定画布
		canvas.rotate(m, center, center); // 旋转画布
		Path path2 = new Path();
		path2.moveTo(center, center); // 开始的基点
		path2.lineTo(center, center / 5); // 最后的基点
		canvas.drawPath(path2, paint);
		canvas.restore();// 回到原位置

		// 秒针
		paint.setColor(Color.BLACK);
		paint.setStrokeWidth(4);
		canvas.save(); // 线锁定画布
		canvas.rotate(s, center, center); // 旋转画布
		Path path3 = new Path();
		path3.moveTo(center, center); // 开始的基点
		path3.lineTo(center, center / 6); // 最后的基点
		canvas.drawPath(path3, paint);

		// Path path = new Path();
		// path.moveTo(center, padding);
		// path.lineTo(center, center / Line1);
		// canvas.drawPath(path, paint);

		// canvas.rotate(s, center, center); // 旋转画布

		canvas.save();
		paint.setStyle(Paint.Style.FILL); // 设置空心
		canvas.drawCircle(center, padding + 10, 5, paint); //

		canvas.drawCircle(center, center, 8, paint); //
		canvas.save();

	}
}
