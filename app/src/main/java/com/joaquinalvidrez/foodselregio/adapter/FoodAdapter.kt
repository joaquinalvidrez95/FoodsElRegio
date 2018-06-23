package com.joaquinalvidrez.foodselregio.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.joaquinalvidrez.foodselregio.R
import com.joaquinalvidrez.foodselregio.model.Food
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_food.view.*

class FoodAdapter(val menu: List<Food>) : RecyclerView.Adapter<FoodAdapter.ViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder =
            ViewHolder(LayoutInflater.from(p0.context).inflate(R.layout.item_food, p0, false))

    override fun getItemCount(): Int = menu.size

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        p0.bind(menu[p1])
    }

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(food: Food) {
            Picasso
                    .get()
                    .load(food.urlImagen)
                    .resize(300, 300)
                    .into(view.image_view_food)
            view.text_view_food_name.text = food.nombre
            view.text_view_food_description.text = food.descripcion
            view.text_view_food_price.text = "$${food.precio}.00"
        }
    }
}


