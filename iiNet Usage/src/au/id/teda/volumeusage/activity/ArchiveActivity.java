package au.id.teda.volumeusage.activity;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import au.id.teda.volumeusage.R;
import au.id.teda.volumeusage.adapter.ArchiveArrayAdapter;
import au.id.teda.volumeusage.async.RefreshUsageAsync;
import au.id.teda.volumeusage.helper.AccountHelper;
import au.id.teda.volumeusage.prefs.Preferences;
import au.id.teda.volumeusage.service.ServiceHelper;
import au.id.teda.volumeusage.view.SetStatusBar;

/**
 * 
 * @author Ian
 *
 */
// http://pareshnmayani.wordpress.com/tag/android-listview-example/
// http://www.vogella.de/articles/AndroidListView/article.html

// http://saigeethamn.blogspot.com/2010/04/custom-listview-android-developer.html
// http://ykyuen.wordpress.com/2010/01/03/android-simple-listview-using-simpleadapter/
// http://ykyuen.wordpress.com/2010/03/15/android-%E2%80%93-applying-alternate-row-color-in-listview-with-simpleadapter/

// http://www.vogella.de/articles/AndroidListView/article.html

public class ArchiveActivity extends ListActivity {
	
	// Static strings for debug tags
	private static final String DEBUG_TAG = "iiNet Usage";
	private static final String INFO_TAG = ArchiveActivity.class.getSimpleName();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		//Log.i(INFO_TAG, "onCreate()");
		setContentView(R.layout.archive);

		// Create instance of AccountHelper 
		AccountHelper myAccountHelper = new AccountHelper(this);

		// Set list adapter with string array values from AccountHelper
		setListAdapter((ListAdapter) new ArchiveArrayAdapter(this, myAccountHelper.getArchivedMonths()));
		
	}

	@Override
	protected void onListItemClick(ListView listView, View view, int position, long id) {
		super.onListItemClick(listView, view, position, id);
		
		// Get the item that was clicked
		String item = (String) getListAdapter().getItem(position);
		Toast.makeText(this, item + " selected", Toast.LENGTH_LONG).show();
	}
	
	public void onAnalysisClick(View view){
		Toast.makeText(this, "Analysis Archive", Toast.LENGTH_LONG).show();
	}
	
	/**
	 * onClick method for action bar
	 * @param button
	 */
	public void onActionBarClick(View button){
		Log.i(INFO_TAG, "onClick() > Button: " + button.getId());
		switch (button.getId()) {
		case R.id.action_bar_home_button:
			Intent dashboardActivityIntent = new Intent(this, MainActivity.class);
            startActivity(dashboardActivityIntent);
			break;
		case R.id.action_bar_refresh_button:
			new RefreshUsageAsync(this, handler).execute();
			break;
		default:
			Log.i(INFO_TAG, "onClick() > Default switch");
		}
	}
	
	
	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();
		Log.i(INFO_TAG, "onRestart()");
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		//Log.i(INFO_TAG, "onResume()");
		
        // Set status bar hide or not
    	SetStatusBar setStatusBar = new SetStatusBar(this);
    	setStatusBar.showHide();
	}


	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		Log.i(INFO_TAG, "onDestroy()");
	}
	
	/**
	 *  Handler for passing messages from other classes
	 */
    public Handler handler = new Handler() {
        public void handleMessage(Message msg) {
        	Log.i(INFO_TAG, "handleMessage( " + msg + " )");
           //TODO: how do i use this??
        }
    };

	/**
	 * onCreate method for options menu
	 * What happens when we press the menu button
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		Log.i(INFO_TAG, "onCreateOptionsMenu()");
		super.onCreateOptionsMenu(menu);
		MenuInflater inflater = getMenuInflater(); // New menu object
		inflater.inflate(R.menu.menu, menu); // Lets list the menu.xml
		return true;
	}
	
	/**
	 * onClick method for menu buttons
	 * What happens when some clicks a menu item
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		Log.i(INFO_TAG, "onOptionsItemSelected() > Button: " + item.getTitle());
		switch (item.getItemId()) {
		case R.id.menu_settings_button:
			Intent menuIntent = new Intent(this, Preferences.class);
			startActivity(menuIntent);
			return true;
		case R.id.menu_refresh_button:
			ServiceHelper serviceHelper = new ServiceHelper(this);
			new RefreshUsageAsync(this, handler).execute();
			return true;
		case R.id.menu_about_button:
			Intent aboutIntent2 = new Intent(this, AboutActivity.class);
			startActivity(aboutIntent2);
			return true;
		}
		return false;
	}
	
	/**
	 *  Method used to set action bar title, reference buttons and set onClick listener
	 */
	public void setupActionBar(){
		Log.i(INFO_TAG, "setUpActionBar()");
		// Set action bar title
		TextView abTitle = (TextView) findViewById(R.id.action_bar_title_text);
		abTitle.setText(R.string.ab_history_title);
		
		// Reference action bar buttons and set onClick
		// This is the refresh button on the action bar
        ImageButton abRefreshButton = (ImageButton) findViewById(R.id.action_bar_refresh_button);
        //abRefreshButton.setOnClickListener(this);
        
        // Take me back to the dashboard
        ImageButton abHomeButton = (ImageButton) findViewById(R.id.action_bar_home_button);
        //abHomeButton.setOnClickListener(this);
	}
	

}
