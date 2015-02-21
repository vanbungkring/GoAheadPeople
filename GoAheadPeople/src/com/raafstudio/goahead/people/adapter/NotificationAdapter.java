package com.raafstudio.goahead.people.adapter;

import java.util.ArrayList;
 
import android.app.Activity;
import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy; 
import com.raafstudio.goahead.people.R;
import com.raafstudio.goahead.people.component.CircleImageView;
import com.raafstudio.goahead.people.model.Notification;

public class NotificationAdapter extends ArrayAdapter<Notification> {
	private Context context;
	private int layoutResourceId;
	private ArrayList<Notification> data = new ArrayList<Notification>();

	public NotificationAdapter(Context context, int layoutResourceId,
			ArrayList<Notification> data) {
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
			holder.imgAvatar = (CircleImageView) row
					.findViewById(R.id.NotifImgAvatar);
			holder.imgAktif = (CircleImageView) row
					.findViewById(R.id.NotifImgAktif);
			holder.tvMessage = (TextView) row.findViewById(R.id.NotifTvMessage);
			holder.tvTime = (TextView) row.findViewById(R.id.NotifTvTime);
			row.setTag(holder);
		} else {
			holder = (ViewHolder) row.getTag();
		}

		Notification notif = data.get(position);
		Glide.with(context).load(notif.getUser_avatar()).asBitmap()
				.diskCacheStrategy(DiskCacheStrategy.SOURCE)
				.into(holder.imgAvatar);
		holder.tvMessage.setText(Html.fromHtml(notif.getMessage().replace(
				"strong>", "b>")));
		holder.tvTime.setText(notif.getDuration());
		if (notif.getIs_read() == 1)
			holder.imgAktif.setVisibility(View.GONE);
		return row;
	}

	ProgressBar pb1;

	static class ViewHolder {
		CircleImageView imgAvatar, imgAktif;
		TextView tvMessage, tvTime;
	}
}
