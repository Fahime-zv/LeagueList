package life.league.challenge.kotlin.data.sharedpreferences

import android.content.SharedPreferences
import androidx.annotation.VisibleForTesting

class SharedPref(private val sharedPreferences: SharedPreferences) :
    SharedPreferences.OnSharedPreferenceChangeListener {

    @VisibleForTesting
    var onKeyChanged: ((String) -> Unit)? = null

    // Strings
    fun read(key: String, defValue: String?): String? {
        return sharedPreferences.getString(key, defValue)
    }

    fun write(key: String, value: String) {
        val prefsEditor = sharedPreferences.edit()
        prefsEditor.putString(key, value)
        prefsEditor.apply()
    }

    // Boolean
    fun read(key: String, defValue: Boolean): Boolean {
        return sharedPreferences.getBoolean(key, defValue)
    }

    fun write(key: String, value: Boolean) {
        val prefsEditor = sharedPreferences.edit()
        prefsEditor.putBoolean(key, value)
        prefsEditor.apply()
    }

    // Int
    fun read(key: String, defValue: Int): Int {
        return sharedPreferences.getInt(key, defValue)
    }

    fun write(key: String, value: Int) {
        val prefsEditor = sharedPreferences.edit()
        prefsEditor.putInt(key, value)
        prefsEditor.apply()
    }

    // Long
    fun read(key: String, defValue: Long): Long {
        return sharedPreferences.getLong(key, defValue)
    }

    fun write(key: String, value: Long) {
        val prefsEditor = sharedPreferences.edit()
        prefsEditor.putLong(key, value)
        prefsEditor.apply()
    }

    // General
    fun remove(key: String) {
        val prefsEditor = sharedPreferences.edit()
        prefsEditor.remove(key)
        prefsEditor.apply()
    }


    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences?, key: String?) {
        if (key != null) {
            onKeyChanged?.invoke(key)
        }
    }
}