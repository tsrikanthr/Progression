package com.flywhales.progression;

import java.util.Timer;
import java.util.TimerTask;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

public class ProgressionView extends View{

	private Paint bgPaint;
	private RectF bgRect;
	private int viewHeight;
	private int viewWidth;
	private static final int BG_EDGE_RADIUS = 8;
	private int innerSquareLength;
	private Point viewCenter;
	private static final int SQUARES_SPACING = 1;
	private int blinkPos = 0;
	private static final int TIME_INTERVAL = 300;

	private static final String bgColor = "#ececec";
	private static final String boxColor = "#252949";

	private Rect[] rects = new Rect[4];

	private Paint blinkPaint;
	private Paint stablePaint;

	private boolean startedLoading = false;

	public ProgressionView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public ProgressionView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public ProgressionView(Context context) {
		super(context);
	}

	private void init() {
		
		bgPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		bgPaint.setColor(Color.parseColor(bgColor));

		blinkPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		stablePaint = new Paint(Paint.ANTI_ALIAS_FLAG);

		blinkPaint.setColor(Color.parseColor(boxColor));
		blinkPaint.setAlpha(60);

		stablePaint.setColor(Color.parseColor(boxColor));

		if(viewHeight !=0 && viewWidth != 0){

			viewCenter = new Point(viewWidth/2, viewHeight/2);

			bgRect = new RectF(0, 0, viewWidth, viewHeight);

			innerSquareLength = viewHeight/4;

			rects[0] = new Rect(viewCenter.x - SQUARES_SPACING - innerSquareLength, viewCenter.y - SQUARES_SPACING - innerSquareLength, 
					viewCenter.x - SQUARES_SPACING, viewCenter.y - SQUARES_SPACING);

			rects[1] = new Rect(viewCenter.x + SQUARES_SPACING, viewCenter.y - SQUARES_SPACING - innerSquareLength, 
					viewCenter.x + SQUARES_SPACING + innerSquareLength, viewCenter.y - SQUARES_SPACING);

			rects[2] = new Rect(viewCenter.x + SQUARES_SPACING, viewCenter.y + SQUARES_SPACING, 
					viewCenter.x + SQUARES_SPACING + innerSquareLength, viewCenter.y + SQUARES_SPACING + innerSquareLength);

			rects[3] = new Rect(viewCenter.x - SQUARES_SPACING - innerSquareLength, viewCenter.y + SQUARES_SPACING, 
					viewCenter.x - SQUARES_SPACING, viewCenter.y + SQUARES_SPACING + innerSquareLength);


			if(!startedLoading) {

				new Timer().scheduleAtFixedRate(new TimerTask() {

					@Override
					public void run() {
						postInvalidate();
					}
				}, 0, TIME_INTERVAL);

				startedLoading = true;
			}
		}


	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

		viewWidth = MeasureSpec.getSize(widthMeasureSpec);
		viewHeight = MeasureSpec.getSize(heightMeasureSpec);
		init();

		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);

		canvas.drawRoundRect(bgRect, BG_EDGE_RADIUS, BG_EDGE_RADIUS, bgPaint);
		
		for(int i=0; i< rects.length; i++){

			if(i == blinkPos){
				canvas.drawRect(rects[i], blinkPaint);
			}else{
				canvas.drawRect(rects[i], stablePaint);
			}
		}

		if(blinkPos >= rects.length-1){
			blinkPos = 0;
		}else{
			blinkPos++;
		}

	}

}
