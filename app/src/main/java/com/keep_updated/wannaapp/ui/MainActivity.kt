package com.keep_updated.wannaapp.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.keep_updated.wannaapp.data.model.Bets
import com.keep_updated.wannaapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var betAdapter: BetAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        betAdapter = BetAdapter(this, getData().data)
        rvSetup()

    }

    //Setting Up RecycleView.
    private fun rvSetup() {
        binding.rvBet.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            setHasFixedSize(true)
            adapter = betAdapter
        }
    }

    //Fetching Data from the file.
    private fun getData(): Bets {
        var json = ""
        try {
            val inputStream = assets.open("data.json")
            json = inputStream.bufferedReader().use { it.readText() }
        } catch (e: Exception) {
            Toast.makeText(this, "Reading JSON File Error!", Toast.LENGTH_SHORT)
                .show()
        }
        val gson = Gson()
        return gson.fromJson(json, Bets::class.java)
    }
}