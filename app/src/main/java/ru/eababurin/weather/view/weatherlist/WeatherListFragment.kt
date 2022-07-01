package ru.eababurin.weather.view.weatherlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.Group
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import ru.eababurin.weather.databinding.FragmentWeatherListBinding
import ru.eababurin.weather.viewmodel.AppState


class WeatherListFragment : Fragment() {
    companion object {
        fun newInstance() = WeatherListFragment()
    }

    private lateinit var binding: FragmentWeatherListBinding
    private lateinit var viewModel: WeatherListViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWeatherListBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(WeatherListViewModel::class.java)
        viewModel.getLiveData().observe(viewLifecycleOwner) { t -> renderData(t) }

        try {
            viewModel.sentRequest()
        } catch (e: IllegalStateException) {
            viewModel.getLiveData().removeObservers(viewLifecycleOwner)
            binding.errorGroup.visibility = Group.VISIBLE
            binding.mainGroup.visibility = Group.INVISIBLE
            binding.loadingGroup.visibility = Group.INVISIBLE
        }
    }

    private fun renderData(appState: AppState) {
        when (appState) {
            AppState.Loading -> {
                binding.errorGroup.visibility = Group.INVISIBLE
                binding.mainGroup.visibility = Group.INVISIBLE
                binding.loadingGroup.visibility = Group.VISIBLE
            }
            else -> {
                binding.errorGroup.visibility = Group.INVISIBLE
                binding.loadingGroup.visibility = Group.INVISIBLE
                binding.mainGroup.visibility = Group.VISIBLE

                val result = (appState as AppState.Success).weatherData

                binding.cityName.text = result.city.name
                binding.temperatureValue.text = result.temperature.toString()
                binding.feelsLikeValue.text = result.feelsLike.toString()
                binding.cityCoordinates.text = "${result.city.lat}/${result.city.lon}"
            }
        }
    }
}