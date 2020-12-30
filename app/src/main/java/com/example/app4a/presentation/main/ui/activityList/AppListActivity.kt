package com.example.app4a.presentation.main.ui.activityList

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.app4a.R
import com.example.app4a.domain.entities.Currency
import com.example.app4a.presentation.main.buttonStatus.ApiError
import com.example.app4a.presentation.main.buttonStatus.ApiSuccess
import com.example.app4a.presentation.main.viewModel.MainViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.activity_create_account.*
import org.koin.android.ext.android.inject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class AppListActivity : AppCompatActivity() {

    private val mainViewModel : MainViewModel by inject()
    private var recyclerView: RecyclerView? = null
    private var mAdapter: RecyclerView.Adapter<*>? = null
    private var layoutManager: RecyclerView.LayoutManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_app_list)

        mainViewModel.fetchDataForAdapter(object : Callback<List<Currency>> {
            override fun onResponse(
                call: Call<List<Currency>>,
                response: Response<List<Currency>>
            ) {
                if (response.isSuccessful() && response.body() != null) {
                    println("ApiSuccess")
                    //ApiSuccess(response.body())
                    showList(response.body())
                    println(response.body())
                } else {
                    //ApiError
                    println("Bad Data Fetched From Api")
                }
            }

            override fun onFailure(call: Call<List<Currency>>, t: Throwable) {
                t.printStackTrace();
                println("Failure API Call")
                //ApiStatus.get()
            }
        })
    }

    private fun showList(temporel : List<Currency>?) {
        recyclerView = findViewById<View>(R.id.recycler_view) as RecyclerView
        recyclerView!!.setHasFixedSize(true)

        layoutManager = LinearLayoutManager(this)
        recyclerView!!.setLayoutManager(layoutManager)

        mAdapter = MyAdapter(temporel as MutableList<Currency>)
        recyclerView!!.setAdapter(mAdapter)
    }

}


