package co.carpware.remitto.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import co.carpware.remitto.R
import co.carpware.remitto.model.Bank
import co.carpware.remitto.model.Email
import co.carpware.remitto.model.Phone
import co.carpware.remitto.util.getBanks
import co.carpware.remitto.util.getEmail
import co.carpware.remitto.util.getPhones
import kotlinx.android.synthetic.main.fragment_profile.*


/**
 * Created by jide on 04/07/17.
 */

class ProfileFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(R.layout.fragment_profile, container, false)
        return view
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        list_banks.adapter = ListAdapter(getBanks(), false)
        list_emails.adapter = ListAdapter(getEmail(), false)
        list_phone.adapter = ListAdapter(getPhones(), false)
        list_email_recipient.adapter = ListAdapter(getEmail(), true)
    }

}

class ListAdapter(val listItem: List<Any>, val isRecipient: Boolean) : BaseAdapter() {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        val view_id = when(isRecipient){
             true -> R.layout.row_recipient
              false-> R.layout.row_profile
        }

        val view = LayoutInflater.from(parent?.context)
                .inflate(view_id, parent, false)
        bindData(view, listItem[position])
        return view
    }

    private fun bindData(view: View, data: Any) {

        val text = view.findViewById(R.id.text_value) as TextView
      if(! isRecipient){

          val image = view.findViewById(R.id.image_type) as ImageView

          when (data) {
              is Bank -> {
                  text.text = data.name
                  image.setImageResource(R.drawable.ic_bank)
              }
              is Email -> {
                  text.text = data.address
                  image.setImageResource(R.drawable.ic_email)
              }
              is Phone -> {
                  text.text = data.mobile
                  image.setImageResource(R.drawable.ic_phone)
              }
          }
      }
      else{
          text.text = (data as Email).address
      }
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItem(position: Int): Any {
        return listItem[position]
    }

    override fun getCount(): Int {
        return listItem.count()
    }
}