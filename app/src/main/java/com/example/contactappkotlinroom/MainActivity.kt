package com.example.contactappkotlinroom

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.contactappkotlinroom.databinding.ActivityMainBinding
import com.example.contactappkotlinroom.repository.ContactRepository
import com.example.contactappkotlinroom.room.ContactDatabase
import com.example.contactappkotlinroom.room.EntityClass
import com.example.contactappkotlinroom.view.myRecyclerviewAdapter
import com.example.contactappkotlinroom.viewmodel.ContactViewModel
import com.example.contactappkotlinroom.viewmodel.ViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: ContactViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
         binding = DataBindingUtil.setContentView(this,R.layout.activity_main)


        //initialised the Room database
         val doa = ContactDatabase.getInstance(applicationContext)?.doa
        val repository = ContactRepository(doa!!)
        val factory = ViewModelFactory(repository)

        // for the view model when view model class is empty constructor

//        viewModel = ViewModelProvider(this).get(
//            ContactViewModel::class.java
//        )

        viewModel = ViewModelProvider(this,factory).get(ContactViewModel::class.java)


        binding.contactViewModel = viewModel

        binding.lifecycleOwner = this

        initRecyclerView()
    }

    private fun initRecyclerView() {
        //tells RecyclerView how to display items
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        DisplayUserList()

    }

    private fun DisplayUserList() {
        viewModel.contacts.observe(this,{
            binding.recyclerView.adapter =myRecyclerviewAdapter(it,{selectedItem:EntityClass -> listItemclicked(selectedItem)})


        })
    }

    private fun listItemclicked(selectedItem: EntityClass) {

        Toast.makeText(this, "Selected name is ${selectedItem.contact_Name}", Toast.LENGTH_SHORT).show()

        viewModel.initUpdateAndDelete(selectedItem)

    }
}