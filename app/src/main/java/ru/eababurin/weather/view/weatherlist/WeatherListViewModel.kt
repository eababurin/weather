package ru.eababurin.weather.view.weatherlist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.eababurin.weather.model.Repository
import ru.eababurin.weather.model.RepositoryLocalImpl
import ru.eababurin.weather.model.RepositoryRemoteImpl
import ru.eababurin.weather.viewmodel.AppState
import java.lang.IllegalStateException

class WeatherListViewModel(
    private val liveData: MutableLiveData<AppState> = MutableLiveData<AppState>()
) : ViewModel() {
    lateinit var repository: Repository

    fun getLiveData(): MutableLiveData<AppState> {
        choiceRepository()
        return liveData
    }

    private fun choiceRepository() {
        if (isConnection()) {
            repository = RepositoryRemoteImpl()
        } else {
            repository = RepositoryLocalImpl()
        }
    }

    fun sentRequest() {
        liveData.value = AppState.Loading
        if ((1..10).random() == 1) {
            liveData.postValue(AppState.Error(throw IllegalStateException("Что-то пошло не так :(")))
        }
        liveData.postValue(AppState.Success(repository.getWeather(55.755826, 37.617299)))
    }

    private fun isConnection(): Boolean {
        return false
    }
}