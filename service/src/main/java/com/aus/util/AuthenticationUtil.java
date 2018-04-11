package com.aus.util;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * 这个工具是根据linux文件权限系统原理扩展而来
 *  按照linux 有局限性 在java 中最大只能2^63，也就是最多只能表示63个菜单
 *  扩展后可无限大
 * Created by xy on 2017/11/24.
 */
public class AuthenticationUtil {

    public static void main(String[] args) {
        System.out.println(authentication("11000000000", 7));
    }

    /**
     * 鉴权
     * @param str
     * @param pow 通常用表id表示
     * @return
     */
    public static Boolean authentication(String str, int pow){
        if (str == null || str.trim().length() == 0){
            return false;
        }
        return AuthenticationUtil.andOperation(str, pow).equals(intToStr(pow));
    }

    /**
     * 与运算
     * @param str 或运算后结果
     * @param pow 值
     * @return
     */
    public static String andOperation(String str, int pow){
        byte[] args1 = new byte[str.length()];
        for (int i = 0; i < args1.length; i++) {
            args1[i] = (byte)str.charAt(i);
        }
        byte[] args2 = intToByte(pow);
        int length = Math.max(args1.length, args2.length);
        byte[] c1 = align(args1, length);
        byte[] c2 = align(args2, length);
        byte[] result = new byte[length];
        for (int i = 0; i < length; i++) {
            result[i] = (byte) (c1[i] & c2[i]);
        }
        return byteToStr(result);
    }

    /**
     * 批量或运算
     * @param args
     * @return
     */
    public static String orOperation(String ...args){
        if (args == null || args.length == 0){
            return "0";
        }
        Integer[] ints = new Integer[args.length];
        for (int i = 0; i < args.length; i++) {
            if (StringUtils.isEmpty(args[i])){
                return "0";
            }
            ints[i] = Integer.parseInt(args[i]);
        }
        return orOperation(ints);
    }

    /**
     * 批量或运算
     * @param args
     * @return
     */
    public static String orOperation(Integer ...args){
        if (args == null || args.length == 0){
            return "";
        }
        List<byte[]> bytes = new ArrayList<>(args.length);
        for (int i = 0; i < args.length; i++) {
            bytes.add(intToByte(args[i]));
        }
        return orOperation(bytes.toArray(new byte[0][]));
    }

    /**
     * 批量或运算
     * @param args
     * @return
     */
    private static String orOperation(byte[] ...args){
        byte[] result = null;
        if (args.length == 1){
            result = orOperation(args[0], args[0]);
        }else{
            for (int i = 0; i < args.length; i++) {
                if (i == 0){
                    result = orOperation(args[i], args[++i]);
                }else{
                    result = orOperation(result, args[i]);
                }
            }
        }
        return byteToStr(result);
    }

    /**
     * 或运算
     * @param args1
     * @param args2
     * @return
     */
    private static byte[] orOperation(byte[] args1, byte[] args2){
        int length = Math.max(args1.length, args2.length);
        byte[] c1 = align(args1, length);
        byte[] c2 = align(args2, length);
        byte[] result = new byte[length];
        for (int i = 0; i < result.length; i++) {
            result[i] = (byte) (c1[i] | c2[i]);
        }
        return result;
    }

    /**
     * 字节数组转字符口串
     * @param args
     * @return
     */
    private static String byteToStr(byte[] args){
        StringBuffer stringBuffer = new StringBuffer();
        Boolean floor = false;
        for (int i = 0; i < args.length; i++) {
            if (args[i] != 0){
                floor = true;
            }
            if (floor){
                stringBuffer.append(args[i]);
            }
        }
        return stringBuffer.toString();
    }

    /**
     * 数组对齐
     * @param args
     * @param length
     * @return
     */
    private static byte[] align(byte[] args, int length){
        if (args.length == length){
            return args;
        }
        byte[] pool = new byte[length];
        int index = args.length - 1;
        for (int i = pool.length - 1; i >= 0 && index >= 0; i--) {
            pool[i] = args[index--];
        }
        return pool;
    }

    /**
     * 2^pow转字节数组
     * @param pow
     * @return
     */
    private static byte[] intToByte(int pow) {
        byte[] bin = new byte[pow + 1];
        for (int i = 0; i < pow; i++) {
            bin[i] = 0;
        }
        bin[0] = 1;
        return bin;
    }

    /**
     * 2^pow转字符串
     * @param pow
     * @return
     */
    public static String intToStr(int pow) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(1);
        for (int i = 0; i < pow; i++) {
            stringBuffer.append(0);
        }
        return stringBuffer.toString();
    }

}
