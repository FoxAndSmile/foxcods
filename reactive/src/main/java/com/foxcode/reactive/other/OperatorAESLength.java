package com.foxcode.reactive.other;

public class OperatorAESLength {
    public static void main(String[] args) {

        String x = "C704C716F4D7387428C4AAFE4DE3EF99C704C716F4D7387428C4AAFE4DE3EF99C704C716F4D7387428C4AAFE4DE3EF99C704C716F4D7387428C4AAFE4DE3EF990650FBCCA317842A5421DA375E34F707";
        System.out.println(x.length());
        System.out.println(Math.ceil(6/16));
        System.out.println("6 : " + getAesLengthSizeBySize(6));
        System.out.println("64 : " + getAesLengthSizeBySize(64));
        System.out.println("128 : " + getAesLengthSizeBySize(128));
        System.out.println("256 : " + getAesLengthSizeBySize(256));
    }

    public static int getAesLengthSizeBySize(int size) {
        return (int) (16 * (Math.ceil(size / 16) + 1)) * 2;
    }
}
