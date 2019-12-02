package com.ice.miyamuraizimi.sudoku;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.Toast;

public class PuzzleActivity extends Activity {
    protected static final String KEY_DIFFICULTY = "difficulty";
    protected static final int DIFFICULTY_EASY = 0;
    protected static final int DIFFICULTY_MEDIUM = 1;
    protected static final int DIFFICULTY_HARD = 2;

    // ��������Ѻ�кض֧������ʶҹТͧ��
    private static final String PREF_PUZZLE = "puzzle";
    // ��ҷ��͡�����������Ŵʶҹ�����ͧ����Ѻ�� ᷹��������������� 
    protected static final int DIFFICULTY_CONTINUE = -1;

    private int puzzle[] = new int[9 * 9];

    private PuzzleView puzzleView;
    
    private final String easyPuzzle = 
            "000070000080000273009036800" +
            "001247000807050309000389400" +
            "003510700124000090000020000";

        private final String mediumPuzzle = 
            "300000056400000000000897000" +
            "002080400006103800005070300" +
            "000462000000000008510000007";

        private final String hardPuzzle = 
            "462900000900100000705800000" +
            "631000000000000000000000259" +
            "000005901000008003000001542";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int diff = getIntent().getIntExtra(KEY_DIFFICULTY, DIFFICULTY_EASY);
        puzzle = getPuzzle(diff);
        calculateUsedTiles();

        puzzleView = new PuzzleView(this);
        setContentView(puzzleView);
        puzzleView.requestFocus();
        
        getIntent().putExtra(KEY_DIFFICULTY, DIFFICULTY_CONTINUE);
    }

    protected void showKeypadOrError(int x, int y) {
        // �ҵ���Ţ���١�����������Ѻ��ͧ�����˹� x, y
        int tiles[] = getUsedTiles(x, y);

        /*
         * ��ҵ���Ţ 1-9 �١��������Ǩ��ʴ� Toast
         * �͡���������������ö�������Ţ�����ͧ�����
         * �����ѧ����͵���Ţ�����������ʴ�˹�Ҩ� Keypad �����
         */
        if (tiles.length == 9) {
            Toast toast = Toast.makeText(this, R.string.no_moves_label,
                    Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        } else {
            Dialog v = new Keypad(this, tiles, puzzleView);
            v.show();
        }
    }

    protected boolean setTileIfValid(int x, int y, int value) {
        // �ҵ���Ţ���١�����������Ѻ��ͧ�����˹� x, y
        int tiles[] = getUsedTiles(x, y);

        if (value != 0) {
            for (int tile : tiles) {
                // ��ҵ���Ţ����ͧ��������١������� ����͡�ҡ���ʹ�ѹ��
                if (tile == value)
                    return false;
            }
        }

        setTile(x, y, value); // �������Ţ value ŧ㹪�ͧ�����˹� x, y
        calculateUsedTiles(); // �ҵ���Ţ���١�����������Ѻ�ء��ͧ����
        return true;
    }

    private final int used[][][] = new int[9][9][];

    protected int[] getUsedTiles(int x, int y) {
        return used[x][y];
    }

    private void calculateUsedTiles() {
        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                used[x][y] = calculateUsedTiles(x, y);
            }
        }
    }

    private int[] calculateUsedTiles(int x, int y) {
        int c[] = new int[9]; // �������좹Ҵ 9 ��Ҫԡ ����Ѻ�纵���Ţ 1 �֧ 9

        // �Ǻ�������Ţ���١�������������ǡѹ (�ǹ͹)
        for (int i = 0; i < 9; i++) {
            if (i == y) // ��������Ţ㹪�ͧ�ͧ����ͧ�
                continue;
            int t = getTile(x, i);
            if (t != 0) // �������Ţ 0 ������¶֧��ͧ��ҧ
                c[t - 1] = t;
        }

        // �Ǻ�������Ţ���١�������㹤���������ǡѹ (�ǵ��)
        for (int i = 0; i < 9; i++) {
            if (i == x) // ��������Ţ㹪�ͧ�ͧ����ͧ�
                continue;
            int t = getTile(i, y);
            if (t != 0) // �������Ţ 0 ������¶֧��ͧ��ҧ
                c[t - 1] = t;
        }

        // �Ǻ�������Ţ㹵��ҧ���� 3x3 ���ǡѹ
        int startx = (x / 3) * 3; // �ش���������ǹ͹�ͧ���ҧ���� 3x3
        int starty = (y / 3) * 3; // �ش���������ǵ�駢ͧ���ҧ���� 3x3
        for (int i = startx; i < startx + 3; i++) {
            for (int j = starty; j < starty + 3; j++) {
                if (i == x && j == y) // ��������Ţ㹪�ͧ�ͧ����ͧ�
                    continue;
                int t = getTile(i, j);
                if (t != 0) // �������Ţ 0 ������¶֧��ͧ��ҧ
                    c[t - 1] = t;
            }
        }

        // �ŧ�������� c ���������� c1
        // ����բ�Ҵ��ҡѺ�ӹǹ����Ţ���١��仨�ԧ�
        int nused = 0;
        for (int t : c) { // ������������� c1 ����բ�Ҵ����
            if (t != 0)
                nused++;
        }
        int c1[] = new int[nused]; // ���ҧ�������� c1 �����Ҵ�������
        nused = 0;
        for (int t : c) { // ��ҵ���Ţ�������� 0 �ҡ�������� c
                          // ����ŧ��������� c1
            if (t != 0)
                c1[nused++] = t;
        }

        return c1; // �觤׹�������� c1 ��Ѻ�͡仨ҡ���ʹ
    }

    private void setTile(int x, int y, int value) {
        puzzle[y * 9 + x] = value;
    }

    private int getTile(int x, int y) {
        return puzzle[y * 9 + x];
    }

    protected String getTileString(int x, int y) {
        int v = getTile(x, y);

        // ������Ţ 0 �Ф׹ʵ�ԧ��ҧ�͡� ����������Ф׹���ʵ�ԧ�ͧ�Ţ����͡�
        if (v == 0)
            return "";
        else
            return String.valueOf(v);
    }

    private int[] getPuzzle(int diff) {
        String puz;
            
        switch (diff) {
        case DIFFICULTY_CONTINUE:
            puz = getPreferences(MODE_PRIVATE).getString(PREF_PUZZLE, 
                  easyPuzzle);
            break;
        case DIFFICULTY_HARD:
            puz = hardPuzzle;
            break;
        case DIFFICULTY_MEDIUM:
            puz = mediumPuzzle;
            break;
        case DIFFICULTY_EASY:
        default:
            puz = easyPuzzle;
            break;
        }
            
        return fromPuzzleString(puz);
    }

    static protected int[] fromPuzzleString(String str) {
        int[] puz = new int[str.length()];
        for (int i = 0; i < puz.length; i++) {
            puz[i] = str.charAt(i) - '0';
        }
        return puz;
    }

    static private String toPuzzleString(int[] puz) {
        StringBuilder buf = new StringBuilder();
        for (int element : puz) {
            buf.append(element);
        }
        return buf.toString();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Music.play(this, R.raw.game);
    }
        
    @Override
    protected void onPause() {
        super.onPause();
        Music.stop();
        
        // ��ʶҹлѨ�غѹ�ͧ��ŧ� Preference
        getPreferences(MODE_PRIVATE).edit().putString(PREF_PUZZLE, 
            toPuzzleString(puzzle)).commit();
    }
}
