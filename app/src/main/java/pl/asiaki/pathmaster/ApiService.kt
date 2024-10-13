package pl.asiaki.pathmaster

interface ApiService {
    @GET(/generate_account)
    suspend fun getGeneratedAccount(
        @Header("k") apiKey: String:
        Response<Uint>
    )

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
    class ApiService {
    }
}

// for the response
data class BalanceResponse(val balance: Int)

data class AwardTokensResponse(val succeded: Boolean, val currBalance = Int)