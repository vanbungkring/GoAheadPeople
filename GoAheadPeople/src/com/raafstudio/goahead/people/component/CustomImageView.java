package com.raafstudio.goahead.people.component;

import com.raafstudio.goahead.people.R;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.View.MeasureSpec;
import android.widget.ImageView;

public class CustomImageView extends ImageView {
	private static final int INVALID_POINTER_ID = -1;

	private Drawable mIcon;
	private float mPosX;
	private float mPosY;

	private float mLastTouchX;
	private float mLastTouchY;
	private int mActivePointerId = INVALID_POINTER_ID;

	private ScaleGestureDetector mScaleDetector;
	private float mScaleFactor = 1.f;

	public CustomImageView(Context context, Drawable img) {

		this(context, null, 0, img);
	}

	public CustomImageView(Context context, AttributeSet attrs, Drawable img) {
		this(context, attrs, 0, img);
	}

	public CustomImageView(Context context, AttributeSet attrs, int defStyle,
			Drawable img) {
		super(context, attrs, defStyle);
		if (img == null)
			mIcon = context.getResources().getDrawable(R.drawable.ic_launcher);
		else
			mIcon = img;
		mIcon.setBounds(0, 0, mIcon.getIntrinsicWidth(),
				mIcon.getIntrinsicHeight());

		mScaleDetector = new ScaleGestureDetector(context, new ScaleListener());
	}

	public void setCustomDrawable(Drawable img) {
		mIcon = img;
		invalidate();
	}

	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		// Let the ScaleGestureDetector inspect all events.
		mScaleDetector.onTouchEvent(ev);

		final int action = ev.getAction();
		switch (action & MotionEvent.ACTION_MASK) {
		case MotionEvent.ACTION_DOWN: {
			final float x = ev.getX();
			final float y = ev.getY();

			mLastTouchX = x;
			mLastTouchY = y;
			mActivePointerId = ev.getPointerId(0);
			break;
		}

		case MotionEvent.ACTION_MOVE: {
			final int pointerIndex = ev.findPointerIndex(mActivePointerId);
			final float x = ev.getX(pointerIndex);
			final float y = ev.getY(pointerIndex);

			// Only move if the ScaleGestureDetector isn't processing a gesture.
			if (!mScaleDetector.isInProgress()) {
				final float dx = x - mLastTouchX;
				final float dy = y - mLastTouchY;

				mPosX += dx;
				mPosY += dy;

				invalidate();
			}

			mLastTouchX = x;
			mLastTouchY = y;

			break;
		}

		case MotionEvent.ACTION_UP: {
			mActivePointerId = INVALID_POINTER_ID;
			break;
		}

		case MotionEvent.ACTION_CANCEL: {
			mActivePointerId = INVALID_POINTER_ID;
			break;
		}

		case MotionEvent.ACTION_POINTER_UP: {
			final int pointerIndex = (ev.getAction() & MotionEvent.ACTION_POINTER_INDEX_MASK) >> MotionEvent.ACTION_POINTER_INDEX_SHIFT;
			final int pointerId = ev.getPointerId(pointerIndex);
			if (pointerId == mActivePointerId) {
				// This was our active pointer going up. Choose a new
				// active pointer and adjust accordingly.
				final int newPointerIndex = pointerIndex == 0 ? 1 : 0;
				mLastTouchX = ev.getX(newPointerIndex);
				mLastTouchY = ev.getY(newPointerIndex);
				mActivePointerId = ev.getPointerId(newPointerIndex);
			}
			break;
		}
		}

		return true;
	}

	@Override
	public void onDraw(Canvas canvas) {
		super.onDraw(canvas);

		canvas.save();
		canvas.translate(mPosX, mPosY);
		canvas.scale(mScaleFactor, mScaleFactor);
		mIcon.draw(canvas);
		canvas.restore();
	}

	private class ScaleListener extends
			ScaleGestureDetector.SimpleOnScaleGestureListener {
		@Override
		public boolean onScale(ScaleGestureDetector detector) {
			mScaleFactor *= detector.getScaleFactor();

			// Don't let the object get too small or too large.
			mScaleFactor = Math.max(0.1f, Math.min(mScaleFactor, 5.0f));

			invalidate();
			return true;
		}
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

		Drawable d = getDrawable();
		if (d != null) {
			// ceil not round - avoid thin vertical gaps along the left/right
			// edges
			try {
				float imgHeight = (float) ((BitmapDrawable) d).getBitmap()
						.getHeight();
				float imgWidth = (float) ((BitmapDrawable) d).getBitmap()
						.getWidth();
				float pembagi = 1;
				int width = 1;
				int height = 1;// (int)
				pembagi = imgHeight / imgWidth;
				width = MeasureSpec.getSize(widthMeasureSpec);
				height = (int) ((float) width * pembagi);
				setMeasuredDimension(width, height);
			} catch (Exception e) {
				// TODO: handle exception
				super.onMeasure(widthMeasureSpec, heightMeasureSpec);

			}

		} else {
			super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		}
	}
}