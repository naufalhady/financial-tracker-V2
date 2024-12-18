package com.example.financialtracker.views

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.financialtracker.R

class Login : AppCompatActivity() {
    private lateinit var btn_login:Button
    private lateinit var btn_register:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        btn_login=findViewById(R.id.btn_login)
        btn_login.setOnClickListener{
            val intent=Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        btn_register=findViewById(R.id.btn_register)
        btn_register.setOnClickListener{
            val intent=Intent(this, Register::class.java)
            startActivity(intent)
            }
    }
}