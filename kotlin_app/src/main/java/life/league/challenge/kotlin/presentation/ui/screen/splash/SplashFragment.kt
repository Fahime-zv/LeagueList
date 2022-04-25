package life.league.challenge.kotlin.presentation.ui.screen.splash

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import life.league.challenge.kotlin.R
import life.league.challenge.kotlin.presentation.architucture.BaseFragmentVM
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf


class SplashFragment : BaseFragmentVM<SplashViewModel>() {

    override val viewModel: SplashViewModel by viewModel {
        parametersOf(requireContext().getString(R.string.anErrorOccurred))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }


}