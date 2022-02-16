package com.example.ioweyou.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.ioweyou.R
import com.example.ioweyou.databinding.RowExpenseListBinding
import com.example.ioweyou.models.Expenses
import com.example.ioweyou.utils.AppConstants
import com.example.ioweyou.utils.Preferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ExpenseAdapter(private val clickListener: ItemClickListener) :
    ListAdapter<Expenses, ExpenseAdapter.ExpenseViewHolder>(DiffUtils()) {
    var context: Context? = null

    //View Holder class with DataBinding
    class ExpenseViewHolder(val rowExpenseListBinding: RowExpenseListBinding) :
        RecyclerView.ViewHolder(rowExpenseListBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExpenseViewHolder {
        context = parent.context
        val root = RowExpenseListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ExpenseViewHolder(root)
    }

    override fun onBindViewHolder(holder: ExpenseViewHolder, position: Int) {

        //Apply some condition to modify data according to view needs
        val item = getItem(position)

        val data = Preferences.getData(AppConstants.LOGGED_IN_USER_NAME, "")
        val listToString = listToString(item.splitWith)
        val stringToList = stringToList(listToString!!.replace(data!!, "You"))
        item.splitWith = stringToList

        if (item.paidBy == Preferences.getData(AppConstants.LOGGED_IN_USER_ID, 0).toString())
            context!!.getString(R.string.you).also {
                item.paidByName = it
                item.isByYou = true

            }
        else
            holder.rowExpenseListBinding.ivDelete.visibility = View.GONE

        //setting data to xml file
        holder.rowExpenseListBinding.mData = item

        holder.rowExpenseListBinding.mainLayout.setOnClickListener {
            clickListener.onItemClicked(item, AppConstants.CLICK_TYPE.DETAIL)
        }

        holder.rowExpenseListBinding.ivDelete.setOnClickListener {
            clickListener.onItemClicked(item, AppConstants.CLICK_TYPE.DELETE)
        }
    }

    //DiffUtils to optimize the performance of recyclerview
    class DiffUtils : androidx.recyclerview.widget.DiffUtil.ItemCallback<Expenses>() {
        override fun areItemsTheSame(oldItem: Expenses, newItem: Expenses): Boolean {
            return oldItem.expenseId == newItem.expenseId
        }

        override fun areContentsTheSame(oldItem: Expenses, newItem: Expenses): Boolean {
            return oldItem == newItem
        }

    }

    //this method converts strings to list<String>
    private fun stringToList(value: String?): List<String>? {
        if (value == null)
            return null
        val gson = Gson()
        val type = object : TypeToken<List<String>>() {}.type
        return gson.fromJson(value, type)
    }

    //this method converts list<String> to String
    private fun listToString(value: List<String>?): String? {
        if (value == null)
            return null
        val gson = Gson()
        val type = object : TypeToken<List<String>>() {}.type
        return gson.toJson(value, type)
    }

}

//Interface for recycler Click
interface ItemClickListener {
    fun onItemClicked(expenses: Expenses, clickType: String)
}