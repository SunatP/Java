package izimi.panda.ice.sudokutraining;

import android.content.Context;
import android.media.MediaPlayer;

public class Music {
    private static MediaPlayer mPlayer = null;

    // ��ش����ŧ�������ҡ�͹ �������������ŧ������ Resource ID ����к�
    public static void play(Context context, int resId) {
        stop();
        
        if (SettingsActivity.getOptionMusic(context)) {
            mPlayer = MediaPlayer.create(context, resId);
            mPlayer.setLooping(true);  // ����ͨ��ŧ����Ѻ����������� ǹ�����������
            mPlayer.start();
        }
    }
    
    // ��ش����ŧ
    public static void stop() {
        if (mPlayer != null) {
            mPlayer.stop();
            mPlayer.release();
            mPlayer = null;
        }
    }
}
