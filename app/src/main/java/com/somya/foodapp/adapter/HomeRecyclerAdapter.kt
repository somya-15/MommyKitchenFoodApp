package com.somya.foodapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.somya.foodapp.R
import com.somya.foodapp.model.Restaurants
import com.squareup.picasso.Picasso

class HomeRecyclerAdapter(val context: Context, var itemList: ArrayList<Restaurants>): RecyclerView.Adapter<HomeRecyclerAdapter.HomeViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.recycler_home_single_row,parent,false)
    return HomeViewHolder(view)
    }

    override fun getItemCount(): Int {

        return itemList.size
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {

        val restaurant = itemList[position]
        holder.txtRestaurantsName.tag = restaurant.restaurantsId + ""
        holder.txtRestaurantsName.text=restaurant.restaurantsName

        holder.txtRestaurantsCost.text=restaurant.cost_for_one + "/Person"

        holder.txtRestaurantsRating.text=restaurant.restaurantsRating


        //holder.imgRestaurantsImage.setImageResource(restaurant.restaurantsImage)
        Picasso.get().load(restaurant.restaurantsImage).error(R.drawable.dish).into(holder.imgRestaurantsImage)



    }
    class HomeViewHolder(view: View): RecyclerView.ViewHolder(view){
        val imgRestaurantsImage: ImageView=view.findViewById(R.id.imgRestaurantsImage)
        val txtRestaurantsName: TextView =view.findViewById(R.id.txtRestaurantsName)
        val txtRestaurantsCost: TextView =view.findViewById(R.id.txtRestaurantsCost)
        val txtRestaurantsRating: TextView =view.findViewById(R.id.txtRestaurantsRating)
        //val llContent: LinearLayout = view.findViewById(R.id.llContent)

    }

}
/*val imgRestaurant: ImageView = view.findViewById(R.id.imgRestaurant)
        val txtRestaurantName: TextView = view.findViewById(R.id.txtRestaurantName)
        val txtPrice: TextView = view.findViewById(R.id.txtPrice)
        val txtRating: TextView = view.findViewById(R.id.txtRating)
        val llContent: LinearLayout = view.findViewById(R.id.llContent)
        val txtFavorite: TextView = view.findViewById(R.id.txtFavorite)*/
