package izimi.panda.ice.sudokutraining;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }


    public void onClick(View v) {

    }



    private void openNewGameDialog() {

    }

    private void startGame(int i) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        Music.play(this, R.raw.main);
    }
        
    @Override
    protected void onPause() {
        super.onPause();
        Music.stop();
    }
}
