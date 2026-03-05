Contact App (Kotlin + Room + MVVM)
📱 Project Overview

This is a simple Android Contact App built using Kotlin, Room Database, and MVVM Architecture.
The app allows users to store and manage contacts with a Name and Email Address.

Contacts are displayed in a CardView list, and users can update or delete a contact by clicking on the card.

🚀 Features

Add a new contact (Name & Email)

Store contacts locally using Room Database

Display contacts in RecyclerView with CardView

Update existing contact

Delete contact

MVVM architecture for clean and maintainable code

🏗 Architecture

The app follows the MVVM (Model–View–ViewModel) architecture.

Components used:

Model

Room Entity

DAO

Repository

View

Activity

RecyclerView

CardView UI

ViewModel

Handles UI-related data

Communicates between Repository and UI

🛠 Tech Stack

Kotlin

MVVM Architecture

Room Database

RecyclerView

CardView

LiveData

ViewModel

📂 App Workflow

User enters Name and Email.

Contact is saved in the Room Database.

All contacts appear in a RecyclerView using CardView.

When a user clicks on a CardView, they can:

Update the contact

Delete the contact
