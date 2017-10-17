package co.carpware.remitto.activities

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import co.carpware.remitto.R
import co.carpware.remitto.model.Payment
import com.google.gson.Gson
import kotlinx.android.synthetic.main.partial_toolbar.*

/**
 * Created by jide on 09/07/17.
 */
class HistoryDetailsActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history_details)
        setSupportActionBar(toolbar)

        val payment = Gson().fromJson(intent.getStringExtra("history"), Payment::class.java)

        supportActionBar?.title = ""
        text_toolbar_title.text = payment.users.first().name
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_arrow_back)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.activity_history, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return super.onOptionsItemSelected(item)
    }

}