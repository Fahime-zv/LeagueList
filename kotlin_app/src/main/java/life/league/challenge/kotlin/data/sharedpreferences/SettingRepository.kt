package life.league.challenge.kotlin.data.sharedpreferences

interface SettingRepository {
    fun hasToken(): Boolean
    fun saveToken(token: String)
    fun getToken(): String?
}

const val KEY_TOKEN = "token"

class SettingRepositoryImpl(
    private val sharedPref: SharedPref
) : SettingRepository {

    override fun hasToken(): Boolean {
        val read = sharedPref.read(KEY_TOKEN, null)
        return read != null
    }

    override fun saveToken(token: String) {
        sharedPref.write(KEY_TOKEN, token)
    }

    override fun getToken(): String? {
        return sharedPref.read(KEY_TOKEN, null)
    }

}