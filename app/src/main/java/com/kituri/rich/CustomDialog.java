package com.kituri.rich;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.kituri.wowrich.R;
import com.kituri.tools.CustomRequest;
public class CustomDialog extends Dialog implements OnClickListener {

	private TextView DigTitle;
	private ImageView DigIcon;
	private TextView DigMessage;
	private ImageButton ButtonLeft;
	private ImageButton ButtonRight;
	private ImageButton ButtonCenter;
	private CustomRequest customRequest;
	private int EvenTpyeID;
	private Context _context;
	private int Step;
	
	static public final int BUTTON_CENTER = 0;
	static public final int BUTTON_LEFT = 1;
	static public final int BUTTON_RIGHT = 2;
	
	static public final int DIG_NULL = -1;
	static public final int DIG_OK = 0;
	static public final int DIG_CANCEL = 1;
	static public final int DIG_FIGHT = 2;
	static public final int DIG_PAY = 3;
	
	private int[] DrawableID;
	public CustomDialog(Context context,CustomRequest customRequest) {
		super(context,R.style.dialog);
		this._context = context;
		this.customRequest = customRequest;
		
		DrawableID = new int[4];
		DrawableID[DIG_OK] = R.drawable.button_ok_xml;
		DrawableID[DIG_CANCEL] = R.drawable.button_cancel_xml;
		DrawableID[DIG_FIGHT] = R.drawable.button_attack_xml;
		DrawableID[DIG_PAY] = R.drawable.button_pay_xml;
		// TODO Auto-generated constructor stub
		
//		WindowManager.LayoutParams lp=getWindow().getAttributes();
//		lp.dimAmount=0.8f;
//		getWindow().setAttributes(lp);
//		getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		setContentView(R.layout.custom_dialog);
		// setTitle("Custom Dialog");

		DigTitle = (TextView) findViewById(R.id.DigTitle);
		// text.setText("Hello, this is a custom dialog!");
		DigIcon = (ImageView) findViewById(R.id.DigIcon);
		DigMessage = (TextView) findViewById(R.id.DigMessage);
		// image.setImageResource(R.drawable.sepurple);
		ButtonLeft = (ImageButton) findViewById(R.id.ButtonLeft);
		ButtonRight = (ImageButton) findViewById(R.id.ButtonRight);
		ButtonCenter = (ImageButton)findViewById(R.id.ButtonCenter);
		ButtonLeft.setOnClickListener(this);
		ButtonRight.setOnClickListener(this);
		ButtonCenter.setOnClickListener(this);
		
		ButtonLeft.setVisibility(View.GONE);
		ButtonRight.setVisibility(View.GONE);
		ButtonCenter.setVisibility(View.GONE);
		
		this.setTitle("");
		this.setCancelable(false);

	}

//	public void onCreate(Bundle savedInstanceState) {
//		super.onCreate(savedInstanceState);
//
//
//		//this.requestWindowFeature(Window.FEATURE_NO_TITLE);
//	}

	// called when this dialog is dismissed
//	protected void onStop() {
//		Log.d("TAG", "+++++++++++++++++++++++++++");
//	}
	private void SetButtonType(int type){
		switch(type){
		case DIG_NULL:
			ButtonLeft.setVisibility(View.GONE);
			ButtonRight.setVisibility(View.GONE);
			ButtonCenter.setVisibility(View.GONE);
			break;
		case DIG_OK:
			ButtonCenter.setVisibility(View.VISIBLE);
			ButtonCenter.setImageDrawable(_context.getResources().getDrawable(DrawableID[DIG_OK]));
			break;
		}
	}
	
	private void SetButtonType(int typeLeft,int typeRight){
		ButtonLeft.setImageDrawable(_context.getResources().getDrawable(DrawableID[typeLeft]));
		ButtonRight.setImageDrawable(_context.getResources().getDrawable(DrawableID[typeRight]));
		ButtonLeft.setVisibility(View.VISIBLE);
		ButtonRight.setVisibility(View.VISIBLE);
	}
	
	public void Show(int typeCenter,CharSequence title,CharSequence message,int DrawableID,int EvenTpyeID,int Step){
		SetButtonType(typeCenter);
		ShowEven(title,message,DrawableID,EvenTpyeID,Step);
	}
	
	private void ShowEven(CharSequence title,CharSequence message,int DrawableID,int EvenTpyeID,int Step){
		this.EvenTpyeID = EvenTpyeID;
		this.Step = Step;
		DigTitle.setText(title);
		if(message.toString().indexOf("\n") > -1){
			DigMessage.setGravity(android.view.Gravity.LEFT);
		}else{
			DigMessage.setGravity(android.view.Gravity.CENTER);
		}
		DigMessage.setText(message);
		//Log.i("DrawableID:", "DrawableID:" + DrawableID);
		DigIcon.setImageDrawable(_context.getResources().getDrawable(DrawableID));
		super.onContentChanged();
		super.show();
	}
	
	public void Show(int typeLeft,int typeRight,CharSequence title,CharSequence message,int DrawableID,int EvenTpyeID,int Step){
		SetButtonType(typeLeft,typeRight);
		ShowEven(title,message,DrawableID,EvenTpyeID,Step);
	}
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if (ButtonCenter.equals(v)) {
			dismiss();
			SetButtonType(DIG_NULL);
			customRequest.DigRequest(EvenTpyeID, BUTTON_CENTER,Step);
		}
		else if (ButtonLeft.equals(v)) {
			dismiss();
			SetButtonType(DIG_NULL);
			customRequest.DigRequest(EvenTpyeID, BUTTON_LEFT,Step);
		} else if (ButtonRight.equals(v)) {
			dismiss();
			SetButtonType(DIG_NULL);
			customRequest.DigRequest(EvenTpyeID, BUTTON_RIGHT,Step);
		}
	}

}
