package uz.javokhirdev.geometrycalculator.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import uz.javokhirdev.geometrycalculator.helpers.ShapeResponse
import uz.javokhirdev.geometrycalculator.databinding.ItemShapeBinding

class HomeAdapter(private val listener: HomeListener) :
    ListAdapter<ShapeResponse, HomeAdapter.ViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        ItemShapeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(getItem(position))

    inner class ViewHolder(private val binding: ItemShapeBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: ShapeResponse) {
            with(binding) {
                item.icon?.let { imageShape.setImageResource(it) }

                root.setOnClickListener { listener.onShapeClick(item) }
            }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<ShapeResponse>() {
        override fun areItemsTheSame(
            oldItem: ShapeResponse,
            newItem: ShapeResponse
        ): Boolean = oldItem.id == newItem.id

        override fun areContentsTheSame(
            oldItem: ShapeResponse,
            newItem: ShapeResponse
        ): Boolean = oldItem == newItem
    }

    interface HomeListener {
        fun onShapeClick(item: ShapeResponse)
    }
}