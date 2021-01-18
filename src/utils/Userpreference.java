/*package utils;
import java.util.prefs.*;

import HTTPCall.User;

public class Userpreference {
	public Userpreference(){

    }
	Preferences myPrefs = Preferences.userNodeForPackage( User.class );
	//private static Preferences prefs;
	//prefs = Preferences.userRoot().node(this.getClass());
	
	prefs.get(Constant.KEY_USERNAME," ");
	prefs.get(Constant.KEY_FIRSTNAME," ");
	
	pref.get()

    public static boolean saveEmail(String email) {
        prefs.put(Constant.KEY_EMAIL, email);
        return true;
    }

    public static String getEmail(Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        return prefs.getString(Constants.KEY_EMAIL, null);
    }

    public static boolean savePassword(String password, Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor prefsEditor = prefs.edit();
        prefsEditor.putString(Constants.KEY_PASSWORD, password);
        prefsEditor.apply();
        return true;
    }
}
*/