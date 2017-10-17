package co.carpware.remitto.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Filter
import android.widget.Filterable
import co.carpware.remitto.R
import co.carpware.remitto.model.User

/**
 * Created by jide on 09/07/17.
 */
class ChipAdapter : BaseAdapter(), Filterable {

    val items = mutableListOf<User>()

    override fun getFilter(): Filter? {
        return null
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val v = LayoutInflater.from(parent?.context).inflate(R.layout.item_chip, parent, false)
        return v
    }

    fun add(contact: User) {
        items.add(contact)
        notifyDataSetChanged()
    }

    fun clear() {
        items.clear()
        notifyDataSetChanged()
    }

    override fun getItem(position: Int): Any {
        return items[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return items.count()
    }
}