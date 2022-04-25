package life.league.challenge.kotlin.data

import android.content.Context
import android.content.SharedPreferences
import life.league.challenge.kotlin.data.network.api.Service
import life.league.challenge.kotlin.data.network.generator.NetworkHeaderGenerator
import life.league.challenge.kotlin.data.network.generator.NetworkHeadersGeneratorImpl
import life.league.challenge.kotlin.data.network.generator.NetworkRequestInterceptor
import life.league.challenge.kotlin.data.network.repository.NetworkRepository
import life.league.challenge.kotlin.data.sharedpreferences.SettingRepository
import life.league.challenge.kotlin.data.sharedpreferences.SettingRepositoryImpl
import life.league.challenge.kotlin.data.sharedpreferences.SharedPref
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dataModule = module {
    //***************************************
    //           SharedPreferences          *
    //***************************************

    factory {
        val sharedPreferences: SharedPreferences =
            androidContext().getSharedPreferences(
                androidContext().packageName,
                Context.MODE_PRIVATE
            )
        sharedPreferences
    }

    factory {
        SharedPref(get())
    }

    factory {
        val settingRepository: SettingRepository =
            SettingRepositoryImpl(
                sharedPref = get()
            )
        settingRepository
    }

    //***************************************
    //               Network                *
    //***************************************

    // ------------- API -------------
    factory {
        val networkHeadersGenerator: NetworkHeaderGenerator = NetworkHeadersGeneratorImpl(
            settingRepository = get(),
        )
        networkHeadersGenerator
    }

    factory {
        NetworkRequestInterceptor(headersGenerator = get())
    }

    single {
        Service(networkRequestInterceptor = get())
    }

    // ------------- Repositories -------------
    factory {
        NetworkRepository(service = get())
    }
}

