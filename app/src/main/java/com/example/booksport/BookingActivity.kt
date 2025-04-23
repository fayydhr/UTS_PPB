package com.example.booksport

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import java.util.*


class BookingActivity : AppCompatActivity() {
    private lateinit var etDate: EditText
    private lateinit var etTime: EditText
    private lateinit var spinnerSport: Spinner
    private lateinit var btnSubmit: Button
    private lateinit var tvPlace: TextView

    private var selectedSport: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_booking)

        val placeName = intent.getStringExtra("place_name")

        etDate = findViewById(R.id.etDate)
        etTime = findViewById(R.id.etTime)
        spinnerSport = findViewById(R.id.spinnerSport)
        btnSubmit = findViewById(R.id.btnSubmit)
        tvPlace = findViewById(R.id.tvPlace)

        tvPlace.text = "Tempat: $placeName"

        // Pilihan olahraga
        val sports = arrayOf("Futsal", "Basket", "Badminton", "Tenis", "Voli")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, sports)
        spinnerSport.adapter = adapter
        spinnerSport.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: android.view.View, position: Int, id: Long) {
                selectedSport = sports[position]
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

        // Tanggal picker
        etDate.setOnClickListener {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            DatePickerDialog(this, { _, y, m, d ->
                etDate.setText("$y-${String.format("%02d", m + 1)}-${String.format("%02d", d)}")
            }, year, month, day).show()
        }

        // Waktu picker
        etTime.setOnClickListener {
            val calendar = Calendar.getInstance()
            val hour = calendar.get(Calendar.HOUR_OF_DAY)
            val minute = calendar.get(Calendar.MINUTE)

            TimePickerDialog(this, { _, h, m ->
                etTime.setText(String.format("%02d:%02d", h, m))
            }, hour, minute, true).show()
        }

        btnSubmit.setOnClickListener {
            val date = etDate.text.toString()
            val time = etTime.text.toString()

            if (date.isEmpty() || time.isEmpty()) {
                Toast.makeText(this, "Tanggal dan waktu wajib diisi!", Toast.LENGTH_SHORT).show()
            } else {
                val intent = Intent(this, ConfirmationActivity::class.java)
                intent.putExtra("place_name", placeName)
                intent.putExtra("date", date)
                intent.putExtra("time", time)
                intent.putExtra("sport", selectedSport)
                startActivity(intent)
            }
        }
    }
}
