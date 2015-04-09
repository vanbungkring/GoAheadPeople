package com.raafstudio.goahead.people.activity;

import java.text.DateFormatSymbols;
import java.util.Calendar;

import com.raafstudio.goahead.people.R;

import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;

public class ActivityRegistration extends ActivityBase implements
		OnDateSetListener {

	private DatePickerDialog dpd;
	private int year, month, day;
	private Button BtRegister;
	private ImageView ImgUserBirth, ImgUserBrand;
	private TextView TvUserBirth, TvUserBrand;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		TvUserBirth = (TextView) findViewById(R.id.TvUserBirth);
		TvUserBrand = (TextView) findViewById(R.id.TvUserBrand);
		ImgUserBirth = (ImageView) findViewById(R.id.ImgUserBirth);
		ImgUserBrand = (ImageView) findViewById(R.id.ImgUserBrand);
		BtRegister = (Button) findViewById(R.id.BtRegister);
		final Calendar calendar = Calendar.getInstance();
		year = calendar.get(Calendar.YEAR);
		month = calendar.get(Calendar.MONTH);
		day = calendar.get(Calendar.DAY_OF_MONTH);

		dpd = new DatePickerDialog(this, this, year, month, day);

		ImgUserBirth.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				dpd.show();
			}
		});
		BtRegister.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

			}
		});
	}

	@Override
	public void onDateSet(DatePicker view, int year, int monthOfYear,
			int dayOfMonth) {
		// TODO Auto-generated method stub
		TvUserBirth.setText(dayOfMonth + " - " + getMonth(monthOfYear) + " - "
				+ year);
	}

	public String getMonth(int month) {
		return new DateFormatSymbols().getMonths()[month];
	}
}
