package com.somya.foodapp.fragment

import android.content.Context
import android.graphics.drawable.ClipDrawable.VERTICAL
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.somya.foodapp.R
import com.somya.foodapp.adapter.HomeRecyclerAdapter
import com.somya.foodapp.model.Restaurants
import com.somya.foodapp.util.ConnectionManager

lateinit var recyclerHome: RecyclerView
lateinit var layoutManager:RecyclerView.LayoutManager
lateinit var recyclerAdapter: HomeRecyclerAdapter
lateinit var progressLayout: RelativeLayout
lateinit var progressBar: ProgressBar

var restaurantsInfoList=arrayListOf<Restaurants>()


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
class Home : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        var view = inflater.inflate(R.layout.fragment_home, container, false)
        recyclerHome = view.findViewById(R.id.recyclerHome)
        layoutManager = LinearLayoutManager(activity)

        progressLayout = view.findViewById(R.id.progressLayout)
        progressBar = view.findViewById(R.id.progressBar)
        if (ConnectionManager().checkConnectivity(activity as Context)) {
            progressLayout.visibility = View.VISIBLE
            try {
                val queue = Volley.newRequestQueue(activity as Context)
                val url = "http://13.235.250.119/v2/restaurants/fetch_result/"
                val jsonObjectRequest =
                    object : JsonObjectRequest(Request.Method.GET, url, null, Response.Listener {
                        println("Response12 is $it")
                        val response = it.getJSONObject("data")
                        val success = response.getBoolean("success")
                        if (success) {

                            val data = response.getJSONArray("data")
                            for (i in 0 until data.length()) {
                                val restaurantJsonObject = data.getJSONObject(i)
                                val restaurantObject = Restaurants(
                                    restaurantJsonObject.getString("id"),
                                    restaurantJsonObject.getString("name"),
                                    restaurantJsonObject.getString("rating"),
                                    restaurantJsonObject.getString("cost_for_one"),
                                    restaurantJsonObject.getString("image_url")
                                )
                                restaurantsInfoList.add(restaurantObject)
                                recyclerAdapter =
                                    HomeRecyclerAdapter(
                                        activity as Context,
                                        restaurantsInfoList
                                    )
                                recyclerHome.adapter = recyclerAdapter
                                recyclerHome.layoutManager = layoutManager
                            }
                        }
                        progressLayout.visibility = View.INVISIBLE
                    },
                        Response.ErrorListener {

                            progressLayout.visibility = View.INVISIBLE

                            Toast.makeText(
                                activity as Context,
                                "Some Error occurred!!!",
                                Toast.LENGTH_SHORT
                            ).show()
                        }) {
                        override fun getHeaders(): MutableMap<String, String> {
                            val headers = HashMap<String, String>()
                            headers["Content-type"] = "application/json"
                            headers["token"] = "10cada353cc454"
                            return headers
                        }
                    }

                queue.add(jsonObjectRequest)

            } catch (e: Exception) {
                Toast.makeText(
                    activity as Context,
                    "Some Unexpected error occurred!!!",
                    Toast.LENGTH_SHORT
                ).show()
            }
        } else {

            Toast.makeText(activity as Context, "ONE", android.widget.Toast.LENGTH_SHORT).show()
        }
        return view
    }

        /*  progressLayout.visibility=View.GONE
             val success = it.getBoolean("success")

             if(success) {
                 val data = it.getJSONArray("data")
                 for (i in 0 until data.length()) {

                         val restaurantsJsonObject = data.getJSONObject(i)
                         val restaurantsObject = Restaurants(
                             restaurantsJsonObject.getString("id"),
                             restaurantsJsonObject.getString("name"),
                             restaurantsJsonObject.getString("rating"),
                             restaurantsJsonObject.getString("cost_for_one"),
                             restaurantsJsonObject.getString("image_url")

                         )

                      restaurantsInfoList.add(restaurantsObject)

                     recyclerHome.layoutManager = layoutManager
                     recyclerAdapter = HomeRecyclerAdapter(activity as Context, restaurantsInfoList)
                     recyclerHome.adapter = recyclerAdapter
                     recyclerHome.layoutManager = layoutManager

                     recyclerHome.addItemDecoration(
                         DividerItemDecoration(
                             recyclerHome.context,
                             (layoutManager as LinearLayoutManager).orientation
                         )
                     )

                     }

                 }  else{
                 Toast.makeText(activity as Context,"ONE", android.widget.Toast.LENGTH_SHORT).show()
             }
             } catch(e: Exception){
                 Toast.makeText(activity as Context,"Some Error happened!!", android.widget.Toast.LENGTH_SHORT).show()
             }

            println("Response is $it")

        },Response.ErrorListener {
             //handling errors here
            println("error is $it")
        }){
            override fun getHeaders(): MutableMap<String, String>{
                val headers=HashMap<String, String>()
                headers["Content-type"]="application/json"
                headers["token"]="10cada353cc454"
                return headers

            }
        }
        queue.add(jsonObjectRequest)
        return view
    }*/






        companion object {
            /**
             * Use this factory method to create a new instance of
             * this fragment using the provided parameters.
             *
             * @param param1 Parameter 1.
             * @param param2 Parameter 2.
             * @return A new instance of fragment Home.
             */
            // TODO: Rename and change types and number of parameters
            @JvmStatic
            fun newInstance(param1: String, param2: String) =
                Home().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
        }
    }
