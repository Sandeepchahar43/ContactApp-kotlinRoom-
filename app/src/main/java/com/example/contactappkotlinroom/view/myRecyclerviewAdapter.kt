package com.example.contactappkotlinroom.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.contactappkotlinroom.R
import com.example.contactappkotlinroom.databinding.ActivityMainBinding
import com.example.contactappkotlinroom.databinding.CardItemBinding
import com.example.contactappkotlinroom.room.EntityClass

class myRecyclerviewAdapter(private val contactsList:List<EntityClass>,
                            private val clicklistener:(EntityClass) ->Unit):RecyclerView.Adapter<myRecyclerviewAdapter.MyViewHolder>() {


   class MyViewHolder(val binding: CardItemBinding):RecyclerView.ViewHolder(binding.root){


           fun bind(contact:EntityClass,clicklistener: (EntityClass) -> Unit){

               binding.contactName.text = contact.contact_Name
               binding.contactEmail.text = contact.contact_Number

               binding.listItemLayout.setOnClickListener(){
                   clicklistener(contact)
               }
           }

       }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding:CardItemBinding = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.card_item,
            parent,false)

        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return contactsList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
       holder.bind(contactsList[position],clicklistener)
    }


}