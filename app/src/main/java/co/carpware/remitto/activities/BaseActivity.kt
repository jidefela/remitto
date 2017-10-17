package co.carpware.remitto.activities

import android.app.Dialog
import android.support.v7.app.AppCompatActivity
import co.carpware.remitto.R
import kotlinx.android.synthetic.main.partial_toolbar.*


/**
 * Created by jide on 09/07/17.
 */
open class BaseActivity : AppCompatActivity() {

    companion object {
        val PERMISSION_REQUEST_CONTACTS: Int = 0
    }

    protected fun showHelpDialog() {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(1)
        dialog.setContentView(R.layout.dialog_help)
        dialog.findViewById(R.id.image_close).setOnClickListener({
            dialog.dismiss()
        })
        dialog.show()
    }

    protected fun setToolbarTitle(title: String){
       if(supportActionBar != null){
           supportActionBar?.title = ""
           text_toolbar_title.text = title
       }
    }

    protected fun setToolbarIcon(resId: Int){
        if(supportActionBar != null){
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.setHomeButtonEnabled(true)
            supportActionBar?.setHomeAsUpIndicator(resId)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }


//    protected fun hasContactPermission(): Boolean {
//       val permissionGranted =  (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_CONTACTS) == PackageManager.PERMISSION_GRANTED)
//        if (! permissionGranted) {
//            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.WRITE_CONTACTS, Manifest.permission.READ_CONTACTS), PERMISSION_REQUEST_CONTACTS)
//        }
//        return permissionGranted
//    }


}