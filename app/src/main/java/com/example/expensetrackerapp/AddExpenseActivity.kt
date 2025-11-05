package com.example.expensetrackerapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class AddExpenseActivity : AppCompatActivity() {

    private lateinit var titleInput: EditText
    private lateinit var amountInput: EditText
    private lateinit var categoryInput: EditText
    private lateinit var saveButton: Button
    private lateinit var dbHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_expense)

        titleInput = findViewById(R.id.editTextTitle)
        amountInput = findViewById(R.id.editTextAmount)
        categoryInput = findViewById(R.id.editTextCategory)
        saveButton = findViewById(R.id.buttonSave)
        dbHelper = DatabaseHelper(this)

        saveButton.setOnClickListener {
            val title = titleInput.text.toString().trim()
            val amountText = amountInput.text.toString().trim()
            val category = categoryInput.text.toString().trim()

            if (title.isEmpty() || amountText.isEmpty() || category.isEmpty()) {
                Toast.makeText(this, R.string.error_invalid_input, Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val amount = amountText.toDoubleOrNull()
            if (amount == null || amount <= 0) {
                Toast.makeText(this, R.string.error_invalid_input, Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val success = dbHelper.insertExpense(title, amount, category)
            if (success) {
                Toast.makeText(this, R.string.toast_added, Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(this, "Error saving expense", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
