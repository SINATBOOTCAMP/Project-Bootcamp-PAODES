package com.example.fr_philippe.gestionemploye;

import android.app.NotificationManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by FR-PHILIPPE on 11/27/2017.
 */

public class NotificationServices extends Fragment {

    private Button.OnClickListener mOnDisplayNotificationButtonClick = new Button.OnClickListener() {

        @Override
        public void onClick(View view) {
            NotificationCompat.Builder builder =
                    new NotificationCompat.Builder(getContext())
                            .setSmallIcon(R.drawable.ic_notifications_black_24dp)
                            .setContentTitle("BottomNavigationView")
                            .setContentText("Example Notification");
            NotificationManager mNotifyMgr =
                    (NotificationManager) getActivity().getSystemService(Context.NOTIFICATION_SERVICE);
            mNotifyMgr.notify(1, builder.build());

        }
    };

    public NotificationServices() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.notification_services, container, false);
        // Inflate the layout for this fragment
        Button displayNotificationButton = (Button) view.findViewById(R.id.display_notification);
        displayNotificationButton.setOnClickListener(mOnDisplayNotificationButtonClick);
        return view;
    }
}
