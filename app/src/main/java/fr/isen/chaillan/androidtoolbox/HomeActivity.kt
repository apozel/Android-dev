package fr.isen.chaillan.androidtoolbox

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        cycleVieButton.setOnClickListener {
            onClickCycledevie()
        }
    }



    fun onClickCycledevie() {
        val intent = Intent(this,cycledevieActivity::class.java)
        startActivity(intent)
    }
}
