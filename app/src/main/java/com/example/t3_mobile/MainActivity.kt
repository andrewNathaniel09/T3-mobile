package com.example.t3_mobile

import android.os.Bundle
import android.view.Gravity
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.t3_mobile.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val etNama         = findViewById<EditText>(R.id.etNama)
        val tvErrorNama    = findViewById<TextView>(R.id.tvErrorNama)
        val tvErrorKelamin = findViewById<TextView>(R.id.tvErrorKelamin)
        val rgJenisKelamin = findViewById<RadioGroup>(R.id.rgJenisKelamin)
        val layoutKelamin  = findViewById<LinearLayout>(R.id.layoutKelamin)
        val cbMembaca      = findViewById<CheckBox>(R.id.cbMembaca)
        val cbCoding       = findViewById<CheckBox>(R.id.cbCoding)
        val cbOlahraga     = findViewById<CheckBox>(R.id.cbOlahraga)
        val btnTampilkan   = findViewById<Button>(R.id.btnTampilkan)
        val tvHasil        = findViewById<TextView>(R.id.tvHasil)

        btnTampilkan.setOnClickListener {

            val nama = etNama.text.toString().trim()
            var valid = true

            if (nama.isEmpty()) {
                tvErrorNama.visibility = TextView.VISIBLE
                etNama.setBackgroundResource(R.drawable.bg_error)
                Toast.makeText(this, "Nama tidak boleh kosong!", Toast.LENGTH_SHORT).show()
                valid = false
            } else {
                tvErrorNama.visibility = TextView.GONE
                etNama.setBackgroundResource(R.drawable.bg_input)
            }

            if (rgJenisKelamin.checkedRadioButtonId == -1) {
                tvErrorKelamin.visibility = TextView.VISIBLE
                layoutKelamin.setBackgroundResource(R.drawable.bg_error)
                Toast.makeText(this, "Jenis kelamin harus dipilih!", Toast.LENGTH_SHORT).show()
                valid = false
            } else {
                tvErrorKelamin.visibility = TextView.GONE
                layoutKelamin.setBackgroundResource(R.drawable.bg_dashed_blue)
            }

            if (!valid) return@setOnClickListener

            val kelamin = when (rgJenisKelamin.checkedRadioButtonId) {
                R.id.rbLakiLaki  -> "Laki-laki"
                R.id.rbPerempuan -> "Perempuan"
                else -> "-"
            }

            val hobiList = mutableListOf<String>()
            if (cbMembaca.isChecked)  hobiList.add("Membaca")
            if (cbCoding.isChecked)   hobiList.add("Coding")
            if (cbOlahraga.isChecked) hobiList.add("Olahraga")

            val hobi = if (hobiList.isEmpty()) "-" else hobiList.joinToString(", ")

            // =========================
            // TAMPILKAN HASIL
            // =========================
            tvHasil.setTextColor(getColor(android.R.color.black))
            tvHasil.text = "Nama    : $nama\nKelamin : $kelamin\nHobi    : $hobi"
        }
    }

    private fun showCustomToast(message: String) {
        val inflater = layoutInflater
        val layout = inflater.inflate(R.layout.custom_toast, null)

        val tv = layout.findViewById<TextView>(R.id.tvToast)
        tv.text = message

        val toast = Toast(applicationContext)
        toast.duration = Toast.LENGTH_SHORT
        toast.view = layout
        toast.setGravity(Gravity.BOTTOM, 0, 150)
        toast.show()
    }
}
