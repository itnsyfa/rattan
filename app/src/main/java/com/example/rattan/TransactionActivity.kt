package com.example.rattan

import android.Manifest
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.app.ActivityCompat

import androidx.core.app.NotificationCompat
import com.example.rattan.databinding.ActivityTransactionBinding
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.squareup.picasso.Picasso
import okhttp3.internal.notify

class TransactionActivity : AppCompatActivity(), OnMapReadyCallback  {
    private lateinit var binding : ActivityTransactionBinding
    lateinit var notificationChannel: NotificationChannel
    lateinit var notificationManager: NotificationManager

    lateinit var builder : Notification.Builder
    private val channelId = "com.example.rattan"
    private val description = "Test Notification"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTransactionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val value : Int = 0
        val REQUEST_CODE = 100
        val mapView = binding.mapView

        val intent : Intent = intent
        val nama : String = intent.getStringExtra("nama_barang").toString()
        val katagori : String = intent.getStringExtra("katagori").toString()
        val harga : String = intent.getStringExtra("harga").toString()
        val gambar : String = intent.getStringExtra("gambar").toString()


        mapView.getMapAsync(this)
        binding.textBarang.text = nama
        binding.textKatagori.text = katagori
        binding.textHarga.text = harga
        Picasso.with(this).load(gambar).into(binding.gambarBarang)

        binding.BtnAdd.setOnClickListener{
                val value : Int =+ 1
                binding.textQuantity.text = (value).toString()

        }
        binding.BtnRem.setOnClickListener{
            if (binding.textQuantity.text == "0"){

            }else{
                binding.textQuantity.text = (value-1).toString()

            }
        }

        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        binding.button5.setOnClickListener{

            val intent2 = Intent(this,MainActivity::class.java)
            val pendingIntent = PendingIntent.getActivity(this,0, intent2, PendingIntent.FLAG_MUTABLE)

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                notificationChannel =
                    NotificationChannel(channelId, description, NotificationManager.IMPORTANCE_HIGH)
                notificationChannel.enableLights(true)
                notificationChannel.lightColor = Color.GREEN
                notificationChannel.enableVibration(false)
                notificationManager.createNotificationChannel(notificationChannel)

                builder = Notification.Builder(this, channelId)
                    .setContentTitle("Your Product Has Been Ordered")
                    .setContentText("We will contact you shortly")
                    .setSmallIcon(R.drawable.logo).setLargeIcon(BitmapFactory.decodeResource(this.resources, R.drawable.logo))
                    .setContentIntent(pendingIntent)
            }else{
                builder = Notification.Builder(this)
                    .setContentTitle("Your Product Has Been Ordered")
                    .setContentText("We will contact you shortly")
                    .setSmallIcon(R.drawable.logo).setLargeIcon(BitmapFactory.decodeResource(this.resources, R.drawable.logo))
                    .setContentIntent(pendingIntent)
            }
            startActivity(Intent(this, MainActivity::class.java))
            notificationManager.notify(1234, builder.build())

        }

        if (binding.radioButton2.isActivated){
            binding.button3.visibility = View.VISIBLE
        }else{
            binding.button3.visibility = View.GONE
        }

        binding.button3.setOnClickListener{
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, REQUEST_CODE)
        }


    }

    override fun onMapReady(p0: GoogleMap) {
        p0.addMarker(
            MarkerOptions()
                .position(LatLng(0.0, 0.0))
                .title("Marker")
        )
        p0.getUiSettings().setMyLocationButtonEnabled(false);
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return
        }
        p0.setMyLocationEnabled(true);
    }
}