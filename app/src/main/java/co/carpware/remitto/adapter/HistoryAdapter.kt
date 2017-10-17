package co.carpware.remitto.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import co.carpware.remitto.R
import co.carpware.remitto.model.Payment
import co.carpware.remitto.util.titleCase

/**
 * Created by jide on 08/07/17.
 */
class HistoryAdapter(var data: List<Payment>, val onHistoryItemClickListener: OnHistoryItemClickListener) : RecyclerView.Adapter<HistoryAdapter.ViewHolder>() {

    interface OnHistoryItemClickListener {
        fun itemClick(item: Payment)
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.row_history, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder?.bindData(data[position], this)
    }

    override fun getItemCount(): Int {
        return data.count()
    }

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {

        fun bindData(payment: Payment, adapter: HistoryAdapter) {

            itemView.findViewById(R.id.lyt_row_history).setOnClickListener {
                adapter.onHistoryItemClickListener.itemClick(payment)
            }

            val name = itemView.findViewById(R.id.text_name) as TextView
            val status = itemView.findViewById(R.id.text_status) as TextView
            val image_recipient = itemView.findViewById(R.id.image_recipient) as ImageView
            val amount_date = itemView.findViewById(R.id.text_amount_date) as TextView

            val bg_status = when (payment.status.value) {
                -1 -> R.drawable.btn_pending
                0 -> R.drawable.btn_sent
                1 -> R.drawable.btn_received
                else ->
                    R.drawable.btn_pending
            }

            status.text = titleCase(payment.status.toString())
            status.setBackgroundResource(bg_status)
            amount_date.text = "${payment.amount} - ${payment.date}"

            name.text = payment.users.first().name
            image_recipient.setImageResource(payment.users.first().profile_image)
            if (payment.users.size > 1) {
                name.text = payment.users.map { it.name }
                        .joinToString(separator = ", ")
                image_recipient.setImageResource(R.drawable.ic_group)
            }

        }

    }

}