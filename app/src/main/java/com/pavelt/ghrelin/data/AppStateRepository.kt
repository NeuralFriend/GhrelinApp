package com.pavelt.ghrelin.data

import android.content.Context
import androidx.core.content.edit
import com.pavelt.ghrelin.GhrelinApplication
import com.pavelt.ghrelin.domain.AppState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withContext
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

object AppStateRepository {

    private val sharedPrefs by lazy {
        GhrelinApplication.appContext.getSharedPreferences(
            "appState",
            Context.MODE_PRIVATE,
        )
    }

    private val mutex = Mutex()

    private val _appStateFlow = MutableStateFlow(AppState())

    fun observe(): Flow<AppState> = flow {
        val initial = get()
        emit(initial)
        _appStateFlow.collect {
            emit(it)
        }
    }

    suspend fun get(): AppState = withContext(Dispatchers.IO) {
        mutex.withLock {
            val result = readAppState()
            _appStateFlow.emit(result)
            result
        }
    }

    private fun readAppState(): AppState {
        val json = sharedPrefs.getString("state", null)
            ?: return AppState()
        return Json.decodeFromString<AppState>(json)
    }

    suspend fun update(update: (AppState) -> AppState) = withContext(Dispatchers.IO) {
        mutex.withLock {
            val oldAppState = readAppState()
            val newAppState = update(oldAppState)
            val json = Json.encodeToString(newAppState)
            sharedPrefs.edit(commit = true) {
                putString("state", json)
            }
            _appStateFlow.emit(newAppState)
        }
    }
}