package receiver.service.android.vogella.com.project1edx.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import java.util.List;
import java.util.Random;

import receiver.service.android.vogella.com.project1edx.R;
import receiver.service.android.vogella.com.project1edx.database.CardDataSource;
import receiver.service.android.vogella.com.project1edx.model.Card;

/**
 * Created by hp on 08/05/2017.
 */

public class Widget extends AppWidgetProvider {
    CardDataSource dataSource;

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        final int count = appWidgetIds.length;
        dataSource = new CardDataSource(context);
        dataSource.open();
        List<Card> cards;
        cards = dataSource.getAllCards();
        Random randomValue = new Random();
        int randomIndex = randomValue.nextInt(cards.size());

        for (int i = 0; i < count; i++) {
            int widgetId = appWidgetIds[i];

            RemoteViews remoteViews = new RemoteViews(context.getPackageName(),
                    R.layout.simple_widget);
            remoteViews.setTextViewText(R.id.tvWidgetQuestion, cards.get(randomIndex).getQuestion());
            remoteViews.setTextViewText(R.id.tvWidgetAnswer, cards.get(randomIndex).getAnswer());

            Intent intent = new Intent(context, Widget.class);
            intent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
            intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, appWidgetIds);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(context,
                    0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            remoteViews.setOnClickPendingIntent(R.id.btWidgetNext, pendingIntent);
            appWidgetManager.updateAppWidget(widgetId, remoteViews);
        }
    }

}
