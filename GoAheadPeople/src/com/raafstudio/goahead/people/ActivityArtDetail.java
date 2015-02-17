package com.raafstudio.goahead.people;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.raaf.rDialog;
import com.raaf.custom.CircleImageView;
import com.raafstudio.goahead.people.component.ImageviewBox;
import com.raafstudio.goahead.people.component.ImageviewNormal;
import com.raafstudio.goahead.people.helper.API;
import com.raafstudio.goahead.people.helper.so;
import com.raafstudio.goahead.people.model.Artwork;
import com.raafstudio.goahead.people.model.Comment;

import android.content.Context;
import android.os.Bundle;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ActivityArtDetail extends ActivityBase {

	ImageviewNormal ArtImg;
	TextView ArtTvTitlle, ArtTvLikeCount, ArtTvCommentCount, ArtTvDesc,
			ArtTvLike, ArtTvComment, ArtTvFullname, ArtTvCommentSum;
	ImageView ArtImgMenu, ArtImgFollow;
	CircleImageView ArtImgUserAvatar;
	LinearLayout mListArtwork, ArtLayoutComment;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_artwork_detail);
		bindToolbar();
		getSupportActionBar().setTitle("Artwork Detail");
		ArtImg = (ImageviewNormal) findViewById(R.id.ArtImg);
		ArtTvTitlle = (TextView) findViewById(R.id.ArtTvTitlle);
		ArtTvLikeCount = (TextView) findViewById(R.id.ArtTvLikeCount);
		ArtTvCommentCount = (TextView) findViewById(R.id.ArtTvCommentCount);
		ArtTvDesc = (TextView) findViewById(R.id.ArtTvDesc);
		ArtTvLike = (TextView) findViewById(R.id.ArtTvLike);
		ArtTvComment = (TextView) findViewById(R.id.ArtTvComment);
		ArtTvFullname = (TextView) findViewById(R.id.ArtTvFullname);
		ArtTvCommentSum = (TextView) findViewById(R.id.ArtTvCommentSum);
		ArtImgMenu = (ImageView) findViewById(R.id.ArtImgMenu);
		ArtImgFollow = (ImageView) findViewById(R.id.ArtImgFollow);
		ArtImgUserAvatar = (CircleImageView) findViewById(R.id.ArtImgUserAvatar);
		mListArtwork = (LinearLayout) findViewById(R.id.ArtLayout);
		ArtLayoutComment = (LinearLayout) findViewById(R.id.ArtLayoutComment);
		User_id = getIntent().getIntExtra("user_id", 0);
		String_id = getIntent().getStringExtra("string_id");
		Glide.with(this).load(so.getUserOther().getUser_avatar()).asBitmap()
				.diskCacheStrategy(DiskCacheStrategy.SOURCE)
				.into(ArtImgUserAvatar);
		ArtTvFullname.setText("By " + so.getUserOther().getFullname());
		// if (so.getUserOther().getArtworks().size() == 0 && UserId > 0)
		ArtTvLike.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				API.DiscoverLikeArtwork(ArtTvLike.getTag().toString(), handler);
			}
		});
		ArtTvComment.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View vo) {

				View v = ArtLayoutComment.getChildAt(0);
				EditText EtComment = (EditText) v.findViewById(R.id.EtComment);
				EtComment.requestFocus();
				EtComment.setSelection(0);
				InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
				imm.showSoftInput(EtComment, InputMethodManager.SHOW_IMPLICIT);
			}
		});
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();

		API.DiscoverGetArtwork(String_id, handler);
		API.DiscoverGetArtworkComment(String_id, 1, handler);
		// else
		// loadArtwork();
	}

	@Override
	protected void handlerResponse(Message msg) {
		// TODO Auto-generated method stub
		super.handlerResponse(msg);
		if (so.meta.getCode() == 200) {
			if (so.meta.getModul() == so.modul_discover) {
				switch (so.meta.getMode()) {
				case 1:

					break;
				case 2:
					loadArtwork();
					break;
				case 3:
					loadComment();
					break;
				case 4:

					break;
				case 5:
					API.DiscoverGetArtworkComment(String_id, 1, handler);
					break;
				case 6:
					for (Artwork art : so.getUserOther().getArtworks()) {
						if (art.getString_id().equals(String_id)) {

							ArtTvLikeCount.setText(art.getLike_stat() + "");

							if (ArtTvLike.getText().equals("LIKE"))
								ArtTvLike.setText("UNLIKE");
							else
								ArtTvLike.setText("LIKE");

						}
					}
					break;
				}

			}
		} else
			rDialog.SetToast(this,
					so.meta.getCode() + " - " + so.meta.getErrorDetail());
		rDialog.CloseProgressDialog();
	}

	private void loadArtwork() {
		LayoutInflater inflater = null;
		inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		mListArtwork.removeAllViews();
		View v = null;
		for (Artwork art : so.getUserOther().getArtworks()) {
			if (art.getString_id().equals(String_id)) {
				Glide.with(this).load(art.getImage()).asBitmap()
						.diskCacheStrategy(DiskCacheStrategy.SOURCE)
						.placeholder(R.drawable.trans).into(ArtImg);
				ArtTvTitlle.setTag(art.getArtwork_id());
				ArtTvTitlle.setText(art.getTitle());
				ArtTvDesc.setText(art.getDescription());
				ArtTvLikeCount.setText(art.getLike_stat() + "");

				ArtTvLike.setTag(art.getArtwork_id());
				if (art.getIs_liked().equals("TRUE"))
					ArtTvLike.setText("UNLIKE");
				else
					ArtTvLike.setText("LIKE");

			} else {
				v = inflater.inflate(R.layout.item_artwork, null);
				ImageviewBox img = (ImageviewBox) v
						.findViewById(R.id.ImgArtwork);

				Glide.with(this).load(art.getImage()).asBitmap()
						.diskCacheStrategy(DiskCacheStrategy.SOURCE).into(img);
				final String sid = art.getString_id();
				img.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						String_id = sid;
						API.DiscoverGetArtwork(String_id, handler);
						API.DiscoverGetArtworkComment(String_id, 1, handler);
					}
				});
				mListArtwork.addView(v);
			}
		}
	}

	private void loadComment() {
		LayoutInflater inflater = null;
		inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		ArtLayoutComment.removeAllViews();
		View v = null;
		v = inflater.inflate(R.layout.item_comment, null);
		CircleImageView img1 = (CircleImageView) v.findViewById(R.id.ImgAvatar);
		((TextView) v.findViewById(R.id.TvCommentName))
				.setVisibility(View.GONE);
		((TextView) v.findViewById(R.id.TvCommentContent))
				.setVisibility(View.GONE);
		((TextView) v.findViewById(R.id.TvCommentTime))
				.setVisibility(View.GONE);
		final EditText EtComment = (EditText) v.findViewById(R.id.EtComment);

		final ImageView BtSend = (ImageView) v.findViewById(R.id.BtCommentSend);
		((LinearLayout) v.findViewById(R.id.LayoutComment))
				.setVisibility(View.VISIBLE);
		BtSend.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (EtComment.getText().toString().length() > 0)
					API.DiscoverPostArtworkComment(ArtTvTitlle.getTag()
							.toString(), EtComment.getText().toString(),
							handler);
			}
		});
		Glide.with(this).load(so.getUser().getUser_avatar()).asBitmap()
				.diskCacheStrategy(DiskCacheStrategy.SOURCE).into(img1);
		ArtLayoutComment.addView(v);

		for (Artwork art : so.getUserOther().getArtworks()) {
			if (art.getString_id().equals(String_id)) {
				ArtTvCommentCount.setText(art.getComments().size() + "");
				ArtTvCommentSum.setText(art.getComments().size() + " Comments");
				for (int i = 0; i < art.getComments().size(); i++) {
					Comment c = art.getComments().get(i);
					v = inflater.inflate(R.layout.item_comment, null);
					CircleImageView img = (CircleImageView) v
							.findViewById(R.id.ImgAvatar);
					final TextView TvName = (TextView) v
							.findViewById(R.id.TvCommentName);
					TextView TvContent = (TextView) v
							.findViewById(R.id.TvCommentContent);
					TextView TvTime = (TextView) v
							.findViewById(R.id.TvCommentTime);

					Glide.with(this).load(c.getUser().getUser_avatar())
							.asBitmap()
							.diskCacheStrategy(DiskCacheStrategy.SOURCE)
							.into(img);
					TvName.setTag(c.getUser().getUser_id());
					TvName.setText(c.getUser().getFullname());
					TvContent.setText(c.getComment_content());
					TvTime.setText(c.getCreatedatetime());

					img.setOnClickListener(new OnClickListener() {

						@Override
						public void onClick(View v) {
							ShowProfile(Integer.parseInt(TvName.getTag()
									.toString()));
						}
					});
					TvName.setOnClickListener(new OnClickListener() {

						@Override
						public void onClick(View v) {
							ShowProfile(Integer.parseInt(TvName.getTag()
									.toString()));
						}
					});
					ArtLayoutComment.addView(v);
				}

			}
		}

	}
}
