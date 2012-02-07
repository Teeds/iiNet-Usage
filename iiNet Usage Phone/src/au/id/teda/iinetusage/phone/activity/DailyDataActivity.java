package au.id.teda.iinetusage.phone.activity;

import android.app.Activity;
import android.app.ListActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.WindowManager;
import au.id.teda.iinetusage.phone.R;
import au.id.teda.iinetusage.phone.cursor.DailyDataCursorAdapter;
import au.id.teda.iinetusage.phone.database.DailyDataDBAdapter;
import au.id.teda.iinetusage.phone.helper.AccountHelper;
import au.id.teda.iinetusage.phone.helper.PreferenceHelper;

public class DailyDataActivity extends ListActivity {

	//Static strings for debugging 
	private static final String DEBUG_TAG = "iiNet Usage";
	private static final String INFO_TAG = DailyDataActivity.class.getSimpleName();
	
	// Set list objects
	private DailyDataDBAdapter myDailyDataDB;
	private Cursor myDailyDBCursor;
	private AccountHelper myAccount;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setContentView(R.layout.daily_data);
        
        // Set Database adapter
        myDailyDataDB = new DailyDataDBAdapter();
        
        // Set AccountHelper
        myAccount = new AccountHelper();
	}
	
	
	
	@Override
	protected void onResume() {
		super.onResume();
		loadData();
	}



	private void loadData(){
		
		// Get string value of current data period
		String dataPeriod = myAccount.getCurrentDataPeriod();
		
		// Open database (i.e. set tables if needed)
		myDailyDataDB.open();
		
		// Set cursor object
		myDailyDBCursor = myDailyDataDB.fetchPeriodUsage(dataPeriod);
		
		// Start managing cursor TODO: What does this mean?
		startManagingCursor(myDailyDBCursor);
		
		// Load data into cursor array
		setListAdapter(new DailyDataCursorAdapter(this, myDailyDBCursor));
		
		// Close database
		myDailyDataDB.close();
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
	

}
