package com.example.southparkapplication

import android.content.ContentValues.TAG
import android.content.Context
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target

class CharacterViewAdapter(private val characterList: ArrayList<CharactersData>, private val context: Context)
    : RecyclerView.Adapter<CharacterViewAdapter.CharacterViewHolder>() {

    class CharacterViewHolder (itemView: View): RecyclerView.ViewHolder(itemView){
        val characterAvatar : ImageView = itemView.findViewById(R.id.character_avatar)
        var characterFamily : TextView = itemView.findViewById(R.id.character_family)
        val characterName : TextView = itemView.findViewById(R.id.character_name)
        val characterSex : TextView = itemView.findViewById(R.id.character_sex)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val character = characterList[position]
        holder.characterFamily.text = character.family
        holder.characterName.text = character.name
        holder.characterSex.text = character.sex

        Glide.with(context)
            .load(character.image)
            .listener(object : RequestListener<Drawable> {

            override fun onLoadFailed(
                e: GlideException?,
                type: Any?,
                target: Target<Drawable>,
                isFirstResource: Boolean
            ): Boolean {
                Log.e(TAG, "Load failed", e)
                e?.logRootCauses(TAG)
                return false
            }

                override fun onResourceReady(
                    resource: Drawable,
                    model: Any,
                    target: Target<Drawable>?,
                    dataSource: DataSource,
                    isFirstResource: Boolean,
                ): Boolean {
                    Log.d(TAG, "OnResourceReady")
                    return false
                }
            })
            .circleCrop()
            .into(holder.characterAvatar)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment_item, parent, false)
        return CharacterViewHolder(view)
    }

    override fun getItemCount(): Int {
        return characterList.size
    }
}



