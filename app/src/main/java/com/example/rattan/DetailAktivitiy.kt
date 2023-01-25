package com.example.rattan

import android.content.Intent
import android.graphics.Canvas
import android.os.Bundle
import android.view.MotionEvent
import android.view.ScaleGestureDetector
import android.view.ScaleGestureDetector.SimpleOnScaleGestureListener
import androidx.appcompat.app.AppCompatActivity
import com.example.rattan.databinding.ActivityDetailAktivitiyBinding
import com.squareup.picasso.Picasso


class DetailAktivitiy : AppCompatActivity() {

    private lateinit var binding: ActivityDetailAktivitiyBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailAktivitiyBinding.inflate(layoutInflater)


        setContentView(binding.root)
        val intent : Intent = intent
        val nama : String = intent.getStringExtra("nama_barang").toString()
        val katagori : String = intent.getStringExtra("katagori").toString()
        val harga : String = intent.getStringExtra("harga").toString()
        val gambar : String = intent.getStringExtra("gambar").toString()

        binding.textView.text = nama
        binding.textView2.text = harga
        Picasso.with(this).load(gambar).into(binding.imageView5)


        val imageView = binding.imageView5
        var scaleFactor = 1f
        val scaleGestureDetector = ScaleGestureDetector(
            this,
            object : ScaleGestureDetector.SimpleOnScaleGestureListener() {
                override fun onScale(detector: ScaleGestureDetector): Boolean {
                    scaleFactor *= detector.scaleFactor
                    scaleFactor = scaleFactor.coerceIn(0.1f, 5.0f)

                    imageView.scaleX = scaleFactor
                    imageView.scaleY = scaleFactor

                    return super.onScale(detector)
                }
            }
        )

        binding.imageButton.setOnClickListener{
            startActivity(Intent(this, TransactionActivity::class.java).apply {
                putExtra("nama_barang", nama)
                putExtra("katagori", katagori)
                putExtra("harga", harga)
                putExtra("gambar", gambar)
            })
        }

    }

}