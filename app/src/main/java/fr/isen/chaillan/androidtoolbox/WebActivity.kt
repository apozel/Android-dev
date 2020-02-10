package fr.isen.chaillan.androidtoolbox

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import fr.isen.chaillan.androidtoolbox.Models.RandomUserResult
import fr.isen.chaillan.androidtoolbox.Models.UserModelAdaptater
import kotlinx.android.synthetic.main.activity_web.*

class WebActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web)
        testGet()
    }

    fun testGet() {


        val userApiUrl = "https://randomuser.me/api/?results=2"
        val queue = Volley.newRequestQueue(this)

        val req = StringRequest(Request.Method.GET, userApiUrl, Response.Listener {

            val gson = Gson()
            val sortie = gson.fromJson(it, RandomUserResult::class.java)

            recycleViewUser.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

            sortie.results?.let {
                recycleViewUser.adapter = UserModelAdaptater(it)
            }


        }, Response.ErrorListener {
            Log.d("volley", it.toString())
        })





        queue.add(req)

    }

}
