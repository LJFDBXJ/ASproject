package com.example.quckqud;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.xys.libzxing.zxing.activity.CaptureActivity;
import com.xys.libzxing.zxing.encoding.EncodingUtils;

public class MainActivity extends AppCompatActivity {
    private CheckBox mLogo;
    private Button scan;
    private TextView mTvResult;
    EditText mInput;
    private ImageView mResult;
    private CalendarView calen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        scan = (Button) findViewById(R.id.scan);
        mTvResult = (TextView) findViewById(R.id.tv_result);
        mInput = (EditText) findViewById(R.id.et_text);
        mResult = (ImageView) findViewById(R.id.QR_imageview);
        mLogo = (CheckBox) findViewById(R.id.cb_logo);
        calen= (CalendarView) findViewById(R.id.calendarView);
    }

    public void scan(View v) {
        startActivityForResult(new Intent(MainActivity.this, CaptureActivity.class), 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            Bundle bundle = data.getExtras();
            
            String result = bundle.getString("result");
            mTvResult.setText(result);
        }
    }

    public void make(View v) {
        String input = mInput.getText().toString();
        if (input.equals("")) {
            Toast.makeText(MainActivity.this, "输入不能为空", Toast.LENGTH_LONG).show();
        } else {
            Bitmap bitmap = EncodingUtils.createQRCode(input, 300, 300,
                    mLogo.isChecked() ?
                            BitmapFactory.decodeResource(getResources(), R.mipmap.logo_black_24dp) : null);
            mResult.setImageBitmap(bitmap);
        }
    }
}
