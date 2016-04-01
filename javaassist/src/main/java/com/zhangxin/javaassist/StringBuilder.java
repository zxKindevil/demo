package com.zhangxin.javaassist;

/**
 * @author zhangxin.zhang created on 16-4-1.
 */
public class StringBuilder
{
    private String buildString(int length) {
        String result = "";
        for (int i = 0; i < length; i++) {
            result += (char)(i%26 + 'a');
        }
        return result;
    }

    public static void main(String[] argv) {
        argv = new String[] {"1000", "2000", "4000", "8000", "6000"};
        StringBuilder inst = new StringBuilder();
        for (int i = 0; i < argv.length; i++) {
            String result = inst.buildString(Integer.parseInt(argv[i]));
            System.out.println("Constructed string of length " +
                    result.length());
        }
    }
}
