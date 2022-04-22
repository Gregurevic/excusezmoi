package edu.excusezmoi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import edu.excusezmoi.model.Excuse
import edu.excusezmoi.ui.ExcuseListAdapter
import edu.excusezmoi.ui.MainStateEvent
import edu.excusezmoi.ui.MainViewModel
import edu.excusezmoi.util.DataState

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    private val mainViewModel: MainViewModel by viewModels()

    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter: RecyclerView.Adapter<ExcuseListAdapter.ItemViewHolder>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        subscribeObservers()
        mainViewModel.setStateEvent(MainStateEvent.GetExcuseEvents)

        /// category spinner
        val spinner: Spinner = findViewById(R.id.spinner)
        spinner.setSelection(0)
        ArrayAdapter.createFromResource(
            this,
            R.array.category_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }

        /// excuse recycler_view
        layoutManager = LinearLayoutManager(this)
        adapter = ExcuseListAdapter(this, mainViewModel.excuses)
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter
        recyclerView.setHasFixedSize(true)

        /// new excuses' button

        /// add excuse button
    }

    override fun onItemSelected(parent: AdapterView<*>, view: View?, pos: Int, id: Long) {
        // An item was selected. You can retrieve the selected item using
        // parent.getItemAtPosition(pos)
        /// get new excuses of the given category
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        /// do nothing
    }

    private fun subscribeObservers() {
        mainViewModel.dataState.observe(this, Observer { dataState ->
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