package com.arkam.yelp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.arkam.yelp.model.ReservationClass
import com.arkam.yelp.R
import com.arkam.yelp.util.Constant
import com.arkam.yelp.util.Preference
import kotlinx.android.synthetic.main.item_reservation.view.*
import java.util.ArrayList

private const val TAG = "RestaurantsAdapter"
class ReservationAdapter(
    val context: Context,
    private val reservations: MutableList<ReservationClass>
)
    : RecyclerView.Adapter<ReservationAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_reservation, parent, false))
    }

    override fun getItemCount() = reservations.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(reservations[position])
    }
    fun removeAt(position: Int) {
        reservations.removeAt(position)

        Preference.getInstance().putReservations(context, Constant.RESERVATIONS,
            reservations as ArrayList<ReservationClass>?
        )
        notifyItemRemoved(position)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(reservation: ReservationClass) {
            itemView.txDate.text = reservation.date
            itemView.txName.text = reservation.name
            itemView.txTime.text = reservation.time
            itemView.txEmail.text = reservation.email
            itemView.txNum.text = (position +1).toString()

        }
    }
}
