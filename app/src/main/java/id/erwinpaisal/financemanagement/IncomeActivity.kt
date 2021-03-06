package id.erwinpaisal.financemanagement

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import id.erwinpaisal.financemanagement.adapter.IncomeCategoriesAdapter
import id.erwinpaisal.financemanagement.model.ResponseIncomeByCategories
import id.erwinpaisal.financemanagement.networking.ConfigNetwork
import kotlinx.android.synthetic.main.activity_income.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class IncomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_income)

        generateIncomeByCategories()

    }

    private fun generateIncomeByCategories() {
        val responseTransaksi = ConfigNetwork.transaksiService().getIncomeByCategories()
        responseTransaksi.enqueue(object : Callback<ResponseIncomeByCategories>{
            override fun onFailure(call: Call<ResponseIncomeByCategories>, t: Throwable) {
                Toast.makeText(this@IncomeActivity, "Error : ${t.message}", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(
                call: Call<ResponseIncomeByCategories>,
                response: Response<ResponseIncomeByCategories>
            ) {
               if (response.isSuccessful){
                   val data = response.body()?.data
                   val incomeAdapter = IncomeCategoriesAdapter(data)
                   rv_income_by_categories.adapter = incomeAdapter
               }
            }

        })
    }
}