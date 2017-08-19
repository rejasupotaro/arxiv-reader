package rejasupotaro.arxiv.reader.notification

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.support.v4.app.NotificationCompat
import rejasupotaro.arxiv.reader.R
import rejasupotaro.arxiv.reader.data.model.Paper
import rejasupotaro.arxiv.reader.ui.paper.read.PaperReadActivityAutoBundle
import java.util.*

class PdfDownloadedPushNotification(private val context: Context, private val paper: Paper) {
    private val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    val id = Random().nextInt()

    fun show() {
        val intent = PaperReadActivityAutoBundle.builder(paper.id)
                .build(context)

        val pendingIntent = PendingIntent.getActivity(context, -1, intent, PendingIntent.FLAG_ONE_SHOT);

        val notification = NotificationCompat.Builder(context, PdfDownloadingPushNotification::class.java.simpleName)
                .setSmallIcon(R.drawable.ic_check_box_white_24dp)
                .setContentTitle("${paper.title} is downloaded")
                .setContentIntent(pendingIntent)
                .build()
        notificationManager.notify(id, notification)
    }

    fun cancel() {
        notificationManager.cancel(id)
    }
}

