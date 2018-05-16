package vyvital.fitz.data;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.RemoteViews;

import vyvital.fitz.MainActivity;
import vyvital.fitz.R;

public class FitzWidgetProvider extends AppWidgetProvider {

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.fitz_widget_provider);
        SharedPreferences mFav = context.getSharedPreferences("Tdee", Context.MODE_PRIVATE);
        int cal = mFav.getInt("GOAL", 0);
        String name = mFav.getString("WORK", "Pick a program");
        String first = "";
        String second = "";
        for (int i = 0; i < name.length(); i++) {
            if (name.charAt(i) == ' ') {
                first = name.substring(0, i);
                second = name.substring(i + 1, name.length());
                break;
            }
        }

        views.setTextViewText(R.id.prog_name, first);
        views.setTextViewText(R.id.prog_name2, second);
        views.setTextViewText(R.id.user_cal, String.valueOf(cal));
        Intent intent = new Intent(context, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
        views.setOnClickPendingIntent(R.id.widgetID, pendingIntent);

        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}