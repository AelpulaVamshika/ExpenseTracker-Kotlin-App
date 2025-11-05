package com.example.expensetrackerapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var dbHelper: DatabaseHelper
    private lateinit var adapter: ExpenseAdapter
    private lateinit var expenseList: ArrayList<Expense>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dbHelper = DatabaseHelper(this)
        expenseList = dbHelper.getAllExpenses()

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewExpenses)
        val addButton = findViewById<Button>(R.id.buttonAddExpense)
        val totalText = findViewById<TextView>(R.id.textViewTotal)

        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = ExpenseAdapter(this, expenseList)
        recyclerView.adapter = adapter

        // show initial total
        totalText.text = "Total: ₹${"%.2f".format(dbHelper.getTotalExpense())}"

        addButton.setOnClickListener {
            startActivity(Intent(this, AddExpenseActivity::class.java))
        }
    }

    override fun onResume() {
        super.onResume()
        // refresh list when returning from AddExpenseActivity
        expenseList.clear()
        expenseList.addAll(dbHelper.getAllExpenses())
        adapter.notifyDataSetChanged()
        val totalText = findViewById<TextView>(R.id.textViewTotal)
        totalText.text = "Total: ₹${"%.2f".format(dbHelper.getTotalExpense())}"
    }
}
