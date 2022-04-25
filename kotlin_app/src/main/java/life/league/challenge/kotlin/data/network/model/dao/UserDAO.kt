package life.league.challenge.kotlin.data.network.model.dao

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


@Parcelize
data class UserDAO(
    @SerializedName("id") val id: Int,
    @SerializedName("avatar") val avatar: AvatarDAO,
    @SerializedName("name") val name: String,
    @SerializedName("username") val userName: String,
    @SerializedName("email") val email: String,
    @SerializedName("address") val address: AddressDAO,
    @SerializedName("phone") val phone: String,
    @SerializedName("website") val website: String,
    @SerializedName("company") val company: CompanyDAO
) : Parcelable {

    @Parcelize
    data class AvatarDAO(
        @SerializedName("large") val large: String,
        @SerializedName("medium") val medium: String,
        @SerializedName("thumbnail") val thumbnail: String,
    ) : Parcelable

    @Parcelize
    data class AddressDAO(
        @SerializedName("street") val street: String,
        @SerializedName("suite") val suite: String,
        @SerializedName("city") val city: String,
        @SerializedName("zipcode") val zipcode: String,
        @SerializedName("geo") val geo: GeoDAO
    ) : Parcelable

    @Parcelize
    data class GeoDAO(
        @SerializedName("lat") val lat: String,
        @SerializedName("lng") val lng: String
    ) : Parcelable

    @Parcelize
    data class CompanyDAO(
        @SerializedName("name") val name: String,
        @SerializedName("catchPhrase") val catchPhrase: String,
        @SerializedName("bs") val bs: String,
    ) : Parcelable

}

