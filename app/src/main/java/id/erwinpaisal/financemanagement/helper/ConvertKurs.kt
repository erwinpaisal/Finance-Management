package id.erwinpaisal.financemanagement.helper

import java.text.NumberFormat
import java.util.*

class ConvertKurs {

   companion object {
       fun formatRupiah(nominal: Int) : String{
           val localeID = Locale("in ", "ID ")
           val numberFormat = NumberFormat.getCurrencyInstance(localeID)
           return numberFormat.format (nominal).toString()
       }
   }

}