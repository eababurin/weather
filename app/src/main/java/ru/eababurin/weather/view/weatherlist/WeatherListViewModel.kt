package ru.eababurin.weather.view.weatherlist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.eababurin.weather.model.*
import ru.eababurin.weather.viewmodel.AppState

class WeatherListViewModel(
    private val liveData: MutableLiveData<AppState> = MutableLiveData<AppState>()
) : ViewModel() {

    lateinit var getMultiWeatherResult: MultiResultWeatherGetable
    lateinit var getOneWeatherResult: OneResultWeatherGetable

    fun getLiveData(): MutableLiveData<AppState> {
        choiceRepository()
        return liveData
    }

    private fun choiceRepository() {
        if (isConnection()) {
            getOneWeatherResult = RepositoryRemoteImpl()
        } else {
            getMultiWeatherResult = RepositoryLocalImpl()
        }
        getMultiWeatherResult = RepositoryLocalImpl()
    }

    fun getWeatherList(location: Location) {
        sentRequest(location)
    }

    private fun sentRequest(location: Location) {
        liveData.value = AppState.Loading
        try {
            if ((1..2).random() == 1) {
                liveData.postValue(AppState.Error(throw IllegalStateException("Что то нехорошее произошло")))
            } else {
                liveData.postValue(AppState.SuccessMulti(getMultiWeatherResult.getListWeather(location)))
            }
        } catch (e: IllegalStateException) {
            liveData.postValue(AppState.Error(e))
        }

    }

    private fun isConnection(): Boolean {
        return false
    }

}