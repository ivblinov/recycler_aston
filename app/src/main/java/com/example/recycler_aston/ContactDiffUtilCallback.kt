package com.example.recycler_aston

import androidx.recyclerview.widget.DiffUtil

class ContactDiffUtilCallback(
    private val oldContactList: List<Contact>,
    private val newContactList: List<Contact>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldContactList.size

    override fun getNewListSize(): Int = newContactList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldContactList[oldItemPosition].id == newContactList[newItemPosition].id

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldContactList[oldItemPosition] == newContactList[newItemPosition]

}