package com.example.recycler_aston


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.recycler_aston.databinding.CountryListItemBinding

class CountryAdapter : RecyclerView.Adapter<CountryViewHolder>() {

    val values = listOf(
        Country(1, "Андорра", R.drawable.andora),
        Country(2, "Афганистан", R.drawable.afghanistan),
        Country(3, "Антигуа и Барбуда", R.drawable.ag),
        Country(4, "Ангилья", R.drawable.angilia),
        Country(5, "Албания", R.drawable.albania),
        Country(6, "Армения", R.drawable.armenia),
        Country(7, "Ангола", R.drawable.angola),
        Country(8, "Антарктида", R.drawable.antarctida),
        Country(9, "Аргентина", R.drawable.argentina),
        Country(10, "Американское Самоа", R.drawable.americanskoe_samoa),
        Country(11, "Австрия", R.drawable.avstria),
        Country(12, "Австралия", R.drawable.australia),
        Country(13, "Аруба", R.drawable.aruba),
        Country(14, "Аландские острова", R.drawable.ax),
        Country(15, "Азербайджан", R.drawable.az),
        Country(16, "Босния и Герцеговина", R.drawable.ba),
        Country(17, "Барбадос", R.drawable.bb),
        Country(18, "Бангладеш", R.drawable.bd),
        Country(19, "Бельгия", R.drawable.be),
        Country(20, "Буркина-Фасо", R.drawable.bf),
        Country(21, "Болгария", R.drawable.bg),
        Country(22, "Бахрейн", R.drawable.bh),
        Country(23, "Бурунди", R.drawable.bi),
        Country(24, "Бенин", R.drawable.bj),
        Country(25, "Сен-Бартелеми", R.drawable.bl),
        Country(26, "Бермудские Острова", R.drawable.bm),
        Country(27, "Бруней", R.drawable.bn),
        Country(28, "Боливия", R.drawable.bo),
        Country(29, "Бонайре, Синт-Эстатиус и Саба", R.drawable.bq),
        Country(30, "Бразилия", R.drawable.br)
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val binding = CountryListItemBinding.inflate(LayoutInflater.from(parent.context))
        return CountryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        val item = values[position]
        holder.binding.tvId.text = item.name

        item.let {
            Glide
                .with(holder.itemView)
                .load(it.image)
                .into(holder.binding.imageId)
        }
    }

    override fun getItemCount(): Int = values.size
}