package com.appveg.farmfamily.ui.chart


import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Half.toFloat
import android.util.Log
import android.widget.Toast
import com.appveg.farmfamily.R
import com.appveg.farmfamily.ui.home.DetailGardenFirebase
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.formatter.IAxisValueFormatter
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.listener.OnChartValueSelectedListener
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_chart.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList
import com.github.mikephil.charting.data.DataSet as DataSet1


class ChartActivity : AppCompatActivity(), OnChartValueSelectedListener {

    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chart)
        // take info temp
        gardenInfo()

    }

    override fun onNothingSelected() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onValueSelected(e: Entry?, h: Highlight?) {

    }
    private fun buildChart(arrayList: ArrayList<Float>,arrayListDate: ArrayList<String>){

        combinedChart.description.isEnabled = false
        combinedChart.setDrawBarShadow(false)
        combinedChart.setBackgroundColor(Color.WHITE)
        combinedChart.setDrawGridBackground(false)
        combinedChart.isHighlightFullBarEnabled = false
        combinedChart.setOnChartValueSelectedListener(this)

        // column right
        var rightAxis = combinedChart.axisRight
        rightAxis.setDrawGridLines(false)
        rightAxis.axisMinimum = 0f

        // column left
        var leftAxis = combinedChart.axisLeft
        leftAxis.setDrawGridLines(false)
        leftAxis.axisMinimum = 0f

        // created month
        var xLabel : ArrayList<String> = arrayListDate


        var xAxis = combinedChart.xAxis
        xAxis.position = XAxis.XAxisPosition.BOTTOM
        xAxis.axisMinimum = 0f
        xAxis.granularity = 1f

        xAxis.valueFormatter =
            IAxisValueFormatter { value, axis -> xLabel[value.toInt() % xLabel.size] }

        var data = CombinedData()
        var lineDatas = LineData()
        lineDatas.addDataSet(dataChart(arrayList) as ILineDataSet)

        data.setData(lineDatas)

        xAxis.axisMaximum = data.xMax + 0.25f

        combinedChart.data = data
        combinedChart.invalidate()
    }
    //data: IntArray
    private fun dataChart(arrayList: ArrayList<Float>): DataSet1<Entry> {

        var d = LineData()

//        arrayList.add(30.0F)
//        arrayList.add(30.0F)
//        arrayList.add(30.0F)
//        arrayList.add(45.0F)
//        arrayList.add(50.0F)

        var data = arrayList

            var entries : ArrayList<Entry> = ArrayList()
             if(!data.isNullOrEmpty()) {
                 for (index in 0 until data.size) {
                     entries.add(Entry(index.toFloat(), data[index]))
                 }
             }
            var set = LineDataSet(entries, getString(R.string.decription_Humidity_vi))
            set.color = Color.GREEN
            set.lineWidth = 2.5f
            set.setCircleColor(Color.GREEN)
            set.circleRadius = 5f
            set.fillColor = Color.GREEN
            set.mode = LineDataSet.Mode.CUBIC_BEZIER
            set.setDrawValues(true)
            set.valueTextSize = 10f
            set.valueTextColor = Color.GREEN

            set.axisDependency = YAxis.AxisDependency.LEFT
            d.addDataSet(set)

        return set
    }

    /**
     * the method to get data from intent
     */
    private fun gardenInfo(){
        database = FirebaseDatabase.getInstance().reference
        // My top posts by number of stars
        var garden = getDataFromItent()
        // My top posts by number of stars
        database.child(garden).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                var arrayListOf = ArrayList<Float>()
                var arrayListTemp = ArrayList<String>()

                for (postSnapshot in dataSnapshot.children) {
                    var chartGarden : DetailGardenFirebase? = postSnapshot.getValue(DetailGardenFirebase::class.java)
                    arrayListOf.add(chartGarden?.Humidity?.split(" ")!![0].toFloat())

                    val formatter: SimpleDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS")
                    val formatted: String = formatter.format(Date(chartGarden.timestamp!!))

                    val formatter1: SimpleDateFormat = SimpleDateFormat("dd/MM")
                    val dayOnly: String = formatter1.format(Date(chartGarden.timestamp!!))
                    title_chart.text = "Biểu đồ độ ẩm, "+formatted

                    arrayListTemp.add(dayOnly)
                }
                buildChart(arrayListOf,arrayListTemp)
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.w("AAA", "loadPost:onCancelled", databaseError.toException())
                // ...
            }
        })
    }

    /**
     * the method to get data from intent
     */
    private fun getDataFromItent() : String {
        val bundle:Bundle = intent.extras
        val id: String =
            bundle.get("garden_code") as String
        return id

    }
}
