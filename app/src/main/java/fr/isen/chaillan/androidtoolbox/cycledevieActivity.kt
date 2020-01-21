package fr.isen.chaillan.androidtoolbox

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import kotlinx.android.synthetic.main.activity_cycledevie.*

class cycledevieActivity : AppCompatActivity(), OnFragmentInteractionListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cycledevie)
        supportFragmentManager.beginTransaction().replace(R.id.fragmentCentral, TextFragment()).commit()
        switchFragment.setOnCheckedChangeListener { buttonView, isChecked ->
            changeFragment(isChecked)
        }
    }

    fun changeFragment(onOff : Boolean) {

        val transaction = supportFragmentManager.beginTransaction()
        var newFragment: Fragment?
        if (onOff) {
            newFragment = TextFragment2()

        } else {
            newFragment = TextFragment()
        }
        transaction.replace(R.id.fragmentCentral, newFragment)
        transaction.commit()
    }

    override fun onPause() {
        super.onPause()


        Log.d("changement", "arriere-plan cycle")
    }

    override fun onDestroy() {
        super.onDestroy()
        Toast.makeText(this, "destruction cycle de vie", Toast.LENGTH_SHORT).show()
    }

    override fun onFragmentInteraction(number: Int) {
        Log.i("listener ", "from cycle")
        Toast.makeText(this, "destruction fragment ${number}", Toast.LENGTH_LONG).show()
    }
}
