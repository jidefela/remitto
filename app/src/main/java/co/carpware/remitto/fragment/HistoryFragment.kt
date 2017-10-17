package co.carpware.remitto.fragment

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import co.carpware.remitto.R
import co.carpware.remitto.activities.HistoryDetailsActivity
import co.carpware.remitto.adapter.HistoryAdapter
import co.carpware.remitto.model.Payment
import co.carpware.remitto.util.getPayments
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_history.*

/**
 * Created by jide on 04/07/17.
 */
class HistoryFragment : Fragment(), HistoryAdapter.OnHistoryItemClickListener {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(R.layout.fragment_history, container, false)
        return view
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recycler_history.setHasFixedSize(true)
        val llm = LinearLayoutManager(context)
        llm.orientation = LinearLayoutManager.VERTICAL
        recycler_history.layoutManager = llm
        recycler_history.adapter = HistoryAdapter(getPayments(), this)
    }

    override fun itemClick(item: Payment) {
        val historyDetail = Intent(context, HistoryDetailsActivity::class.java)
        val paymentJson = Gson().toJson(item, Payment::class.java)
        historyDetail.putExtra("history", paymentJson)
        context.startActivity(historyDetail)
    }

}