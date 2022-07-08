package ru.eababurin.weather.view.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.eababurin.weather.databinding.FragmentDetailsBinding
import ru.eababurin.weather.domain.Weather
import ru.eababurin.weather.model.dto.WeatherDTO
import ru.eababurin.weather.utils.WeatherLoader

class DetailsFragment : Fragment() {

    private var _binding: FragmentDetailsBinding? = null
    private val binding: FragmentDetailsBinding
        get() {
            return _binding!!
        }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val weather = arguments?.getParcelable<Weather>(BUNDLE_WEATHER_EXTRA)

        weather?.let { weatherLocal ->

            WeatherLoader.request(
                weatherLocal.city.lat,
                weatherLocal.city.lon
            ) { weatherDTO ->
                bindWeatherLocalWithWeatherDTO(weatherLocal, weatherDTO)
            }
        }
    }

    private fun bindWeatherLocalWithWeatherDTO(
        weatherLocal: Weather,
        weatherDTO: WeatherDTO
    ) {
        requireActivity().runOnUiThread {
            renderData(weatherLocal.apply {
                weatherLocal.feelsLike = weatherDTO.fact.feelsLike
                weatherLocal.temperature = weatherDTO.fact.temp
            })
        }
    }

    private fun renderData(weather: Weather) {
        binding.run {
            cityName.text = weather.city.name
            temperatureValue.text = weather.temperature.toString()
            feelsLikeValue.text = weather.feelsLike.toString()
            cityCoordinates.text = "${weather.city.lat}/${weather.city.lon}"
        }
    }

    companion object {
        const val BUNDLE_WEATHER_EXTRA = "data"
        fun newInstance(weather: Weather): DetailsFragment {
            return DetailsFragment().also { fr ->
                fr.arguments = Bundle().also { bundle ->
                    bundle.putParcelable(BUNDLE_WEATHER_EXTRA, weather)
                }
            }
        }
    }
}