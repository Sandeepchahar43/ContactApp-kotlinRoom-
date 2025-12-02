package com.example.contactappkotlinroom.viewmodel



import androidx.databinding.Bindable
import androidx.databinding.Observable


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.contactappkotlinroom.repository.ContactRepository
import com.example.contactappkotlinroom.room.EntityClass
import kotlinx.coroutines.launch

 class ContactViewModel(private val repository: ContactRepository):ViewModel(),
     Observable {

    val contacts = repository.contacts

     var isUpdateOrDelete = false

    lateinit var contactToUpdateOrDelete:EntityClass

     @Bindable
     val inputName = MutableLiveData<String?>()

     @Bindable
     val inputEmail = MutableLiveData<String?>()

     @Bindable
     var saveOrUpdateButtonText = MutableLiveData<String?>()

     @Bindable
     var clearAllDeleteButtonText = MutableLiveData<String?>()


     /**
      * so init {} block is first execute when we create a  object of this view model and set button text initially this
      */
     init {
          saveOrUpdateButtonText.value ="Save"
          clearAllDeleteButtonText.value ="Clear All"
      }

    /**
     *    now i have to create a function to  call the function of repository and also we can't call a suspend function from
     *    UI so we create a normal function and use a viewModelScope.launch to start a coroutines
      */

      fun insert(contact:EntityClass)= viewModelScope.launch {
          repository.insertContact(contact)
     }


      fun update(contact: EntityClass)=viewModelScope.launch {
        repository.updateContact(contact)

          inputName.value = null
          inputEmail.value = null
          saveOrUpdateButtonText.value ="Save"
          clearAllDeleteButtonText.value ="Clear All"


      }

       fun delete(contact: EntityClass)= viewModelScope.launch {
           repository.deleteContact(contact)
           //here i reset
           inputName.value = null
           inputEmail.value = null
           saveOrUpdateButtonText.value ="Save"
           clearAllDeleteButtonText.value ="Clear All"
       }
       fun clearAll() = viewModelScope.launch {
         repository.deleteAll()
       }

    /**
     *
     * when user normally come it either  insert or clear All so for that case isUpdateOrDelete is false
     * when the user tab item in recyclerView then isUpdateOrDelete become true so here either update that item
     * or delete that item
     */

    fun saveOrUpdate(){
        if(isUpdateOrDelete){   //click on update button
            // make an update
            contactToUpdateOrDelete.contact_Name = inputName.value!!
            contactToUpdateOrDelete.contact_Number = inputEmail.value!!
            update(contactToUpdateOrDelete)
            isUpdateOrDelete = false
        }else{
            //insert
               val name = inputName.value!!
              val email = inputEmail.value!!
              insert(EntityClass(0,name,email))  //intial execute on click save
              inputName.value = null
            inputEmail.value = null
        }
    }

    fun clearAllorDelete(){
        if(isUpdateOrDelete){   // when click on delete button
            delete(contactToUpdateOrDelete)
        }else{
            clearAll()  // initial execute when click on clearAll button
        }
    }

    /**
     * when user click on item in recyclerview  button change to update or delete
     */

    fun initUpdateAndDelete(contact:EntityClass){
         inputName.value = contact.contact_Name!!
          inputEmail.value = contact.contact_Number!!
         isUpdateOrDelete = true
        contactToUpdateOrDelete = contact
        clearAllDeleteButtonText.value ="Delete"
        saveOrUpdateButtonText.value ="Update"
    }

     override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

     }

     override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

     }


 }