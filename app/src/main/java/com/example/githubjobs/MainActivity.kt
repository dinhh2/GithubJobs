package com.example.githubjobs

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.ProgressBar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.githubjobs.model.Jobs
import com.example.githubjobs.networking.APIclient
import com.example.githubjobs.networking.APIendpoints
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create

class MainActivity : AppCompatActivity() {

    private var jobAdapter: JobsAdapter? = null
    private val job: ArrayList<Jobs>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)
        jobAdapter = JobAdapter(this, job)

        searchFunction()
    }
    private fun searchFunction() {
        val searchBar = findViewById<EditText>(R.id.searchBar)
        val button = findViewById<Button>(R.id.submitButton)
        button.setOnClickListener {

            job.clear()
            progress(true)
            jobAdapter?.notifyDataSetChanged()
            val client = APIclient.client.create(APIendpoints::class.java)
            val specificJobCall = client.getSpecificJob(searchBar.text.toString())
            specificJobCall.enqueue(object: Callback<List<Jobs>> {
                override fun onResponse(call: Call<List<Jobs>>, response: Response<List<Jobs>>) {
                    for (list: Jobs in response!!.body()!!) {
                        job.add(list)
                    }
                    progress(false)
                    jobAdapter?.notifyDataSetChanged()
                }
                override fun onFailure(call: Call<List<Jobs>>, t: Throwable) {
                    Log.e("Error Message", "Error" )
                }

            })
        }
    }
    private fun progress(progress: Boolean) {
        val progressBar = findViewById<ProgressBar>(R.id.loader)
        if (progress) {
            progressBar.visibility = View.VISIBLE
        } else {
            progressBar.visibility = View.GONE
        }

    }
}