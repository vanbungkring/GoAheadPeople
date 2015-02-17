package com.raafstudio.goahead.people;

import android.os.Bundle; 
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

public class ActivitySearch extends ActivityBase {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search);
		Toolbar mToolbar = (Toolbar) findViewById(R.id.SearchToolbar);
		setSupportActionBar(mToolbar);
		getSupportActionBar().setDisplayShowHomeEnabled(true);

	}

	@Override
	public boolean onOptionsItemSelected(MenuItem menuItem) {

		if (menuItem.getItemId() == android.R.id.home) {
			finish();
		}
		return super.onOptionsItemSelected(menuItem);
	}
}
