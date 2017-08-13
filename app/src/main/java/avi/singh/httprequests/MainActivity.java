package avi.singh.httprequests;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {


    Button btnSendRequest;
    TextView JsonResp;

    private OkHttpClient client;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSendRequest =(Button)findViewById(R.id.send);

        JsonResp = (TextView)findViewById(R.id.response);


        btnSendRequest.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {
                mywebservice();

            }
        });

        client = new OkHttpClient();

    }

    private void mywebservice(){
       final Request request = new Request.Builder().url("YourUrl").build();
            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            JsonResp.setText("Fail");

                        }
                    });


                }

                @Override
                public void onResponse(Call call,  final Response responses) throws IOException {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            try{
                                JsonResp.setText(responses.body().string());

                            } catch (IOException e) {
                               e.printStackTrace();
                            }

                        }
                    });

                }
            });


    }
}
