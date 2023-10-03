package com.example.recycler_aston

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.recycler_aston.databinding.ActivityContactsBinding
import java.io.Serializable
import kotlin.random.Random

private const val TAG = "MyLog"
private const val PREF_NAME = "prefs_name"
private const val KEY_STRING_NAME = "KEY_STRING"

class ContactsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityContactsBinding
    private var contactList = mutableListOf<Contact>()
    private var adapter: ContactAdapter? = null
    lateinit var prefs: SharedPreferences
    var count = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContactsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        prefs = getSharedPreferences(PREF_NAME, MODE_PRIVATE)
        val editor = prefs.edit()
        editor.clear()
        editor.apply()

        getContactList()

        adapter = ContactAdapter(contactList)
        binding.recyclerViewContact.adapter = adapter

        binding.recyclerViewContact.addItemDecoration(
            DividerItemDecoration(
                this,
                DividerItemDecoration.VERTICAL
            )
        )

        binding.deleteButton.setOnClickListener {
            val newContactList = adapter!!.removeItem()
            updateList(newContactList)
        }

        binding.addButton.setOnClickListener {
            val intent = Intent(this, AddContactActivity::class.java)
            startActivity(intent)
        }

        binding.editButton.setOnClickListener {
            val intent = Intent(this, AddContactActivity::class.java)
            val contact = adapter!!.editItem()
            if (contact != null) {
                intent.putExtra("contact", contact.id)
                intent.putExtra("name", contact.name)
                intent.putExtra("lastName", contact.lastName)
                intent.putExtra("numberPhone", contact.numberPhone)
                startActivity(intent)
            }
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart: ")
        val newContactList = addItem()
        if (newContactList != null) {
            updateList(newContactList)
            prefs = getSharedPreferences(PREF_NAME, MODE_PRIVATE)
            val editor = prefs.edit()
            editor.clear()
            editor.apply()
        }
    }

    fun addItem(): MutableList<Contact>? {
        val contactId = prefs.getInt("contactId", -1)
        val name = prefs.getString(KEY_STRING_NAME, "")?: ""
        val lastName = prefs.getString("lastName", "")?: ""
        val numberPhone = prefs.getString("phoneNumber", "")?: ""
        var data: List<Contact>? = null

        if (name.isNotEmpty()) {
            val contact = Contact(
                id = count,
                name = name,
                lastName = lastName,
                numberPhone = numberPhone.toLong(),
                visibility = false
            )

            if (contactId == -1) {
                contact.id = ++count
                data = ArrayList(contactList).plus(
                    contact
                )
            }
            else {
                data = ArrayList(contactList).toMutableList()
                val indexToDelete = data.indexOfFirst { it.id == contactId }
                if (indexToDelete != -1) {
                    data.removeAt(indexToDelete)

                    contact.id = contactId
                    data.add(indexToDelete, contact)
                }
            }

        }
        return data?.toMutableList()
    }

    private fun getContactList(): List<Contact> {
        val names = listOf("Иван", "Олег", "Алексей", "Игорь", "Александр", "Виктор", "Семён", "Егор", "Андрей", "Никита", "Борис")
        val lastNames = listOf("Солнечный", "Белый", "Черный", "Васильев", "Есенин", "Тютчев", "Толстой", "Гребенщиков", "Пушкин", "Ломов", "Ким")
        for (item in 1..100) {
            val itemContact = Contact(
                id = item,
                name = names[Random.nextInt(names.size)],
                lastName = lastNames[Random.nextInt(lastNames.size)],
                numberPhone = Random.nextLong(80000000000, 89999999999),
                visibility = false
            )
            count = item
            contactList.add(itemContact)

        }
        return contactList
    }

    private fun updateList(newContactList: MutableList<Contact>) {
        val result = DiffUtil.calculateDiff(
            ContactDiffUtilCallback(
                oldContactList = contactList,
                newContactList = newContactList
            )
        )
        adapter?.let { adapter ->
            adapter.data = newContactList
            result.dispatchUpdatesTo(adapter)
        }
        contactList = newContactList.toMutableList()
    }
}