package com.lebogang.triwizardkotlin.repo

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.lebogang.triwizardkotlin.networking.NetworkUtils
import com.lebogang.triwizardkotlin.pojo.Houses
import com.lebogang.triwizardkotlin.threadexecutor.AppExecutors
import org.json.JSONArray
import org.json.JSONException
import java.io.IOException
import java.net.URL


class MyRepository {
    //URL that points to the houses JSON
    private val HOUSES_ENDPOINT =
        "https://www.potterapi.com/v1/houses?key=$2a$10$1JEnmtEF417yBaFZcr51qukRjaKv8d5toEG5DKP/IUZWIVwfsaF7y"

    /**
     * This is an instance of the @class MyRepository class that will be used in the
     *
     * @Class HousesActivity to
     */


    private val mutableHousesLiveData =
        MutableLiveData<LiveData<List<Houses?>?>?>()

    companion object{
    fun getInstance(): MyRepository? {
        var instance: MyRepository? = null
        if (instance == null) {
            synchronized(MyRepository::class.java) {
                if (instance == null) {
                    instance = MyRepository()
                }
            }
        }
        return instance
    }
    }

    // This ensures that only the repository can cause a change
    fun getHousesLiveData(): MutableLiveData<LiveData<List<Houses?>?>?> {
        return mutableHousesLiveData
    }

    /**
     * This method gets called from an Activity's onCreate method.
     * It fetches data off the main thread using an Executor(Runnable object)
     */
    fun getHouses() {
        //I made this into a local variable so it can be killed after calling this method to save resources.
        val executors = AppExecutors()
        executors.diskIO()?.execute(Runnable {
            val data: MutableList<Houses> = ArrayList()
            val data1: List<Houses> = ArrayList()
            val url: URL = NetworkUtils.buildUrl(HOUSES_ENDPOINT)!!
            var result: String? = null
            try {
                result = NetworkUtils.getResponseFromHttpUrl(url)
            } catch (e: IOException) {
                e.printStackTrace()
            }
            println("Repo results: $result")
            var jArray: JSONArray? = null
            try {
                jArray = JSONArray(result)
                for (i in 0 until jArray.length()) {

                    //Get objects from the JSONArray.
                    val jsonObject = jArray.getJSONObject(i)

                    //Initialize an object of the class House so we can append data to it.
                    val houseData = Houses()

                    //Set data to references.
                    houseData.name = jsonObject.getString("name")

                    //Store the data into an ArrayList.
                    data.add(houseData)

                    //Post the value(s) of the data to the LiveData Object.
                    mutableHousesLiveData.value
                    Log.i("Repo: ", houseData.name.toString())
                }
            } catch (j: NullPointerException) {
                j.printStackTrace()
            } catch (j: JSONException) {
                j.printStackTrace()
            }
        })
    }

}

