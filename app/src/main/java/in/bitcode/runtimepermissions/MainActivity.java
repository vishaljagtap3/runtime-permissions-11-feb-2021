package in.bitcode.runtimepermissions;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //int permissionState = checkSelfPermission(Manifest.permission.READ_SMS);
        int permissionState =
                ActivityCompat.checkSelfPermission(
                        this,
                        Manifest.permission.READ_SMS
                );

        if(permissionState == PackageManager.PERMISSION_GRANTED) {
            mt("Permission Granted");
        }
        else {
            //Request permission
            ActivityCompat.requestPermissions(
                    this,
                    new String[] {Manifest.permission.READ_SMS, Manifest.permission.READ_EXTERNAL_STORAGE},
                    1
            );
            /*requestPermissions(
                    new String[] {Manifest.permission.READ_SMS, Manifest.permission.READ_EXTERNAL_STORAGE},
                    1
            );*/
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        for(int i  = 0; i < permissions.length; i++) {
            mt( permissions[i] + " - " +
                            (grantResults[i] == PackageManager.PERMISSION_GRANTED? " Granted" : "Denied")
                    );
        }
    }

    private void mt(String text) {
        Toast.makeText(this, text, Toast.LENGTH_LONG).show();
        Log.e("tag", text);
    }
}