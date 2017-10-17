package co.carpware.remitto.util

import co.carpware.remitto.R
import co.carpware.remitto.model.*

/**
 * Created by jide on 08/07/17.
 */


fun getBanks(): List<Bank> {
    return mutableListOf(Bank("IT40S0542811101000000123456\nPSPBFIHH"), Bank("IT40S0542811101000000123456\n" +
            "PSPBFIHH"))
}

fun getEmail(): List<Email> {
    return listOf(Email("1 (345) 215-1037"), Email("1 (345) 215-1037"))
}

fun getPhones(): List<Phone> {
    return listOf(Phone("carol.pierce@gmail.com"), Phone("carol.pierce@yolo.com"))
}


fun getUsers(): List<User> {
    return mutableListOf(
            User(R.drawable.user, "Helen Kelley"),
            User(R.drawable.friend_3, "Tammy Schmidt"),
            User(R.drawable.friend_8, "Amar Sagoo"),
            User(R.drawable.friend_3, "Tammy Schmidt"),
            User(R.drawable.friend_8, "Amar Sagoo"),
            User(R.drawable.friend_3, "Tammy Schmidt"),
            User(R.drawable.friend_8, "Amar Sagoo"),
            User(R.drawable.friend_3, "Tammy Schmidt"),
            User(R.drawable.friend_8, "Amar Sagoo"),
            User(R.drawable.friend_3, "Tammy Schmidt"),
            User(R.drawable.friend_8, "Amar Sagoo"),
            User(R.drawable.friend_3, "Tammy Schmidt"),
            User(R.drawable.friend_8, "Amar Sagoo"),
            User(R.drawable.friend_3, "Tammy Schmidt"),
            User(R.drawable.friend_8, "Amar Sagoo")
    )
}

fun getPayments(): List<Payment> {
    return listOf(
            Payment(getUsers().subList(0, 1).toMutableList(), amount = "€23.50", date = "Jan. 22, 2015", status = Payment.PaymentStatus.PENDING),
            Payment(getUsers().subList(1, 2).toMutableList(), amount = "€36.00", date = "Jan. 22, 2015", status = Payment.PaymentStatus.RECEIVED),
            Payment(getUsers().subList(0, 1).toMutableList(), amount = "€13.10", date = "Jan. 22, 2015", status = Payment.PaymentStatus.SENT),
            Payment(getUsers().subList(1, 4).toMutableList(), amount = "€58.20", date = "Jan. 22, 2015", status = Payment.PaymentStatus.PENDING)
    )
}

