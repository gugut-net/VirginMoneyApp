package com.example.myfirstapp.ui.people

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myfirstapp.data.model.people.PeopleModelItemModel
import com.example.virginmoneyapp.R
import com.example.virginmoneyapp.databinding.ItemPeopleBinding

class PeopleAdapter(
    val peopleList: MutableList<PeopleModelItemModel> = mutableListOf(),
    val clickListener: (PeopleModelItemModel) -> Unit
) : RecyclerView.Adapter<PeopleAdapter.ViewHolder>() {

    fun updateAdapterPeople(newPeople: List<PeopleModelItemModel>) {
        peopleList.clear()
        peopleList.addAll(newPeople)
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val view: ItemPeopleBinding) :
        RecyclerView.ViewHolder(view.root) {

        fun setup(peopleModelItemModel: PeopleModelItemModel) {
            with(itemView) {
                Glide.with(context)
                    .load(peopleModelItemModel.avatar)
                    .placeholder(R.drawable.animate_loading)
                    .centerCrop()
                    .into(view.ivUserPic)
            }
            view.tvTitle.text = "${peopleModelItemModel.firstName} ${peopleModelItemModel.lastName}"
            view.tvDesc.text = peopleModelItemModel.jobtitle
            itemView.setOnClickListener { //display ->
                clickListener.invoke(peopleModelItemModel)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        ItemPeopleBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun getItemCount(): Int = peopleList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setup(peopleList[position])
    }
}