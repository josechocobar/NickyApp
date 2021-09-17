package com.cuty.nicky.ui

import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import com.cuty.nicky.R

class LoadingDialog (myactivity: Activity){
    lateinit var activity: Activity
    var dialog: Dialog?=null

    init {
        activity = myactivity
    }
    fun startLoadingDialog(){
        val builder : AlertDialog.Builder = AlertDialog.Builder(activity)
        val inflater = activity.layoutInflater
        builder.setView(inflater.inflate(R.layout.custom_dialog, null))
        builder.setCancelable(true)
        dialog = builder.create()
        dialog?.show()

    }
    fun dissmissDialog(){
        dialog?.dismiss()

    }


}