package life.league.challenge.kotlin.data.network.generator

import life.league.challenge.kotlin.data.network.generator.model.NetworkRequestHeader
import life.league.challenge.kotlin.data.sharedpreferences.SettingRepository
import java.util.ArrayList

interface NetworkHeaderGenerator {
    fun getHeader(): List<NetworkRequestHeader>
}

class NetworkHeadersGeneratorImpl(
    private val settingRepository: SettingRepository,
) : NetworkHeaderGenerator {
    override fun getHeader(): List<NetworkRequestHeader> {
        // Have been Used the array list  because if need to add another header we can add easily
        val arrayList = ArrayList<NetworkRequestHeader>()
        settingRepository.getToken()?.let { token ->
            arrayList.addAll(
                listOf(
                    NetworkRequestHeader("x-access-token", token)
                )
            )
        }
        return arrayList

    }

}