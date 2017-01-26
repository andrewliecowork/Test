package ecoworkinc.com.test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button) findViewById(R.id.test);
        final TextView internalName = (TextView) findViewById(R.id.internal_name);
        final TextView dataVersion = (TextView) findViewById(R.id.data_version);
        final TextView name = (TextView) findViewById(R.id.name);
        final TextView profileIconId = (TextView) findViewById(R.id.profile_icon_id);
        final TextView revisionId = (TextView) findViewById(R.id.revision_id);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String data = "{\n" +
                        "\"internalName\": \"domin91\",\n" +
                        "\"dataVersion\": 0,\n" +
                        "\"name\": \"Domin91\",\n" +
                        "\"profileIconId\": 578,\n" +
                        "\"revisionId\": 0\n" +
                        "}";

                Gson gson = new Gson();
                Message inboxMessage = gson.fromJson(data, Message.class);

                internalName.setText(inboxMessage.getInternalName());
                dataVersion.setText(String.valueOf(inboxMessage.getDataVersion()));
                name.setText(inboxMessage.getName());
                profileIconId.setText(String.valueOf(inboxMessage.getProfileIconId()));
                revisionId.setText(String.valueOf(inboxMessage.getRevisionId()));
            }
        });
    }
}
