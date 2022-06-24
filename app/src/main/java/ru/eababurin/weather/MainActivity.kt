package ru.eababurin.weather

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.constraintlayout.widget.Group
import androidx.core.view.isVisible

const val TAG = "TAG"

data class User(val firstName: String, val lastName: String, val age: Int)

class MainActivity : AppCompatActivity() {

    private val data = User("Евгений", "Бабурин", 29)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        start()

        fillFields(data)

        findViewById<Button>(R.id.button).setOnClickListener {
            showInformation()
        }
    }

    private fun showInformation() {
        val group = findViewById<Group>(R.id.group)

        if (group.isVisible) {
            group.visibility = Group.INVISIBLE
        } else {
            group.visibility = Group.VISIBLE
        }
    }

    private fun fillFields(user: User) {
        val copyUser = user.copy()

        findViewById<TextView>(R.id.first_name).text = copyUser.firstName
        findViewById<TextView>(R.id.last_name).text = copyUser.lastName
        findViewById<TextView>(R.id.age).text = copyUser.age.toString()
    }

    private fun start() {

        for (i in 1..10) {

            when (i) {
                in 4..7 -> Log.d(TAG, "В середине цикла; внутри when, i = $i")
            }

            while (i <= 3) {
                Log.d(TAG, "В начале цикла; внутри while, i = $i"); break
            }

            do {
                if (i <= 7) break
                Log.d(TAG, "В конце цикла; внутри do-while, i = $i"); break
            } while (i >= 7)
        }
    }
}
