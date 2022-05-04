package edu.excusezmoi

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import edu.excusezmoi.model.Excuse
import edu.excusezmoi.ui.DetailsViewModel

@AndroidEntryPoint
class DetailsActivity : AppCompatActivity() {

    private val detailsViewModel: DetailsViewModel by viewModels()

    private var currentId: Int = -1
    private var currentCategory: String = "office"
    private var currentText: String = "Please specify the excuse!"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.details_activity)

        /// acquire intent data
        currentId = intent.getIntExtra("EXCUSE_ID", -1)
        currentCategory = intent.getStringExtra("EXCUSE_CATEGORY").toString()
        currentText = intent.getStringExtra("EXCUSE_TEXT").toString()
        var currentCategoryPosition = resources.getStringArray(R.array.category_array)
            .indexOf(currentCategory[0].uppercaseChar() + currentCategory.substring(1))
        if (currentCategoryPosition == -1) {
            currentCategoryPosition = 0
            currentCategory = resources.getStringArray(R.array.category_array)[currentCategoryPosition].lowercase()
        }

        /// text view
        val editText: EditText = findViewById(R.id.edit_excuse_text)
        editText.setText(currentText)

        /// category spinner
        val spinner: Spinner = findViewById(R.id.spinner)
        spinner.setSelection(currentCategoryPosition)
        ArrayAdapter.createFromResource(
            this,
            R.array.details_category_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }
        spinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, pos: Int, id: Long) {
                currentCategory = resources.getStringArray(R.array.category_array)[pos].lowercase()
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {
                ///
            }
        }

        /// back button
        val backButton = findViewById<Button>(R.id.back_button)
        backButton.setOnClickListener {
            val intent = Intent(baseContext, MainActivity::class.java)
            startActivity(intent)
        }

        /// save button
        val saveButton = findViewById<Button>(R.id.button_save_excuse)
        saveButton.setOnClickListener {
            detailsViewModel.save(currentCategory, editText.text.toString())
            val intent = Intent(baseContext, MainActivity::class.java)
            startActivity(intent)
            Toast.makeText(this, "Excuse added to repertoire!", Toast.LENGTH_LONG).show()
        }

        /// personalize button
        val personalizeButton = findViewById<Button>(R.id.button_mod_excuse)
        personalizeButton.isEnabled = (currentId >= 0)
        personalizeButton.isClickable = (currentId >= 0)
        personalizeButton.setOnClickListener {
            detailsViewModel.personalize(currentId, currentCategory, editText.text.toString())
            val intent = Intent(baseContext, MainActivity::class.java)
            startActivity(intent)
            Toast.makeText(this, "Personalisation saved!", Toast.LENGTH_LONG).show()
        }

        /// destroy button
        val destroyButton = findViewById<Button>(R.id.button_ban_excuse)
        destroyButton.isEnabled = (currentId >= 0)
        destroyButton.isClickable = (currentId >= 0)
        destroyButton.setOnClickListener {
            detailsViewModel.donutShowAgain(currentId)
            val intent = Intent(baseContext, MainActivity::class.java)
            startActivity(intent)
            Toast.makeText(this, "Excuse removed.", Toast.LENGTH_LONG).show()
        }
    }
}