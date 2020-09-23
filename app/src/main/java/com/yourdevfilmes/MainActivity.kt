package com.yourdevfilmes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.yourdevfilmes.view.RvAdapter
import com.yourdevfilmes.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.concurrent.schedule

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: MainViewModel
    val rvAdapter = RvAdapter(arrayListOf())


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        rv.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = rvAdapter
        }

        viewModel.fetchMovies()

        viewModel.response.observe(this, Observer {movies ->
            movies?.let {
                rvAdapter.update(it.results)
            }
        })

        edt.addTextChangedListener(object : TextWatcher {
            var timer = Timer()

            override fun beforeTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) { }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) { }

            override fun afterTextChanged(s: Editable?) {
                timer.cancel()
                val sleep = 1000L
                timer = Timer()
                timer.schedule(sleep) {
                    if (s.isNullOrEmpty()) {
                        viewModel.fetchMovies()
                    }else{
                        viewModel.fetchMovieByText(s.toString())
                    }
                }
            }
        })

    }

}