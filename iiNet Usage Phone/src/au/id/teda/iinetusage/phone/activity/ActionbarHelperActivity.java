package au.id.teda.iinetusage.phone.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import au.id.teda.iinetusage.phone.R;
import au.id.teda.iinetusage.phone.helper.PreferenceHelper;

public class ActionbarHelperActivity extends Activity {
	
	// Static strings for debug tags
	private static final String DEBUG_TAG = "iiNet Usage";
	private static final String INFO_TAG = ActionbarHelperActivity.class.getSimpleName();

	@Override
	protected void onResume() {

		// Show hide phone status bar
		setPhoneStatusBar();

		super.onResume();
	}

	public void onClickActionBarHome(View view) {

		// Start dashboard activity
		Intent dashboardActivityIntent = new Intent(this, MainActivity.class);
		startActivity(dashboardActivityIntent);
	}

	public void hideActionbarHomeIcon() {
		ImageButton myHomeImageButton = (ImageButton) findViewById(R.id.actionbar_home_button);
		ImageView mySeparatorImage = (ImageView) findViewById(R.id.actionbar_separator_1);
		myHomeImageButton.setVisibility(View.GONE);
		mySeparatorImage.setVisibility(View.GONE);
	}

	public void showActionbarHomeIcon() {
		ImageButton myHomeImageButton = (ImageButton) findViewById(R.id.actionbar_home_button);
		ImageView mySeparatorImage = (ImageView) findViewById(R.id.actionbar_separator_1);
		myHomeImageButton.setVisibility(View.VISIBLE);
		mySeparatorImage.setVisibility(View.VISIBLE);
	}

	public void setActionbarTitle(String actionbarTitle) {
		TextView myActionbarTitle = (TextView) findViewById(R.id.actionbar_title);
		myActionbarTitle.setText(actionbarTitle);
	}

	public void hideActionbarRefersh() {
		ImageButton myRefereshImageButton = (ImageButton) findViewById(R.id.actionbar_refresh_button);
		myRefereshImageButton.setVisibility(View.GONE);
	}

	public void showActionbarRefersh() {
		ImageButton myRefereshImageButton = (ImageButton) findViewById(R.id.actionbar_refresh_button);
		myRefereshImageButton.setVisibility(View.VISIBLE);
	}

	/**
	 * Action bar menu onClick method
	 * @param button
	 */
	public void onActionbarMenuClick(View button) {
		switch (button.getId()) {
		case R.id.actionbar_menu_stats:
			// Intent statsIntent = new Intent(this, MainActivity.class);
			// startActivity(statsIntent);
			Toast.makeText(this, "Stats Activity", Toast.LENGTH_SHORT).show();
			break;
		case R.id.actionbar_menu_graph:
			Toast.makeText(this, "Graph Activity", Toast.LENGTH_SHORT).show();
			break;
		case R.id.actionbar_menu_data:
			Toast.makeText(this, "Data Activity", Toast.LENGTH_SHORT).show();
			break;
		case R.id.actoionbar_menu_archive:
			Toast.makeText(this, "Archive Activity", Toast.LENGTH_SHORT).show();
			break;
		default:
			Toast.makeText(this, "Button not recognised", Toast.LENGTH_SHORT)
					.show();

		}
	}

	/**
	 * Show hide phone status based on preference setting
	 */
	public void setPhoneStatusBar() {
		PreferenceHelper mySetttings = new PreferenceHelper();

		if (mySetttings.hidePhoneStatusBar()) {
			((Activity) this).getWindow().addFlags(
					WindowManager.LayoutParams.FLAG_FULLSCREEN);
		} else {
			((Activity) this).getWindow().clearFlags(
					WindowManager.LayoutParams.FLAG_FULLSCREEN);
		}
	}

	/**
	 * Get int value of screen orientation
	 * @return int
	 */
	public int getScreenOrientation() {
	        return getResources().getConfiguration().orientation;
	}
	
	/**
	 * Check if in portrait mode
	 * @return true if device is in portrait
	 */
	public boolean isPortrait() {
		 if(getScreenOrientation() == Configuration.ORIENTATION_PORTRAIT) {
			 //App is in Portrait mode
			 return true;
		 }
		 else{
		     //App is in LandScape mode
			 return false;
		}
	}

}
