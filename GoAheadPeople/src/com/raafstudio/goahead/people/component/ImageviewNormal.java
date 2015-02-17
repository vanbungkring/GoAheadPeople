package com.raafstudio.goahead.people.component;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View.MeasureSpec;
import android.widget.ImageView;

public class ImageviewNormal extends ImageView {

	public ImageviewNormal(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		attrs.getAttributeValue(0);
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
				height = (int) ((float) width * pembagi);// (int)
//				if (imgWidth > imgHeight) {
//					pembagi = imgHeight / imgWidth;
//					width = MeasureSpec.getSize(widthMeasureSpec);
//					height = (int) ((float) width * pembagi);// (int)
//				} else {
//					pembagi = imgWidth / imgHeight;
//					height = MeasureSpec.getSize(heightMeasureSpec);
//					width = (int) ((float) height * pembagi);// (int)
//					//width = MeasureSpec.getSize(widthMeasureSpec);
//					// height = width;
//					//height = width;
//
//					// pembagi = imgHeight / imgWidth;
//				}

				// (Math.ceil((float)
				// width * 3 / 4));
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
