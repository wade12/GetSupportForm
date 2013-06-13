package com.skynet.support;

public class Support
{
	@com.google.gson.annotations.SerializedName("name")
	private String mName;
	
	@com.google.gson.annotations.SerializedName("email")
	private String mEmail;
	
	@com.google.gson.annotations.SerializedName("comments")
	private String mComments;

	@com.google.gson.annotations.SerializedName("id")
	private int mId;

	public Support()
	{
	} // end 0-argument constructor
	
	public Support(String name, String email, String comments)
	{
		mName = name;
		mEmail = email;
		mComments = comments;
	} // end 3-argument constructor
	
	public String getName()
	{
		return mName;
	} // end method getName
	
	public void setName(String name)
	{
		mName = name;
	} // end method setName
	
	public String getEmail()
	{
		return mEmail;
	} // end method getEmail
	
	public void setEmail(String email)
	{
		mEmail = email;
	} // end method setEmail
	
	public String getComments()
	{
		return mComments;
	} // end method getComments
	
	public void setComments(String comments)
	{
		mComments = comments;
	} // end method setComments

	public int getId()
	{
		return mId;
	} // end method getId

	public final void setId(int id)
	{
		mId = id;
	} // end method setId

	@Override
	public boolean equals(Object object)
	{
		return object instanceof Support && ((Support) object).mId == mId;
	} // end method equals
	
} // end Class Supports
