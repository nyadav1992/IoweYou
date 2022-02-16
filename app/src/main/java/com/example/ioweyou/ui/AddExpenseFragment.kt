package com.example.ioweyou.ui

import android.app.DatePickerDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.DatePicker
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.example.ioweyou.R
import com.example.ioweyou.models.Expenses
import com.example.ioweyou.models.User
import com.example.ioweyou.utils.AppConstants
import com.example.ioweyou.viewModel.ExpensesViewModel
import kotlinx.android.synthetic.main.layout_add_expense.view.*
import java.io.Serializable
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.*

class AddExpenseFragment : DialogFragment(), DatePickerDialog.OnDateSetListener {

    //using viewmodel to save date instance for screen rotation and to submit data
    private lateinit var expenseViewModel: ExpensesViewModel
    private var myView: View? = null

    companion object {

        const val TAG = "AddExpenseDialog"

        private const val USER = "user_id"
        private const val USER_LIST = "user_list"

        fun newInstance(userId: User, userList: List<String>): AddExpenseFragment {
            val args = Bundle()
            args.putSerializable(USER, userId as Serializable)
            args.putSerializable(USER_LIST, userList as Serializable)
            val fragment = AddExpenseFragment()
            fragment.arguments = args
            return fragment
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.layout_add_expense, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        myView = view

        //initializing viewmodel
        expenseViewModel = ViewModelProvider(
            requireActivity(),
            ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application))[ExpensesViewModel::class.java]

        //seting date to textview
        expenseViewModel.dateString.observe(viewLifecycleOwner) {
            myView?.tvDate?.text = it
        }

        setupClickListeners(view)
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog?.window?.attributes?.windowAnimations = R.style.DialogAnimation
        dialog?.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
    }

    //it will open date picker dialog
    private fun selectDate() {
        val calendar: Calendar = Calendar.getInstance()
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        val month = calendar.get(Calendar.MONTH)
        val year = calendar.get(Calendar.YEAR)
        val datePickerDialog =
            DatePickerDialog(requireContext(), this, year, month, day)
        datePickerDialog.datePicker.maxDate = calendar.timeInMillis
        datePickerDialog.show()
    }

    private fun setupClickListeners(myView: View) {
        val user = arguments?.getSerializable(USER) as User
        val userList = arguments?.getSerializable(USER_LIST) as List<*>
        val decimalFormat = DecimalFormat("0.00")

        myView.tvDate.setOnClickListener {
            selectDate()
        }
        myView.btnAddExpense.setOnClickListener{
            //to check fourm validation before submit data
            val isValidated = validateInputFields()

            if (isValidated) {
                val amount = myView.etAmount.text.toString().trim()
                val getBack = amount.toDouble().div(userList.size)

                expenseViewModel.insertExpense(
                    Expenses(
                        0,
                        myView.etTitle.text.toString().trim(),
                        myView.tvDate.text.toString().trim(),
                        myView.etAmount.text.toString().trim(),
                        myView.etDesc.text.toString().trim(),
                        user.id.toString(),
                        user.userName,
                        decimalFormat.format(getBack).toString(),
                        decimalFormat.format(getBack * 3 ).toString(),
                        userList as List<String>,
                        false
                    )

                )
                dismiss()
            }
        }
    }

    //method to validate form input data for empty fields
    private fun validateInputFields(): Boolean {
        val title = myView?.etTitle?.text.toString().trim()
        val date = myView?.tvDate?.text.toString().trim()
        val amount = myView?.etAmount?.text.toString().trim()

        return when {
            TextUtils.isEmpty(title) -> {
                myView?.etTitle?.error = getString(R.string.mandatory_field)
                false
            }
            TextUtils.isEmpty(date) -> {
                myView?.tvDate?.error = getString(R.string.mandatory_field)
                false
            }
            TextUtils.isEmpty(amount) -> {
                myView?.etAmount?.error = getString(R.string.mandatory_field)
                false
            }
            else -> true
        }
    }

    //date picker callback
    override fun onDateSet(p0: DatePicker?, year: Int, month: Int, day: Int) {
        myView?.tvDate?.error = null
        expenseViewModel.setText(convertDateFormatCustom((month + 1).toString() +"/"+day+"/"+year, AppConstants.DATE_FORMAT_MM_dd_yyyy, AppConstants.DATE_FORMAT_MMM_dd_yyyy)!!)
    }

    //method to convert date on desire format
    private fun convertDateFormatCustom(
        currentDate: String,
        currentDateFormatString: String?, reqDateFormat: String?
    ): String? {
        val currentDateFormat = SimpleDateFormat(
            currentDateFormatString, Locale.US
        )
        val format = SimpleDateFormat(reqDateFormat, Locale.US)
        try {
            val d = currentDateFormat.parse(currentDate)
            return format.format(d!!)
        } catch (e: Exception) {
            Log.e("date", "$currentDate ")
            e.printStackTrace()
        }
        return currentDate
    }

}