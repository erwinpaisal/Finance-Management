package id.erwinpaisal.financemanagement.networking

import id.erwinpaisal.financemanagement.model.ResponseAction
import id.erwinpaisal.financemanagement.model.ResponseExpenseByCategories
import id.erwinpaisal.financemanagement.model.ResponseIncomeByCategories
import id.erwinpaisal.financemanagement.model.ResponseTransaksi
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface TransactionService {

    // Get transaksi masuk (income) by categories
    @GET("getIncomeByCategories.php")
    fun getIncomeByCategories(): Call<ResponseIncomeByCategories>

    // Get transaksi keluar (expense) by cateories
    @GET("getExpensesByCategory.php")
    fun getExpenseByCategories(): Call<ResponseExpenseByCategories>

    // Get all transaksi
    @GET("getTransaksi.php")
    fun getAllTransaksi(): Call<ResponseTransaksi>

    // Add transaksi
    @FormUrlEncoded
    @POST("tambahTransaksi.php")
    fun addTransaksi(
        @Field("id_kategori") idKategori: String?,
        @Field("keterangan_transaksi") keterangan: String,
        @Field("nominal_transaksi") nominal: String,
        @Field("tanggal_transaksi") tanggal: String
    ): Call<ResponseAction>

    // update transaksi
    @FormUrlEncoded
    @POST("updateTransaksi.php")
    fun updateTransaksi(
        @Field("id_transaksi") idTransaksi: String?,
        @Field("id_kategori") idKategori: String?,
        @Field("keterangan_transaksi") keterangan: String,
        @Field("nominal_transaksi") nominal: String,
        @Field("tanggal_transaksi") tanggal: String
    ):Call<ResponseAction>

    // delete transaksi
    @FormUrlEncoded
    @POST("hapusTransaksi.php")
    fun deleteTransaksi(
        @Field("id_transaksi") idTransaksi: String?
    ):Call<ResponseAction>
}