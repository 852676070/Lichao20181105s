package com.lichao.bwei.com.lichao20181105;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;

/**
 * date:2018/11/5
 * author:李超(li)
 * function:
 */
public class ZhuanPan extends View  implements View.OnClickListener {

    private Paint mPaint;
    private int mX;
    private int mY;
    private boolean isRote;
    private int[] mColor;
    private String[] mText;
    private RotateAnimation mRotateAnimation;

    public ZhuanPan(Context context) {
        this(context,null);
    }

    public ZhuanPan(Context context,  AttributeSet attrs) {
        this(context, attrs,-1);
    }

    public ZhuanPan(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        int widthPixels = displayMetrics.widthPixels;
        int heightPixels = displayMetrics.heightPixels;
        mX = widthPixels / 2;
        mY = heightPixels / 2;
        mText = new String[]{"京东","百度","阿里","华为","顺丰","联想"};
        mColor = new int[]{Color.RED,Color.BLUE,Color.GREEN,Color.RED,Color.BLUE,Color.GREEN};
        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(40);
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);
        this.setOnClickListener(this);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(100,100);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.translate(mX,mY);
        //画弧
        RectF rectF = new RectF(-240, -240, 240, 240);
        int start = 60;
        for (int i = 0; i < 6; i++) {
            mPaint.setColor(mColor[i]);
           canvas.drawArc(rectF,start*i,60,true,mPaint);
        }
        //画中心圆
        mPaint.setColor(Color.RED);
        canvas.drawCircle(0,0,100,mPaint);
        //写中心字
        mPaint.setColor(Color.WHITE);
        mPaint.setTextSize(40);
        Rect rect = new Rect();
        mPaint.getTextBounds("start",0,5,rect);
        int width = rect.width();
        int height = rect.height();
        canvas.drawText("start",-width/2,height,mPaint);
        //写字
        RectF rect1 = new RectF(-150,-150,150,150);
        for (int i = 0; i < 6; i++) {
            mPaint.setColor(Color.WHITE);
            Path path = new Path();
            path.addArc(rect1,start*i+15,60);
            canvas.drawTextOnPath(mText[i],path,0,0,mPaint);
        }
    }


    @Override
    public void onClick(View v) {
        if (!isRote){
            startaction();
        }
    }

    private void startaction() {
        isRote = true;
        double random = Math.random();
        mRotateAnimation = new RotateAnimation(0,(float)(720*random),mX,mY);
        mRotateAnimation.setFillAfter(true);
        mRotateAnimation.setDuration(1000);
        mRotateAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                isRote = false;
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    startAnimation(mRotateAnimation);

    }
}
