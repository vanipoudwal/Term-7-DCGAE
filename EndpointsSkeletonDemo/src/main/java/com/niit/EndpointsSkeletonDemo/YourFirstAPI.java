package com.niit.EndpointsSkeletonDemo;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiMethod.HttpMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.config.Named;

@Api
(
	name = "testapi", 
	version = "v1",
	namespace = @ApiNamespace(ownerDomain = "greeting.example.com", ownerName = "greeting.example.com", packagePath = ""), 
	scopes = { Constants.EMAIL_SCOPE }, clientIds = { Constants.WEB_CLIENT_ID, Constants.API_EXPLORER_CLIENT_ID }
)

public class YourFirstAPI 
{
	@ApiMethod(name = "method1",path = "method1", httpMethod = HttpMethod.GET)
	public MyBean show(@Named("name") String str)
	{
		MyBean m1 = new MyBean();
		m1.setMsg(str);
		System.out.println(m1.getMsg());
		return m1;
	}
	
	@ApiMethod(name = "showTime", path = "showTime", httpMethod = HttpMethod.GET)
	public MyBean showTime(@Named("name") String name, @Named("time") String time)
	{
		MyBean m2 = new MyBean(name, time);
		System.out.println(m2.getMsg());
		return m2;
	}
}
