package com.example.stars.koin



import com.example.stars.ui.main.details.DetailsViewModel
import com.example.stars.ui.main.details.ImagesAdapter
import com.example.stars.ui.main.displayImage.DisplayImageViewModel
import com.example.stars.ui.main.home.HomeViewModel
import com.example.stars.ui.main.home.PeopleAdapter

import org.koin.dsl.module

val viewModelModule = module {
     factory { HomeViewModel() }
     factory { DetailsViewModel() }
     factory { DisplayImageViewModel() }
     factory { PeopleAdapter() }
     factory { ImagesAdapter() }

}

