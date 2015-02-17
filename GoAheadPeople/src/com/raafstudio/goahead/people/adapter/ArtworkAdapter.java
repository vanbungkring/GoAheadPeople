package com.raafstudio.goahead.people.adapter;
 
import java.util.ArrayList;
 
import android.app.Activity;
import android.content.Context; 
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar; 

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy; 
import com.raafstudio.goahead.people.model.Artwork;

public class ArtworkAdapter extends ArrayAdapter<Artwork> {
	private Context context;
	private int layoutResourceId;
	private ArrayList<Artwork> data = new ArrayList<Artwork>();

	public ArtworkAdapter(Context context, int layoutResourceId,
			ArrayList<Artwork> data) {
		super(context, layoutResourceId, data);
		this.layoutResourceId = layoutResourceId;
		this.context = context;
		this.data = data;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View row = convertView;
		ViewHolder holder = null;
		if (row == null) {
			LayoutInflater inflater = ((Activity) context).getLayoutInflater();
			row = inflater.inflate(layoutResourceId, parent, false);
			holder = new ViewHolder(); 

			row.setTag(holder);
		} else {
			holder = (ViewHolder) row.getTag();
		}

		Artwork art = data.get(position);
		Glide.with(context).load(art.getUser_avatar()).asBitmap()
				.diskCacheStrategy(DiskCacheStrategy.SOURCE).into(holder.img);

		return row;
	}

	ProgressBar pb1;

	static class ViewHolder {
		ImageView img;
	}
}