package silas.dev.neatly.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHost
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable
import silas.dev.neatly.ui.collections.CollectionScreen
import silas.dev.neatly.ui.collections.CollectionScreenViewModel
import silas.dev.neatly.ui.home.HomeScreen
import silas.dev.neatly.ui.home.HomeScreenViewModel
import silas.dev.neatly.ui.products.ProductInfoViewState
import silas.dev.neatly.ui.products.ProductScreen
import silas.dev.neatly.ui.products.ProductScreenViewModel

@Serializable
object MainScreen

@Serializable
data class ProductScreen(val id: Int)

@Serializable
data class CollectionScreen(val collectionName: String)

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
                onProductClick = { id ->
                    navController.navigate(route = ProductScreen(id = id))
                },
                onCollectionClick = { collectionName ->
                    navController.navigate(route = CollectionScreen(collectionName))
                }
            )
        }
        composable<CollectionScreen> { backStackEntry ->
            val name = backStackEntry.arguments?.getString("collectionName")
            val collectionScreenViewModel: CollectionScreenViewModel = hiltViewModel()
            collectionScreenViewModel.initWithName(name!!)
            CollectionScreen(
                viewModel = collectionScreenViewModel,
                onProductClick = {id ->
                    navController.navigate(route = ProductScreen(id))
                },
                onAddProductClick = {
                    navController.navigate(route = ProductScreen(-1))
                },
            )
        }
        composable<ProductScreen> { backStackEntry ->
            val productScreenViewModel: ProductScreenViewModel = hiltViewModel()
            productScreenViewModel.initWithId(id)
            ProductScreen(
                viewModel = productScreenViewModel
            )
        }
    }
}