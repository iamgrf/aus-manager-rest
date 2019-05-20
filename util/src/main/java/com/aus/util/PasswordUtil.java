package com.aus.util;

public class PasswordUtil {

    private static String generate(String password) {
        for (int i = 0; i < 5; i++) {
            password = StringUtil.md5(password).toUpperCase();
        }
        return password;
    }

    /**
     * 生成密码
     */
    public static String generatePassword(String password){
        return generate(password);
    }

    /**
     * 校验密码
     * @param password
     * @param ciphertext
     * @return
     */
    public static boolean verify(String password, String ciphertext){
        String newPossword = generate(password);
        return newPossword.equals(ciphertext);
    }

    public static void main(String[] args) {
        System.out.println(generatePassword(StringUtil.md5("admin") + "admin"));
        System.out.println(verify(StringUtil.md5("admin"), "9F6AD23B1BDBB1C67C9FB35A90C0A646"));
    }

}
