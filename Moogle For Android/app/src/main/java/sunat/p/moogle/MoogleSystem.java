package sunat.p.moogle;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by Acer- on 24/3/2561.
 */
import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.content.Intent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import sunat.p.moogle.MainActivity;

public class MoogleSystem extends Activity {

    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mooglesystem);

        Button Start = (Button) findViewById(R.id.GetBack);
        Start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MoogleSystem.this , MainActivity.class);
                startActivity(intent);

            }
        });


    }
}
