package com.example.TokenSell

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class SaleAdapter(private val saleList: ArrayList<Sale>) :
    RecyclerView.Adapter<SaleAdapter.ModelViewHolder>() {

    //View'da neler var onları tanımlıyoruz.
    class ModelViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val saleStatus: TextView = view.findViewById(R.id.textViewStatus)
        val saleDate: TextView = view.findViewById(R.id.textViewDate)
        val saleInvNo: TextView = view.findViewById(R.id.textViewInvNo)
        val salePaymentType: TextView = view.findViewById(R.id.textViewPaymentType)
        val saleAmount: TextView = view.findViewById(R.id.textViewTotalAmount)

        //Burda ise sale'ları viewholder'ımızda tanımladığımız elemanlara bağlıyoruz
        fun bindSales(sale: Sale) {
            saleStatus.text = sale.Status
            saleDate.text = "Date: " + sale.Date
            saleInvNo.text = "Receipt Number: " + sale.invNo
            salePaymentType.text = "Payment Type: " + sale.paymentTypes
            saleAmount.text = "Total: " + sale.total.toString() + " ₺"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ModelViewHolder {
        //Adapter oluştuğunda viewHolder’ı başlatmak için bu metod çağrılır.
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_sale, parent, false)
        return ModelViewHolder(view)
    }

    override fun getItemCount(): Int {
        return saleList.size
    }

    override fun onBindViewHolder(holder: ModelViewHolder, position: Int) {
        //onCreateViewHolder() metodundan dönen veriyi bağlamak için kullanılır.???
        holder.bindSales(saleList[position])

    }
}
