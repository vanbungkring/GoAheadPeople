package com.raafstudio.goahead.people.fragment;

import com.raaf.rDialog;
import com.raafstudio.goahead.people.activity.ActivityMain;
import com.raafstudio.goahead.people.helper.API;
import com.raafstudio.goahead.people.helper.so;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

@SuppressLint("HandlerLeak")
public class FragmentBase extends Fragment {
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
		if (so.meta.getCode() == 401)
			((ActivityMain) getActivity()).doLogin();
		else if (so.meta.getCode() != 200)
			rDialog.SetToast(getActivity(), so.meta.getErrorType() + " - "
					+ so.meta.getErrorDetail());
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
