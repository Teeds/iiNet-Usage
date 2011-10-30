package au.id.teda.volumeusage.sax;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import au.id.teda.volumeusage.MyApp;
import au.id.teda.volumeusage.activity.UserPassActivity;

public class CheckUserPassSAXHandler extends DefaultHandler {
	
	/**
	 *  Static tag strings for logging information and debug
	 */
	// Debug tag for LogCat
	private static final String DEBUG_TAG = "iiNet Usage";
	private static final String INFO_TAG = CheckUserPassSAXHandler.class.getSimpleName();
	
	// Set tag variables
	public static final String II_FEED = "ii_feed"; // Man xml parent tag
	public static final String ERROR = "error"; // Error tag
	
	// Set inTag variables
    private boolean inFeed = false;
    private boolean inError = false;
    
    private boolean chkUserPass = true;
    private String errorText = null;
    
	/**
	 * This method is called when the parser reaches an xml start tag
	 */
	@Override
	public void startElement(String uri, String myTag, String qName,
			Attributes myAtt) throws SAXException {
		super.startElement(uri, myTag, qName, myAtt); //TODO: Do I need this super?
		//Log.d(DEBUG_TAG, "startElement is: " + myTag.trim());
		
		if (myTag.trim().equalsIgnoreCase(II_FEED)){
			inFeed = true;
		} else if (myTag.trim().equalsIgnoreCase(ERROR)){
			inError = true;
			
			//If error found then set to username password check to false
			chkUserPass = false;
		}
	}

	/**
	 * This method is called when the parser reaches an xml end tag
	 */
	@Override
	public void endElement(String uri, String myTag, String qName)
			throws SAXException {
		super.endElement(uri, myTag, qName);
		//Log.d(DEBUG_TAG, "endElement is: " + myTag.trim());

		if (myTag.trim().equalsIgnoreCase(ERROR)){
			inError = false;
		} else if (myTag.trim().equalsIgnoreCase(II_FEED)){
			//Log.d(DEBUG_TAG, "chkUserPass : " + chkUserPass);
			
			// Once we reach the end of the feed set shared preferrence to username password boolean value
			SharedPreferences preferences = PreferenceManager
                    .getDefaultSharedPreferences(MyApp.getAppContext());
			
			SharedPreferences.Editor editor = preferences.edit();
			editor.putBoolean("isPassedChk", chkUserPass); // 
			editor.commit();
			
			inFeed = false;
		}
	}

	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		super.characters(ch, start, length);
		
		// Set string value for xml tag currently in
		String chars = (new String(ch).substring(start, start + length));
		
		// if we are in the feed tag and in an error tag set error string
		if (inFeed && inError){
			errorText = chars;
		}
		
		
	}



}