package com.skynet.support;

import android.app.Application;

public class SupportApplication extends Application
{
	private SupportService mSupportService;
	
	public SupportApplication()
	{		
	} // end 0-argument constructor
	
	public SupportService getSupportService()
	{
		if (mSupportService == null)
		{
			mSupportService = new SupportService(this);
		} // end if
		return mSupportService;
	} // end method getSupportService
	
} // end Class SupportApplication
