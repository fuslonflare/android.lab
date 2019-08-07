package csd.gisc.carcheckerlab;

import android.Manifest;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import androidx.annotation.NonNull;
import com.google.android.material.snackbar.Snackbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.jraska.falcon.Falcon;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ScreenshotActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = ScreenshotActivity.class.getSimpleName();
    private static final int WRITE_EXTERNAL_STORAGE_PERMISSION_REQUEST_CODE = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screenshot);

        AppCompatButton screenshotButton = findViewById(R.id.button_screenshot);
        screenshotButton.setOnClickListener(this);

        checkWriteExternalStoragePermission();
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.button_screenshot) {
            Snackbar.make(findViewById(android.R.id.content),
                    "งาน 7119 ถูกปิดแล้ว เมื่อวันที่ 19/09/2018 เวลา 14:59:17 น.",
                    Snackbar.LENGTH_LONG)
                    .addCallback(new Snackbar.Callback() {
                        @Override
                        public void onShown(Snackbar sb) {
                            super.onShown(sb);
                            screenshotWrapper();
                        }
                    })
                    .show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == WRITE_EXTERNAL_STORAGE_PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Write external storage permission granted", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Write external storage permission denied", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void checkWriteExternalStoragePermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, WRITE_EXTERNAL_STORAGE_PERMISSION_REQUEST_CODE);
        }
    }

    private File getScreenshotDirectory(Context context) {
        ApplicationInfo applicationInfo = context.getApplicationInfo();
        int stringId = context.getApplicationInfo().labelRes;
        String directoryName = stringId == 0 ? applicationInfo.nonLocalizedLabel.toString() :
                context.getString(stringId);

        File rootDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        File directory = new File(rootDirectory, directoryName);

        if (!directory.exists()) {
            if (!directory.mkdirs()) {
                Log.e(TAG, "Unable to create screenshot directory " + directory.getAbsolutePath());
            }
        }
        return directory;
    }

    private void screenshotWrapper() {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", new Locale("th")).format(new Date());

        File file = new File(getScreenshotDirectory(this), "/Screenshot_" + timeStamp + ".png");

        Falcon.takeScreenshot(this, file);
        Toast.makeText(this, "Screenshot saved in " + file.getAbsolutePath(),
                Toast.LENGTH_LONG).show();
    }
}
