package pl.asiaki.pathmaster

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface ApiService {
    @GET("/generate_account")
    suspend fun generateAccount(
        @Header("k") apiKey: String,
    ): Response<AccountResponse>

    @GET("/check_balance")
    suspend fun checkBalance(
        @Query("k") apiKey: String,
        @Query("address") address: String
    ): Response<BalanceResponse>

    @GET("/award_tokens")
    suspend fun awardTokens(
        @Query("k") apiKey: String,
        @Query("address") address: String,
        @Query("amount") amount: Int
    ): Response<AwardTokensResponse>
}

// for the response
data class AccountResponse(val accountKey: String)

data class BalanceResponse(val balance: Int)

data class AwardTokensResponse(val succeeded: Boolean, val currBalance: Int) // ??? XD why

object RetrofitInstance {
    val api: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl("http://10.0.2.2:5000")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}

class AccountViewModel : ViewModel() {

    private val _balance = MutableStateFlow<Int?>(null)
    val balance: StateFlow<Int?> = _balance

    private val _tokenAwarded = MutableStateFlow<Pair<Boolean, Int>?>(null)
    val tokenAwarded: StateFlow<Pair<Boolean, Int>?> = _tokenAwarded

    fun generateAccount(apiKey: String) {
        viewModelScope.launch {
            try {
                RetrofitInstance.api.generateAccount(apiKey)
            } catch (e: Exception) {
                Log.d("API", "generateAccount request failed!")
            }
        }
    }

    fun checkBalance(apiKey: String, address: String) {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.checkBalance(apiKey, address)
                if (response.isSuccessful) {
                    _balance.value = response.body()?.balance
                }
            } catch (e: Exception) {
                Log.d("API", "checkBalance request failed!")
            }
        }
    }

    fun awardTokens(apiKey: String, address: String, amount: Int) {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.awardTokens(apiKey, address, amount)
                if (response.isSuccessful) {
                    response.body()?.let {
                        _tokenAwarded.value = Pair(it.succeeded, it.currBalance)
                    }
                }
            } catch (e: Exception) {
                Log.d("API", "awardTokens request failed!")
            }
        }
    }
}
