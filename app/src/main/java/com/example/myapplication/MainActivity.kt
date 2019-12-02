package com.example.myapplication

import android.app.PendingIntent
import android.content.Intent
import android.content.pm.PackageManager
import android.net.sip.SipRegistrationListener
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

  private var REQUEST_USE_SIP = 999

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    test_button.setOnClickListener { view -> requestPermission() }
  }

  fun requestPermission() {
    val permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.USE_SIP)
    if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
      ActivityCompat.requestPermissions(
        this,
        arrayOf(Manifest.permission.USE_SIP),
        REQUEST_USE_SIP
      )
    } else {
      showSipDemoIntent()
    }
  }

  fun showSipDemoIntent() {
    val intent = Intent()
    intent.action = "android.SipDemo.INCOMING_CALL"
    val pendingIntent = PendingIntent.getBroadcast(this, 0, intent, Intent.FILL_IN_DATA)
    SipManagerWrapper.getInstance().init(this)
    SipProfileWrapper.getInstance()
      .init(username.text.toString(), domain.text.toString(), password.text.toString())


    SipManagerWrapper.getInstance().sipManager?.setRegistrationListener(
      SipProfileWrapper.getInstance().sipProfile?.uriString,
      object :
        SipRegistrationListener {

        override fun onRegistering(localProfileUri: String) {
          showToast("Registering with SIP Server...")
          Log.i("SipRegistrationListener", "Registering with SIP Server...")
        }

        override fun onRegistrationDone(localProfileUri: String, expiryTime: Long) {
          showToast("Ready")
          Log.i("SipRegistrationListener", "Ready")
        }

        override fun onRegistrationFailed(
          localProfileUri: String,
          errorCode: Int,
          errorMessage: String
        ) {
          showToast("Registration failed. Please check settings. Message: " + errorMessage + " errorCode: " + errorCode)
          Log.i(
            "SipRegistrationListener",
            "Registration failed. Please check settings. Message: " + errorMessage
          )
        }
      })


    SipManagerWrapper.getInstance().sipManager.open(
      SipProfileWrapper.getInstance().sipProfile,
      pendingIntent,
      null
    )
  }

  fun showToast(text: String) {
    this@MainActivity.runOnUiThread {
      val toast = Toast.makeText(
        this@MainActivity, text, Toast.LENGTH_SHORT
      )
      toast.setGravity(Gravity.CENTER, 0, 0)
      toast.show()
    }
  }

  override fun onRequestPermissionsResult(
    requestCode: Int,
    permissions: Array<out String>,
    grantResults: IntArray
  ) {
    super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    if (REQUEST_USE_SIP == requestCode) {
      showSipDemoIntent()
    }
  }

  override fun onCreateOptionsMenu(menu: Menu): Boolean {
    // Inflate the menu; this adds items to the action bar if it is present.
    menuInflater.inflate(R.menu.menu_main, menu)
    return true
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    return when (item.itemId) {
      R.id.action_settings -> true
      else -> super.onOptionsItemSelected(item)
    }
  }
}
