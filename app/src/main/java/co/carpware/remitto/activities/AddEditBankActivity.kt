package co.carpware.remitto.activities

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import co.carpware.remitto.R
import kotlinx.android.synthetic.main.partial_toolbar.*

/**
 * Created by jide on 11/07/17.
 */

class AddEditBankActivity: BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_edit_bank)
        setSupportActionBar(toolbar)

        setToolbarTitle("Edit Bank Account")
        setToolbarIcon(R.drawable.ic_close)
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