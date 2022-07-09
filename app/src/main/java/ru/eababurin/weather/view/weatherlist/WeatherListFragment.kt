package ru.eababurin.weather.view.weatherlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import ru.eababurin.weather.R
import ru.eababurin.weather.databinding.FragmentWeatherListBinding
import ru.eababurin.weather.domain.Weather
import ru.eababurin.weather.repositories.Location
import ru.eababurin.weather.view.details.DetailsFragment
import ru.eababurin.weather.view.details.OnItemClick
import ru.eababurin.weather.viewmodel.AppState


class WeatherListFragment : Fragment(), OnItemClick {
    companion object {
        fun newInstance() = WeatherListFragment()
    }

    private var isRussian = true

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
        viewModel.getLiveData().observe(viewLifecycleOwner) { renderData(it) }

        showListOfCities()

        binding.weatherListFragmentFAB.setOnClickListener {
            isRussian = !isRussian
            showListOfCities()
        }
    }

    private fun showListOfCities() {
        if (isRussian) {
            viewModel.getWeatherList(Location.Russian)
            binding.weatherListFragmentFAB.setImageResource(R.drawable.ic_russia)
        } else {
            viewModel.getWeatherList(Location.World)
            binding.weatherListFragmentFAB.setImageResource(R.drawable.ic_earth)
        }
    }

    private fun renderData(appState: AppState) {
        when (appState) {
            AppState.Loading -> {
//                Toast.makeText(requireContext(), R.string.loading_title, Toast.LENGTH_SHORT).show()
            }
            is AppState.Error -> {
                binding.root.errorHandling(
                    appState.message,
                    Snackbar.LENGTH_INDEFINITE,
                    " Попробовать снова"
                ) {
                    showListOfCities()
                }
            }
            is AppState.SuccessMulti -> {
                binding.mainFragmentRecyclerView.adapter =
                    WeatherListAdapter(appState.weatherList, this)
            }
            is AppState.SuccessOne -> { }
        }
    }

    private fun View.errorHandling(
        message: String,
        duration: Int,
        label: String,
        block: (v: View) -> Unit
    ) {
        Snackbar.make(this, message, duration).setAction(label, block).show()
    }

    override fun onItemClick(weather: Weather) {
        requireActivity().supportFragmentManager.beginTransaction().replace(
            R.id.container, DetailsFragment.newInstance(weather)
        ).addToBackStack("").commit()
    }
}