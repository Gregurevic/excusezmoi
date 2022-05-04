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

    fun save(category: String, excuse: String) {
        viewModelScope.launch {
            detailsRepository.createExcuse(category, excuse)
        }
    }

    fun personalize(id: Int, category: String, excuse: String) {
        viewModelScope.launch {
            detailsRepository.updateExcuse(id, category, excuse)
        }
    }

    fun donutShowAgain(id: Int) {
        viewModelScope.launch {
            detailsRepository.destroyExcuse(id)
        }
    }
}
