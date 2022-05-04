package edu.excusezmoi.ui

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import edu.excusezmoi.DetailsActivity
import edu.excusezmoi.R
import edu.excusezmoi.model.Excuse


class ExcuseListAdapter(
    private val context: Context,
    private val dataset: List<Excuse>
) : RecyclerView.Adapter<ExcuseListAdapter.ItemViewHolder>() {

    class ItemViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        val button: Button = view.findViewById(R.id.excuse_list_item_copy)
        val imageButton: ImageButton = view.findViewById(R.id.excuse_list_item_edit)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.excuse_list_item, parent, false)
        return ItemViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.button.text = dataset[position].text
        holder.button.setOnClickListener {
            val clipboard: ClipboardManager? = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager?
            val clip = ClipData.newPlainText(dataset[position].text, dataset[position].text)
            clipboard?.setPrimaryClip(clip)
            Toast.makeText(context, "Copied!", Toast.LENGTH_SHORT).show()
        }
        holder.imageButton.setOnClickListener {
            val intent = Intent(context, DetailsActivity::class.java)
            intent.putExtra("EXCUSE_ID", dataset[position].id)
            intent.putExtra("EXCUSE_CATEGORY", dataset[position].category)
            intent.putExtra("EXCUSE_TEXT", dataset[position].text)
            context.startActivity(intent)
        }
    }

    override fun getItemCount() = dataset.size
}
