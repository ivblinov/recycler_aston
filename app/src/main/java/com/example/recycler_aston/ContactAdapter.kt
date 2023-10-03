package com.example.recycler_aston

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.recycler_aston.databinding.ContactListItemBinding

private const val TAG = "MyLog"

class ContactAdapter(data: List<Contact>) : RecyclerView.Adapter<ContactViewHolder>() {

    var data = data.toMutableList()
    val listItemId: MutableList<Int> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val binding = ContactListItemBinding.inflate(LayoutInflater.from(parent.context))
        return ContactViewHolder(binding)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        val item = data[position]

        with(holder.binding) {
            contId.text = item.id.toString()
            name.text = item.name
            lastName.text = item.lastName
            numberPhone.text = item.numberPhone.toString()
            backgroundBlack.visibility = View.INVISIBLE
        }

        if (item.visibility)
            holder.binding.backgroundBlack.visibility = View.VISIBLE

        holder.binding.root.setOnClickListener {
            if (item.id in listItemId) {
                listItemId.removeAt(listItemId.indexOf(item.id))
                item.visibility = false
            } else {
                listItemId.add(item.id)
                item.visibility = true
            }

            if (item.visibility) {
                holder.binding.backgroundBlack.visibility = View.VISIBLE
            } else {
                holder.binding.backgroundBlack.visibility = View.INVISIBLE
            }

        }
    }


    fun removeItem(): MutableList<Contact> {
        data = ArrayList(data)
        listItemId.forEach { itemId ->
            val indexToDelete = data.indexOfFirst { it.id == itemId }
            if (indexToDelete != -1) {
                data.removeAt(indexToDelete)
            }
        }
        listItemId.clear()
        return data
    }

    fun editItem(): Contact? {
        return if (listItemId.isNotEmpty()) {
            val lastItem = listItemId.last()
            val indexToEdit = data.indexOfFirst { it.id == lastItem }
            if (indexToEdit != -1) {
                listItemId.clear()
                data[indexToEdit]
            } else {
                listItemId.clear()
                null
            }
        } else null
    }

}