package com.example.expensetrackerapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ExpenseAdapter(
    private val context: Context,
    private val expenseList: ArrayList<Expense>
) : RecyclerView.Adapter<ExpenseAdapter.ExpenseViewHolder>() {

    class ExpenseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.expenseTitle)
        val amount: TextView = itemView.findViewById(R.id.expenseAmount)
        val category: TextView = itemView.findViewById(R.id.expenseCategory)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExpenseViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_expense, parent, false)
        return ExpenseViewHolder(view)
    }

    override fun onBindViewHolder(holder: ExpenseViewHolder, position: Int) {
        val expense = expenseList[position]
        holder.title.text = expense.title
        holder.amount.text = "₹${"%.2f".format(expense.amount)}"
        holder.category.text = expense.category
    }

    override fun getItemCount(): Int = expenseList.size
}
