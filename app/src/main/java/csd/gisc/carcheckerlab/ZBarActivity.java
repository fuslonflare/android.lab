package csd.gisc.carcheckerlab;

import android.content.Intent;
import android.hardware.Camera;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;

import me.dm7.barcodescanner.zbar.Result;
import me.dm7.barcodescanner.zbar.ZBarScannerView;

public class ZBarActivity extends AppCompatActivity implements ZBarScannerView.ResultHandler {

    private static final String TAG = ZBarActivity.class.getSimpleName();
    private static final int CAMERA_ID = Camera.CameraInfo.CAMERA_FACING_BACK;

    private ZBarScannerView zBarScannerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zbar);

        FrameLayout contentFrame = findViewById(R.id.content_frame);
        zBarScannerView = new ZBarScannerView(this);
        contentFrame.addView(zBarScannerView);
    }

    @Override
    protected void onResume() {
        super.onResume();
        zBarScannerView.setResultHandler(this);
        zBarScannerView.startCamera(CAMERA_ID);
    }

    @Override
    protected void onPause() {
        super.onPause();
        zBarScannerView.stopCamera();
    }

    @Override
    public void handleResult(Result result) {
        //Toast.makeText(this, "result = " + result.getContents(), Toast.LENGTH_SHORT).show();
        setResult(RESULT_OK, new Intent().putExtra("result", result.getContents()));
        finish();
    }
}


