package co.carpware.remitto.activities

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import co.carpware.remitto.R
import kotlinx.android.synthetic.main.activity_edit_profile.*
import kotlinx.android.synthetic.main.partial_toolbar.*

/**
 * Created by jide on 05/07/17.
 */
class EditProfileActivity : BaseActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)
        setSupportActionBar(toolbar)

        setToolbarTitle("Edit Profile")
        setToolbarIcon(R.drawable.ic_close)

        text_add_account.setOnClickListener {
            startActivity(Intent(this, AddEditBankActivity::class.java))
        }
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_check, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.menu_submit -> {
                finish()
                //Todo Show ProgressDialog Here
                return true
            }

        }
        return super.onOptionsItemSelected(item)
    }

}