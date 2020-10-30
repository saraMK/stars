package com.example.stars.models

data class ResponseModel<T>(val results:T,val total_pages:Int)