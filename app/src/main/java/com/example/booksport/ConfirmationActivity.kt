package com.example.booksport

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ConfirmationActivity : AppCompatActivity() {
    private lateinit var etPlace: EditText
    private lateinit var etDate: EditText
    private lateinit var etTime: EditText
    private lateinit var etSport: EditText
    private lateinit var etDuration: EditText
    private lateinit var btnConfirm: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confirmation)

        // Menghubungkan elemen UI
        etPlace = findViewById(R.id.etPlace)
        etDate = findViewById(R.id.etDate)
        etTime = findViewById(R.id.etTime)
        etSport = findViewById(R.id.etSport)
        etDuration = findViewById(R.id.etDuration)
        btnConfirm = findViewById(R.id.btnConfirm)

        // Mengambil data dari Intent
        val place = intent.getStringExtra("place_name")
        val date = intent.getStringExtra("date")
        val time = intent.getStringExtra("time")
        val sport = intent.getStringExtra("sport")
        val duration = intent.getStringExtra("duration")

        // Menampilkan data di EditText
        etPlace.setText(place)
        etDate.setText(date)
        etTime.setText(time)
        etSport.setText(sport)
        etDuration.setText(duration)

        // Aksi ketika tombol konfirmasi ditekan
        btnConfirm.setOnClickListener {
            if (etPlace.text.isEmpty() || etDate.text.isEmpty() || etTime.text.isEmpty()
                || etSport.text.isEmpty() || etDuration.text.isEmpty()) {
                Toast.makeText(this, "Harap lengkapi semua data", Toast.LENGTH_SHORT).show()
            } else {
                val confirmedPlace = etPlace.text.toString()
                val confirmedDate = etDate.text.toString()
                val confirmedTime = etTime.text.toString()
                val confirmedSport = etSport.text.toString()
                val confirmedDuration = etDuration.text.toString()

                val message = """
                    Pemesanan Berhasil!
                    Tempat: $confirmedPlace
                    Tanggal: $confirmedDate
                    Waktu: $confirmedTime
                    Jenis Olahraga: $confirmedSport
                    Durasi: $confirmedDuration jam
                """.trimIndent()

                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra("place_name", confirmedPlace)
                intent.putExtra("date", confirmedDate)
                intent.putExtra("time", confirmedTime)
                intent.putExtra("sport", confirmedSport)
                intent.putExtra("duration", confirmedDuration)

                Toast.makeText(this, message, Toast.LENGTH_LONG).show()
                startActivity(intent)
                finish()
            }
        }
    }
}
