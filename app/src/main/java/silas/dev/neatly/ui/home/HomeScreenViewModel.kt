package silas.dev.neatly.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import silas.dev.neatly.domain.Repository
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val repo: Repository
) : ViewModel() {
    private val _collectionsViewState = MutableStateFlow<CollectionsViewState>(CollectionsViewState.Loading)
    val collectionsViewState: StateFlow<CollectionsViewState> = _collectionsViewState

    init {
        viewModelScope.launch(Dispatchers.IO) {

        }

    }

    private fun addProduct(){

    }

}