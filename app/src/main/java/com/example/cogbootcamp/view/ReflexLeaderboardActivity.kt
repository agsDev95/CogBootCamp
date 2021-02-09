package com.example.cogbootcamp.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cogbootcamp.adapter.LeaderboardAdapter
import com.example.cogbootcamp.databinding.ActivityReflexLeaderboardBinding
import kotlin.collections.ArrayList

class ReflexLeaderboardActivity : AppCompatActivity()  {
    private lateinit var binding: ActivityReflexLeaderboardBinding
    private lateinit var leaderboardAdapter: LeaderboardAdapter
    private lateinit var layoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReflexLeaderboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setRecyclerView()
        setOnClickListeners()
    }

    private fun setRecyclerView() {
        layoutManager = LinearLayoutManager(this@ReflexLeaderboardActivity)
        leaderboardAdapter = LeaderboardAdapter(getLeaderboardData())
        binding.leaderboardScores.layoutManager = layoutManager
        binding.leaderboardScores.adapter = leaderboardAdapter
    }

    private fun getLeaderboardData(): ArrayList<Pair<String, Float>> {
        // TODO -> dummy method to return a hard-coded set of leaderboard values (real values would be retrieved via API)
        val currentUserResults: Pair<String, Float> =
            Pair(intent.getStringExtra("USER_INITIALS")!!, intent.getFloatExtra("USER_TIME", 0.000F)!!)

        val currentLeaderboardData: ArrayList<Pair<String, Float>> =
            arrayListOf(
                Pair("ABC", 0.123f),
                Pair("DEF", 0.987f),
                Pair("GHI", 0.234f),
                Pair("JKL", 0.876f),
                Pair("MNO", 0.321f),
//                Pair("PQR", 0.765f),
//                Pair("STU", 0.432f),
//                Pair("VWX", 0.678f),
                Pair("YZZ", 0.567f)
            )
        currentLeaderboardData.add(currentUserResults)

        return concatenateEmptyPlaceholders(sortLeaderboardValues(currentLeaderboardData))
    }

    private fun concatenateEmptyPlaceholders(sortedLeaderboard: ArrayList<Pair<String, Float>>): ArrayList<Pair<String, Float>> {
        if(sortedLeaderboard.size == 10) { return sortedLeaderboard }

        while(sortedLeaderboard.size != 10) {
            sortedLeaderboard.add(Pair("---", -0.000F))
        }

        return sortedLeaderboard
    }

    private fun sortLeaderboardValues(leaderboardValues: ArrayList<Pair<String, Float>>): ArrayList<Pair<String, Float>> {
       return leaderboardValues.sortedWith(compareBy { it.second }).toMutableList() as ArrayList<Pair<String, Float>>
    }

    private fun setOnClickListeners() {
        binding.tryAgainButton.setOnClickListener {
            val intent =  Intent(this@ReflexLeaderboardActivity, ReflexButtonActivity::class.java)
            startActivity(intent)
        }

        binding.quitButton.setOnClickListener {
            val intent =  Intent(this@ReflexLeaderboardActivity, MainActivity::class.java)
            startActivity(intent)
        }
    }
}