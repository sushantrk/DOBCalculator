package com.ud.dobcalc_xml

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.ud.dobcalc_xml.R.id.tvSelectedDate
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class MainActivity : AppCompatActivity() {

    private var tvSelectedDate:TextView?=null
    private var tvAgeInMinutes:TextView?=null
    private var tvAgeInHrs:TextView?=null
    private var tvAge:TextView?=null

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn =findViewById<Button>(R.id.btnDatePicker)
        tvSelectedDate=findViewById<TextView>(R.id.tvSelectedDate)
        tvAgeInMinutes=findViewById(R.id.tvAgeInMinutes)
        tvAgeInHrs=findViewById(R.id.tvAgeInHrs)
        tvAge=findViewById(R.id.tvAge)

        btn.setOnClickListener {
            clickDataPicker()

        }
    }

    fun clickDataPicker(){
        val myCalender =Calendar.getInstance()
        val year=myCalender.get(Calendar.YEAR)
        val month=myCalender.get(Calendar.MONTH)
        val day =myCalender.get(Calendar.DAY_OF_MONTH)

        val ddt= DatePickerDialog(this,
            DatePickerDialog.OnDateSetListener{ _,selectedYear,selectedMonth,selectedDayOfMonth->
                val selectedDate="$selectedDayOfMonth/${selectedMonth+1}/$selectedYear"

                tvSelectedDate?.text=selectedDate

                val sdf=SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)

                val theDate=sdf.parse(selectedDate)
                val selectedDateInMinutes= theDate!!.time / 60000

                val currentDate=sdf.parse(sdf.format(System.currentTimeMillis()))
                val currentDateInMinutes= currentDate!!.time/60000

                val differnceInMinutes=currentDateInMinutes-selectedDateInMinutes

                tvAgeInMinutes?.text=differnceInMinutes.toString()

                tvAgeInHrs?.text=(differnceInMinutes / 60.0).toString()

                tvAge?.text=(differnceInMinutes / 525600.0).toInt().toString()
            },
            year,
            month,
            day
        )
        ddt.datePicker.maxDate=System.currentTimeMillis()-86400000
        ddt.show()
    }
}