package com.example.androidqbackgroundlocation;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.provider.Telephony;
import android.telephony.SmsMessage;
import android.telephony.TelephonyManager;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        if(intent.getAction().equals("android.intent.action.PHONE_STATE")) {
            TelephonyManager tm = (TelephonyManager) context.getSystemService(Service.TELEPHONY_SERVICE);
            int state = tm.getCallState();

            if (state == TelephonyManager.CALL_STATE_OFFHOOK) {
                Toast.makeText(context, "Call Active", Toast.LENGTH_SHORT).show();
            }

            if (state == TelephonyManager.CALL_STATE_IDLE) {
                Toast.makeText(context, "Call End", Toast.LENGTH_SHORT).show();
            }

            if (state == TelephonyManager.CALL_STATE_RINGING) {
                Toast.makeText(context, "Ringing ", Toast.LENGTH_SHORT).show();
            }
        }else {
            SmsMessage[] msgs = Telephony.Sms.Intents.getMessagesFromIntent(intent);
            for (SmsMessage sms : msgs) {
                String message = sms.getMessageBody();
                String number = sms.getOriginatingAddress();


                Toast.makeText(context, "Message: " + message, Toast.LENGTH_SHORT).show();
                Toast.makeText(context, "Number: " + number, Toast.LENGTH_SHORT).show();


            }
        }

    }
}
