package org.cocos2dx.extension;

import com.tencent.bugly.agent.GameAgent;
import java.io.UnsupportedEncodingException;

public class CocosBuglyAgent {
    public static void setLog(int level, String tag, byte[] message) {
        try {
            GameAgent.setLog(level, tag, new String(message, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
