package com.skynet.support;

import com.microsoft.windowsazure.mobileservices.ServiceFilterResponse;
import com.microsoft.windowsazure.mobileservices.TableOperationCallback;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

public class SupportActivity extends Activity
{
	private final String TAG = "SupportActivity";
	private EditText mTxtName;
	private EditText mTxtEmailAddress;
	private EditText mTxtComments;
	private Button mBtnSubmit;
	private TextView mLblCharactersLeft;
	private int mMaxChars;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_support);

		mLblCharactersLeft = (TextView) findViewById(R.id.lblCharactersLeft);
		mTxtName = (EditText) findViewById(R.id.txtName);
		mTxtEmailAddress = (EditText) findViewById(R.id.txtEmailAddress);
		mTxtComments = (EditText) findViewById(R.id.txtComments);
		
		mTxtComments.addTextChangedListener(new CommentWatcher());
		//Set max character limit
		InputFilter[] FilterArray = new InputFilter[1];
		mMaxChars = getResources().getInteger(R.integer.comments_max_length);
		FilterArray[0] = new InputFilter.LengthFilter(mMaxChars);
		mTxtComments.setFilters(FilterArray);
		mLblCharactersLeft.setText(mMaxChars + " characters left");
		
		mBtnSubmit = (Button) findViewById(R.id.btnSubmit);
		mBtnSubmit.setOnClickListener(new OnClickListener()
		{			
			@Override
			public void onClick(View view)
			{
				submitSupport();				
			} // end method onClick
		}); // end OnClickListener
	} // end method onCreate

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_support, menu);
		return true;
	} // end method onCreateOptionsMenu
	
	private void submitSupport()
	{
		SupportApplication myApp = (SupportApplication) getApplication();
		SupportService supportService = myApp.getSupportService();
		final Activity activity = this;
		
		Support support = new Support(mTxtName.getText().toString(), mTxtEmailAddress.getText().toString(), mTxtComments.getText().toString());		
		supportService.insertSupport(support, new TableOperationCallback<Support>()
		{			
			@Override
			public void onCompleted(Support entity, Exception exception, ServiceFilterResponse response)
			{				
				if (exception != null)
				{
					Log.e(TAG, exception.getMessage());
					exception.printStackTrace();
					AlertDialog.Builder builder = new AlertDialog.Builder(activity);
					builder.setMessage(exception.getMessage());
					builder.setTitle("Error");
					builder.create().show();
					return;
				} // end if
				
				//Successfully submitted, finish the activity
				finish();
			} // end method onCompleted
		}); // end TableOperationCallback
	} // end method submitSupport

	private class CommentWatcher implements TextWatcher
	{		
		@Override
		public void onTextChanged(CharSequence s, int start, int before, int count)
		{
			int charsLeft = mMaxChars - start - count;
			if (charsLeft >= 0)
			{
				mLblCharactersLeft.setText(charsLeft + " characters left");
			} // end if
		} // end onTextChanged
		
		@Override
		public void beforeTextChanged(CharSequence s, int start, int count, int after)
		{
		} // end empty method beforeTextChanged
		
		@Override
		public void afterTextChanged(Editable s)
		{
		} // end empty method afterTextChanged
	} // end Class CommentWatcher
	
} // end Class SupportActivity
