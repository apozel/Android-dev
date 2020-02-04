package fr.isen.chaillan.androidtoolbox.Models


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

import fr.isen.chaillan.androidtoolbox.R

import kotlinx.android.synthetic.main.recycler_view_user_cell.view.*

class UserModelAdaptater(val contacts: ArrayList<UserModel>): RecyclerView.Adapter<UserModelAdaptater.ContactViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_view_contact_cell, parent, false)
        return ContactViewHolder(view)
    }

    override fun getItemCount(): Int {
        return contacts.count()
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        val contact = contacts[position]
        holder.bind(contact)
        holder.picasso(contact)
    }

    class ContactViewHolder(val view: View): RecyclerView.ViewHolder(view) {
        fun bind(contact: UserModel) {
            view.presentationText.text = contact.name?.first
        }

        fun picasso(contact: UserModel){
            Picasso.get().load(contact.picture?.thumbnail).resize(200, 200).into(view.pictureUser)
        }
    }
}