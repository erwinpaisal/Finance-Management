package id.erwinpaisal.financemanagement.networking

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ConfigNetwork {
    companion object {
        private fun getRetrofit(): Retrofit {
            return Retrofit.Builder()
                .baseUrl("http://10.0.2.2/financemanagement/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        fun transaksiService(): TransactionService {
            return getRetrofit().create(TransactionService::class.java)
        }

        fun categorySercice(): CategoryService{
            return getRetrofit().create(CategoryService::class.java)
        }
    }
}