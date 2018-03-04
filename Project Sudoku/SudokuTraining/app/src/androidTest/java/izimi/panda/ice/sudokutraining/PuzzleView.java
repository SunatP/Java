package izimi.panda.ice.sudokutraining;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.FontMetrics;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;

public class PuzzleView extends View {
    private final PuzzleActivity game;
    private static final String SELX = "selX";
    private static final String SELY = "selY";
    private static final String VIEW_STATE = "viewState";

    public PuzzleView(Context context) {
        super(context);
        // �纡����ҧ�ԧ�ͧ PuzzleActivity ����������¡�����ʹ�
        // PuzzleActivity
        this.game = (PuzzleActivity) context;
        setFocusable(true);
        setFocusableInTouchMode(true);
        setId(99);
    }

    private float width; // �������ҧ�ͧ��ͧ���ҧ
    private float height; // �����٧�ͧ��ͧ���ҧ
    private int selX; // ���˹���ǹ͹�ͧ��������
    private int selY; // ���˹���ǵ�駢ͧ��������
    private final Rect selRect = new Rect(); // ��鹷��ͧ��ͧ���ҧ����繵��˹���������

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        width = w / 9f;
        height = h / 9f;
        getRect(selX, selY, selRect);
        super.onSizeChanged(w, h, oldw, oldh);
    }

    private void getRect(int x, int y, Rect rect) {
        rect.set((int) (x * width), (int) (y * height),
                (int) (x * width + width), (int) (y * height + height));
    }

    @Override
    protected void onDraw(Canvas canvas) {

        }


}
