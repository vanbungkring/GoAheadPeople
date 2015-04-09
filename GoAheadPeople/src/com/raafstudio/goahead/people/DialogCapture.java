package com.raafstudio.goahead.people;

import java.io.File;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.raaf.rDialog;
import com.raaf.rIO;
import com.raaf.rImaging;
import com.raaf.rIntent;
import com.raaf.rSecurity;
import com.raaf.io.rFile;
import com.raafstudio.goahead.people.component.CircleImageView;
import com.raafstudio.goahead.people.helper.Util;
import com.raafstudio.goahead.people.helper.so;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Bitmap.CompressFormat;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class DialogCapture extends Activity {
	private static int RESULT_LOAD_IMAGE = 1;
	private static int RESULT_CAMERA_IMAGE = 2;
	CircleImageView imageView;
	Button BtApply;
	TextView TvTitle;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		so.apply_image = false;
		setContentView(R.layout.dialog_image);
		imageView = (CircleImageView) findViewById(R.id.ImgAvatar);
		BtApply = (Button) findViewById(R.id.BtApplyImage);
		TvTitle = (TextView) findViewById(R.id.TvDialogTitle);
		if (so.requester == 1)
			TvTitle.setText("Avatar Source");
		else
			TvTitle.setText("Image Source");
		((ImageView) findViewById(R.id.ImgCaptureCam))
				.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						rIntent.TakePicture(DialogCapture.this, getFileName(),
								RESULT_CAMERA_IMAGE);
					}
				});
		((ImageView) findViewById(R.id.ImgCaptureFile))
				.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						Intent i = new Intent(
								Intent.ACTION_PICK,
								android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

						startActivityForResult(i, RESULT_LOAD_IMAGE);
					}
				});
		BtApply.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (so.requester == 2) {
					File f = new File(so.getFileArtSource());
					if (f.exists())
						f.delete();
					rIO.copyFile(getFileName(), so.getFileArtSource());
				}
				so.requester = 11;
				so.apply_image = true;
				finish();
			}
		});
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == RESULT_OK) {
			if (requestCode == RESULT_LOAD_IMAGE
					|| requestCode == RESULT_CAMERA_IMAGE) {
				if (data != null) {
					Uri selectedImage = data.getData();
					String[] filePathColumn = { MediaStore.Images.Media.DATA };

					Cursor cursor = getContentResolver().query(selectedImage,
							filePathColumn, null, null, null);
					cursor.moveToFirst();

					int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
					so.PicturePath = cursor.getString(columnIndex);
					cursor.close();
				} else
					so.PicturePath = getFileName();
				imageView.setVisibility(View.VISIBLE);
				BtApply.setVisibility(View.VISIBLE);
				File imageFile = new File(so.PicturePath);
				Bitmap bitmap = null;
				double pembanding = 0;
				if (imageFile.exists()) {
					bitmap = BitmapFactory.decodeFile(imageFile
							.getAbsolutePath());
					Boolean isLandscape = (bitmap.getWidth() > bitmap
							.getHeight());
					if (isLandscape) {
						if ((0.75 * (double) bitmap.getWidth()) > (double) bitmap
								.getHeight()) {
							double newWidth = (4 * (double) bitmap.getHeight()) / 3;
							double newX = ((double) bitmap.getWidth() - newWidth) / 2;
							bitmap = Bitmap.createBitmap(bitmap, (int) newX, 0,
									(int) newWidth, bitmap.getHeight());
						}
						pembanding = (double) bitmap.getHeight()
								/ (double) bitmap.getWidth();
						bitmap = rImaging.ScaleImage(bitmap, 1000,
								(int) (1000 * pembanding));
					} else {
						double newHeight = (double) bitmap.getWidth();
						double newY = ((double) bitmap.getHeight() - newHeight) / 2;
						bitmap = Bitmap.createBitmap(bitmap, 0, (int) newY,
								bitmap.getWidth(), (int) newHeight);

						bitmap = rImaging.ScaleImage(bitmap, 1000, 1000);
					}

					imageFile = new File(getFileName());
					if (imageFile.exists())
						imageFile.delete();
					rImaging.SaveImageToFile(bitmap, getFileName(),
							CompressFormat.JPEG, 100);
					bitmap.recycle();
				}
				imageView.setImageBitmap(rImaging
						.getImageFromFile(getFileName()));
				if (so.requester == 2) {
					File f = new File(so.getFileArtSource());
					if (f.exists())
						f.delete();
					rIO.copyFile(getFileName(), so.getFileArtSource());
				}
				so.requester = 11;
				so.apply_image = true;
				finish();
				// Glide.with(this).load(so.PicturePath).asBitmap().centerCrop()
				// .override(220, 220)
				// .diskCacheStrategy(DiskCacheStrategy.NONE)
				// .listener(new RequestListener<String, Bitmap>() {
				//
				// @Override
				// public boolean onResourceReady(Bitmap arg0,
				// String arg1, Target<Bitmap> arg2,
				// boolean arg3, boolean arg4) {
				// String filename = getFileName();
				// File fl = new File(filename);
				// if (fl.exists())
				// fl.delete();
				// rImaging.SaveImageToFile(arg0, filename,
				// CompressFormat.JPEG, 100);
				// return false;
				// }
				//
				// @Override
				// public boolean onException(Exception arg0,
				// String arg1, Target<Bitmap> arg2,
				// boolean arg3) {
				// // TODO Auto-generated method stub
				// return false;
				// }
				// }).into(imageView);
			}

		}

	}

	private String getFileName() {
		String filename;
		if (so.requester == 1)
			filename = Util.getDir() + "/"
					+ rSecurity.getMD5(so.getUser().getUser_id() + "")
					+ ".jpeg";
		else
			filename = Util.getDirArt() + "/" + rSecurity.getMD5("new_art");
		return filename;
	}

	@Override
	public void onBackPressed() {
		so.requester = 12;
		super.onBackPressed();
	}
}
