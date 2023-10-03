package com.example.recycler_aston

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.recycler_aston.databinding.ActivityAddContactBinding

private const val PREF_NAME = "prefs_name"
private const val KEY_STRING_NAME = "KEY_STRING"

class AddContactActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddContactBinding
    lateinit var prefs: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddContactBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val contactId = intent.getIntExtra("contact", -1)

        if (contactId != -1) {
            binding.editName.setText(intent.getStringExtra("name"))
            binding.editLastName.setText(intent.getStringExtra("lastName"))
            val phoneNumber = intent.getLongExtra("numberPhone", 0)
            binding.editPhoneNumber.setText(phoneNumber.toString())
        }

        prefs = getSharedPreferences(PREF_NAME, MODE_PRIVATE)
        val editor = prefs.edit()

        binding.button.setOnClickListener {
            editor.putInt("contactId", contactId)
            editor.putString(KEY_STRING_NAME, binding.editName.text.toString())
            editor.putString("lastName", binding.editLastName.text.toString())
            editor.putString("phoneNumber", binding.editPhoneNumber.text.toString())
            editor.apply()
            finish()
        }

    }
}