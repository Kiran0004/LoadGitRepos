package com.sample.adapter


import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.sample.loadgitrepos.R


import com.sample.model.RepositoryDetailModel
import com.squareup.picasso.Picasso

import java.text.DecimalFormat

/**
 * Created by Kiran on 2019-11-16.
 */

class RepositoriesListAdapter(private val repositories: List<RepositoryDetailModel>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

   override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.list_item_repository, parent, false)
        return RepositoryViewHolder(view)
    }

    override fun getItemCount(): Int {

        return repositories.size
    }

    override  fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val repositoryViewHolder = holder as RepositoryViewHolder
        repositoryViewHolder.name.text = (repositories[position]).mName
        repositoryViewHolder.description.text = (repositories[position]).mDescription
        var starsNumber = repositories[position].mStarsNumber
        if (starsNumber > 1000) starsNumber /= 1000f
        repositories[position].mStarsNumber = starsNumber
        repositoryViewHolder.stars.text = (DecimalFormat("##.#").format(starsNumber.toDouble()) + "k")
        repositoryViewHolder.username.text = (repositories[position].owner?.login)
        Picasso.with(holder.itemView.context).load(repositories[position].owner?.avatar_url).resize(210, 200).centerCrop().onlyScaleDown()
                .into(repositoryViewHolder.avatar)

    }
    class RepositoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
         var name: TextView
         var description: TextView
         var username: TextView
         var stars: TextView
         var avatar: ImageView
        init {
            name = itemView.findViewById(R.id.name)
            description = itemView.findViewById(R.id.description)
            username = itemView.findViewById(R.id.username)
            avatar = itemView.findViewById(R.id.avatar)
            stars = itemView.findViewById(R.id.stars)
        }
    }

}
