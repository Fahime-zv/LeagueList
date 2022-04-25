package life.league.challenge.kotlin.presentation.ui

import life.league.challenge.kotlin.presentation.ui.screen.detailpost.PostDetailViewModel
import life.league.challenge.kotlin.presentation.ui.screen.postlist.PostListViewModel
import life.league.challenge.kotlin.presentation.ui.screen.splash.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    //****************************************
    //              ViewModels               *
    //****************************************/
    viewModel {
        PostListViewModel(networkRepository = get())
    }

    viewModel { parameters ->
        PostDetailViewModel(post = parameters.get())
    }

    viewModel { parameters ->
        SplashViewModel(
            networkRepository = get(),
            settingRepository = get(),
            networkErrorMsg = parameters.get()
        )
    }
}