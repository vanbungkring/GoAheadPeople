package com.raafstudio.goahead.people.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TableRow;

import com.raafstudio.goahead.people.ActivitySettingAccount;
import com.raafstudio.goahead.people.ActivitySettingBasic;
import com.raafstudio.goahead.people.ActivitySettingHelp;
import com.raafstudio.goahead.people.ActivitySettingTerms;
import com.raafstudio.goahead.people.R;
import com.raafstudio.goahead.people.helper.so;

public class FragmentSetting extends FragmentBase implements OnClickListener {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		rView = inflater.inflate(R.layout.fragment_setting, container, false);

		((TableRow) rView.findViewById(R.id.tableRow1))
				.setOnClickListener(this);
		((TableRow) rView.findViewById(R.id.tableRow2))
				.setOnClickListener(this);
		((TableRow) rView.findViewById(R.id.tableRow3))
				.setOnClickListener(this);
		((TableRow) rView.findViewById(R.id.tableRow4))
				.setOnClickListener(this);
		return rView;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tableRow1:
			startActivity(new Intent(getActivity(), ActivitySettingBasic.class));
			break;
		case R.id.tableRow2:
			startActivity(new Intent(getActivity(),
					ActivitySettingAccount.class));
			break;
		case R.id.tableRow3:
			startActivity(new Intent(getActivity(), ActivitySettingHelp.class));
			break;
		case R.id.tableRow4:
			startActivity(new Intent(getActivity(), ActivitySettingTerms.class));
			break;
		}
	}
}
