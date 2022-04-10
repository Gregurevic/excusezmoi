package edu.excusezmoi.ui

import androidx.lifecycle.*
import dagger.assisted.Assisted
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.excusezmoi.model.Excuse
import edu.excusezmoi.repository.DetailsRepository
import edu.excusezmoi.util.DataState
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val detailsRepository: DetailsRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _dataState: MutableLiveData<DataState<Excuse>> = MutableLiveData()

    val dataState: LiveData<DataState<Excuse>>
        get() = _dataState

    fun setStateEvent(detailsStateEvent: DetailsStateEvent) {
        viewModelScope.launch {
            when(detailsStateEvent) {
                /// is DetailsStateEvent.State
                    /// detailsRepository.action()
                    /// set dataState
                    /// launchIn(viewModelScope)
            }
        }
    }
}

sealed class DetailsStateEvent {
    ///...
}
