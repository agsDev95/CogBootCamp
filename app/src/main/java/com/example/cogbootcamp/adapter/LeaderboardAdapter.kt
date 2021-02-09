package com.example.cogbootcamp.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cogbootcamp.R

class LeaderboardAdapter(private val dataSet: ArrayList<Pair<String, Float>>):
    RecyclerView.Adapter<LeaderboardAdapter.ViewHolder>() {

    private var leaderboardCounter: Int = 0

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        var leaderboardPlacement: TextView = view.findViewById(R.id.leaderboard_placement)
        var participantInitials: TextView = view.findViewById(R.id.participant_initials)
        var participantTime: TextView = view.findViewById(R.id.particpant_time)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.layout_leaderboard_row, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if(leaderboardCounter == 10) { return }

        holder.leaderboardPlacement.text = validateLeaderboardPlacementString()
        holder.participantInitials.text = dataSet[position].first
        val timeString = validateParticipantTimeString(dataSet[position].second)
        holder.participantTime.text = timeString

        Log.e("HERE", "placement value = ${holder.leaderboardPlacement.text}")
    }

    override fun getItemCount() = dataSet.size

    private fun validateLeaderboardPlacementString(): String {
        return if(leaderboardCounter != 10) {
            " " + (++leaderboardCounter).toString()
        }
        else  {
            (++leaderboardCounter).toString()
        }
    }

    private fun validateParticipantTimeString(time: Float): String {
        return if(time == -0.000F) {
            "---"
        }
        else {
            time.toString() + "s"
        }
    }
}