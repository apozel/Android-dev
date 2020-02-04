package fr.isen.chaillan.androidtoolbox

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : AppCompatActivity() {

    private val name: String = Constants.Name
    private val pass: String = Constants.Password
    public var dataSaved: SharedPreferences? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        this.dataSaved = getSharedPreferences(
            Constants.UserPrefName,
            Context.MODE_PRIVATE
        )
        checkPreferences()
        loginButton.setOnClickListener {
            onClick()
        }
    }

    fun onClick() {

        if (userName.text.toString() == name && password.text.toString() == pass
        ) {
            saveData(userName.text.toString(),password.text.toString())
            startHomeActivity()
        } else {
            Toast.makeText(this, "connexion impossible", Toast.LENGTH_LONG).show()
        }
    }

    fun saveData(login: String, password: String) {
        val editor  = this.dataSaved?.edit()
        editor?.putString(Constants.kName, login)
        editor?.putString(Constants.kPassword, password)
        editor?.apply()

    }

    fun checkPreferences() {
        val identifier = dataSaved?.getString(Constants.kName, null) ?: ""
        val password = dataSaved?.getString(Constants.kPassword, null) ?: ""
        if(identifier == Constants.Name && password == Constants.Password) {
            startHomeActivity()
        }
    }

    fun startHomeActivity() {
        Toast.makeText(this, "connexion reussi", Toast.LENGTH_LONG).show()
        val intent = Intent(this, HomeActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        finish()
    }

}
