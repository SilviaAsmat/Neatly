package silas.dev.neatly.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import kotlinx.serialization.Serializable
import silas.dev.neatly.ui.collections.CollectionScreen
import silas.dev.neatly.ui.collections.CollectionScreenViewModel
import silas.dev.neatly.ui.home.HomeScreen
import silas.dev.neatly.ui.home.HomeScreenViewModel
import silas.dev.neatly.ui.products.ProductScreen
import silas.dev.neatly.ui.products.ProductScreenViewModel

@Serializable
object MainScreen

@Serializable
data class ProductScreenDestination(val productId: Int, val collectionId: Int)

@Serializable
data class CollectionScreen(val collectionId: Int)

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
                onProductClick = { productWithCollectionViewState ->
                    navController.navigate(
                        route = ProductScreenDestination(
                            productId = productWithCollectionViewState.productId,
                            collectionId = productWithCollectionViewState.collectionId
                        )
                    )
                },
                onCollectionClick = { collectionInfoViewState ->
                    navController.navigate(route = CollectionScreen( collectionInfoViewState.id))
                }
            )
        }
        composable<CollectionScreen> { backStackEntry ->
            val collectionScreenViewModel: CollectionScreenViewModel = hiltViewModel()
            CollectionScreen(
                viewModel = collectionScreenViewModel,
                onProductClick = { productWithCollectionViewState ->
                    navController.navigate(route = ProductScreenDestination(
                        productId = productWithCollectionViewState.productId,
                        collectionId = productWithCollectionViewState.collectionId
                    ))
                },
                onAddProductClick = { productWithCollectionViewState ->
                    navController.navigate(route = ProductScreenDestination(
                        productId = productWithCollectionViewState.productId,
                        collectionId = productWithCollectionViewState.collectionId
                    ))
                },
            )
        }
        composable<ProductScreenDestination> { backStackEntry ->
            val productDetails: ProductScreenDestination = backStackEntry.toRoute()
            val productScreenViewModel: ProductScreenViewModel = hiltViewModel()
            productScreenViewModel.initWithId(productDetails.productId)
            ProductScreen(
                viewModel = productScreenViewModel
            )
        }
    }
}