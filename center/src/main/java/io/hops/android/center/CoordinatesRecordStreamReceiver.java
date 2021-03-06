package io.hops.android.center;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import io.hops.android.streams.records.CoordinatesRecord;
import io.hops.android.streams.streams.RecordStreamWorker;


public class CoordinatesRecordStreamReceiver extends BroadcastReceiver {

    public CoordinatesRecordStreamReceiver(){

    }

    @Override
    public void onReceive(Context context, Intent intent) {

        String action=intent.getStringExtra("action");
        if(action.equals("stop")){
            RecordStreamWorker.closeStream(CoordinatesRecord.class);
        }
        Toast.makeText(context,"Producing stopped.",Toast.LENGTH_SHORT).show();

        NotificationManager n =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        n.cancel(1);

        Intent it = new Intent(Intent.ACTION_CLOSE_SYSTEM_DIALOGS);
        context.sendBroadcast(it);
    }


}