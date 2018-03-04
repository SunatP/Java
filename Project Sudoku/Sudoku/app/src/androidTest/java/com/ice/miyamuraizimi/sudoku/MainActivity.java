package com.ice.miyamuraizimi.sudoku;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Button;
import android.view.Menu;
import android.view.MenuItem;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.util.Log;

public class MainActivity extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // ��˹� Listener ���Ѻ���� ������������
        View btnNewGame = findViewById(R.id.new_game_button);
        btnNewGame.setOnClickListener(this);
        // ��˹� Listener ���Ѻ���� ��������ͨҡ����
        View btnContinue = findViewById(R.id.continue_button);
        btnContinue.setOnClickListener(this);
        // ��˹� Listener ���Ѻ���� ��͡�ҡ���
        View btnExit = findViewById(R.id.exit_button);
        btnExit.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
        case R.id.settings:
            Intent i = new Intent(this, SettingsActivity.class);
            startActivity(i);
            return true;
            // ...
            // ������Ѻ�������������� (�����)
            // ...
        }
        return false;
    }

    public void onClick(View v) {
        switch (v.getId()) {
        case R.id.continue_button:
            startGame(PuzzleActivity.DIFFICULTY_CONTINUE);
            break;
        case R.id.new_game_button:
            openNewGameDialog();
            break;
        case R.id.exit_button:
            finish();
            break;
        }
    }

    private static final String TAG = "Sudoku";

    private void openNewGameDialog() {
        // ���ҧ Alert Dialog
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);

        // ��˹� Title �ͧ Alert Dialog
        dialog.setTitle(R.string.new_game_title);
        
        /*
         * ��˹���¡�õ�����͡� Alert Dialog ��� Listener
         * ����кء�÷ӧҹ����͵�����͡� Alert Dialog �١���͡
         */
        dialog.setItems(R.array.difficulty, 
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        startGame(i); // �����������
                    }
                });
        
        // �ʴ� Alert Dialog �͡��
        dialog.show();
    }

    private void startGame(int i) {
        Log.d(TAG, "�س���͡ " + i);
        // ������Ѻ�����������
        Intent intent = new Intent(this, PuzzleActivity.class);
        intent.putExtra(PuzzleActivity.KEY_DIFFICULTY, i);
        startActivity(intent);
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
