package com.example.stars.ui.main.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.stars.R
import com.example.stars.databinding.HomeFragmentBinding
import com.example.stars.ui.base.BaseFragment
import com.example.stars.ui.main.details.DetailsActivity
import org.koin.android.ext.android.inject

class HomeFragment : BaseFragment<HomeFragmentBinding>(), PeopleAdapter.Actions {


    private var isLoading: Boolean=true
    private val viewModel: HomeViewModel by inject()
    private val adapter: PeopleAdapter by inject()
    private lateinit var view_: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        view_ = setViewModelWithDataBinding(inflater,R.layout.home_fragment)
        binding.viewModel=viewModel

        return view_
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setList()
    }
    private fun setList() {
        val gLay=GridLayoutManager(context,2)
        binding.recyclerView.layoutManager=gLay
        binding.recyclerView.adapter=adapter
        adapter.action=this
      getPeopleList()
        binding.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (gLay.findLastVisibleItemPosition() >= adapter.itemCount - 3 && isLoading) {
                    getPeopleList()
                    isLoading = false
                }
            }
        })
    }

    private fun getPeopleList() {
        viewModel.getList().observe(viewLifecycleOwner, Observer {
            adapter.list.addAll(it)
            adapter.notifyItemRangeInserted(adapter.list.size-it.size,it.size)

        })

        viewModel.loadMore.observe(viewLifecycleOwner, Observer {
            isLoading = it
        })
    }

    override fun onItemClick(id: String) {
        val intent=Intent(activity,DetailsActivity::class.java)
        intent.putExtra("ID",id)
        startActivity(intent)
    }


}
