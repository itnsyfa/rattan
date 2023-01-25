package com.example.rattan.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import android.content.Context
import android.widget.BaseAdapter
import com.example.rattan.R
import com.example.rattan.model.GridViewModal
import com.example.rattan.databinding.GridviewItemBinding
import com.example.rattan.fragment.TerbaruFragment
import com.squareup.picasso.Picasso



internal class GridVterbaruAdapter(
    private val courseList: List<GridViewModal>,
    private val context: Context
) :
    BaseAdapter() {
    // in base adapter class we are creating variables
    // for layout inflater, course image view and course text view.
    private var layoutInflater: LayoutInflater? = null
    private lateinit var courseNama: TextView
    private lateinit var courseKatagori: TextView
    private lateinit var courseHarga: TextView
    private lateinit var courseGambar: ImageView

    // below method is use to return the count of course list
    override fun getCount(): Int {
        return courseList.size
    }

    // below function is use to return the item of grid view.
    override fun getItem(position: Int): Any? {
        return null
    }

    // below function is use to return item id of grid view.
    override fun getItemId(position: Int): Long {
        return 0
    }

    // in below function we are getting individual item of grid view.
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
        var convertView = convertView
        // on blow line we are checking if layout inflater
        // is null, if it is null we are initializing it.
        if (layoutInflater == null) {
            layoutInflater =
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        }
        // on the below line we are checking if convert view is null.
        // If it is null we are initializing it.
        if (convertView == null) {
            // on below line we are passing the layout file
            // which we have to inflate for each item of grid view.
            convertView = layoutInflater!!.inflate(R.layout.gridview_item, null)
        }
        // on below line we are initializing our course image view
        // and course text view with their ids.
        courseGambar = convertView!!.findViewById(R.id.idGambarProduk)
        courseNama = convertView!!.findViewById(R.id.idNamaProduk)
        courseKatagori = convertView!!.findViewById(R.id.idKatagoriProduk)
        courseHarga = convertView!!.findViewById(R.id.idHargaProduk)
        // on below line we are setting image for our course image view.
        Picasso.with(context).load(courseList.get(position).produkImg).into(courseGambar)
        // courseGambar.setImageResource(courseList.get(position).produkImg)
        // on below line we are setting text in our course text view.
        courseNama.setText(courseList.get(position).produkNama)
        courseKatagori.setText(courseList.get(position).produkKatagori)
        courseHarga.setText(courseList.get(position).produkHarga)
        // at last we are returning our convert view.
        return convertView
    }

    }