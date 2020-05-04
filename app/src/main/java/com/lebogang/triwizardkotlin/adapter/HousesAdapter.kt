package com.lebogang.triwizardkotlin.adapter

import android.R
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.lebogang.triwizardkotlin.pojo.Houses
import kotlinx.android.synthetic.main.houses_info_item.view.*
import java.util.*


class HousesAdapter(
    context: Context?,
    housesList: List<Houses?>?
) : RecyclerView.Adapter<RecyclerView.ViewHolder>()  {

    private val TAG = HousesAdapter::class.java.simpleName

    private var context: Context? = null
    private var inflater: LayoutInflater? = null;

    private var data: List<Houses> = Collections.emptyList()

    fun HousesAdapter(context: Context?, listItems: List<Houses?>) {
        Log.i(TAG, "Creating Constructor.")
        this.context = context!!
        inflater = LayoutInflater.from(context)
        this.data = listItems as List<Houses>
    }


    // Inflate the layout when ViewHolder is created.
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        Log.i(TAG, "Inflating the layout.")
        val view: View = inflater!!.inflate(R.layout.simple_list_item_checked, parent, false)
        return HouseViewHolder(view)
    }


    // return total item from List
    override fun getItemCount(): Int {
        return data.size
    }

    // Bind data
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Log.i(TAG, "Binding data.")
        // Get houses position of item in recyclerview to bind data and assign values from list
        val housesViewHolder: HouseViewHolder = holder as HouseViewHolder
        val houses = data[position]
        housesViewHolder.name.setText(houses.name)

        //Set the Recyclerview onClick and pass data to an Intent
        holder.itemView.setOnClickListener {
            Log.i(TAG, "Position: " + position + "House Name: " + houses.name)
           /* val intent = Intent(context, HousesInfoActivity::class.java)
            intent.putExtra("id", houses.id)
            context!!.startActivity(intent)*/
        }
    }

    class HouseViewHolder (view : View) : RecyclerView.ViewHolder(view){
        //Holds the text view
        val name = view.houses_info_tv1
    }
}