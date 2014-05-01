package com.example.memoryalloc;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;

import com.example.memoryalloc.R;

public class MainActivity extends Activity implements OnClickListener{
	private static final String TAG = "memoryAlloc";
	int size=0;

	View goButton,stopButton;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
//		requestWindowFeature(Window.FEATURE_NO_TITLE);

		setContentView(R.layout.activity_main);
		goButton = this.findViewById(R.id.Button01);
		goButton.setOnClickListener((android.view.View.OnClickListener) this);
		stopButton = this.findViewById(R.id.Button02);
		stopButton.setOnClickListener((android.view.View.OnClickListener) this);
	}

	@Override
    public void onClick(View v){
		if(v==goButton){
			while(true){
				size += 1024*64*16;
				Log.i(TAG, "pushed Go and alloc "+(size*4)+ "Bytes");
				if (alloconce(size)!=0){
					size=0;
					break;
				}
			}
		} else {
			size += 1024*64*16;
			Log.i(TAG, "pushed Step and alloc "+(size*4)+ "Bytes");
			if (alloconce(size)!=0)
				size=0;
		}
    }
	private int alloconce(int size) {
		try {
			int[] memory = new int[size];
			for(int i=0;i<size;i++){
				memory[i]=i;
			}
		} catch(java.lang.OutOfMemoryError e) {
			Log.e(TAG, "Out Of Memmory Error : size = "+(size*4));
			return -1;
		}
		return 0;
	}
}
