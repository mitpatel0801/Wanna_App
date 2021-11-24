package com.keep_updated.wannaapp.ui

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.keep_updated.wannaapp.data.model.Data
import com.keep_updated.wannaapp.databinding.OverUnderBetListItemBinding
import com.keep_updated.wannaapp.databinding.SpreadBetListItemBinding

class BetAdapter(val context: Context, private var list: List<Data>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val SPREAD_BET = 0
    private val SPREAD = "spread"

    private val OVER_UNDER_BET = 1
    private val OVER_UNDER = "over_under"


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        Log.d("AAA", "I am called")

        return when (viewType) {
            SPREAD_BET -> {

                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = SpreadBetListItemBinding.inflate(layoutInflater, parent, false)
                SpreadBetViewHolder(binding)
            }
            OVER_UNDER_BET -> {

                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = OverUnderBetListItemBinding.inflate(layoutInflater, parent, false)
                OverUnderBetViewHolder(binding)
            }
            else -> throw IllegalArgumentException("Wrong bet Type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            SPREAD_BET -> {
                (holder as SpreadBetViewHolder).bind(list[position])
            }
            OVER_UNDER_BET -> {
                (holder as OverUnderBetViewHolder).bind(list[position])
            }
        }
    }

    override fun getItemCount(): Int {
        Log.d("AAA", "onCreate: ")
        return list.size
    }

    inner class SpreadBetViewHolder(private val item: SpreadBetListItemBinding) :
        RecyclerView.ViewHolder(item.root) {

        @SuppressLint("SetTextI18n")
        fun bind(data: Data) {
            with(data) {

                //Player name
                item.tvBetterName.text = data.better?.userName
                item.tvTakerName.text = data.taker.userName

                //Player images
                Glide.with(context).load(better?.image).into(item.imBetter)
                Glide.with(context).load(taker.image).into(item.imTaker)

                //Team name
                item.tvBetterTeamName.text = data.awayTeam.name
                item.tvTakerTeamName.text = data.homeTeam.name

                //Team image
                Glide.with(context).load(awayTeam.logo).into(item.imBetterTeam)
                Glide.with(context).load(homeTeam.logo).into(item.imTakerTeam)

                //Time
                var time = ""
                if (event.quarter != null) {
                    time += event.quarter
                }
                time += "|"
                if (event.clock != null) {
                    time += event.clock
                }
                item.tvTime.text = time

                //Loss-Win
                item.tvBetterScore.text = "(${data.better?.totalWin}-${data.better?.totalLose})"
                item.tvTakerScore.text = "(${data.taker.totalWin}-${data.taker.totalLose})"

                //spread_points
                item.tvBetterTeamScore.text = data.awayTeam.spreadPoints
                item.tvTakerTeamScore.text = data.homeTeam.spreadPoints

                //Match Score
                item.tvMatchPoint.text =
                    "${data.event.awayScore}-${data.event.homeScore}"

                //Bet Values
                item.tvBetAmount.text = data.betAmount.toString()
                item.tvBetTotalAmount.text = "PAYS:${data.betAmount * data.betValue}"
            }
        }
    }

    inner class OverUnderBetViewHolder(private val item: OverUnderBetListItemBinding) :
        RecyclerView.ViewHolder(item.root) {

        @SuppressLint("SetTextI18n")
        fun bind(data: Data) {
            with(data) {
                event.scheduled?.let { item.tvTime.text = Util.getRequiredDateFormat(it) }

                //Team name
                item.tvAwayTeamName.text = data.awayTeam.name
                item.tvHomeTeamName.text = data.homeTeam.name

                //Team Image
                Glide.with(context).load(awayTeam.logo).into(item.imAwayTeam)
                Glide.with(context).load(homeTeam.logo).into(item.imHomeTeam)

                //Player name
                item.tvBetterName.text = data.better?.userName
                item.tvTakerName.text = data.taker.userName

                //Player images
                Glide.with(context).load(better?.image).into(item.imBetter)
                Glide.with(context).load(taker.image).into(item.imTaker)

                //Loss-Win
                item.tvBetterScore.text = "(${data.better?.totalWin}-${data.better?.totalLose})"
                item.tvTakerScore.text = "(${data.taker.totalWin}-${data.taker.totalLose})"

                //spread_points
                item.tvSpreadPoint.text = data.homeTeam.spreadPoints

                //Bet Values
                item.tvBetAmount.text = data.betAmount.toString()
                item.tvBetTotalAmount.text = "PAYS:${data.betAmount * data.betValue}"
            }

        }
    }

    override fun getItemViewType(position: Int): Int {
        val betType = list[position].betType ?: return SPREAD_BET

        if (betType == SPREAD) return SPREAD_BET
        if ((betType == "moneyline") or (betType == OVER_UNDER)) return OVER_UNDER_BET
        return -1
    }
}
