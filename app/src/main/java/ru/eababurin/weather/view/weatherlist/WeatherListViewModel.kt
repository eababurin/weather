package ru.eababurin.weather.view.weatherlist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.eababurin.weather.model.*
import ru.eababurin.weather.viewmodel.AppState
import java.lang.IllegalStateException

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

    fun getWeatherListForRussia() {
        sentRequest(Location.Russian)
    }
    fun getWeatherListForWorld() {
        sentRequest(Location.World)
    }

    private fun sentRequest(location: Location) {
        liveData.value = AppState.Loading
        if (false) { // TODO - FIXME - HW
            liveData.postValue(AppState.Error(throw IllegalStateException("Что-то пошло не так :(")))
        }
        liveData.postValue(AppState.SuccessMulti(getMultiWeatherResult.getListWeather(location)))
    }

    private fun isConnection(): Boolean {
        return false
    }

    override fun onCleared() { // TODO HW ***
        super.onCleared()
    }
}