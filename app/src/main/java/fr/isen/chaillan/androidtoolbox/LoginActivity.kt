package fr.isen.chaillan.androidtoolbox

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : AppCompatActivity() {

    private val name: String = "admin"
    private val pass: String = "123"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        loginButton.setOnClickListener {
            onClick()
        }
    }

    fun onClick() {

        if (userName.text.toString() == name && password.text.toString() == pass
        ) {
            Toast.makeText(this, "connexion reussi", Toast.LENGTH_LONG).show()
            val intent = Intent(this,HomeActivity::class.java)
            startActivity(intent)
        } else {
            Toast.makeText(this, "connexion impossible", Toast.LENGTH_LONG).show()
        }
    }

}
