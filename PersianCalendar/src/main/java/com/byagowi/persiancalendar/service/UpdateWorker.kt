package com.byagowi.persiancalendar.service

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.byagowi.persiancalendar.utils.Utils
import com.byagowi.persiancalendar.utils.update
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UpdateWorker(val context: Context, workerParams: WorkerParameters) : CoroutineWorker(context, workerParams) {

    override suspend fun doWork(): Result = withContext(Dispatchers.Main) {
        try {
            Utils.setChangeDateWorker(context)
            Utils.updateStoredPreference(applicationContext)
            update(applicationContext, true)
            Result.success()
        } catch (error: Throwable) {
            Result.failure()
        }
    }

}
