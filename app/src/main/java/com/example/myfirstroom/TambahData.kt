package com.example.myfirstroom

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myfirstroom.database.Note
import com.example.myfirstroom.database.NoteRoomDatabase
import com.example.myfirstroom.helper.DateHelper.getCurrentDate
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async

class TambahData : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_tambah_data)

        val DB : NoteRoomDatabase = NoteRoomDatabase.getDatabase(this)
        val tanggal : String = getCurrentDate()
        val etJudul = findViewById<EditText>(R.id.etJudul)
        val etDeskripsi = findViewById<EditText>(R.id.etDeskripsi)
        val btnTambah = findViewById<Button>(R.id.btnTambah)
        val btnUpdate = findViewById<Button>(R.id.btnUpdate)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        btnTambah.setOnClickListener {
            CoroutineScope(Dispatchers.IO).async {
                DB.funnoteDao().insert(
                    Note(
                        id = 0,
                        judul = etJudul.text.toString(),
                        deskripsi = etDeskripsi.text.toString(),
                        tanggal = tanggal
                    )
                )
            }
            finish() // Kembali ke layar sebelumnya
        }
    }
}