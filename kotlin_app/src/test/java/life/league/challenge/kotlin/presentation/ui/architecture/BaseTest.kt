package life.league.challenge.kotlin.presentation.ui.architecture

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import life.league.challenge.kotlin.TestCoroutineRule
import org.junit.Rule

@ExperimentalCoroutinesApi
abstract class BaseTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()
}