package edu.excusezmoi.ui

import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.excusezmoi.model.Excuse
import edu.excusezmoi.repository.MainRepository
import edu.excusezmoi.util.DataState
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val mainRepository: MainRepository,
    private val savedStateHandle: SavedStateHandle
): ViewModel() {

    private val _dataState: MutableLiveData<DataState<List<Excuse>>> = MutableLiveData()

    val excuses: List<Excuse> = listOf(Excuse(1, "asd", "asd"), Excuse(2, "asd", "asdasd"))

    val dataState: LiveData<DataState<List<Excuse>>>
        get() = _dataState

    fun setStateEvent(mainStateEvent: MainStateEvent) {
        viewModelScope.launch {
            when(mainStateEvent) {
                is MainStateEvent.GetExcuseEvents -> {
                    mainRepository.getNewExcuses().onEach { dataState ->
                        _dataState.value = dataState
                    }.launchIn(viewModelScope)
                }
                /// is SomethinElse ...
            }
        }
    }
}

sealed class MainStateEvent {
    object GetExcuseEvents: MainStateEvent()
    ///...
}