package ru.eababurin.weather

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.constraintlayout.widget.Group
import androidx.core.view.isVisible

data class User(val firstName: String, val lastName: String, val age: Int)

class MainActivity : AppCompatActivity() {

    val data = User("Евгений", "Бабурин", 29)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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
}
