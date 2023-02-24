package test1;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lee
 * @date 2022-12-16 20:29:19
 * @description
 */
public class MqttPlayLoadToString {
    public MqttPlayLoadToString() {
    }
    //把传过来的消息负载转为16进制
    public static String bytes_String16(byte[] b) {
        char[] _16 = {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
        StringBuilder sb = new StringBuilder();
        for(int i = 0 ; i<b.length;i++) {
            sb.append(_16[b[i]>>4&0xf])
                    .append(_16[b[i]&0xf]);
        }
        String TransFerResult=sb.toString();
        String FinalResult="";
        for (int i = 0; i < TransFerResult.length(); i++) {
            if(i%2==0&&i!=0){
                FinalResult+=" ";
                FinalResult+=TransFerResult.charAt(i);
            }else{
                FinalResult+=TransFerResult.charAt(i);
            }
        }
        return FinalResult;
    }
    public static Map<String,Object> hexToInt(String hex){
        //按空格把传进来的字符串分割成字符串数组
        String [] s;

        //初始化一个map
        Map<String,Object> stringObjectMap=new HashMap<>();
        String temperString="";
        Integer [] integers=new Integer[50];
        s=hex.split(" ");
        for (int i = 0; i < s.length; i++) {
            System.out.println(s[i]);
            if(i<=3){//存mac地址
                temperString+=String.valueOf(Integer.parseInt(s[i],16));
                if(i==3){
                    stringObjectMap.put("mac",temperString);

                    temperString="";
                }

            }else if(i>3&&i<=8){//存第一个温度
                temperString+=String.valueOf(Integer.parseInt(s[i],16)-48);

                if(i==8)  {
                    stringObjectMap.put("Temp1",formateInterger(temperString)) ;
                    System.out.println(temperString);
                    temperString="";
                }

            } else if (i>8&&i<=13) {
                temperString+=String.valueOf(Integer.parseInt(s[i],16)-48);
                if(i==13)  {
                    stringObjectMap.put("Temp2",formateInterger(temperString)) ;
                    temperString="";
                }

            } else if (i>13&&i<=18) {
                temperString+=String.valueOf(Integer.parseInt(s[i],16)-48);
                if(i==18)  {
                    stringObjectMap.put("Temp3",formateInterger(temperString)) ;
                    temperString="";
                }

            } else if (i>18&&i<=23) {
                temperString+=String.valueOf(Integer.parseInt(s[i],16)-48);
                if(i==23){
                    stringObjectMap.put("Temp4",formateInterger(temperString)) ;
//                    temperString="";
                }

            }

//            System.out.println(integers[i]);
        }

        return stringObjectMap;
    }

    public static int formateInterger(String num){
        int res=0;

            res+=Integer.parseInt(String.valueOf(num.charAt(4)))*1;
            res+=Integer.parseInt(String.valueOf(num.charAt(3)))*10;
            res+=Integer.parseInt(String.valueOf(num.charAt(2)))*100;
            res+=Integer.parseInt(String.valueOf(num.charAt(1)))*1000;
            res+=Integer.parseInt(String.valueOf(num.charAt(0)))*10000;
        System.out.println(res);
            return res;
    }
    public static void main(String[] args) {
        System.out.println("0.33".split(".").length);
        System.out.println(MqttPlayLoadToString.hexToInt("59 76 2e 09 30 30 30 31 34 30 30 30 31 35 30 30 30 31 35 30 30 30 31 35 30 30 32 33 36"));
//        8C 3A 03 07 30 30 30 31 34 30 30 30 31 34 30 30 30 31 34 30 30 30 31 34 30 30 32 33 33 0D OA
//          58 4f 40 27 30 31 30 32 33 30 31 30 32 33 30 31 30 32 33 30 31 30 32 33 30 30 32 32 38
//        00 01 40 00 15 00 01 50 00 15 00 23 6


    }

}
