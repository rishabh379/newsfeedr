package com.pvsrishabh.newsfeedr.data.manager

import android.content.Context
import androidx.compose.ui.unit.Constraints
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.pvsrishabh.newsfeedr.domain.manager.LocalUserManager
import com.pvsrishabh.newsfeedr.util.Constants
import com.pvsrishabh.newsfeedr.util.Constants.USER_SETTINGS
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class LocalUserManagerImpl(
    private val context: Context
): LocalUserManager {

    override suspend fun saveAppEntry() {
        context.datastore.edit {settings->
            settings[PreferencesKey.APP_ENTRY] = true
        }
    }

    override fun readAppEntry(): Flow<Boolean> {
        return context.datastore.data.map {preferences->
            preferences[PreferencesKey.APP_ENTRY]?: false
        }
    }
}

private val Context.datastore: DataStore<Preferences> by preferencesDataStore(name = USER_SETTINGS)

private object PreferencesKey{
    val APP_ENTRY = booleanPreferencesKey(name = Constants.APP_ENTRY)
}