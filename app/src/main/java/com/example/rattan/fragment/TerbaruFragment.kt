package com.example.rattan.fragment



import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.GridView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.rattan.DetailAktivitiy
import com.example.rattan.R
import com.example.rattan.adapters.GridVterbaruAdapter
import com.example.rattan.databinding.ActivityMainBinding
import com.example.rattan.databinding.FragmentTerbaruBinding
import com.example.rattan.model.GridViewModal
import com.example.rattan.model.barangModel
import com.example.rattan.service_api.ApiService
import com.example.rattan.service_api.ServiceGenerator
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class TerbaruFragment : Fragment(){

    private lateinit var binding: FragmentTerbaruBinding
    private lateinit var binding2 : ActivityMainBinding
    lateinit var courseGRV: GridView
    lateinit var courseList: List<GridViewModal>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_terbaru, container, false)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val serviceGenerator = ServiceGenerator.buildService(ApiService::class.java)
        val call = serviceGenerator.getPost()
        courseList = ArrayList<GridViewModal>()
        courseGRV = view.findViewById(R.id.gridview)

        call.enqueue(object : Callback<List<barangModel>> {


            override fun onResponse(
                call: Call<List<barangModel>>,
                response: Response<List<barangModel>>
            ) {
                if (response.isSuccessful){
                    Log.e("success", response.toString())
                }
                val responseBody = response.body()!!

                for (myData in responseBody){
                    Log.e("id barang", myData.barang_id.toString())
                    Log.e("nama", myData.nama.toString())
                    Log.e("katagori", myData.katagori.toString())
                    Log.e("data", "http://10.0.2.2:8000/storage/posts/"+myData.gambar.toString())
                    courseList = courseList + GridViewModal(myData.barang_id.toString(),myData.nama.toString(),myData.katagori.toString(), myData.harga.toString(),myData.stok.toString(), "http://10.0.2.2:8000/storage/posts/"+myData.gambar.toString())

                }


                val courseAdapter = GridVterbaruAdapter(courseList = courseList, requireContext())

                courseGRV.adapter = courseAdapter

                courseGRV.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
                    // inside on click method we are simply displaying
                    // a toast message with course name.
                    activity?.let{
                        val intent = Intent (it, DetailAktivitiy::class.java)
                        intent.putExtra("barang_id", courseList[position].produkid)
                        intent.putExtra("nama_barang", courseList[position].produkNama)
                        intent.putExtra("katagori", courseList[position].produkKatagori)
                        intent.putExtra("harga", courseList[position].produkHarga)
                        intent.putExtra("stok", courseList[position].produkStok)
                        intent.putExtra("gambar", courseList[position].produkImg)
                        it.startActivity(intent)
                    }
                    Toast.makeText(requireContext(), courseList[position].produkNama + " selected",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            override fun onFailure(call: Call<List<barangModel>>, t: Throwable) {
                Log.e("error", t.message.toString())
            }


        })



    }






}





