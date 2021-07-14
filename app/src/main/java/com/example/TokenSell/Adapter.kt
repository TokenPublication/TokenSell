package com.example.TokenSell

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

//Bu sınıfı oluşturmamızın nedeni yaptığımız tasarım ile data model arasında bir veri akışı sağlamak.
//Kısacası veriyi .xml'deki recyclerview'a adapte etmek

class Adapter(private val itemList: MutableList<ItemModel>, val checkoutListener: (Double) -> Unit) : RecyclerView.Adapter<Adapter.ModelViewHolder>() {

    private var totalCheckoutAmount: Double = 0.0

    class ModelViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val itemImage: ImageView = view.findViewById(R.id.imageViewImage)
        val itemTitle: TextView = view.findViewById(R.id.title)
        val itemDescription: TextView = view.findViewById(R.id.description)
        val itemQuantity : TextView = view.findViewById(R.id.quantity)
        val itemPrice: TextView = view.findViewById(R.id.textViewPrice)
        val buttonPlus = view.findViewById<Button>(R.id.plusButton)
        val buttonMinus = view.findViewById<Button>(R.id.minusButton)

        fun bindItems(item: ItemModel) {
            itemImage.setImageResource(item.image)
            itemTitle.setText(item.title)
            itemDescription.setText(item.description)
            itemQuantity.setText(item.quantity.toInt().toString())
            itemPrice.setText(item.itemPrice.toString())
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ModelViewHolder {
        //Adapter oluştuğunda viewHolder’ı başlatmak için bu metod çağrılır.
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row, parent, false)
        return ModelViewHolder(view)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: ModelViewHolder, position: Int) {
        //onCreateViewHolder() metodundan dönen veriyi bağlamak için kullanılır.???
        holder.bindItems(itemList[position])
        holder.buttonPlus.setOnClickListener {
            println("+'ya tıkladık.")
            itemList.get(position).quantity = itemList.get(position).quantity + 1
            holder.itemQuantity.setText(itemList.get(position).quantity.toInt().toString())
            totalCheckoutAmount = totalCheckoutAmount + itemList.get(position).itemPrice
            checkoutListener(totalCheckoutAmount)
        }
        holder.buttonMinus.setOnClickListener {
            println("-'ye tıkladık.")
            if(itemList.get(position).quantity > 0)
            {
            itemList.get(position).quantity = itemList.get(position).quantity - 1
            holder.itemQuantity.setText(itemList.get(position).quantity.toInt().toString())
            totalCheckoutAmount = totalCheckoutAmount - itemList.get(position).itemPrice
            checkoutListener(totalCheckoutAmount)
            }
        }

    }
}