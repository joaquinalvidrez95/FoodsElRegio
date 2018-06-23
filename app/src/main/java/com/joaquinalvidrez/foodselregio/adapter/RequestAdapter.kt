package com.joaquinalvidrez.foodselregio.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.joaquinalvidrez.foodselregio.R

class RequestAdapter() : RecyclerView.Adapter<RequestAdapter.ViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(p0.context).inflate(R.layout.abc_action_bar_title_item, p0, false))
    }

    override fun getItemCount(): Int = 2

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    }
}