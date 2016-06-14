package com.other.net;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;

import android.util.Log;

public class StringUtil {

	/**
	 * 拆分字符串
	 * 
	 * @param src
	 * @param mark
	 * @return
	 */
	public static String[] split(String src, String mark) {
		if (src == null)
			return null;
		Vector<String> split = new Vector<String>();
		while (true) {
			int index = src.indexOf(mark);
			if (index == -1) {
				// 没有拆分的情况下，整个字符返回
				if (src != null && !src.equals(""))
					split.addElement(src);
				break;
			} else {
				// 有的话拆分
				split.addElement(src.substring(0, index));
				src = src.substring(index + 1, src.length());
			}
		}

		String[] s = new String[split.size()];
		// vector-->String[]
		split.copyInto(s);
		return s;
	}

	public static final String readStringFromBytes(byte[] bytes) {
		ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
		DataInputStream dis = new DataInputStream(bais);
		String string = "";
		try {
			int ch;
			StringBuffer sb = new StringBuffer();
			while ((ch = dis.read()) != -1) {
				string = sb.append((char) ch).toString();
			}
			bais.close();
			dis.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return string;
	}

	public static String parse(String src, boolean flag) {
		src = src.trim();
		src = StringUtil.split(src, "~")[1];
		if (flag)
			return unicodeToString(src).trim();
		else
			return src.trim();
	}

	public static int char2int(char t) {
		Character c = new Character(t);
		String s = c.toString();
		return Integer.parseInt(s);
	}

	public static String getFormatXmlTime(String timestr, int mode) {
		// SimpleDateFormat simpleDateFormat=new
		// SimpleDateFormat("yyyy年MM月dd日HH小时mm分钟ss秒");
		//
		// StringBuffer sb = new StringBuffer("");
		// String[] s01 = split(timestr.substring(0, timestr.indexOf(" ")),"-");
		// String[] s02 = split(timestr.substring(timestr.indexOf(" ") +
		// 1),":");
		// sb.append(s01[0] + "年");
		// sb.append(s01[1] + "月");
		// sb.append(s01[2] + "日");
		// sb.append(s02[0] + "小时");
		// sb.append(s02[1] + "分钟");
		// sb.append(s02[2] + "秒");
		// //加LOG排错
		// long l = 0;
		//
		// try {
		// l = simpleDateFormat.parse(sb.toString()).getTime();
		// } catch (ParseException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		//
		// l = l - System.currentTimeMillis();
		// StringBuffer sb1 = new StringBuffer("");
		// if(l > 0){
		// // /秒/分钟/小时/天
		//		
		// long Days = l / 1000 / 60 / 60/24;
		// long Hours = (l - Days * 1000 * 60 * 60 * 24)/ 1000 / 60 / 60;
		// long Minutes = (l - Hours * 1000 * 60 * 60 - Days * 1000 * 60 * 60 *
		// 24) / 1000/60;
		// long Seconds = (l - Hours * 1000 * 60 * 60 - Days * 1000 * 60 * 60 *
		// 24
		// - Minutes * 1000 * 60 )/ 1000;
		// if(Max4 > 0){
		// sb1.append(Days);
		// sb1.append("天");
		// }
		// if(Max4 > 1){
		// sb1.append(Hours);
		// sb1.append("小时");
		// }
		// if(Max4 > 2){
		// sb1.append(Minutes);
		// sb1.append("分");
		// }
		// if(Max4 > 3){
		// sb1.append(Seconds);
		// sb1.append("秒");
		// }
		//		
		// }else{
		// sb1.append("0分0秒0小时");
		// }
		// return sb1.toString();
		String str = "";
		long l = Long.parseLong(timestr);
		Log.i("StringUtil", "old" + String.valueOf(l));
		l = l*1000 - System.currentTimeMillis();
		Log.i("StringUtil", "System.currentTimeMillis()" + String.valueOf(System.currentTimeMillis()));
		Log.i("StringUtil", "new" + String.valueOf(l));
		//getLongDate(l,star,end).toString();
		//str = getLongDate(l,star,end);
		str = format(l,mode);
		Log.i("StringUtil", str);

		return str;
	}

	public static String format(long ms , int mode) {//将毫秒数换算成x天x时x分x秒x毫秒
		   int ss = 1000;
		   int mi = ss * 60;
		   int hh = mi * 60;
		   int dd = hh * 24;

		   long day = ms / dd;
		   long hour = (ms - day * dd) / hh;
		   long minute = (ms - day * dd - hour * hh) / mi;
		   long second = (ms - day * dd - hour * hh - minute * mi) / ss;
		   //long milliSecond = ms - day * dd - hour * hh - minute * mi - second * ss;

		   String strDay = day < 10 ? "0" + day : "" + day;
		   String strHour = hour < 10 ? "0" + hour : "" + hour;
		   String strMinute = minute < 10 ? "0" + minute : "" + minute;
		   String strSecond = second < 10 ? "0" + second : "" + second;
		   //String strMilliSecond = milliSecond < 10 ? "0" + milliSecond : "" + milliSecond;
		   //strMilliSecond = milliSecond < 100 ? "0" + strMilliSecond : "" + strMilliSecond;
	if(mode == 0){
		   return strDay + "天" + strHour + "小时" + strMinute + "分钟" + strSecond + "秒";
	}else{
		   return strDay + "天" + strHour + "小时";	
	}	
	} 
	
	// 共6位
	public static String getLongDate(long timeMillis, int star, int end) {
		SimpleDateFormat dateFormat = null;

		// SimpleDateFormat dateFormat = new
		// SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		Vector<String> str = new Vector<String>();
		str.add("yyyy年");
		str.add("MM月");
		str.add("dd天");
		str.add("HH小时");
		str.add("mm分");
		str.add("ss秒");

		StringBuffer sb = new StringBuffer("");
		if(end - star <= 0){
			return "";
		}
		if(end > str.size() || star > str.size()){
			return "";
		}
		for (int i = 0; i < end - star + 1; i++) {
			sb.append(str.get(star + i));
		}
		
		//Log.i("StringUtil", sb.toString());
		
		dateFormat = new SimpleDateFormat(sb.toString());

			try {
				return conversion(dateFormat,timeMillis);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return "";
			} 
	}
	
	public static String conversion(SimpleDateFormat formatter_f,long time)
    {
   //这里可以对 毫秒数进行运算
//   time = time - 140000000;
  
   String datetime = formatter_f.format((new Date(time)));
  
   return datetime;  
    }

	public static String getFormatDate() {
		TimeZone timezone = TimeZone.getTimeZone("GMT+8");
		Calendar calendar = Calendar.getInstance(timezone);
		int i = calendar.get(1);
		int j = calendar.get(2) + 1;
		int k = calendar.get(5);
		int week = calendar.get(7);

		StringBuffer sb = new StringBuffer();
		switch (week) {
		case 1:
			sb.append("星期日");
			break;
		case 2:
			sb.append("星期一");
			break;
		case 3:
			sb.append("星期二");
			break;
		case 4:
			sb.append("星期三");
			break;
		case 5:
			sb.append("星期四");
			break;
		case 6:
			sb.append("星期五");
			break;
		case 7:
			sb.append("星期六");
			break;
		}
		sb.append(" ");
		sb.append(i);
		sb.append("年");
		sb.append(j);
		sb.append("月");
		sb.append(k);
		sb.append("日");

		return sb.toString();
	}

	/**
	 * 取得当前时间
	 * 
	 * @return
	 */
	public static String getFormatTime() {
		TimeZone timezone = TimeZone.getTimeZone("GMT+8");
		Calendar calendar = Calendar.getInstance(timezone);
		int l = calendar.get(11);
		int i1 = calendar.get(12);
		// int j1 = calendar.get(13);

		StringBuffer sb = new StringBuffer();

		sb.append(l >= 10 ? "" : "0");
		sb.append(l);
		sb.append(":");
		sb.append(i1 >= 10 ? "" : "0");
		sb.append(i1);

		return sb.toString();
	}

	public static String getCurrentTime() {
		return null;
	}

	public static String stringToUnicode(String s) {
		if (s == null) {
			return null;
		}
		StringBuffer result = new StringBuffer();
		int i;
		for (i = 0; i < s.length(); i++) {

			if (s.charAt(i) >= 0x2018) // 汉字Unicode的编码都大于0x2018，只需要将汉字转换成Unicode
			{
				result.append('\\'); // 加上表示Unicode的
				result.append('u');
				String hex = Integer.toHexString(s.charAt(i)); //将对应字符转换成16进制表示（
																// 因为Unicode是16进制数表示
																// ）
				result.append(hex);
			} else if (s.charAt(i) == 0x005c) {
				result.append("\\u005c");
			} else // 英文的就不用转换了
			{
				result.append(s.charAt(i));
			}
		}
		return result.toString();
	}

	public static String unicodeToString(String s) {
		if (s == null) {
			return null;
		}
		StringBuffer result = new StringBuffer();
		int tempI, i, ch;
		for (i = 0; i < s.length(); i++) {
			if ((ch = s.charAt(i)) == '\\') // 如果是Unicode编码（开始是），则将它转换为汉字
			{
				tempI = i;
				i += 2;
				while (s.length() > i && s.charAt(i) == 'u') {
					i++;
				}
				if (s.length() >= i + 4) {
					ch = Integer.parseInt(s.substring(i, i + 4), 16); // 将Unicode16进制数转换为10进制
					i += 3;

				} else {
					i = tempI;
				}
			}
			// 对于汉字，将它从整形数据转换成字符后，附在result后，对于英文字符，直接使用就行
			result.append((char) ch);
		}
		return result.toString();
	}

	public static boolean isNumeric(String str) {
		Pattern pattern = Pattern.compile("[0-9]*");
		return pattern.matcher(str).matches();
	}

	public String StringFilter(String str) {
		String str1;
		str1 = str;
		str1 = str1.replace("#", " ");
		str1 = str1.replace("~", " ");
		return str1;
	}

	public static String TAG = "mm_pay";	
	public static String getMD5(InputStream fis){
		byte[] buffer = new byte[1024];
		MessageDigest md5 = null;
		try {
			md5 = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e1) {
			// TODO Auto-generated catch block
			Log.i(TAG, "md5 = MessageDigest.getInstance error");
			//e1.printStackTrace();
		}
		
		int numRead = 0;
		try {
			while ((numRead = fis.read(buffer)) > 0) {
			    md5.update(buffer, 0, numRead);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			Log.i(TAG, "while IO error");
		}
		try {
			fis.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			Log.i(TAG, "fis.close() error");
		}
		return toHexString(md5.digest());
	}
	
	public static String getHash(String fileName, String hashType) throws
    Exception {
InputStream fis;
fis = new FileInputStream(fileName);
byte[] buffer = new byte[1024];
MessageDigest md5 = MessageDigest.getInstance(hashType);
int numRead = 0;
while ((numRead = fis.read(buffer)) > 0) {
    md5.update(buffer, 0, numRead);
}
fis.close();
return toHexString(md5.digest());
}

public static String toHexString(byte[] b) {
	
	 char[] hexChar = {'0', '1', '2', '3',  
        '4', '5', '6', '7',  
        '8', '9', 'a', 'b',  
        'c', 'd', 'e', 'f'};  
	
StringBuilder sb = new StringBuilder(b.length * 2);
for (int i = 0; i < b.length; i++) {
    sb.append(hexChar[(b[i] & 0xf0) >>> 4]);
    sb.append(hexChar[b[i] & 0x0f]);
}
return sb.toString();
}


	
	//laberStr   = <laber>
public static String getLableValue(String oldStr,String laberStr1,String laberStr2){

	//Log.i("tag", "laberStr:" + laberStr);
	
	String str = "";
	int fristIndex = oldStr.indexOf(laberStr1) + laberStr1.length();
	if(oldStr.indexOf(laberStr1) == -1){
		Log.i("mm_pay","laberStr1 is N");
		return "N";
	}
	Log.i("mm_pay","fristIndex:" + fristIndex );
	str = oldStr.substring(fristIndex);
	Log.i("mm_pay","substring:" + str );
	
	
	if(str.indexOf(laberStr2) == -1){
		Log.i("mm_pay","laberStr2 is N");
		return "N";
	}
	str = str.substring(0,str.indexOf(laberStr2));
	Log.i("mm_pay","ok:" + str );
	//int lastIndex 
	
	return str;
}

static public int RandomInt(int mix, int max) {
	Random random = new Random();
	if (mix == max) {
		return mix;
	}
	int randomInt = mix + random.nextInt(max - mix);
	return randomInt;
}
	

public static String getCookieHeader(String oldStr){
	//wap_user=e32f988e556b5bdb92f8a98b911aabca;
	String str = "";
	int fristIndex = oldStr.indexOf("=");
	str = oldStr.substring(0,fristIndex);
	return str;
}
	
public static String getCookieValue(String oldStr){
	String str = "";
	int fristIndex = oldStr.indexOf(";");
	str = oldStr.substring(0,fristIndex);
	return str;
}


public static String getStringURL(String oldStr,String findStr){
String str = "";
int last = oldStr.lastIndexOf(">" + findStr + "</a>") - 1;
if(last == -2){
	Log.i(TAG, "last no find");
	return str;
}
str = oldStr.substring(0,last);
int frist = str.lastIndexOf("<a href=") + "<a href=".length() + 1;
if(frist == 0){
	Log.i(TAG, "frist no find");
	return str;
}
str = str.substring(frist);
Log.i(TAG, "getStringURL:" + str + "<--over");
return str;
}

}
