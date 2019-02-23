package electronicwallet.lyhoangvinh.com.local;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

public class SharedPrefs {
    /**
     * KEY SHARE PREFERENCS
     **/

    private static final String PREFS_NAME = "SharedPrefs_PREFS_NAME";

    /**
     * FEILD OF CLASS *
     */
    private String mPrefName = PREFS_NAME;
    private SharedPreferences mSharedPre;
    private SharedPreferences.Editor mEditor;
    private Context mContext;

    private static SharedPrefs mInstance;

    public static SharedPrefs getInstance(Context context, String prefName) {
        if (mInstance != null && !mInstance.mPrefName.equals(prefName)) {
            mInstance = null;
        }
        if (mInstance == null) {
            // IMPORTANCE: use getApplication context to prevent memory leak when store context instance as static field
            // Since application context is instance of application and lives as long as application
            // Thus store application context as static field is SAFE.
            mInstance = new SharedPrefs(context.getApplicationContext(), prefName);
        }
        return mInstance;
    }

    public static SharedPrefs getInstance(Context context) {
        return getInstance(context, PREFS_NAME);
    }

    @SuppressLint("CommitPrefEdits")
    public SharedPrefs(final Context context, String prefName) {
        mContext = context;
        mPrefName = prefName;
        if (null != context) {
            if (mSharedPre == null) {
                mSharedPre = context.getSharedPreferences(mPrefName, 0);
            }

            mEditor = mSharedPre.edit();
        } else {
            mEditor = null;
            mSharedPre = null;
        }
    }

    public SharedPrefs(final Context context) {
        this(context, PREFS_NAME);
    }

    @SuppressLint("CommitPrefEdits")
    private void refresh() {
        if (null != mContext) {
            mSharedPre = mContext.getSharedPreferences(mPrefName, 0);
            mEditor = mSharedPre.edit();
        } else {
            mEditor = null;
            mSharedPre = null;
        }
    }

    @SuppressWarnings("unchecked")
    public <T> T get(String key, Class<T> anonymousClass) {
        if (anonymousClass == String.class) {
            return (T) mSharedPre.getString(key, "");
        } else if (anonymousClass == Boolean.class) {
            return (T) Boolean.valueOf(mSharedPre.getBoolean(key, false));
        } else if (anonymousClass == Float.class) {
            return (T) Float.valueOf(mSharedPre.getFloat(key, 0));
        } else if (anonymousClass == Integer.class) {
            return (T) Integer.valueOf(mSharedPre.getInt(key, 0));
        } else if (anonymousClass == Long.class) {
            return (T) Long.valueOf(mSharedPre.getLong(key, 0));
        } else {
            return new Gson()
                    .fromJson(mSharedPre.getString(key, ""), anonymousClass);
        }
    }

    public <T> void put(String key, T data) {
        SharedPreferences.Editor editor = mSharedPre.edit();
        if (data instanceof String) {
            editor.putString(key, (String) data);
        } else if (data instanceof Boolean) {
            editor.putBoolean(key, (Boolean) data);
        } else if (data instanceof Float) {
            editor.putFloat(key, (Float) data);
        } else if (data instanceof Integer) {
            editor.putInt(key, (Integer) data);
        } else if (data instanceof Long) {
            editor.putLong(key, (Long) data);
        } else {
            editor.putString(key, new Gson().toJson(data));
        }
        editor.apply();
    }

    public void clear() {
        mSharedPre.edit().clear().apply();
    }
}
