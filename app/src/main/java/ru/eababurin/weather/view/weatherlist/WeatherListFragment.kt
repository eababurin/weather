package ru.eababurin.weather.view.weatherlist

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import ru.eababurin.weather.MainActivity
import ru.eababurin.weather.R
import ru.eababurin.weather.databinding.FragmentWeatherListBinding
import ru.eababurin.weather.domain.Weather
import ru.eababurin.weather.model.Location
import ru.eababurin.weather.view.details.DetailsFragment
import ru.eababurin.weather.view.details.OnItemClick
import ru.eababurin.weather.viewmodel.AppState


class WeatherListFragment : Fragment(), OnItemClick {
    companion object {
        fun newInstance() = WeatherListFragment()
    }

    var isRussian = true

    lateinit var binding: FragmentWeatherListBinding
    lateinit var viewModel: WeatherListViewModel

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

        shownCities()

        binding.weatherListFragmentFAB.setOnClickListener {
            isRussian = !isRussian
            shownCities()
        }

    }

    private fun shownCities() {
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
            is AppState.Error -> {
                AlertDialog.Builder(requireContext())
                    .setTitle(R.string.alert_title)
                    .setMessage(R.string.alert_description)
                    .setPositiveButton(R.string.alert_button) { dialog, pos -> requireActivity().finish() }
                    .show()
            }
            AppState.Loading -> {
                Toast.makeText(requireContext(), R.string.loading_title, Toast.LENGTH_SHORT).show()
            }
            is AppState.SuccessMulti -> {
                binding.mainFragmentRecyclerView.adapter =
                    WeatherListAdapter(appState.weatherList, this)
            }

            is AppState.SuccessOne -> { val result = appState.weatherData }
        }
    }

    override fun onItemClick(weather: Weather) {
        requireActivity().supportFragmentManager.beginTransaction().replace(
            R.id.container, DetailsFragment.newInstance(weather)
        ).addToBackStack("").commit()
    }
}