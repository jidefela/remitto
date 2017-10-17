package co.carpware.remitto.activities

import android.os.Bundle
import co.carpware.remitto.R
import kotlinx.android.synthetic.main.activity_confirm_transaction.*
import kotlinx.android.synthetic.main.partial_toolbar.*
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import co.carpware.remitto.model.User
import co.carpware.remitto.util.getUsers
import co.carpware.remitto.util.toSuperScript
import co.carpware.remitto.view.CircularImageView


/**
 * Created by jide on 09/07/17.
 */

class ConfirmActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confirm_transaction)
        setSupportActionBar(toolbar)

        val isMultipleRecipient = intent.getBooleanExtra("is_multiple", false)

        //Bind Multiple Recipient ChipView
        if (isMultipleRecipient) {
            lyt_multiple_recipient.visibility = View.VISIBLE
            lyt_single_recipient.visibility = View.GONE
            val recipient_adapter = RecipientAdapter(getUsers().subList(1, 4))
            list_recipient.adapter = recipient_adapter
        }

        setToolbarTitle("Request")
        setToolbarIcon(R.drawable.ic_close)
        transaction_reason.text = getString(R.string.reason_value, "Dinner")

        val amount = "$ 23.50"
        text_amount.text = amount
        if (amount.contains(".")) text_amount.text = toSuperScript(amount)
    }

    class RecipientAdapter(val recipients: List<User>) : BaseAdapter() {

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val view = LayoutInflater.from(parent?.context).inflate(R.layout.row_recipient_chip, parent, false)
            val photo = view.findViewById(R.id.image_contact) as CircularImageView
            val name = view.findViewById(R.id.text_name) as TextView
            val mRecipient = recipients[position]
            photo.setImageResource(mRecipient.profile_image)
            name.text = mRecipient.name
            return view
        }

        override fun getItem(position: Int): Any {
            return recipients[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getCount(): Int {
            return recipients.size
        }
    }
}