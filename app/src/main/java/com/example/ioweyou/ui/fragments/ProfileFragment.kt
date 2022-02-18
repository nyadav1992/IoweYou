package com.example.ioweyou.ui.fragments

import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.example.ioweyou.IoweYouApplication
import com.example.ioweyou.R
import com.example.ioweyou.databinding.LayoutProfileBinding
import com.example.ioweyou.repository.ExpenseRepository
import com.example.ioweyou.repository.UserRepository
import com.example.ioweyou.ui.LoginActivity
import com.example.ioweyou.utils.AppConstants
import com.example.ioweyou.utils.Preferences
import com.example.ioweyou.viewModel.CommonViewModelFactory
import com.example.ioweyou.viewModel.UserViewModel
import kotlinx.android.synthetic.main.layout_profile.*

class ProfileFragment : DialogFragment() {

    //using view model to get user data
    private lateinit var userViewModel: UserViewModel

    private lateinit var layoutProfileBinding: LayoutProfileBinding

    companion object {

        const val TAG = "ProfileDialog"

        fun newInstance(): ProfileFragment {
            return ProfileFragment()
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        layoutProfileBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.layout_profile,
            container,
            false
        )
        return layoutProfileBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val userDao = (requireActivity().application as IoweYouApplication).database.getUser()
        val userRepository = UserRepository(userDao)

        //initializing view model
        userViewModel = ViewModelProvider(
            requireActivity(),
            CommonViewModelFactory(userRepository)
        )[UserViewModel::class.java]
        val userEmail = Preferences.getData(AppConstants.LOGGED_IN_USER_EMAIL, "")

        //getting user data
        userViewModel.getUser(userEmail!!).observe(requireActivity()) {
            //setting data to xml file
            layoutProfileBinding.userData = it
        }

        layoutProfileBinding.lifecycleOwner = this

        setupClickListeners()
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog?.window?.attributes?.windowAnimations = R.style.DialogAnimationDrawer
        dialog?.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
        dialog?.setCancelable(true)
        dialog?.setCanceledOnTouchOutside(true)
    }

    private fun setupClickListeners() {
        ivCross.setOnClickListener {
            dismiss()
        }

        rlMain.setOnClickListener {
            dismiss()
        }

        btnLogout.setOnClickListener {

            val alertDialogBuilder = AlertDialog.Builder(requireContext())
            alertDialogBuilder.setTitle(requireActivity().getString(R.string.logout))
            alertDialogBuilder.setMessage(requireActivity().getString(R.string.are_you_sure_to_logout))
            alertDialogBuilder.setPositiveButton("Yes") { dialogInterface: DialogInterface, i: Int ->
                Preferences.saveData(AppConstants.isUserLoggedIn, false)
                startActivity(Intent(requireContext(), LoginActivity::class.java))
                requireActivity().finish()
            }
            alertDialogBuilder.setNegativeButton("Cancel") { dialogInterface: DialogInterface, i: Int -> dismiss() }

            val alertDialog = alertDialogBuilder.create()
            alertDialog.setCancelable(false)
            alertDialog.show()


        }

    }

}