package com.kituri.tools;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.data.Constants.Constants;
import com.kituri.rich.GameSaveData;

import android.content.Context;
import android.content.SharedPreferences;


public class DataTools {
	static public String getData(Context context, String key) {
		// getSharedPreferences
		SharedPreferences scDB = context.getSharedPreferences(key, 0);
		return scDB.getString(key, "");
	}

	static public void setData(Context context, String key, String value) {
		SharedPreferences scDB = context.getSharedPreferences(key, 0);
		SharedPreferences.Editor mEditor = scDB.edit();
		mEditor.putString(key, value);
		mEditor.commit();
	}

	
	public void SaveGame(Context context) {
		FileOutputStream fos = null;
		ObjectOutputStream dos = null;
		GameSaveData gameSaveData = new GameSaveData();
		gameSaveData.InitData();
		try {
			// ------单纯用file来写入的方式--------------
			// fos = new FileOutputStream(f);
			// fos.write(et_login.getText().toString().getBytes());
			// fos.write(et_password.getText().toString().getBytes());
			// ------data包装后来写入的方式--------------
			fos = context.openFileOutput(Constants.FileName,
					Context.MODE_PRIVATE);// 备注2
			dos = new ObjectOutputStream(fos);

			//gameSaveData.InitData();

			dos.writeObject(gameSaveData);

            //Log.i("Save", "Save IS OK");
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// 在finally中关闭流 这样即使try中有异常我们也能对其进行关闭操作 ;
			try {
				dos.close();
				fos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
	static public boolean CanLoad(Context context){
		try {
			if (context.openFileInput(Constants.FileName) == null){
				return false;
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			return false;
		}
		return true;
	}

	public GameSaveData LoadGame(Context context) {
		GameSaveData gameSaveData = null;
		// gameSaveData.InitData();
		FileInputStream fis = null;

		ObjectInputStream dis = null;

		//Log.i("Save", "LoadGame try");

		try {
			// openFileInput 不像 sharedPreference 中
			// getSharedPreferences的方法那样找不到会返回默认值,
			// 这里找不到数据文件就会报异常,所以finally里关闭流尤为重要!!!
			if (context.openFileInput(Constants.FileName) != null) {
				//Log.i("Save","context.openFileInput(Constants.FileName) != null");
				fis = context.openFileInput(Constants.FileName);// 备注1
				dis = new ObjectInputStream(fis);
				
				//Log.i("Save", "(GameSaveData) dis.readObject() reday");
				
				gameSaveData = (GameSaveData) dis.readObject();
				
				//Log.i("Save", "(GameSaveData) dis.readObject() over");
				
				//Log.i("Save", "gameSaveData:" + gameSaveData.playerIndex);
//				if (gameSaveData.player == null) {
//					Log.i("Save", "gameSaveData.player == null");
//				}else{
//					Log.i("Save", "gameSaveData.player.length :" + gameSaveData.player.length);
//				}
				// 这里也是在刚启动程序的时候去读入存储的数据
				// 读的时候要注意顺序; 例如我们写入数据的时候
				// 先写的字符串类型,我们也要先读取字符串类型,一一对应！
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			//Log.i("Save", "FileNotFoundException e" + e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//Log.i("Save", "IOException e" + e.getMessage());
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			//Log.i("Save", "ClassNotFoundException e" + e.getMessage());
			e.printStackTrace();
		} finally {
			// 在finally中关闭流!因为如果找不到数据就会异常我们也能对其进行关闭操作 ;
			try {
				if (context.openFileInput(Constants.FileName) != null) {
					// 这里也要判断，因为找不到的情况下，两种流也不会实例化。
					// 既然没有实例化，还去调用close关闭它,肯定"空指针"异常！！！
					fis.close();
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return gameSaveData;
	}

}
