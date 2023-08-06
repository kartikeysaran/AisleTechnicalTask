package kartikey.saran.aisle.Utils

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore : DataStore<Preferences> by preferencesDataStore(
    name = "app_pref"
)

class PrefUtil (private val context: Context) {

    companion object{
        val TOKEN = stringPreferencesKey(name = "token")
    }

    suspend fun saveToken(s: String){
        context.dataStore.edit { preferences ->
            preferences[TOKEN] = s
        }
    }

    val getToken: Flow<String> = context.dataStore.data
        .map { preferences ->
            preferences[TOKEN] ?: ""
        }
}