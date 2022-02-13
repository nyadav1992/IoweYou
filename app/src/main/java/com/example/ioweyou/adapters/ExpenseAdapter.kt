package com.example.ioweyou.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.ioweyou.databinding.RowExpenseListBinding
import com.example.ioweyou.models.Expenses

class ExpenseAdapter: ListAdapter<Expenses, ExpenseAdapter.ExpenseViewHolder>(DiffUtils()) {

    class ExpenseViewHolder(val rowExpenseListBinding: RowExpenseListBinding) : RecyclerView.ViewHolder(rowExpenseListBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExpenseViewHolder {
        val root = RowExpenseListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ExpenseViewHolder(root)
    }

    override fun onBindViewHolder(holder: ExpenseViewHolder, position: Int) {
        holder.rowExpenseListBinding.mData = getItem(position)
    }

    class DiffUtils: androidx.recyclerview.widget.DiffUtil.ItemCallback<Expenses>(){
        override fun areItemsTheSame(oldItem: Expenses, newItem: Expenses): Boolean {
            return oldItem.expenseId == newItem.expenseId
        }

        override fun areContentsTheSame(oldItem: Expenses, newItem: Expenses): Boolean {
            return oldItem == newItem
        }

    }

}