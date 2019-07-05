package com.jpardogo.example.data.common

class IllegalDataSourceException :
    RuntimeException("The data source used is not valid for this operation")