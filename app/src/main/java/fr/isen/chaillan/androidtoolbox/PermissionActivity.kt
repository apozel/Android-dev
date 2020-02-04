package fr.isen.chaillan.androidtoolbox

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_permission.*

class PermissionActivity : AppCompatActivity(), LocationListener {

    lateinit var locationManager: LocationManager
    companion object {
        val pictureRequestCode = 1
        val contactPermissionRequestCode = 2
        val gpsPermissionRequestCode = 3
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_permission)
        cameraButton.setOnClickListener {
            pictureLecture()
            Log.d("click","le click est pris en compte")
        }
        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager

        requestPermission(android.Manifest.permission.READ_CONTACTS, contactPermissionRequestCode) {
            readContacts()
        }
        requestPermission(android.Manifest.permission.ACCESS_COARSE_LOCATION, gpsPermissionRequestCode) {
            startGPS()
        }
    }

    fun readContacts() {
        val contactList = ArrayList<ContactModel>()
        val contacts = contentResolver.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null)
        while(contacts?.moveToNext() ?: false) {
            val displayName = contacts?.getString(contacts.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME))
            val contactModel = ContactModel()
            contactModel.displayName = displayName.toString()
            contactList.add(contactModel)
        }
        contactRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        contactRecyclerView.adapter = ContactsAdapter(contactList)
    }

    fun pictureLecture() {
        var picture = Intent(Intent.ACTION_PICK)
        picture.type = "image/*"

        var cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

        var intentChooser = Intent.createChooser(picture, "gallery photo")

        intentChooser.putExtra(Intent.EXTRA_INITIAL_INTENTS, arrayOf(cameraIntent))
        startActivityForResult(intentChooser, PermissionActivity.pictureRequestCode)
    }

    fun requestPermission(permissionToRequest: String, requestCode: Int, handler: ()-> Unit) {
        if(ContextCompat.checkSelfPermission(this, permissionToRequest) != PackageManager.PERMISSION_GRANTED) {
            if(ActivityCompat.shouldShowRequestPermissionRationale(this, permissionToRequest)) {
                //display toast
            } else {
                ActivityCompat.requestPermissions(this, arrayOf(permissionToRequest), requestCode)
            }
        } else {
            handler()
        }
    }

    @SuppressLint("MissingPermission")
    fun startGPS(){
        Log.d("gps","start position")
        locationManager.requestSingleUpdate(LocationManager.NETWORK_PROVIDER,this,null)
        val location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)
        location?.let{
            refreshPosition(it)
        }
    }

    fun refreshPosition(location: Location) {
        Toast.makeText(this, "latitude : ${location.latitude} \nlongitude : ${location.longitude}", Toast.LENGTH_LONG).show()
    }

    override fun onLocationChanged(location: Location?) {
        location?.let {
            refreshPosition(it)
        }
        Log.d("gps","maj position")
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if(grantResults.isNotEmpty()) {
            if (requestCode == PermissionActivity.contactPermissionRequestCode &&
                grantResults[0] == PackageManager.PERMISSION_GRANTED
            ) {
                readContacts()
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onProviderEnabled(provider: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onProviderDisabled(provider: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PermissionActivity.pictureRequestCode && resultCode == Activity.RESULT_OK) {
            if (data?.data != null) {
                cameraButton.setImageURI(data?.data)
            } else {
                val bitmap = data?.extras?.get("data") as? Bitmap
                bitmap?.let {
                    cameraButton.setImageBitmap(it)
                }
            }
        }
    }
}
