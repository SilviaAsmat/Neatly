package silas.dev.neatly.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHost
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable
import silas.dev.neatly.ui.home.HomeScreen
import silas.dev.neatly.ui.home.HomeScreenViewModel
import silas.dev.neatly.ui.products.ProductScreen
import silas.dev.neatly.ui.products.ProductScreenViewModel

@Serializable
object MainScreen

@Serializable
data class ProductScreen(val productId: Int)

@Composable
fun MainNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = MainScreen,
        modifier = modifier
    ) {
        composable<MainScreen> {
            val homeScreenViewModel: HomeScreenViewModel = hiltViewModel()
            HomeScreen(
                viewModel = homeScreenViewModel,
                onProductClick = { productInfoViewState ->
                    navController.navigate(route = ProductScreen(productId = productInfoViewState.id))
                }
            )
        }
        composable<ProductScreen> {
            val productScreenViewModel: ProductScreenViewModel = hiltViewModel()
            ProductScreen(
                viewModel = productScreenViewModel
            )
        }


    }

}