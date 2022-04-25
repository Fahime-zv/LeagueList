package life.league.challenge.kotlin.presentation.architucture

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.annotation.CallSuper
import life.league.challenge.kotlin.R
import life.league.challenge.kotlin.presentation.extenstions.TAG

/**
 * BaseFragmentVMState, where all [UiState] get observed.
 * You should extend this fragment whenever you have a Fragment with a ViewModel that may change the
 * [UiState].
 * */
abstract class BaseFragmentVMState<VM : BaseViewModelState> : BaseFragmentVM<VM>() {

    abstract override val viewModel: VM

    @CallSuper
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getUiStateLiveData().observe(viewLifecycleOwner) {
            Log.d(TAG, "UiState: $it")
            when (it) {
                UiState.Loading -> onLoading()
                UiState.Data -> onData()
                UiState.NetworkError -> onNetworkError()

            }
        }
    }

    abstract fun onLoading()
    abstract fun onData()
    abstract fun onNetworkError()

}
