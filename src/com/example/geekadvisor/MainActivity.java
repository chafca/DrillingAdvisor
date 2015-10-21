package com.example.geekadvisor;

import geekAdvisor_pack.Support;
import geekAdvisor_pack.Ue;
import org.json.JSONObject;
import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.FragmentTransaction;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.Toast;

@SuppressLint("NewApi")
public class MainActivity extends FragmentActivity implements
		ActionBar.TabListener {

	AppSectionsPagerAdapter mAppSectionsPagerAdapter;
	ViewPager mViewPager;
	static String title;
	int a;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		getActionBar().setDisplayHomeAsUpEnabled(true);

		title = getIntent().getExtras().get("Nom").toString();
		this.setTitle(title);

		// Set up the action bar.
		final ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		mAppSectionsPagerAdapter = new AppSectionsPagerAdapter(
				getSupportFragmentManager());
		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(mAppSectionsPagerAdapter);

		mViewPager
				.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
					@Override
					public void onPageSelected(int position) {
						actionBar.setSelectedNavigationItem(position);
					}
				});

		for (int i = 0; i < mAppSectionsPagerAdapter.getCount(); i++) {
			actionBar.addTab(actionBar.newTab()
					.setText(mAppSectionsPagerAdapter.getPageTitle(i))
					.setTabListener(this));
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.accueil, menu);
		SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
		SearchView searchView = (SearchView) menu.findItem(R.id.search)
				.getActionView();
		searchView.setSearchableInfo(searchManager
				.getSearchableInfo(getComponentName()));
		searchView.setQueryHint("Rechercher un Cours..");
		searchView
				.setOnQueryTextListener(new android.widget.SearchView.OnQueryTextListener() {

					public boolean onQueryTextChange(String newText) {
						return false;
					}

					public boolean onQueryTextSubmit(String query) {
						if (query.length() != 0) {
							Toast.makeText(MainActivity.this, query,
									Toast.LENGTH_LONG).show();

							try {
								String rs = new Support().getSupportName(query);
								JSONObject jObject = new JSONObject(rs);
								JSONObject js = jObject.optJSONObject("" + 0);
								String nomCours = js.getJSONObject("support")
										.getString("Nom");
								String id = ""
										+ js.getJSONObject("support").getInt(
												"Id");
								if (query.equals(nomCours)) {
									Intent intent = new Intent(
											MainActivity.this,
											CoursActivity.class);
									intent.putExtra("Nom", query);
									intent.putExtra("id", id);
									startActivity(intent);
								} else {
									new AlertDialog.Builder(MainActivity.this)
											.setTitle("Alert")
											.setMessage(
													"le nom du cours est incorrect")
											.setNeutralButton("Fermer", null)
											.show();
								}
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}

							return true;

						}
						return false;
					}
				});
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		while ((id == R.id.action_settings) && (id == R.id.search)
				&& (id == R.id.action_overflow)) {
			openSearch();
			openSetting();
			// case R.id.action_settings: openSearch();return true;
		}
		return super.onOptionsItemSelected(item);
	}

	private void openSetting() {

	}

	private void openSearch() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTabSelected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
		mViewPager.setCurrentItem(tab.getPosition());
	}

	@Override
	public void onTabUnselected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
	}

	@Override
	public void onTabReselected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
	}

	public static class AppSectionsPagerAdapter extends FragmentPagerAdapter {

		public AppSectionsPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int i) {
			switch (i) {
			case 0:
				return new ListCoursActivity();
			case 1:
				return new ListTutoriauxActivity();
			case 2:
				return new ListLivresActivity();
			default:
				Fragment fragment = new DefaultActivity();
				Bundle args = new Bundle();
				args.putInt(DefaultActivity.ARG_SECTION_NUMBER, i + 2);
				fragment.setArguments(args);
				return fragment;
			}
		}

		@Override
		public int getCount() {
			return 3;
		}

		@Override
		public CharSequence getPageTitle(int position) {

			switch (position) {
			case 0:
				return "Cours";
			case 1:
				return "Tutoriaux";
			case 2:
				return "Livres";
			}
			return null;
			/* return "Section " + (position + 1); */
		}
	}

	public static class PlaceholderFragment extends Fragment {

		private static final String ARG_SECTION_NUMBER = "section_number";

		public static PlaceholderFragment newInstance(int sectionNumber) {
			PlaceholderFragment fragment = new PlaceholderFragment();
			Bundle args = new Bundle();
			args.putInt(ARG_SECTION_NUMBER, sectionNumber);
			fragment.setArguments(args);
			return fragment;
		}

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			return rootView;
		}
	}

}
