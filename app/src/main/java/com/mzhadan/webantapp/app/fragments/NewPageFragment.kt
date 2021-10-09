package com.mzhadan.webantapp.app.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.mzhadan.webantapp.ImageListItem
import com.mzhadan.webantapp.R
import com.mzhadan.webantapp.app.adapters.ImageRecyclerAdapter
import com.mzhadan.webantapp.data.api.WebantApi
import com.mzhadan.webantapp.data.providers.NetworkService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class NewPageFragment(containerFragmentManager: FragmentManager) : Fragment() {

    private var compositeDisposable = CompositeDisposable()
    private var imageRecyclerAdapter = ImageRecyclerAdapter(containerFragmentManager)
    private var imageList: ArrayList<ImageListItem> = ArrayList()
    private val imageLimit = 10
    private var page = 1

    lateinit var recyclerView: RecyclerView
    lateinit var webantApi: WebantApi
    lateinit var swipeRefreshLayout: SwipeRefreshLayout
    lateinit var progressBar: ProgressBar
    lateinit var nestedScrollView: NestedScrollView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var view: View = inflater.inflate(R.layout.new_page_fragment, container, false)

        recyclerView = view.findViewById(R.id.newPageRecyclerView)
        recyclerView.layoutManager = GridLayoutManager(view.context, 2)
        swipeRefreshLayout = view.findViewById(R.id.newPageSwipeRefreshLayout)
        progressBar = view.findViewById(R.id.newPageProgressBar)
        nestedScrollView = view.findViewById(R.id.newPageNestedScrollView)

        webantApi = NetworkService().provideApiClient()
        loadData(webantApi)

        swipeRefreshLayout.setOnRefreshListener {
            imageList.clear()
            page = 1
            loadData(webantApi)
            swipeRefreshLayout.isRefreshing = false
        }

        nestedScrollView.setOnScrollChangeListener { v: NestedScrollView, scrollX, scrollY, oldScrollX, oldScrollY ->
            if(scrollY == ( v.getChildAt(0).measuredHeight - v.measuredHeight)){
                progressBar.visibility = View.VISIBLE
                page++
                loadData(webantApi)
            }
        }

        return view
    }

    fun loadData(webantApi: WebantApi){
        compositeDisposable.add(webantApi
            .getNewImages(imageLimit,  true, page)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                progressBar.visibility = View.GONE
                imageList.addAll(it.data)
                imageRecyclerAdapter.setData(imageList)
                recyclerView.adapter = imageRecyclerAdapter

            },{

            }))
    }
}