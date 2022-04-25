package life.league.challenge.kotlin.core.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue
import life.league.challenge.kotlin.data.network.model.dao.UserDAO

//Combine user and post
@Parcelize
data class Post(
    val id: Int,
    val user: UserDAO,
    val title: String,
    val description: String
) : Parcelable