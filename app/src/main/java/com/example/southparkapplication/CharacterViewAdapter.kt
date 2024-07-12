package com.example.southparkapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

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



