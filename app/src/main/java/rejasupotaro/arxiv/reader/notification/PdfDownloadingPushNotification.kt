package rejasupotaro.arxiv.reader.notification

import android.app.NotificationManager
import android.content.Context
import android.support.v4.app.NotificationCompat
import rejasupotaro.arxiv.reader.R
import rejasupotaro.arxiv.reader.data.model.Paper
import java.util.*

class PdfDownloadingPushNotification(private val context: Context, private val paper: Paper) {
    private val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    val id = Random().nextInt()

    fun show() {
        val notification = NotificationCompat.Builder(context, PdfDownloadingPushNotification::class.java.simpleName)
                .setSmallIcon(R.drawable.ic_arrow_downward_white_24dp)
                .setContentTitle("Downloading ${paper.title}")
                .setProgress(0, 0, true)
                .build()

        notificationManager.notify(id, notification)
    }

    fun cancel() {
        notificationManager.cancel(id)
    }
}

