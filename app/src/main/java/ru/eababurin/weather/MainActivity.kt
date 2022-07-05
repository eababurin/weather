package ru.eababurin.weather

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.eababurin.weather.databinding.ActivityMainBinding
import ru.eababurin.weather.view.weatherlist.WeatherListFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null)
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, WeatherListFragment.newInstance()).commit()
    }
}