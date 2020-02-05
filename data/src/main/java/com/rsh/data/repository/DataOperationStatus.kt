package com.rsh.data.repository


enum class Status {
    START_DOWNLOAD_FROM_NETWORK,
    END_DOWNLOAD_FROM_NETWORK,
    PROCESSING,
    SUCCESS,
    FAILED,
    START_GET_DATA_FROM_DB,
    END_GET_DATA_FROM_DB,
    END_OF_LIST_FROM_DB
}


class DataOperationStatus(val status: Status, val message: String) {

    companion object {

        val START_DOWNLOAD_FROM_NETWORK: DataOperationStatus
        val END_DOWNLOAD_FROM_NETWORK:DataOperationStatus
        val PROCESSING:DataOperationStatus
        val SUCCESS:DataOperationStatus
        val FAILED:DataOperationStatus
        val START_GET_DATA_FROM_DB:DataOperationStatus
        val END_GET_DATA_FROM_DB:DataOperationStatus
        val END_OF_LIST_FROM_DB: DataOperationStatus

        init {
            START_DOWNLOAD_FROM_NETWORK = DataOperationStatus(Status.START_DOWNLOAD_FROM_NETWORK, "Start download data from server")
            END_DOWNLOAD_FROM_NETWORK = DataOperationStatus(Status.END_DOWNLOAD_FROM_NETWORK, "Completed download data from server")
            PROCESSING = DataOperationStatus(Status.PROCESSING, "Processing")
            SUCCESS = DataOperationStatus(Status.SUCCESS, "Success")
            FAILED = DataOperationStatus(Status.FAILED, "Something wrong")

            START_GET_DATA_FROM_DB = DataOperationStatus(Status.START_GET_DATA_FROM_DB, "Start get data from local database")
            END_GET_DATA_FROM_DB = DataOperationStatus(Status.END_GET_DATA_FROM_DB, "End get data from local database")
            END_OF_LIST_FROM_DB = DataOperationStatus(Status.END_OF_LIST_FROM_DB, "End of data from database")
        }

    }


}