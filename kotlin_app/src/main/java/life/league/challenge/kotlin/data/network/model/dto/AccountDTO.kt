package life.league.challenge.kotlin.data.network.model.dto

import com.google.gson.annotations.SerializedName

data class AccountDTO(@SerializedName("api_key") val apiKey: String? = null)
