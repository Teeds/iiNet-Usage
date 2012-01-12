package au.id.teda.iinetusage.phone.helper;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import au.id.teda.iinetusage.phone.AppGlobals;

public class AccountInfoHelper {
	
	// Static strings for debug tags
	//private static final String DEBUG_TAG = "iiNet Usage";
	//private static final String INFO_TAG = AccountInfoHelper.class.getSimpleName();
	
	// Create instance of shared preferences based on app context
	private SharedPreferences mySettings = PreferenceManager.getDefaultSharedPreferences(AppGlobals.getAppContext());
	
	// Set static string values for preference keys
	private final static String PLAN = "plan";
	private final static String PRODUCT = "product";
	private final static String OFF_PEAK_START = "offPeakStart";
	private final static String OFF_PEAK_END = "offPeakEnd";
	private final static String PEAK_QUOTA = "peakDataQuota";
	private final static String OFF_PEAK_QUOTA = "offPeakDataQuota";
	
	/**
	 * Method for setting account info values to shared preferences
	 * @param plan
	 * @param product
	 * @param offPeakStart
	 * @param offPeakEnd
	 * @param peakQuota
	 * @param offpeakQuota
	 */
	public void setAccountInfo(String plan, String product,
			String offPeakStart, String offPeakEnd, long peakQuota,
			long offpeakQuota){
		
		SharedPreferences.Editor myEditor = mySettings.edit();
		myEditor.putString(PLAN, plan);
		myEditor.putString(PRODUCT, product);
		myEditor.putString(OFF_PEAK_START, offPeakStart);
		myEditor.putString(OFF_PEAK_END, offPeakEnd);
		myEditor.putLong(PEAK_QUOTA, peakQuota);
		myEditor.putLong(OFF_PEAK_QUOTA, offpeakQuota);
		myEditor.commit();
		
	}
	
	/**
	 * Method for getting account plan
	 * @return
	 */
	public String getPlan(){
		return mySettings.getString(PLAN, "Plan not set");
	}
	
	/**
	 * Method for getting account product
	 * @return
	 */
	public String getProduct(){
		return mySettings.getString(PRODUCT, "Product not set");
	}
	
	/**
	 * Method for getting off peak start time
	 * @return
	 */
	public String getOffPeakStart(){
		return mySettings.getString(OFF_PEAK_START, "Off peak start not set");
	}
	
	/**
	 * Method for getting off peak end time
	 * @return
	 */
	public String getOffPeakEnd(){
		return mySettings.getString(OFF_PEAK_END, "Off peak end not set");
	}
	
	/**
	 * Method for getting peak quota value
	 * @return
	 */
	public Long getPeakQuota(){
		return mySettings.getLong(PEAK_QUOTA, 0);
	}
	
	/**
	 * Method for getting off peak quota vale
	 * @return
	 */
	public Long getOffPeakQuota(){
		return mySettings.getLong(OFF_PEAK_QUOTA, 0);
	}

}
