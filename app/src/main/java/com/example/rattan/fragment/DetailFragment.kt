package com.example.rattan.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.GridView
import android.widget.Toast
import com.example.rattan.R
import com.example.rattan.adapters.GridVterbaruAdapter
import com.example.rattan.databinding.FragmentTerbaruBinding
import com.example.rattan.model.GridViewModal

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [DetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DetailFragment : Fragment() {
    private lateinit var binding: FragmentTerbaruBinding
    lateinit var courseGRV: GridView
    lateinit var courseList: List<GridViewModal>
    var nama : String = ""
    var katagori : String = ""
    var harga : String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_detail, container, false)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        courseList = ArrayList<GridViewModal>()
        courseGRV = view.findViewById(R.id.gridview)

//        courseList = courseList + GridViewModal("Kursi 6","Kursi Modern","Rp. 500.000", R.drawable.logo)
//        courseList = courseList + GridViewModal("Kursi 5","Kursi Modern","Rp. 500.000", R.drawable.logo)
//        courseList = courseList + GridViewModal("Kursi 4","Kursi Modern","Rp. 500.000", R.drawable.logo)
//        courseList = courseList + GridViewModal("Kursi 3","Kursi Modern","Rp. 500.000", R.drawable.logo)
//        courseList = courseList + GridViewModal("Kursi 2","Kursi Modern","Rp. 500.000", R.drawable.logo)
//        courseList = courseList + GridViewModal("Kursi 1","Kursi Modern","Rp. 500.000", R.drawable.logo)

        val courseAdapter = GridVterbaruAdapter(courseList = courseList, requireContext())

        courseGRV.adapter = courseAdapter

        courseGRV.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            // inside on click method we are simply displaying
            // a toast message with course name.
            Toast.makeText(requireContext(), courseList[position].produkNama + " selected",
                Toast.LENGTH_SHORT
            ).show()
        }

    }
    public fun openDetail(nama : String, katagori : String, harga : String){


    }


}