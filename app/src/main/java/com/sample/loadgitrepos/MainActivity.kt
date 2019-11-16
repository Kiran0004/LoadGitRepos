package com.sample.loadgitrepos

import android.content.Context
import android.net.ConnectivityManager

import android.os.Bundle
import android.os.Handler
import android.support.design.widget.CoordinatorLayout
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast


import com.sample.adapter.RepositoriesListAdapter
import com.sample.api.RepositoryApiBuilder
import com.sample.model.RepositoryResponse
import com.sample.utils.ErrorUtils

import java.util.HashMap

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by Kiran on 2019-11-16.
 */

class MainActivity : AppCompatActivity() {
    var recyclerView: RecyclerView? = null
    var coordinatorLayout: CoordinatorLayout? = null
    var listadapter: RepositoriesListAdapter? = null
    var repositoriesList: RepositoryResponse? = null
    var currentPage = PAGE_START
    var progressBar: ProgressBar? = null


    private val isNetworkAvailable: Boolean
        get() {
            val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetworkInfo = connectivityManager.activeNetworkInfo
            return activeNetworkInfo != null && activeNetworkInfo.isConnected
        }

     override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        coordinatorLayout = findViewById(R.id.container)
        recyclerView = findViewById(R.id.linear_recyclerview)
        progressBar = findViewById(R.id.repository_progress)
        val linearLayoutManager = LinearLayoutManager(this)
        recyclerView!!.setHasFixedSize(true)
         recyclerView!!.addItemDecoration(DividerItemDecoration(this, 0))
        recyclerView!!.layoutManager = (LinearLayoutManager(this))
        recyclerView!!.addOnScrollListener(object : RecyclerView.OnScrollListener() {
           override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
            }

           override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {

                if (dy > 0) {
                    if (!recyclerView.canScrollVertically(RecyclerView.FOCUS_DOWN)) {
                        Handler().postDelayed(object : Runnable {
                            override fun run() {
                                currentPage += 1
                                fetchNextPage()
                                progressBar!!.setVisibility(View.VISIBLE)
                            }
                        }, 1000)

                    }
                    progressBar!!.setVisibility(View.INVISIBLE)

                }
            }
        })

        if (!isNetworkAvailable) {
            val snack1bar = Snackbar
                    .make(coordinatorLayout!!, "No Internet connection", Snackbar.LENGTH_LONG)
                    .setAction("RETRY", View.OnClickListener() {
                        fun onClick(view: View) {

                            fetchFirstPage()
                        }
                    })

            snack1bar.show()
        } else {
            fetchFirstPage()
        }

    }


    private fun prepareData(repositoriesList: RepositoryResponse) {
        listadapter = repositoriesList?.items?.let { RepositoriesListAdapter(it) }
        recyclerView!!.adapter = (listadapter)
    }

    private fun fetchFirstPage() {
        val data = HashMap<String, String>()
        data?.put("q", "created:>2019-10-16")
        data?.put("sort", "stars")
        data?.put("order", "desc")
        val apiService = RepositoryApiBuilder().service
        val repositoryListCall = apiService.getRepositoryList(data)
        repositoryListCall.enqueue(object : Callback<RepositoryResponse> {
           override fun onResponse(call: Call<RepositoryResponse>, response: Response<RepositoryResponse>) {
                if (response.isSuccessful()) {
                    Toast.makeText(this@MainActivity,
                            " Load Sucessful",
                            Toast.LENGTH_SHORT).show()
                    repositoriesList = response.body()
                    prepareData(repositoriesList!!)

                } else {
                    val error = ErrorUtils.parseError(response)
                    Log.d("error message", error.message)
                }
            }

            override fun onFailure(call: Call<RepositoryResponse>, t: Throwable) {
                Toast.makeText(this@MainActivity,
                        "Request failed. Check your internet connection",
                        Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun fetchNextPage() {
        Log.d(TAG, "loadNextPageDetails: $currentPage")
        val data = HashMap<String, String>()
        data?.put("q", "created:>2019-10-16")
        data?.put("sort", "stars")
        data?.put("order", "desc")
        data?.put("page", currentPage.toString())
        val apiService = RepositoryApiBuilder().service
        val repositoryListCall = apiService.getRepositoryList(data)
        repositoryListCall.enqueue(object : Callback<RepositoryResponse> {
            override fun onResponse(call: Call<RepositoryResponse>, response: Response<RepositoryResponse>) {
                if (response.isSuccessful()) {
                    Toast.makeText(this@MainActivity,
                            " Loading more  ",
                            Toast.LENGTH_SHORT).show()
                    val repositoriesList2 = response.body()
                    progressBar!!.setVisibility(View.INVISIBLE)
                   // repositoriesList?.items?.let { repositoriesList2?.items?.addAll(it) }
                   // Log.d("new size ", repositoriesList!!.items!!.size + "")
                    prepareData(repositoriesList2!!)
                    recyclerView?.invalidate()
                    listadapter?.notifyDataSetChanged()

                } else {

                    val error = ErrorUtils.parseError(response)
                    Log.d("error message", error.message)
                }
            }

           override fun onFailure(call: Call<RepositoryResponse>, t: Throwable) {
                Toast.makeText(this@MainActivity,
                        "Request failed. Check your internet connection",
                        Toast.LENGTH_SHORT).show()
            }
        })
    }

   override fun onResume() {
        super.onResume()
        recyclerView!!.adapter = listadapter
    }

    companion object {

        private val TAG = MainActivity::class.java!!.simpleName

        private val PAGE_START = 1
    }

}

