package com.example.ioweyou.interfaces

interface ICommonViewCallbacks {

    //method use to show progress dialog
    fun showProgressDialog()

    //method use to show progress dialog
    fun showProgressDialogWithMessage(message: String)

    //method use to hide progress dialog
    fun hideProgressDialog()

}