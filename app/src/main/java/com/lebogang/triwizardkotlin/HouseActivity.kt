package com.lebogang.triwizardkotlin

import android.R
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.ContentLoadingProgressBar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lebogang.triwizardkotlin.adapter.HousesAdapter
import com.lebogang.triwizardkotlin.networking.NetworkUtils
import com.lebogang.triwizardkotlin.pojo.Houses
import com.lebogang.triwizardkotlin.repo.MyRepository
import com.lebogang.triwizardkotlin.viewmodel.HousesViewModel


class HouseActivity : AppCompatActivity() {

    //A String constant for Logging.
    private val TAG = HouseActivity::class.java.simpleName

    //The HousesActivity context instead of calling .this all the time where a context is wanted.
    private var context: Context? = null

    //Recyclerview that shows a list of houses.
    private var mRecyclerView: RecyclerView? = null

    //Adapter that binds data read  from the MyRepository class.
    private var mAdapter: HousesAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_houses)

        //Show LoadingProgressBar

        //Show LoadingProgressBar
        val contentLoadingProgressBar: ContentLoadingProgressBar = findViewById(R.id.loadingBar)
        contentLoadingProgressBar.show()

        //initializing the Recyclerview.

        //initializing the Recyclerview.
        mRecyclerView = findViewById(R.id.housesRecyclerView)

        //Setting the Recyclerview in a Linear fashion layout via the LayoutManager.

        //Setting the Recyclerview in a Linear fashion layout via the LayoutManager.
        mRecyclerView.setLayoutManager(LinearLayoutManager(this))

        // Setting up our view model

        // Setting up our view model
        val viewModel: HousesViewModel =
            ViewModelProviders.of(this).get(HousesViewModel::class.java)

        // Observe the view model

        // Observe the view model
        viewModel.getHousesLiveData()
            ?.observe(this, object : Observer List < Houses >() {
                fun onChanged(housesList: List<Houses?>?) {
                    //Updating the UI.
                    contentLoadingProgressBar.hide()
                    mAdapter = HousesAdapter(context, housesList)
                    mRecyclerView.setAdapter(mAdapter)
                    Log.i(TAG, "Update from ViewModel: $housesList")
                }
            })

        //condition to check whether to request data provided there's an internet connection or not.

        //condition to check whether to request data provided there's an internet connection or not.
        if (!NetworkUtils.isInternetAvailable()) {
            // This will start the off-the-UI-thread work that we want to perform.
            MyRepository.getInstance()!!.getHouses()
        } else {
            //Show AlertDialog to prompt user to get a connection.
            Toast.makeText(context, "No connection", Toast.LENGTH_LONG).show()
        }
    }
}
