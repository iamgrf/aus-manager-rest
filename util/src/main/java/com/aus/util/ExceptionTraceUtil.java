package com.aus.util;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Created by xy on 2017/6/15.
 */
public class ExceptionTraceUtil {

    /**
     * 解析异常
     * @param e
     * @return
     */
    public static String getTrace(Throwable e) {
        StringWriter stringWriter= new StringWriter();
        PrintWriter writer= new PrintWriter(stringWriter);
        e.printStackTrace(writer);
        StringBuffer buffer= stringWriter.getBuffer();
        return buffer.toString();
    }

}
