package com.example.app4a.presentation.main.ui.activityList

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.app4a.R
import com.example.app4a.domain.entities.Currency
import com.example.app4a.presentation.main.ui.activityList.fragment.ItemDetailFragment


class MyAdapter     // Provide a suitable constructor (depends on the kind of dataset)
    (private val values: MutableList<Currency>) : RecyclerView.Adapter<MyAdapter.ViewHolder>() {
    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    inner class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        // each data item is just a string in this case
        var txtHeader: TextView
        var txtFooter: TextView
        var txtscore : TextView
        var ImgIcon : ImageView
        var layout: View

        init {
            layout = v
            txtHeader = v.findViewById(R.id.firstLine)
            txtFooter = v.findViewById(R.id.country_edit)
            txtscore = v.findViewById(R.id.trust_score_edit)
            ImgIcon = v.findViewById(R.id.icon)
        }
    }

    fun add(position: Int, item: Currency) {
        values.add(position, item)
        notifyItemInserted(position)
    }

    fun remove(position: Int) {
        values.removeAt(position)
        notifyItemRemoved(position)
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        // create a new view
        val inflater = LayoutInflater.from(
            parent.context
        )
        val v: View = inflater.inflate(R.layout.unit_list_layout, parent, false)
        // set the view's size, margins, paddings and layout parameters
        return ViewHolder(v)
    }

    // Replace the contents of a view (invoked by the layout manager)
    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        val name = values[position]
        holder.txtHeader.text = name.name
        holder.itemView.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val bundle = Bundle()
                val activity = v!!.context as AppCompatActivity
                val itemDetail = ItemDetailFragment()
                bundle.putString("desc", name.toString())
                activity.supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.app_list_activity, itemDetail)
                    .addToBackStack(null)
                    .commit()
                itemDetail.arguments=bundle
            }
        })
        holder.txtFooter.text = name.country
        holder.txtscore.text = name.trust_score.toString()
        Glide.with(holder.ImgIcon.context).load(name.image).into(holder.ImgIcon);
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount(): Int {
        return values.size
    }
}