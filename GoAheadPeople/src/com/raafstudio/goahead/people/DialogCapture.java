package com.raafstudio.goahead.people;

import java.io.File;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.raaf.rDialog;
import com.raaf.rImaging;
import com.raaf.rSecurity;
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
				so.requester = 11;
				so.apply_image = true;
				finish();
			}
		});
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK
				&& null != data) {
			Uri selectedImage = data.getData();
			String[] filePathColumn = { MediaStore.Images.Media.DATA };

			Cursor cursor = getContentResolver().query(selectedImage,
					filePathColumn, null, null, null);
			cursor.moveToFirst();

			int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
			so.PicturePath = cursor.getString(columnIndex);
			cursor.close();
			imageView.setVisibility(View.VISIBLE);
			BtApply.setVisibility(View.VISIBLE);
			Glide.with(this).load(so.PicturePath).asBitmap().centerCrop()
					.override(220, 220)
					.listener(new RequestListener<String, Bitmap>() {

						@Override
						public boolean onResourceReady(Bitmap arg0,
								String arg1, Target<Bitmap> arg2, boolean arg3,
								boolean arg4) {
							String filename;
							if (so.requester == 1)
								filename = Util.getDir()
										+ "/"
										+ rSecurity.getMD5(so.getUser()
												.getUser_id() + "") + ".jpeg";
							else
								filename = Util.getDirArt() + "/"
										+ rSecurity.getMD5("new_art");
							//rDialog.SetToast(DialogCapture.this, filename);
							File fl = new File(filename);
							if (fl.exists())
								fl.delete();
							rImaging.SaveImageToFile(arg0, filename,
									CompressFormat.JPEG, 100);
							return false;
						}

						@Override
						public boolean onException(Exception arg0, String arg1,
								Target<Bitmap> arg2, boolean arg3) {
							// TODO Auto-generated method stub
							return false;
						}
					}).into(imageView);
			// imageView.setImageBitmap(rImaging.getPreview());

		}

	}

	@Override
	public void onBackPressed() {
		so.requester = 12;
		super.onBackPressed();
	}
}
