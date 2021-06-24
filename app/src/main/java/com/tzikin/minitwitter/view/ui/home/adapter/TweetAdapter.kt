package com.tzikin.minitwitter.view.ui.home.adapter

import android.app.Activity
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tzikin.minitwitter.R
import com.tzikin.minitwitter.databinding.TweetLayoutBinding
import com.tzikin.minitwitter.view.common.Constants
import com.tzikin.minitwitter.view.common.SharedPreferenceManager
import com.tzikin.minitwitter.view.viewmodel.repository.model.entity.Tweet


class TweetAdapter(
    val activity: Activity,
    val tweetList: MutableList<Tweet>): RecyclerView.Adapter<TweetAdapter.ViewHolder>() {

    private val username: String? = SharedPreferenceManager.getSomeStringValue(Constants.PREF_USERNAME)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = TweetLayoutBinding.inflate(LayoutInflater.from(activity), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(tweetList[position], activity, username)
    }

    override fun getItemCount(): Int {
        return tweetList.size
    }

    class ViewHolder(val binding: TweetLayoutBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(tweet: Tweet, activity: Activity, username: String?) {

            binding.txtUsername.text = tweet.user?.username
            binding.txtMessage.text = tweet.mensaje
            binding.txtLike.text = tweet.likes?.size.toString()

            if(tweet.user?.photoUrl?.isEmpty()!!){
                Glide.with(activity).load(tweet.user?.photoUrl).into(binding.imgProfile)
            }

            tweet.likes?.forEach {
                if(it.username == username){
                    Glide.with(activity).load(R.drawable.ic_with_like_black).into(binding.imgLike)
                    binding.txtLike.setTextColor(activity.getColor(R.color.pink))
                    binding.txtLike.setTypeface(null, Typeface.BOLD)
                }
            }
        }

    }
}