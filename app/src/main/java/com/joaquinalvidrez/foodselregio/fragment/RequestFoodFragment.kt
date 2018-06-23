package com.joaquinalvidrez.foodselregio.fragment


import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.joaquinalvidrez.foodselregio.R
import com.joaquinalvidrez.foodselregio.activity.LocationActivity
import com.joaquinalvidrez.foodselregio.adapter.FoodAdapter
import com.joaquinalvidrez.foodselregio.model.Food
import kotlinx.android.synthetic.main.fragment_request_food.*
import java.util.*


class RequestFoodFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_request_food, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val menu = ArrayList<Food>()
        val adapter = FoodAdapter(menu, object : FoodAdapter.OnFoodClickListener {
            override fun onFoodClick(food: Food) {
                startActivity(Intent(activity, LocationActivity::class.java))
            }

        })
        FirebaseDatabase.getInstance().getReference("Menu").addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onDataChange(p0: DataSnapshot) {
                p0.children.forEach {
                    it.children.forEach {
                        val food = it.getValue(Food::class.java)
                        food?.let {
                            Log.e(javaClass.name, food.toString())
                            menu.add(food)

                        }

                    }
                }
                adapter.notifyDataSetChanged()
            }

        })
        recycler_view_requested_items.apply {
            this.adapter = adapter
            layoutManager = LinearLayoutManager(context)
        }
    }


}
