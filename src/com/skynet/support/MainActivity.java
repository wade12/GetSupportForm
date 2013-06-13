package com.skynet.support;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity
{
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Button btnSendSupport = (Button) findViewById(R.id.btnSendSupport);
		btnSendSupport.setOnClickListener(new OnClickListener()
		{			
			@Override
			public void onClick(View v)
			{				
				//Start the support activity
				Intent intent = new Intent(getApplicationContext(), SupportActivity.class);		     
		        startActivity(intent);
			} // end method onClick
		}); // end OnClickListener
	} // end method onCreate

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	} // end method onCreateOptionsMenu

} // end Class MainActivity
