package com.keep_updated.wannaapp.ui

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.keep_updated.wannaapp.R
import com.keep_updated.wannaapp.data.model.Data
import com.keep_updated.wannaapp.databinding.OverUnderBetListItemBinding
import com.keep_updated.wannaapp.databinding.SpreadBetListItemBinding
import java.lang.IllegalArgumentException

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

    inner class SpreadBetViewHolder(val item: SpreadBetListItemBinding) :
        RecyclerView.ViewHolder(item.root) {

        fun bind(data: Data) {
            with(data) {
                //Player name
                item.tvBetterName.text = data.better.userName
                item.tvTakerName.text = data.taker.userName
                //Player images
                Glide.with(context).load(better.image).into(item.imBetter)
                Glide.with(context).load(taker.image).into(item.imTaker)
                //Team name
                item.tvBetterTeamName.text = data.awayTeam.name
                item.tvTakerTeamName.text = data.homeTeam.name
                //Team image
                Glide.with(context).load(awayTeam.logo).into(item.imBetterTeam)
                Glide.with(context).load(homeTeam.logo).into(item.imTakerTeam)
                //Time
                event.clock?.let {  item.tvTime.text="2nd $it"}
            }
        }
    }

    inner class OverUnderBetViewHolder(val item: OverUnderBetListItemBinding) :
        RecyclerView.ViewHolder(item.root) {

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
                item.tvBetterName.text = data.better.userName
                item.tvTakerName.text = data.taker.userName
                //Player images
                Glide.with(context).load(better.image).into(item.imBetter)
                Glide.with(context).load(taker.image).into(item.imTaker)
            }

        }
    }

    override fun getItemViewType(position: Int): Int {
        val betType = list[position].betType
        Log.d("AAA", "bind: I am called $betType")


        if (betType.equals(SPREAD)) return SPREAD_BET
        if (betType.equals(OVER_UNDER)) return OVER_UNDER_BET
        return -1
    }
}
