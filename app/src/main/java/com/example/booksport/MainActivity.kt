package com.example.booksport

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var listView: ListView
    private lateinit var tvOrderDetails: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listView = findViewById(R.id.listView)
        tvOrderDetails = findViewById(R.id.tvOrderDetails)

        val places = listOf("Lapangan Futsal ABC", "Gor Badminton XYZ", "Basket Indoor 123")

        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, places)
        listView.adapter = adapter

        listView.setOnItemClickListener { _, _, position, _ ->
            val intent = Intent(this, BookingActivity::class.java)
            intent.putExtra("place_name", places[position])
            startActivity(intent)
        }

        // Mengambil data pesanan yang dikirim dari ConfirmationActivity
        val place = intent.getStringExtra("place_name")
        val date = intent.getStringExtra("date")
        val time = intent.getStringExtra("time")
        val sport = intent.getStringExtra("sport")
        val duration = intent.getStringExtra("duration") // Durasi jam yang ditambahkan

        // Menampilkan data pesanan jika ada
        if (place != null && date != null && time != null && sport != null && duration != null) {
            val orderDetails = """
                Pesanan Anda:
                Tempat: $place
                Tanggal: $date
                Waktu: $time
                Jenis Olahraga: $sport
                Durasi: $duration jam
            """.trimIndent()
            tvOrderDetails.text = orderDetails
        } else {
            tvOrderDetails.text = "Pesanan belum dikonfirmasi"
        }
    }
}
