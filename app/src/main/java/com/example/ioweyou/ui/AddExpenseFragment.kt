package com.example.ioweyou.ui

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.ioweyou.R
import com.example.ioweyou.models.Expenses
import com.example.ioweyou.viewModel.ExpensesViewModel
import kotlinx.android.synthetic.main.layout_add_expense.*
import kotlinx.android.synthetic.main.layout_add_expense.view.*

class AddExpenseFragment : DialogFragment() {

    private lateinit var expenseViewModel: ExpensesViewModel

    companion object {

        const val TAG = "AddExpenseDialog"

        private const val KEY_TITLE = "KEY_TITLE"
        private const val KEY_SUBTITLE = "KEY_SUBTITLE"

        fun newInstance(title: String, subTitle: String): AddExpenseFragment {
            val args = Bundle()
            args.putString(KEY_TITLE, title)
            args.putString(KEY_SUBTITLE, subTitle)
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
        expenseViewModel = ViewModelProvider(requireActivity(), ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application))
            .get(ExpensesViewModel::class.java)
        setupView(view)
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

    private fun setupView(view: View) {
//        view.tvPaidBy.text = arguments?.getString(KEY_TITLE)
//        view.tvPaidByValrajuue.text = arguments?.getString(KEY_SUBTITLE)
    }

    private fun setupClickListeners(myView: View) {
        myView.btnAddExpense.setOnClickListener {
            expenseViewModel.insertExpense(Expenses(0, myView.etTitle.text.toString().trim(), "12", myView.etAmount.text.toString().trim(), myView.etDesc.text.toString().trim(), "3"))
            dismiss()
        }
    }

}