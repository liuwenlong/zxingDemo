package demo.zxing.com.zxingdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.app.Activity;

import com.google.zxing.client.android.CaptureActivity;

public class MainActivity extends Activity {

    TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text = (TextView)findViewById(R.id.text);
        registerReceiver(barCodeReceiver,new IntentFilter(CaptureActivity.ActionDecodeContent));
    }

    public void startZxing(View v){
        startActivity(new Intent(this,CaptureActivity.class));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(barCodeReceiver);
    }

    BroadcastReceiver barCodeReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String barCode = intent.getStringExtra(CaptureActivity.ExtraDisplayContents);
            text.setText(barCode);
        }
    };
}
