package co.carpware.remitto.fragment

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import co.carpware.remitto.R
import kotlinx.android.synthetic.main.fragment_transfer.*
import android.os.Vibrator
import android.util.Log
import co.carpware.remitto.R.id.edt_amount
import co.carpware.remitto.activities.ContactActivity
import co.carpware.remitto.util.toSuperScript


/**
 * Created by jide on 04/07/17.
 */


class TransferFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(R.layout.fragment_transfer, container, false)
        return view
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btn_request.setOnClickListener { showContact("Request") }
        btn_send.setOnClickListener { showContact("Send") }

        btn0.setOnClickListener { addText("0") }
        btn1.setOnClickListener { addText("1") }
        btn2.setOnClickListener { addText("2") }
        btn3.setOnClickListener { addText("3") }
        btn4.setOnClickListener { addText("4") }
        btn5.setOnClickListener { addText("5") }
        btn6.setOnClickListener { addText("6") }
        btn7.setOnClickListener { addText("7") }
        btn8.setOnClickListener { addText("8") }
        btn9.setOnClickListener { addText("9") }
        btn0.setOnClickListener { addText("0") }
        btn_dot.setOnClickListener { addText(".") }
        btn_del.setOnClickListener { removeText() }
        btn_del.setOnLongClickListener { removeAllText() }
    }

    private fun addText(number: String) {
        vibrator(50)
        var amount = edt_amount.text.toString()
        amount = amount.isNotEmpty().let { amount.replace("$", "", false) }

        if (amount.contains(".")) {
            (number != ".").let {
                edt_amount.text = toSuperScript("$amount$number")
            }
        } else {
            edt_amount.setText("$$amount$number")
        }
        //edt_amount.setSelection(amount.length + 1)
    }

    private fun removeText() {
        vibrator(50)
        var amount = edt_amount.text.toString()

        amount.isNotEmpty().let {
            amount = amount.substring(0, amount.length - 1)
            Log.e("Remtto", amount)
            if(amount.contains(".")){
                edt_amount.text = toSuperScript("$amount")
            }else{
                edt_amount.setText("$amount")
            }
        }

        if(amount.isEmpty() || amount == "$")
            edt_amount.setText("")
    }

    private fun removeAllText(): Boolean {
        vibrator(100)
        edt_amount.setText("")
        edt_amount.setSelection(0)
        return edt_amount.text.toString().isEmpty()
    }

    private fun vibrator(mili: Long) {
        (context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator).vibrate(mili)
    }

    private fun showContact(requestOrSend: String) {
        val contactIntent = Intent(context, ContactActivity::class.java)
        contactIntent.putExtra("amount", edt_amount.text.toString())
        contactIntent.putExtra("transfer_type", requestOrSend)
        context.startActivity(contactIntent)
    }


}