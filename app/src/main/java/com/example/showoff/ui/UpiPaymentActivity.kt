package com.example.showoff.ui

import android.R.attr
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.showoff.R


class UpiPaymentActivity : AppCompatActivity() {

    val UPI_PAYMENT = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_upi_payment2)

//        val uri: Uri = Uri.Builder()
//                .scheme("upi")
//                .authority("pay")
//                .appendQueryParameter("pa", "singaraddis@okhdfcbank") // virtual ID
//                .appendQueryParameter("pn", "Sushma Singaraddi") // name
//                .appendQueryParameter("mc", "") // optional
//                .appendQueryParameter("tr", "123456789") // optional
//                .appendQueryParameter("tn", "Payment") // any note about payment
//                .appendQueryParameter("am", "1") // amount
//                .appendQueryParameter("cu", "INR") // currency
////                .appendQueryParameter("url", "your-transaction-url") // optional
//                .build()
//
//        val GOOGLE_PAY_PACKAGE_NAME = "com.google.android.apps.nbu.paisa.user"
//        val GOOGLE_PAY_REQUEST_CODE = 123
//        val intent = Intent(Intent.ACTION_VIEW)
//        intent.data = uri
//        intent.setPackage(GOOGLE_PAY_PACKAGE_NAME)
//        this.startActivityForResult(intent, GOOGLE_PAY_REQUEST_CODE)

        val uri = Uri.parse("upi://pay").buildUpon()
                .appendQueryParameter("pa", "9483126021@ybl")
                .appendQueryParameter("pn", "Sushma Singaraddi")
                .appendQueryParameter("tn", "Payment")
                .appendQueryParameter("am", "1")
                .appendQueryParameter("cu", "INR")
                .build()


        val upiPayIntent = Intent(Intent.ACTION_VIEW)
        upiPayIntent.data = uri

        // will always show a dialog to user to choose an app
        val chooser = Intent.createChooser(upiPayIntent, "Pay with")

        // check if intent resolves
        if (null != chooser.resolveActivity(packageManager)) {
            startActivityForResult(chooser, UPI_PAYMENT)
        } else {
            Toast.makeText(this, "No UPI app found, pl",Toast.LENGTH_LONG)
        }


    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            UPI_PAYMENT -> if (RESULT_OK == resultCode || resultCode == 11) {
                if (attr.data != null) {
                    val trxt: String = data?.getStringExtra("response").toString()
                    Log.d("UPI", "onActivityResult: ")
                    val dataList: ArrayList<String> = ArrayList()
                    dataList.add(trxt)
                    upiPaymentDataOperation(dataList)
                } else {
                    Log.e("UPI", "onActivityResult: " + "Return data is null")
                    val dataList: ArrayList<String> = ArrayList()
                    dataList.add("nothing")
                    upiPaymentDataOperation(dataList)
                }
            } else {
                //when user simply back without payment
                Log.e("UPI", "onActivityResult: " + "Return data is null")
                val dataList: ArrayList<String> = ArrayList()
                dataList.add("nothing")
                upiPaymentDataOperation(dataList)
            }
        }
    }


    private fun upiPaymentDataOperation(data: ArrayList<String>) {
            var str = data[0]
            Log.e("UPIPAY", "upiPaymentDataOperation: $str")
            var paymentCancel = ""
            if (str == null) str = "discard"
            var status = ""
            var approvalRefNo = ""
            val response = str.split("&".toRegex()).toTypedArray()
            for (i in response.indices) {
                val equalStr = response[i].split("=".toRegex()).toTypedArray()
                if (equalStr.size >= 2) {
                    if (equalStr[0].toLowerCase() == "Status".toLowerCase()) {
                        status = equalStr[1].toLowerCase()
                    } else if (equalStr[0].toLowerCase() == "ApprovalRefNo".toLowerCase() || equalStr[0].toLowerCase() == "txnRef".toLowerCase()) {
                        approvalRefNo = equalStr[1]
                    }
                } else {
                    paymentCancel = "Payment cancelled by user."
                }
            }
            if (status == "success") {
                //Code to handle successful transaction here.
                Toast.makeText(this, "Transaction successful.", Toast.LENGTH_SHORT).show()
                Log.e("UPI", "payment successfull: $approvalRefNo")
            } else if ("Payment cancelled by user." == paymentCancel) {
                Toast.makeText(this, "Payment cancelled by user.", Toast.LENGTH_SHORT).show()
                Log.e("UPI", "Cancelled by user: $approvalRefNo")
            } else {
                Toast.makeText(this, "Transaction failed.Please try again", Toast.LENGTH_SHORT).show()
                Log.e("UPI", "failed payment: $approvalRefNo")
            }
    }

}