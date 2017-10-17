package co.carpware.remitto.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import co.carpware.remitto.R
import co.carpware.remitto.model.User
import com.l4digital.fastscroll.FastScroller
import java.util.*
import java.util.Collections.addAll
import kotlin.collections.ArrayList

/**
 * Created by jide on 09/07/17.
 */

class ContactAdapter(var contactSelectedListener: OnContactSelectedListener? = null) : RecyclerView.Adapter<ContactAdapter.ViewHolder>(), FastScroller.SectionIndexer {

    var contacts = mutableListOf<User>()

    var mMapIndex: LinkedHashMap<String, Int> = LinkedHashMap()
    var mSectionList: List<String> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_contact, parent, false)
        //fillSections()
        return ContactAdapter.ViewHolder(view, contactSelectedListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentUser = this.contacts[position]
        val section: String = getSection(currentUser)
        holder.bindData(currentUser, section, mMapIndex[section] == position, position)
    }

    fun addAll(contactList: List<User>) {
        contacts.addAll(contactList)
        contacts = contacts.sortedWith(compareBy<User> { it.name }).toMutableList()
        fillSections()
        notifyDataSetChanged()
    }

    fun clear() {
        contacts.clear()
        notifyDataSetChanged()
    }

    private fun fillSections() {
        mMapIndex = LinkedHashMap<String, Int>()

        for ((key, value) in contacts.withIndex()) {
            val ch = value.name[0].toString().toUpperCase()
            if (!mMapIndex.containsKey(ch)) {
                mMapIndex.put(ch, key)
            }
        }

        Log.e("Show Tag", mMapIndex.values.toString())
        Log.e("mMapIndex Keys", mMapIndex.keys.toString())

        // create a list from the set to sort
        mSectionList = mMapIndex.keys.toList()

        //sort list in alphabetical order
        mSectionList = mSectionList.sortedWith(compareBy<String> { it })
    }

    fun filter(query: String) {
        val contactsCopy: MutableList<User> = contacts.toMutableList()
        clear()
        val filtered = contactsCopy.filter {
            it.name.contains(query)
        }
        if (filtered.count() > 0) {
            //contacts.addAll(filtered)
            addAll(filtered)
        } else {
            addAll(contactsCopy)
        }

        contactsCopy.clear()

//        Log.e("asfdss", contactsCopy.toString())
//        addAll(contactsCopy)
    }

    private fun getSection(user: User): String {
        return user.name[0].toString().toUpperCase()
    }

    override fun getItemCount(): Int {
        return contacts.count()
    }

    override fun getSectionText(position: Int): String {
        return contacts[position].name[0].toString()
    }


    class ViewHolder(v: View, val listener: OnContactSelectedListener?) : RecyclerView.ViewHolder(v) {

        fun bindData(user: User, sectionText: String, showSection: Boolean, position: Int) {

            val sectionName = itemView.findViewById(R.id.section_title) as TextView
            val name = itemView.findViewById(R.id.text_name) as TextView
            val image_view = itemView.findViewById(R.id.image_profile) as ImageView
            val lyt_contact = itemView.findViewById(R.id.lyt_contact) as RelativeLayout
            val check = itemView.findViewById(R.id.check_select) as CheckBox

            name.text = user.name
            image_view.setImageResource(user.profile_image)
            sectionName.text = sectionText

            sectionName.visibility = when (showSection) {true -> View.VISIBLE
                else -> View.GONE
            }

            lyt_contact.setOnClickListener {
                when (check.isChecked) {
                    true -> {
                        listener?.selectedContact(user, Action.REMOVE)
                        check.isChecked = false
                        check.visibility = View.GONE
                    }
                    else -> {
                        listener?.selectedContact(user, Action.ADD)
                        check.isChecked = true
                        check.visibility = View.VISIBLE
                    }
                }


            }

        }


    }
}