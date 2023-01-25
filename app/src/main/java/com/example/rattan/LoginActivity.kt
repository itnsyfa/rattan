package com.example.rattan

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.rattan.databinding.ActivityLoginBinding
import com.example.rattan.model.SignInBody
import com.example.rattan.model.userModel
import com.example.rattan.service_api.ApiService
import com.example.rattan.service_api.ServiceGenerator
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LoginActivity : AppCompatActivity() {

    private lateinit var binding : ActivityLoginBinding
    val MyPREFERENCES = "MyPrefs"
    var PREFS_KEY = "prefs"
    var EMAIL_KEY = "email"
    var PWD_KEY = "pwd"
    var email = ""
    var pwd = ""
    var sharedpreferences: SharedPreferences? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE)
        email = sharedpreferences?.getString(EMAIL_KEY, "").toString()

        pwd = sharedpreferences?.getString(PWD_KEY, "").toString()

        binding.textView4.setOnClickListener{
            startActivity(Intent(this, RegActivity::class.java))
        }

        val serviceGenerator = ServiceGenerator.buildService(ApiService::class.java)
        val loginData = SignInBody(binding.TextEmail.text.toString(), binding.TextPassword.text.toString())
        val call = serviceGenerator.postLogin(loginData)


    binding.button.setOnClickListener {
        if (TextUtils.isEmpty(binding.TextEmail.text.toString()) && TextUtils.isEmpty(binding.TextPassword.text.toString())) {

            // if email and pwd edit text is empty we are displaying a toast message
            Toast.makeText(this, "Please Enter Email and Password", Toast.LENGTH_SHORT).show();

        } else {

            call.enqueue(object : Callback<SignInBody> {
                override fun onResponse(
                    call: Call<SignInBody>,
                    response: Response<SignInBody>
                ) {
                    if (response.code() == 200) {
                        Log.e("success", response.toString())
                        val responseBody = response.body()!!
                        val i = Intent(this@LoginActivity, MainActivity::class.java)
                        val editor: SharedPreferences.Editor? = sharedpreferences?.edit()
                        editor?.putString(EMAIL_KEY, binding.TextEmail.text.toString())
                        editor?.putString(PWD_KEY, binding.TextEmail.text.toString())
                        editor?.apply()
                        startActivity(i)

                        finish()

                    } else {
                        Log.e("error", response.toString())

                    }


                }

                override fun onFailure(call: Call<SignInBody>, t: Throwable) {
                    Log.e("error", t.message.toString())
                }

            })
        }
    }

    }
}