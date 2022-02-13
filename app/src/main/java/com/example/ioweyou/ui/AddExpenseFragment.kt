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
import com.example.ioweyou.utils.AppConstants
import com.example.ioweyou.viewModel.ExpensesViewModel
import kotlinx.android.synthetic.main.layout_add_expense.view.*
import java.text.SimpleDateFormat
import java.util.*

class AddExpenseFragment : DialogFragment(), DatePickerDialog.OnDateSetListener {

    private lateinit var expenseViewModel: ExpensesViewModel
    private var myView: View? = null

    companion object {

        const val TAG = "AddExpenseDialog"

        private const val USER_ID = "user_id"

        fun newInstance(userId: String): AddExpenseFragment {
            val args = Bundle()
            args.putString(USER_ID, userId)
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
        expenseViewModel = ViewModelProvider(
            requireActivity(),
            ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application))[ExpensesViewModel::class.java]

        setupClickListeners(view)
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog?.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
    }

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
        myView.tvDate.setOnClickListener {
            selectDate()
        }
        val function: (View) -> Unit = {
            val isValidated = validateInputFields()
            if (isValidated) {
            expenseViewModel.insertExpense(
                Expenses(
                    0,
                    myView.etTitle.text.toString().trim(),
                    myView.tvDate.text.toString().trim(),
                    myView.etAmount.text.toString().trim(),
                    myView.etDesc.text.toString().trim(),
                    arguments?.getString(USER_ID).toString()
                )

            )
                dismiss()
            }

        }
        myView.btnAddExpense.setOnClickListener(function)
    }

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

    override fun onDateSet(p0: DatePicker?, year: Int, month: Int, day: Int) {
        myView?.tvDate?.error = null
        myView?.tvDate?.text =
            "${convertDateFormatCustom("${month + 1}/$day/$year", AppConstants.DATE_FORMAT_MM_dd_yyyy, AppConstants.DATE_FORMAT_MMM_dd_yyyy)}"
    }

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