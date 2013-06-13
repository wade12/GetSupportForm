package com.skynet.support;

import java.net.MalformedURLException;

import android.content.Context;
import android.util.Log;

import com.microsoft.windowsazure.mobileservices.MobileServiceClient;
import com.microsoft.windowsazure.mobileservices.MobileServiceTable;
import com.microsoft.windowsazure.mobileservices.TableOperationCallback;

public class SupportService
{
	private MobileServiceClient mClient;
	private MobileServiceTable<Support> mSupportTable;
	private Context mContext;
	private String TAG = "Service";
	
	public SupportService(Context context)
	{
		mContext = context;
		
		try
		{
			mClient = new MobileServiceClient("https://<YourMobileServiceUrl>.azure-mobile.net/", "<YourApplicationKey>", mContext);
			mSupportTable = mClient.getTable(Support.class);
		} // end try
		
		catch (MalformedURLException e)
		{
			Log.e(TAG, "There was an error creating the Mobile Service. Verify the URL.");
		} // end catch
		
	} // end 1-argument constructor
	
	public void insertSupport(Support support, TableOperationCallback<Support> callback)
	{
		mSupportTable.insert(support, callback);
	} // end method 
	
} // end Class SupportService
