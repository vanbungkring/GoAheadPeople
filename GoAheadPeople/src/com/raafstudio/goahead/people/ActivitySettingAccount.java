package com.raafstudio.goahead.people;

import java.util.ArrayList;
import java.util.List;
 
import android.database.Cursor;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.raaf.rDialog;
import com.raafstudio.goahead.people.helper.API;
import com.raafstudio.goahead.people.helper.so;
import com.raafstudio.goahead.people.model.Account;
import com.raafstudio.goahead.people.model.Brand;
import com.raafstudio.goahead.people.model.City;
import com.raafstudio.goahead.people.model.Item;
import com.raafstudio.goahead.people.model.Province;
import com.raafstudio.goahead.people.model.SubBrand;

public class ActivitySettingAccount extends ActivityBase {

	Spinner SpProp;
	EditText EtMobilePhone, EtAddress;
	AutoCompleteTextView EtCity, EtSubBrand;
	List<Item> listCity, listSubBrand;
	Boolean doUser, doSubBrand, doCity;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setting_account);
		bindToolbar();
		getSupportActionBar().setTitle("Account");
		rDialog
				.ShowProgressDialog(this, "please wait", "loading data", true);

		SpProp = (Spinner) findViewById(R.id.SpProvince);
		EtCity = (AutoCompleteTextView) findViewById(R.id.EtUserCity);
		EtSubBrand = (AutoCompleteTextView) findViewById(R.id.EtSubBrand);
		EtMobilePhone = (EditText) findViewById(R.id.EtUserMobilePhone);
		EtAddress = (EditText) findViewById(R.id.EtUserAddress);
		SpProp.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				BindCity(false, position);
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub

			}
		});

		((Button) findViewById(R.id.BtSave))
				.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						String prop = so.getProvinces()
								.get(SpProp.getSelectedItemPosition())
								.getStateCode();
						String city = "", brand = "", sub_brand = "";
						for (int i = 0; i < listCity.size(); i++) {
							if (listCity.get(i).getDesc()
									.equals(EtCity.getText().toString()))
								city = listCity.get(i).getCode();
						}
						for (int i = 0; i < so.getSubBrands().size(); i++) {
							if (so.getSubBrands().get(i).getSubBrandName()
									.equals(EtSubBrand.getText().toString())) {
								brand = so.getSubBrands().get(i).getBrandCode();
								sub_brand = so.getSubBrands().get(i)
										.getSubBrandCode();
							}

						}
						if (EtMobilePhone.getText().length() > 0
								&& EtCity.getText().toString().length() > 0
								&& EtSubBrand.getText().toString().length() > 0
								&& city.length() > 0 && brand.length() > 0
								&& sub_brand.length() > 0) {

							API.SettingUpdateAccount("", EtMobilePhone
									.getText().toString(), prop, city, brand,
									sub_brand, so.getUser().getAccount()
											.getRegistrationID(), handler);
							rDialog.ShowProgressDialog(
									ActivitySettingAccount.this, "Please Wait",
									"Updating Account", true);
						} else
							rDialog.SetToast(ActivitySettingAccount.this,
									"field empty");
					}
				});
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		doUser = false;
		doCity = false;
		doSubBrand = false;
		if (so.getUser().getAccount().getFirstName() == "")
			API.SettingGetUserAccount(handler);
		else
			doUser = true;
		if (so.getSubBrands().size() == 0)
			API.BrandListSub(handler);
		else
			BindBrand();
		if (so.getProvinces().size() == 0)
			API.BrandProvince(handler);
		else
			BindCity(true, 0);
		BindUser();
	}

	private void BindUser() {
		if (doCity && doSubBrand && doUser) {
			EtMobilePhone.setText(so.getUser().getAccount().getMobilePhone());
			EtAddress.setText(so.getUser().getAccount().getStreetName());
			for (int i = 0; i < so.getProvinces().size(); i++) {
				if (so.getProvinces().get(i).getStateName()
						.equals(so.getUser().getAccount().getStateID()))
					SpProp.setSelection(i);
			}
			EtCity.setText(so.getUser().getAccount().getCityID());
			for (Item li : listSubBrand) {
				if (li.getCode().equals(
						so.getUser().getAccount().getSubBrand1_ID()))
					EtSubBrand.setText(li.getDesc());
			}
			rDialog.CloseProgressDialog();
		}
	}

	private void BindBrand() {
		doSubBrand=true;
		listSubBrand = new ArrayList<Item>();
		for (SubBrand c : so.getSubBrands()) {
			listSubBrand.add(new Item(c.getSubBrandID(), c.getSubBrandName()));
		}
		EtSubBrand.setAdapter(new ArrayAdapter(this,
				android.R.layout.simple_list_item_1, listSubBrand));
	}

	private void BindCity(Boolean isprop, int prop) {
		if (isprop) {
			List<String> list = new ArrayList<String>();
			for (Province p : so.getProvinces()) {
				list.add(p.getStateName());
			}

			ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
					android.R.layout.simple_spinner_item, list);

			dataAdapter
					.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

			SpProp.setAdapter(dataAdapter);
		} else {
			doCity = true;
			listCity = new ArrayList<Item>();
			for (City c : so.getProvinces().get(prop).getCities()) {
				listCity.add(new Item(c.getCityCode(), c.getCityName()));
			}
			EtCity.setAdapter(new ArrayAdapter(this,
					android.R.layout.simple_list_item_1, listCity));
		}
	}

	@Override
	protected void handlerResponse(Message msg) {
		// TODO Auto-generated method stub
		super.handlerResponse(msg);
		if (so.meta.getCode() == 200) {
			if (so.meta.getModul() == so.modul_brand) {
				switch (so.meta.getMode()) {

				case 2:
					BindBrand();
					BindUser();
					break;
				case 3:
					BindCity(true, 0);
					API.BrandCity(handler);
					break;
				case 4:
					BindCity(false, 0);
					BindUser();
					break;
				}
			} else {
				switch (so.meta.getMode()) {

				case 4:
					doUser = true;
					BindUser();
					break;
				case 5:
					API.SettingGetUserAccount(null);
					rDialog.CloseProgressDialog();
					rDialog.SetToast(this, "sukses");
					break;
				}

			}

		} else
			rDialog.SetToast(this, so.meta.getErrorDetail());
	}
}
