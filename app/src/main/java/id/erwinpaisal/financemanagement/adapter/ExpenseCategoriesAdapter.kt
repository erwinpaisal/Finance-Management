package id.erwinpaisal.financemanagement.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import id.erwinpaisal.financemanagement.R
import id.erwinpaisal.financemanagement.helper.ConvertKurs
import id.erwinpaisal.financemanagement.model.DataExpenseByCategories

class ExpenseCategoriesAdapter(val listExpense: ArrayList<DataExpenseByCategories>?): RecyclerView.Adapter<ExpenseCategoriesAdapter.MyViewHolder>() {

    inner class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val txtCategoryExpense = itemView.findViewById<TextView>(R.id.expense_nama_kategori)
        val txtNominalExpense = itemView.findViewById<TextView>(R.id.expense_nominal_ket)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ExpenseCategoriesAdapter.MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_expense_by_categories, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
       return listExpense?.size ?: 0
    }

    override fun onBindViewHolder(holder: ExpenseCategoriesAdapter.MyViewHolder, position: Int) {
        val data = listExpense?.get(position)
        holder.txtCategoryExpense.text = data?.namaKategori
        holder.txtNominalExpense.text = ConvertKurs.formatRupiah(data?.totalExpense?.toInt() ?: 0)
    }

}