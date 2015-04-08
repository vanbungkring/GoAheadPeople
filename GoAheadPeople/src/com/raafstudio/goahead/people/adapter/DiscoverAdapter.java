package com.raafstudio.goahead.people.adapter;

import java.net.URI;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.raaf.rDialog;
import com.raafstudio.goahead.people.R;
import com.raafstudio.goahead.people.activity.ActivityMain;
import com.raafstudio.goahead.people.component.CircleImageView;
import com.raafstudio.goahead.people.component.ImageviewNormal;
import com.raafstudio.goahead.people.helper.API;
import com.raafstudio.goahead.people.helper.Util;
import com.raafstudio.goahead.people.helper.so;
import com.raafstudio.goahead.people.model.Artwork;

public class DiscoverAdapter extends ArrayAdapter<Artwork> {
	private Context context;
	private int layoutResourceId;
	private ArrayList<Artwork> data = new ArrayList<Artwork>();
	private Handler h;

	public DiscoverAdapter(Context context, int layoutResourceId,
			ArrayList<Artwork> data, Handler h) {
		super(context, layoutResourceId, data);
		this.layoutResourceId = layoutResourceId;
		this.context = context;
		this.data = data;
		this.h = h;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View row = convertView;
		ViewHolder holder = null;
		if (row == null) {
			LayoutInflater inflater = ((Activity) context).getLayoutInflater();
			row = inflater.inflate(layoutResourceId, parent, false);
			holder = new ViewHolder();
			holder.ImgArt = (ImageviewNormal) row.findViewById(R.id.ArtImg);
			holder.ImgUser = (CircleImageView) row
					.findViewById(R.id.ArtImgUserAvatar);
			holder.ImgLike = (ImageView) row.findViewById(R.id.ArtImgLike);
			holder.TvFullname = (TextView) row.findViewById(R.id.ArtTvFullname);
			holder.TvLike = (TextView) row.findViewById(R.id.ArtTvLike);
			holder.TvTime = (TextView) row.findViewById(R.id.ArtTvTime);
			holder.TvTitlle = (TextView) row.findViewById(R.id.ArtTvTitlle);
			holder.TvMissing = (TextView) row.findViewById(R.id.ArtTvMissing);
			holder.ArtPb = (ProgressBar) row.findViewById(R.id.ArtPb);

			holder.LayoutUser = (LinearLayout) row
					.findViewById(R.id.LayoutUser);

			row.setTag(holder);
		} else {
			holder = (ViewHolder) row.getTag();
		}

		final Artwork art = data.get(position);
		holder.LayoutUser.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				((ActivityMain) context).ShowProfile(art.getUser_id());
			}
		});
		if (art.getImage().equals("missing image")) {
			holder.TvMissing.setText("missing image");
			holder.ImgArt.setVisibility(View.INVISIBLE);
			holder.ArtPb.setVisibility(View.INVISIBLE);
		} else {
			holder.ImgArt.setVisibility(View.VISIBLE);
			holder.ImgArt.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					((ActivityMain) context).ShowArt(art.getUser_id(),
							art.getString_id());
				}
			});

			Glide.with(context).load(art.getImage()).asBitmap()
					.diskCacheStrategy(DiskCacheStrategy.SOURCE)
					.placeholder(R.drawable.trans).into(holder.ImgArt);
		}
		Glide.with(context).load(art.getUser_avatar()).asBitmap()
				.diskCacheStrategy(DiskCacheStrategy.SOURCE)
				.placeholder(R.drawable.trans).into(holder.ImgUser);
		holder.TvFullname.setText("By " + art.getFullname());
		holder.TvLike.setText(art.getLike_stat() + "");
		holder.TvTime.setText(art.getTime() + " ago");
		holder.TvTitlle.setText(art.getTitle());
		holder.ImgLike.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// / rDialog.SetToast(context, "like " + art.getArtwork_id());
				API.DiscoverLikeArtwork(art.getArtwork_id(), h);

			}
		});
		return row;
	}

	static class ViewHolder {
		TextView TvTime, TvTitlle, TvFullname, TvLike, TvMissing;
		ImageviewNormal ImgArt;
		CircleImageView ImgUser;
		ImageView ImgLike;
		LinearLayout LayoutUser;
		ProgressBar ArtPb;
	}
}