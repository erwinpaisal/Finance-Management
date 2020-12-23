package id.erwinpaisal.financemanagement

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import id.erwinpaisal.financemanagement.model.DataKategori
import id.erwinpaisal.financemanagement.model.ResponseAction
import id.erwinpaisal.financemanagement.networking.ConfigNetwork
import kotlinx.android.synthetic.main.activity_form_category.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FormCategoryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form_category)

        val dataItem = intent.getParcelableExtra<DataKategori>("data_kategori")

        val txtNameCategory = findViewById<TextView>(R.id.categories_edt_name)
        val radioJenis = findViewById<RadioGroup>(R.id.radio_group_categories)
        var jenisKategori: String = "none"

        if (dataItem != null){
            categories_btn_simpan.setText(getString(R.string.perbarui))
            txtNameCategory.text = dataItem.namaKategori
            if (dataItem.jenis == "income"){
                (radioJenis.getChildAt(0) as RadioButton).isChecked = true
            }else if (dataItem.jenis == "expense"){
                (radioJenis.getChildAt(1) as RadioButton).isChecked = true
            }

            radioJenis.setOnCheckedChangeListener { radioGroup, id ->
                val radioItem = findViewById<RadioButton>(id)
                jenisKategori = radioItem.text.toString().toLowerCase()
            }
        }

        radioJenis.setOnCheckedChangeListener { radioGroup, id ->
            val radioItem = findViewById<RadioButton>(id)
            jenisKategori = radioItem.text.toString().toLowerCase()
        }

        if (categories_btn_simpan.text == getString(R.string.save)){
            categories_btn_simpan.setOnClickListener {
                val categoryName = txtNameCategory.text.toString()
                addToKategori(categoryName, jenisKategori)
            }
        } else if (categories_btn_simpan.text == getString(R.string.perbarui)){
            categories_btn_simpan.setOnClickListener {
                val idKategori = dataItem?.idKategori
                val categoryName = txtNameCategory.text.toString()
                if (jenisKategori == "none"){
                    jenisKategori = dataItem?.jenis!!
                }
                updateToKategori(idKategori, categoryName, jenisKategori)
            }
        }
    }

    private fun updateToKategori(idKategori: String?, categoryName: String, jenisKategori: String) {
        val update = ConfigNetwork.categorySercice().updatePengunjung(idKategori, categoryName, jenisKategori)
        update.enqueue(object : Callback<ResponseAction>{
            override fun onFailure(call: Call<ResponseAction>, t: Throwable) {
                Toast.makeText(this@FormCategoryActivity, "${t.message}", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(
                call: Call<ResponseAction>,
                response: Response<ResponseAction>
            ) {
                if (response.isSuccessful){
                    val message = response.body()?.message
                    Toast.makeText(this@FormCategoryActivity, "$message", Toast.LENGTH_SHORT).show()
                    finish()
                }
            }

        })
    }

    private fun addToKategori(nameCategory: String, jenisKategori: String){
        val addToTable = ConfigNetwork.categorySercice().tambahKategori(nameCategory, jenisKategori)
        addToTable.enqueue(object : Callback<ResponseAction>{
            override fun onFailure(call: Call<ResponseAction>, t: Throwable) {
                Toast.makeText(this@FormCategoryActivity, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(
                call: Call<ResponseAction>,
                response: Response<ResponseAction>
            ) {
               if (response.isSuccessful){
                   val message = response.body()?.message
                   Toast.makeText(this@FormCategoryActivity, "$message", Toast.LENGTH_SHORT).show()
                   finish()
               }
            }

        })
    }
}