package com.qa.opencart.constants;


import java.util.Arrays;
import java.util.List;

public class Constants {
	
	public final static String LOGIN_PAGE_TITLE = "Account Login";
	public final static String LOGIN_PAGE_URL_FRACTION = "route=account/login";
	public final static int DEFAULT_ELEMENT_TIMEOUT = 10;
	public final static int DEFAULT_TIMEOUT = 5;
	public static final String ACCOUNT_PAGE_TITLE = "My Account";
	public static final String ACCOUNT_PAGE_URL_FRACTION = "route=account/account";
	public static final List<String> ACCOUNT_PAGE_SECTION_HEADERS_LIST = 
			Arrays.asList("My Account", "My Orders", "My Affiliate Account", "Newsletter");
	public static final String REGISTER_SUCCESS_MSG = "Your Account Has Been Created";
	public static final String LOGOUT_CONFIRM_MSG = "Account Logout";
	public static final String REGISTER_DATA = "register";
	
	
	

}
