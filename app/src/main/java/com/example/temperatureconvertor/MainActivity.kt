package com.example.temperatureconvertor

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.core.widget.addTextChangedListener
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {

    //Declaring views
    private lateinit var selectedUnitText: TextView
    private lateinit var editInput: EditText
    private lateinit var textResult: TextView
    private lateinit var resultTypeText: TextView
    private lateinit var selectedUnitlayout: LinearLayout

    //Inputtype

    private lateinit var selectedUnit: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Initializing views

        selectedUnitText = findViewById(R.id.type1)
        editInput = findViewById(R.id.input)
        textResult = findViewById(R.id.output)
        resultTypeText = findViewById(R.id.type2)
        selectedUnitlayout = findViewById(R.id.selecttype)

        // bydefault fah is input
        selectedUnit = "Fahrenheit"

        //setting alert dialog for selection
        selectedUnitlayout.setOnClickListener() {
            showAlertDialog()
        }
        editInput.addTextChangedListener() {
            var resultText: String = ""
            var inputVal = editInput.text.toString()

            val df = DecimalFormat("#.##")

            if (inputVal.isNotEmpty()) {
                val doubleInput = inputVal.toDouble()
                if (selectedUnit == "Fahrenheit") {
                    resultText = df.format((doubleInput - 32) * 5 / 9)
                    resultTypeText.text = "Celcius"

                } else {
                    resultText = df.format((doubleInput * 9 / 5 + 32))
                    resultTypeText.text = "Fahrenheit"
                }
                textResult.text = resultText


            }
        }

    }

    private fun showAlertDialog() {
        var alertDialog: AlertDialog.Builder = AlertDialog.Builder(this@MainActivity)
        alertDialog.setTitle("Select Input Unit")//Title
        val items = arrayOf("Fahrenheit", "Celcius")//Options
        val checkedItem = -1 // No items by default selected
        alertDialog.setSingleChoiceItems(items, checkedItem,
            DialogInterface.OnClickListener() { dialog, which ->
                selectedUnit = items[which]
                selectedUnitText.setText(selectedUnit)
            })

        alertDialog.setPositiveButton(android.R.string.ok,
            DialogInterface.OnClickListener() { dialog, which ->
                dialog.dismiss()
            })
        val alert: AlertDialog = alertDialog.create()
        alertDialog.show()
    }
}
