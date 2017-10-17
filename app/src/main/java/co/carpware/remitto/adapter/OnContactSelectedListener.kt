package co.carpware.remitto.adapter

import co.carpware.remitto.model.User

/**
 * Created by jide on 28/07/17.
 */
interface OnContactSelectedListener {

    fun selectedContact(contact: User, action: Action)
}
enum class Action{
    ADD,
    REMOVE
}