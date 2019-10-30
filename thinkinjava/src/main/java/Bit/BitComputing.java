package Bit;

/**
 * Created by wangbo on 2018/8/7.
 */
public class BitComputing {

    /*  <<     :     左移运算符，num << 1,相当于num乘以2
        >>     :     右移运算符，num >> 1,相当于num除以2
        >>>    :     无符号右移，忽略符号位，空位都以0补齐
     */

    public static void main(String[] args) {
        int i = 12;
        byte[] data = new byte[4];
        data[0] = (byte) (i & 0xFF);
        data[1] = (byte) (i >>> 8);
        data[2] = (byte) (i >>> 16);
        data[3] = (byte) (i >>> 24);

        int j = (data[0] & 0xFF) | ((data[1] & 0xFF) << 8) | ((data[2] & 0xFF) << 16) | ((data[3] & 0xFF) << 24);
        System.out.println(129 & 0xFF);
        System.out.println(j);

        int cap = 10;//1010
        System.out.println(cap >>> 2);

        System.out.println(1 ^ 0x1);//异或 1^1为0 1^0为1 0^0为1
    }

}
