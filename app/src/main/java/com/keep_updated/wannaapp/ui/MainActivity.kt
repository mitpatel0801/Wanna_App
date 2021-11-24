package com.keep_updated.wannaapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.keep_updated.wannaapp.R
import com.keep_updated.wannaapp.data.model.Bets
import com.keep_updated.wannaapp.databinding.ActivityMainBinding
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var betAdapter: BetAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        betAdapter = BetAdapter(this, getData().data)
        binding.rvBet.layoutManager = LinearLayoutManager(this)
        binding.rvBet.adapter = betAdapter


    }


    private fun getData(): Bets {
        var json = ""
        try {
            val inputStream = assets.open("data.json")
            json = inputStream.bufferedReader().use { it.readText() }
        } catch (e: Exception) {

        }
        val gson = Gson()
        return gson.fromJson(json, Bets::class.java)
    }
}