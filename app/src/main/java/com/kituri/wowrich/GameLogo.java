package com.kituri.wowrich;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

import com.data.Constants.Constants;
import com.kituri.mediapPlayer.mediaPlayer;
import com.kituri.rich.CustomDialog;
import com.kituri.tools.CustomRequest;
import com.kituri.tools.DataTools;

public class GameLogo extends Activity implements OnClickListener,
		CustomRequest {

	private ImageButton GameStar;
	private ImageButton GameSet;
	private ImageButton GameView;
	private ImageButton GameExit;
	private CustomDialog customDialog;
	private final int TYPE_SOUND = 0;
	private final int LAST_STEP = 1;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.logo);
		GameStar = (ImageButton) findViewById(R.id.ImageButtonCaidan01);
		GameView = (ImageButton) findViewById(R.id.ImageButtonCaidan02);
		GameSet = (ImageButton) findViewById(R.id.ImageButtonCaidan03);
		GameExit = (ImageButton) findViewById(R.id.ImageButtonCaidan04);

		GameStar.setOnClickListener(this);
		GameSet.setOnClickListener(this);
		GameView.setOnClickListener(this);
		GameExit.setOnClickListener(this);
		// GameLogo
		customDialog = new CustomDialog(this, this);
		if(Constants.mp == null){
			Constants.mp = new mediaPlayer(this);
		}

		findViewById(R.id.tv_author).setOnClickListener(this);
		findViewById(R.id.iv_author).setOnClickListener(this);
	}

	public void onClick(View v) {
		// TODO Auto-generated method stub
		if (GameStar.equals(v)) {
			GAME_STAR(0);
		} else if (GameSet.equals(v)) {
			GAME_SET();
		} else if (GameView.equals(v)) {
			GAME_LOAD();
		} else if (GameExit.equals(v)) {
			GAME_EXIT();
		}
		switch (v.getId()){
			case R.id.tv_author:
			case R.id.iv_author:
				Uri uri = Uri.parse("https://github.com/kituri");
				Intent it = new Intent(Intent.ACTION_VIEW, uri);
				startActivity(it);
				break;
		}
	}

	private void GAME_LOAD() {
		// TODO Auto-generated method stub
		
		if(DataTools.CanLoad(this)){
			this.GAME_STAR(1);
			}else{
				this.GAME_STAR(0);
			}
	}

	private void GAME_STAR(int mode) {
		switch(mode){
		case 0:
			Constants.LoadSaveMode = false;
			break;
		case 1:
			Constants.LoadSaveMode = true;
			break;
		}
		
		Intent intent = new Intent(this, MIDlet.class);
		// context.startActivity(intent); 或 context.startService(intent);
		startActivity(intent);
		this.finish();
	}

	private void GAME_SET() {
		// Intent intent = new Intent(this, GameMapViewActivity.class);
		// // context.startActivity(intent); 或 context.startService(intent);
		// startActivity(intent);
		// this.finish();
		customDialog
				.Show(CustomDialog.DIG_OK, CustomDialog.DIG_CANCEL, "魔兽大富翁",
						"是否开启声音", R.drawable.icon_sound, TYPE_SOUND, LAST_STEP);
		
	}

	private void GAME_EXIT() {
		// context.startActivity(intent); 或 context.startService(intent);
		this.finish();
	}

	@Override
	public boolean DigRequest(int TypeID, int ButtonIndex, int Step) {
		// TODO Auto-generated method stub
		//Toast.makeText(this, "1111111",0);
			if (ButtonIndex == CustomDialog.BUTTON_LEFT) {
				Constants.SoundEnabled = true;
				Constants.mp.playSound(mediaPlayer.SOUND_ATTACK05);
			} else if (ButtonIndex == CustomDialog.BUTTON_RIGHT) {
				Constants.SoundEnabled = false;
			}
		return false;
	}

}
