package com.example.ioweyou.utils

import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import android.app.Activity
import com.example.ioweyou.R
import kotlinx.android.synthetic.main.layout_progress.view.*


class MyProgress : DialogFragment() {

    companion object{

        private var activity: Activity? = null
        private var dialog: AlertDialog? = null


        fun show(activity: AppCompatActivity) {

            // adding ALERT Dialog builder object and passing activity as parameter
            val builder = AlertDialog.Builder(
                activity!!
            )

            // layoutinflater object and use activity to get layout inflater
            val inflater = activity!!.layoutInflater
            val view = inflater.inflate(R.layout.layout_progress, null)
            builder.setView(view)
            builder.setCancelable(false)
            dialog = builder.create()
            dialog!!.show()
        }

        fun showWithMessage(activity: AppCompatActivity, message: String) {

            // adding ALERT Dialog builder object and passing activity as parameter
            val builder = AlertDialog.Builder(
                activity!!
            )

            // layoutinflater object and use activity to get layout inflater
            val inflater = activity!!.layoutInflater
            val view = inflater.inflate(R.layout.layout_progress, null)
            builder.setView(view)
            builder.setCancelable(false)
            dialog = builder.create()
            view.message.text = message
                dialog!!.show()
        }

        // dismiss method
        fun hide(activity: AppCompatActivity) {
            if (dialog!= null)
            dialog?.dismiss()
        }

    }

    override fun onDestroyView() {
        Runtime.getRuntime().gc()
        super.onDestroyView()
    }
}