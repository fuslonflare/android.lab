package csd.gisc.carcheckerlab

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private val classes = arrayOf<Class<*>>(
            BarcodeActivity::class.java,
            CrashActivity::class.java,
            ScreenshotActivity::class.java)
    private var descriptionIds = intArrayOf(
            R.string.desc_barcode_activity_ml_kit,
            R.string.desc_custom_activity_on_crash,
            R.string.desc_screenshot_activity)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listView = findViewById<ListView>(R.id.menu_list)
        val adapter = MyArrayAdapter(this, android.R.layout.simple_list_item_2, classes)

        adapter.setDescriptionIds(descriptionIds)
        listView.adapter = adapter
        listView.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
            var clicked = classes[position]
            startActivity(Intent(this, clicked))
        }
    }
}

private class MyArrayAdapter(context: Context,
                             resource: Int,
                             private val classes: Array<Class<*>>) :
        ArrayAdapter<Class<*>>(context, resource, classes) {

    private var descriptionIds: IntArray? = null

    override fun getView(position: Int,
                         convertView: View?,
                         parent: ViewGroup): View {

        var view = convertView
        if (convertView == null) {
            val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            view = inflater.inflate(android.R.layout.simple_list_item_2, null)
        }

        (view!!.findViewById<View>(android.R.id.text1) as TextView).setText(descriptionIds!![position])
        (view.findViewById<View>(android.R.id.text2) as TextView).text = classes[position].simpleName

        return view
    }

    fun setDescriptionIds(descriptionIds: IntArray) {
        this.descriptionIds = descriptionIds
    }
}
