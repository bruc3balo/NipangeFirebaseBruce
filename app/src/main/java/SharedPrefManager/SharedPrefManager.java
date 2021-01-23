package SharedPrefManager;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.nipange.models.User;

public class SharedPrefManager {
    public static final String SHARED_PREF_NAME = "UserCredentials";
    public static final String KEY_ID = "ID_Number";
    public static final String KEY_NAME = "Name";
    public static final String KEY_EMAIL = "Email";
    public static final String KEY_GENDER = "Gender";


    private static SharedPrefManager mInstance;
    private static Context context;

    private SharedPrefManager(Context context) {
        this.context = context;
    }

    public static synchronized SharedPrefManager getInstance(Context context) {
        if(mInstance == null) {
            mInstance = new SharedPrefManager(context);
        }
        return mInstance;
    }

    public void userLogIn(User user) {
        SharedPreferences.Editor editor = context
                .getSharedPreferences(mInstance.SHARED_PREF_NAME, context.MODE_PRIVATE)
                .edit();
        editor.putInt(KEY_ID, user.getID());
        editor.putString(KEY_NAME, user.getName());
        editor.putString(KEY_EMAIL, user.getEmail());
        editor.putString(KEY_GENDER, user.getGender());
        editor.apply();
    }

    public Boolean isLoggedIn() {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, context.MODE_PRIVATE);
        return sharedPreferences.getInt(KEY_ID, -1) != -1;
    }
}
