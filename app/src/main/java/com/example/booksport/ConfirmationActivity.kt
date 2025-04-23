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
    private lateinit var btnConfirm: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confirmation)

        // Menghubungkan elemen UI dengan kode
        etPlace = findViewById(R.id.etPlace)
        etDate = findViewById(R.id.etDate)
        etTime = findViewById(R.id.etTime)
        etSport = findViewById(R.id.etSport)
        btnConfirm = findViewById(R.id.btnConfirm)

        // Mengambil data dari Intent (Jika ada)
        val place = intent.getStringExtra("place_name")
        val date = intent.getStringExtra("date")
        val time = intent.getStringExtra("time")
        val sport = intent.getStringExtra("sport")

        // Menampilkan data di EditText (membiarkan pengguna mengeditnya)
        etPlace.setText(place)
        etDate.setText(date)
        etTime.setText(time)
        etSport.setText(sport)

        // Aksi ketika tombol konfirmasi ditekan
        btnConfirm.setOnClickListener {
            // Memeriksa apakah data sudah lengkap
            if (etPlace.text.isEmpty() || etDate.text.isEmpty() || etTime.text.isEmpty() || etSport.text.isEmpty()) {
                Toast.makeText(this, "Harap lengkapi semua data", Toast.LENGTH_SHORT).show()
            } else {
                val confirmedPlace = etPlace.text.toString()
                val confirmedDate = etDate.text.toString()
                val confirmedTime = etTime.text.toString()
                val confirmedSport = etSport.text.toString()

                // Menampilkan pesan konfirmasi
                val message = """
                    Pemesanan Berhasil!
                    Tempat: $confirmedPlace
                    Tanggal: $confirmedDate
                    Waktu: $confirmedTime
                    Jenis Olahraga: $confirmedSport
                """.trimIndent()

                // Mengirim data kembali ke MainActivity
                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra("place_name", confirmedPlace)
                intent.putExtra("date", confirmedDate)
                intent.putExtra("time", confirmedTime)
                intent.putExtra("sport", confirmedSport)

                // Menampilkan pesan dan kembali ke MainActivity
                Toast.makeText(this, message, Toast.LENGTH_LONG).show()
                startActivity(intent)
                finish() // Menutup ConfirmationActivity agar tidak kembali ke halaman konfirmasi
            }
        }
    }
}
