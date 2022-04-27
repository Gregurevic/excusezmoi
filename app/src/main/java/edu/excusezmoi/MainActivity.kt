package edu.excusezmoi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
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
class MainActivity : AppCompatActivity() {

    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        /// init data
        mainViewModel.setStateEvent(MainStateEvent.GetCurrentExcusesEvent)
        subscribeObservers()

        /// excuse recycler_view
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)
        mainViewModel.dataState.observe(this, Observer { dataState ->
            if (dataState is DataState.Success<List<Excuse>>)
                recyclerView.adapter = ExcuseListAdapter(this, dataState.data)
        })
        recyclerView.setHasFixedSize(true)

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
        spinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, pos: Int, id: Long) {
                if (pos == 0) mainViewModel.setStateEvent(MainStateEvent.GetNewExcusesEvent)
                else mainViewModel.setStateEvent(MainStateEvent.GetNewExcusesByCategoryEvent, resources.getStringArray(R.array.category_array)[pos])
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {
                ///
            }
        }

        /// new button
        val newButton = findViewById<Button>(R.id.new_button)
        newButton.setOnClickListener {
            val pos = spinner.selectedItemPosition
            if (0 < pos)
                mainViewModel.setStateEvent(MainStateEvent.GetNewExcusesByCategoryEvent,
                    resources.getStringArray(R.array.category_array)[pos])
            else mainViewModel.setStateEvent(MainStateEvent.GetNewExcusesEvent)
        }

        /// add button
        val addButton = findViewById<Button>(R.id.add_button)
        addButton.setOnClickListener {
            ///
        }
    }

    private fun subscribeObservers(){
        mainViewModel.dataState.observe(this, Observer { dataState ->
            when(dataState){
                is DataState.Success<List<Excuse>> -> {
                    populateExcuseList(dataState.data)
                }
                is DataState.Error -> {
                    ///
                }
                is DataState.Loading -> {
                    ///
                }
            }
        })
    }

    private fun populateExcuseList(excuses: List<Excuse>){
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView.adapter = ExcuseListAdapter(this, excuses)
    }
}