package com.raafstudio.goahead.people.fragment;
 

import com.raafstudio.goahead.people.helper.API; 

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

@SuppressLint("HandlerLeak") public class FragmentBase extends Fragment {
	private int Pos = -1;
	protected View rView;
	protected ProgressBar pb;
	protected TextView TvNoNetwork;

	protected final Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			handlerResponse(msg);
		}
	};

	protected void handlerResponse(Message msg) {
		API.ApiParser(msg);
	}

	public int getPos() {
		if (Pos == -1)
			Pos = 0;
		return Pos;
	}

	public void setPos(int pos) {
		Pos = pos;
	}
}
