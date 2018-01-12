package com.ads.utils;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Map;

/**
 * PreferencesUtils, easy to get or put data
 *
 * @author <a href="http://www.trinea.cn" target="_blank">Trinea</a> 2013-3-6
 */
public class PreferencesUtil {

    private static String PREFERENCE_NAME = "demo";

    /**
     * clear preferences
     *
     * @param context
     *
     * @return True if the new values were successfully written to persistent storage.
     */
    public static boolean clearAll(Context context) {
        SharedPreferences settings = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.clear();
        return editor.commit();
    }

    /**
     * clear single preferences
     *
     * @param key The name of the preference to modify
     *
     * @return True if the new values were successfully written to persistent storage.
     */
    public static boolean removeString(Context context, String... key) {
        SharedPreferences settings = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        for (int i = 0; i < key.length; i++) {
            editor.remove(key[i]);
        }
        return editor.commit();
    }

    /**
     * query whether a key already exists
     *
     * @param context
     * @param key     The name of the preference to modify
     *
     * @return True if the new values were successfully written to persistent storage.
     */
    public static boolean contains(Context context, String key) {
        SharedPreferences settings = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        return settings.contains(key);
    }

    /**
     * Return all pairs preferences
     *
     * @param context
     *
     * @return True if the new values were successfully written to persistent storage.
     */
    public static Map<String, ?> getAll(Context context) {
        SharedPreferences settings = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        return settings.getAll();
    }

    /**
     * put string preferences
     *
     * @param key   The name of the preference to modify
     * @param value The new value for the preference
     *
     * @return True if the new values were successfully written to persistent storage.
     */
    public static boolean putString(Context context, String key, String value) {
        if (context == null) {
            return false;
        }
        SharedPreferences settings = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(key, value);
        return editor.commit();
    }

    /**
     * get string preferences
     *
     * @param context
     * @param key          The name of the preference to retrieve
     * @param defaultValue Value to return if this preference does not exist
     *
     * @return The preference value if it exists, or defValue. Throws ClassCastException if there is a
     * preference with
     * this name that is not a string
     */
    public static String getString(Context context, String key, String defaultValue) {
        if (context != null) {
            SharedPreferences settings = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
            return settings.getString(key, defaultValue);
        } else {
            return defaultValue;
        }
    }

    /**
     * put int preferences
     *
     * @param context
     * @param key     The name of the preference to modify
     * @param value   The new value for the preference
     *
     * @return True if the new values were successfully written to persistent storage.
     */
    public static boolean putInt(Context context, String key, int value) {
        SharedPreferences settings = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt(key, value);
        return editor.commit();
    }

    /**
     * get int preferences
     *
     * @param context
     * @param key     The name of the preference to retrieve
     *
     * @return The preference value if it exists, or -1. Throws ClassCastException if there is a preference
     * with this
     * name that is not a int
     *
     * @see #getInt(Context, String, int)
     */
    public static int getInt(Context context, String key) {
        return getInt(context, key, -1);
    }


    /**
     * get int preferences
     *
     * @param context
     * @param key          The name of the preference to retrieve
     * @param defaultValue Value to return if this preference does not exist
     *
     * @return The preference value if it exists, or defValue. Throws ClassCastException if there is a
     * preference with
     * this name that is not a int
     */
    public static int getInt(Context context, String key, int defaultValue) {
        if (context != null) {
            SharedPreferences settings = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
            return settings.getInt(key, defaultValue);
        } else {
            return defaultValue;
        }
    }

    /**
     * put long preferences
     *
     * @param context
     * @param key     The name of the preference to modify
     * @param value   The new value for the preference
     *
     * @return True if the new values were successfully written to persistent storage.
     */
    public static boolean putLong(Context context, String key, long value) {
        SharedPreferences settings = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putLong(key, value);

        return editor.commit();
    }

    /**
     * get long preferences
     *
     * @param context
     * @param key     The name of the preference to retrieve
     *
     * @return The preference value if it exists, or -1. Throws ClassCastException if there is a preference
     * with this
     * name that is not a long
     *
     * @see #getLong(Context, String, long)
     */
    public static long getLong(Context context, String key) {
        return getLong(context, key, -1);
    }

    /**
     * get long preferences
     *
     * @param context
     * @param key          The name of the preference to retrieve
     * @param defaultValue Value to return if this preference does not exist
     *
     * @return The preference value if it exists, or defValue. Throws ClassCastException if there is a
     * preference with
     * this name that is not a long
     */
    public static long getLong(Context context, String key, long defaultValue) {
        if (context != null) {
            SharedPreferences settings = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
            return settings.getLong(key, defaultValue);
        } else {
            return defaultValue;
        }
    }

    /**
     * put float preferences
     *
     * @param context
     * @param key     The name of the preference to modify
     * @param value   The new value for the preference
     *
     * @return True if the new values were successfully written to persistent storage.
     */
    public static boolean putFloat(Context context, String key, float value) {
        SharedPreferences settings = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putFloat(key, value);
        return editor.commit();
    }

    /**
     * get float preferences
     *
     * @param context
     * @param key     The name of the preference to retrieve
     *
     * @return The preference value if it exists, or -1. Throws ClassCastException if there is a preference
     * with this
     * name that is not a float
     *
     * @see #getFloat(Context, String, float)
     */
    public static float getFloat(Context context, String key) {
        return getFloat(context, key, -1);
    }

    /**
     * get float preferences
     *
     * @param context
     * @param key          The name of the preference to retrieve
     * @param defaultValue Value to return if this preference does not exist
     *
     * @return The preference value if it exists, or defValue. Throws ClassCastException if there is a
     * preference with
     * this name that is not a float
     */
    public static float getFloat(Context context, String key, float defaultValue) {
        if (context != null) {
            SharedPreferences settings = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
            return settings.getFloat(key, defaultValue);
        } else {
            return defaultValue;
        }
    }

    /**
     * put boolean preferences
     *
     * @param context
     * @param key     The name of the preference to modify
     * @param value   The new value for the preference
     *
     * @return True if the new values were successfully written to persistent storage.
     */
    public static boolean putBoolean(Context context, String key, boolean value) {
        SharedPreferences settings = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean(key, value);
        return editor.commit();
    }

    /**
     * get boolean preferences, default is false
     *
     * @param context
     * @param key     The name of the preference to retrieve
     *
     * @return The preference value if it exists, or false. Throws ClassCastException if there is a
     * preference with this
     * name that is not a boolean
     *
     * @see #getBoolean(Context, String, boolean)
     */
    public static boolean getBoolean(Context context, String key) {
        return getBoolean(context, key, false);
    }

    /**
     * get boolean preferences
     *
     * @param context
     * @param key          The name of the preference to retrieve
     * @param defaultValue Value to return if this preference does not exist
     *
     * @return The preference value if it exists, or defValue. Throws ClassCastException if there is a
     * preference with
     * this name that is not a boolean
     */
    public static boolean getBoolean(Context context, String key, boolean defaultValue) {
        if (context != null) {
            SharedPreferences settings = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
            return settings.getBoolean(key, defaultValue);
        } else {
            return defaultValue;
        }
    }

}