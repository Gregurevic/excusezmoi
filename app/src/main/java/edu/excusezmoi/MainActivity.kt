package edu.excusezmoi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import dagger.hilt.android.AndroidEntryPoint
import edu.excusezmoi.model.Excuse
import edu.excusezmoi.ui.MainFragment
import edu.excusezmoi.ui.MainStateEvent
import edu.excusezmoi.ui.MainViewModel
import edu.excusezmoi.util.DataState

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        subscribeObservers()
        viewModel.setStateEvent(MainStateEvent.GetExcuseEvents)
    }

    private fun subscribeObservers() {
        viewModel.dataState.observe(this, Observer { dataState ->
            when(dataState) {
                is DataState.Success<List<Excuse>> -> {
                    listExcuses(dataState.data)
                }
                is DataState.Error -> {
                    displayError(dataState.exception.message)
                }
                is DataState.Loading -> {
                    ///...
                }
            }
        })
    }

    private fun displayError(message: String?) {
        if (message != null) {
            ///...
        }
        else {
            ///...
        }
    }

    private fun listExcuses(excuses: List<Excuse>) {
        ///...
    }
}