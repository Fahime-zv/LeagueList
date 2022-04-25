package life.league.challenge.kotlin

import life.league.challenge.kotlin.data.dataModule
import life.league.challenge.kotlin.presentation.ui.presentationModule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.test.KoinTest
import org.koin.test.check.checkModules
import org.mockito.junit.MockitoJUnitRunner

//@RunWith(MockitoJUnitRunner::class)
//class KoinModuleUnitTest : KoinTest {

//    @Test
//    fun `check all koin modules`() {
//        /**
//                 * Set mock parameters of each class here like this:
//                 * create<FactoryPresenter> { parametersOf("_FactoryId_") }
//                 * **/
//        checkModules(
//            parameters = {
//                /**
//                 * Set mock parameters of each class here like this:
//                 * create<FactoryPresenter> { parametersOf("_FactoryId_") }
//                 * **/
//
//            }) {
//            modules(dataModule, presentationModule)
//        }
//    }
//}