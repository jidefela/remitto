package co.carpware.remitto.model

/**
 * Created by jide on 08/07/17.
 */
data class Payment(val users: MutableList<User>,
                   val amount: String,
                   val date: String,
                   val status: PaymentStatus) {

    enum class PaymentStatus(val value: Int) {
        PENDING(-1),
        SENT(0),
        RECEIVED(1)
    }
}

