package fr.isen.chaillan.androidtoolbox

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_home.*
import java.io.File

class HomeActivity : AppCompatActivity() {

    var dataSaved: SharedPreferences? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        dataSaved = this.getSharedPreferences(
            Constants.UserPrefName,
            Context.MODE_PRIVATE
        )

        cycleVieButton.setOnClickListener {
            onClickCycledevie()
        }

        permissionButton.setOnClickListener{
            val intent = Intent(this, PermissionActivity::class.java)
            startActivity(intent)
        }

        saveButton.setOnClickListener {
            val intent = Intent(this, FormActivity::class.java)
            startActivity(intent)
        }

        webButton.setOnClickListener {
            val intent = Intent(this, WebActivity::class.java)
            startActivity(intent)
        }
        decoButton.setOnClickListener {
            val editor = dataSaved?.edit()
            editor?.clear()
            editor?.apply()
            val intent = Intent(this, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }

        profileButton.setOnClickListener{
            creationPopUp()
        }



    }

    override fun onResume() {
        super.onResume()
        changementWelcome()
    }

    fun creationPopUp() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Informations Json")
        var file = File(cacheDir.absolutePath + "/" + Constants.Filename)
        if (file.isFile) {
            val readString = file.readText()
            val json: People = Gson().fromJson(readString, People::class.java)
            builder.setMessage(json.toString())
        } else {
            builder.setMessage("le fichier json n'existe pas")
        }
        builder.show()
    }

    fun changementWelcome() {
        var file = File(cacheDir.absolutePath + "/" + Constants.Filename)
        if (file.isFile) {
            val readString = file.readText()
            val json: People = Gson().fromJson(readString, People::class.java)
            welcomeHome.setText(getString(R.string.hello) + " " + json.firstName)
        } else {
            welcomeHome.append(" " + dataSaved?.getString(Constants.kName, null))
        }
    }


    fun onClickCycledevie() {
        val intent = Intent(this, cycledevieActivity::class.java)
        startActivity(intent)
    }
}
