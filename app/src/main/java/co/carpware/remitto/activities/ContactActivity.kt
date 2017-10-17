package co.carpware.remitto.activities

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import co.carpware.remitto.R

import kotlinx.android.synthetic.main.activity_choose_contact.*
import kotlinx.android.synthetic.main.partial_contact_header.*
import kotlinx.android.synthetic.main.partial_toolbar.*
import android.widget.ArrayAdapter
import android.widget.MultiAutoCompleteTextView
import android.content.Intent
import android.support.v4.content.ContextCompat.startActivity
import android.support.v7.widget.RecyclerView
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import co.carpware.remitto.adapter.*
import co.carpware.remitto.model.User
import co.carpware.remitto.util.getUsers


/**
 * Created by jide on 09/07/17.
 */

class ContactActivity : BaseActivity() {

    var contactAdapter: ContactAdapter? = null
    val chipAdapter = ChipAdapter()

    val contacts = mutableListOf(
            User(R.drawable.user, "Helen Kelley"),
            User(R.drawable.friend_3, "Tammy Schmidt"),
            User(R.drawable.friend_8, "Amar Sagoo"),
            User(R.drawable.friend_3, "Tammy Schmidt"),
            User(R.drawable.friend_3, "Aajanle4334"),
            User(R.drawable.friend_8, "Borokinin"),
            User(R.drawable.user, "Budweiser"),
            User(R.drawable.user, "Helen Kelley"),
            User(R.drawable.friend_3, "Tammy Schmidt"),
            User(R.drawable.friend_8, "Amar Sagoo"),
            User(R.drawable.friend_3, "Tammy Schmidt"),
            User(R.drawable.friend_3, "Aajanle4334"),
            User(R.drawable.friend_8, "Borokinin"),
            User(R.drawable.user, "Budweiser")
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_contact)
        setSupportActionBar(toolbar)

        setToolbarIcon(R.drawable.ic_close)
        val amount = intent?.getStringExtra("amount")
        setToolbarTitle("$123")
        initView()
    }


    fun initView() {
        //val COUNTRIES = arrayOf("Belgium", "France", "Italy", "Germany", "Spain")
        //val adapter = ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, COUNTRIES)

        recycler_contacts.setHasFixedSize(true)

        auto_complete_users.setAdapter(chipAdapter)
        auto_complete_users.addTextChangedListener(textWatcher)
        auto_complete_users.setTokenizer(MultiAutoCompleteTextView.CommaTokenizer())

        contactAdapter = ContactAdapter()
        contactAdapter?.addAll(contacts)
        contactAdapter?.contactSelectedListener = onContactSelectedListener
        recycler_contacts.adapter = contactAdapter

        //index_layout.attach(recycler_contacts as RecyclerView)
        recycler_contacts.addOnScrollListener(onScrollListener)
    }


    val onScrollListener = object : RecyclerView.OnScrollListener() {

        override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            if (dy < 0) {
                Log.e("Scroll Event", "To Top")
            } else if (dy > 0) {
                Log.e("Scroll Event", "Scroll Down")
            }

        }

        override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
        }
    }

    val textWatcher = object : TextWatcher {

        override fun afterTextChanged(s: Editable?) {
            auto_complete_users.dismissDropDown()
            contactAdapter?.filter(s.toString())
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            auto_complete_users.dismissDropDown()
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            auto_complete_users.dismissDropDown()
        }

    }

    val onContactSelectedListener = object : OnContactSelectedListener {

        override fun selectedContact(contact: User, action: Action) {
            chipAdapter
        }

    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.activity_contact, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_review_payment -> {
                val confirmIntent = Intent(this, ConfirmActivity::class.java)
                confirmIntent.putExtra("is_multiple", true)
                startActivity(confirmIntent)
                return true
            }

        }
        return super.onOptionsItemSelected(item)
    }


}