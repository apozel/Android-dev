package fr.isen.chaillan.androidtoolbox

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_form.*

import java.io.File


class FormActivity : AppCompatActivity() {

    private var person: People? = null
    var age: Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form)

        saveButton.setOnClickListener {
            this.save()
        }

        readButton.setOnClickListener {
            read()
        }

        dateEditText.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus) {
                dateEditText.clearFocus()
                val dialog = DatePickerDialog(
                    this, DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                        onDateChoose(year, month, dayOfMonth)
                    },
                    1998,
                    4,
                    11
                )
                dialog.show()
            }
        }
    }

    private fun onDateChoose(year: Int, month: Int, day: Int) {
        this.dateEditText.setText(String.format("%02d/%02d/%04d", day, month + 1, year))
        age = getAge(year, month + 1, day)
        Toast.makeText(
            this,
            "date : ${dateEditText.text.toString()}",
            Toast.LENGTH_LONG
        ).show()
    }

    private fun getAge(year: Int, month: Int, day: Int): Int {
        return 0
    }

    fun save() {
        if (firstNameEditText.text.toString().isNotEmpty() &&
            nameEditText.text.toString().isNotEmpty() &&
            dateEditText.text.toString().isNotEmpty()
        ) {
            person = People(firstNameEditText.text.toString(), nameEditText.text.toString(), age)
            val json = Gson().toJson(person)

            Toast.makeText(this, json.toString(), Toast.LENGTH_LONG).show()

            val file = File(cacheDir.absolutePath + "/" + Constants.Filename)
            file.writeText(json)


        } else {
            Toast.makeText(this, "veuillez Remplir le champ manquant", Toast.LENGTH_LONG).show()
        }
    }

    fun read() {
        val file = File(cacheDir.absolutePath + "/" + Constants.Filename)
        Log.d("file","le fichier est ouvert")
        if (file.isFile) {
            val readString = file.readText()
            val json: People = Gson().fromJson(readString, People::class.java)
            Log.d("file","le fichier est ouvert json : " + json.toString())
            Toast.makeText(this, "le fichier est ouvert ${json.age}", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(this, "le truc n'est pas enregistrer", Toast.LENGTH_LONG).show()
        }
    }


}
