package com.niit.vconnect;

import java.util.List;

import com.google.api.server.spi.Constant;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.config.Named;
import com.google.api.server.spi.config.ApiMethod.HttpMethod;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.Work;
import com.googlecode.objectify.cmd.Query;


@Api(name = "addEndPointApi", version = "v1", namespace = @ApiNamespace(ownerDomain = "addendpointApi.example.com", ownerName = "addendpointApi.example.com", packagePath = ""), scopes = {
		Constant.API_EMAIL_SCOPE }, clientIds = { Constants.WEB_CLIENT_ID, Constants.API_EXPLORER_CLIENT_ID })


public class YourFirstAPI {
	
	@ApiMethod(name = "countData",path="countData",httpMethod = HttpMethod.GET)
	public UserCounter countData() {

		UserCounter u=(UserCounter)ConnectService.ofy().load().key(Key.create(UserCounter.class,"1")).now();
		return u;
	}
 
	@ApiMethod(name="saveData" ,path="saveData", httpMethod=HttpMethod.GET)
	public Profile saveData(final @Named("name")String name,final @Named("email")String email,final @Named("profession")String profession,
			final @Named("cell")String cell,final @Named("about")String about){
		Profile p=ConnectService.ofy().transact(new Work<Profile>(){
		final Profile p=new Profile(name,email,profession,cell,about);
		@Override
		public Profile run() {
			UserCounter u=(UserCounter)ConnectService.ofy().load().key(Key.create(UserCounter.class,"1")).now();
			if(u==null) {
				u=new UserCounter(1,"1");
				ConnectService.ofy().save().entity(u).now();
				ConnectService.ofy().save().entity(p).now();
			}
			else
			{
				u.counter=countData().counter+1;
				ConnectService.ofy().save().entities(p,u).now();
			}
			return p;
			}
			
		});
		
		return p;
	}
	@ApiMethod(name="removeData",path="removeData", httpMethod=HttpMethod.GET)
	public Profile removeData(final @Named("email")String email){
		Profile p=ConnectService.ofy().transact(new Work<Profile>(){
			@Override
			public Profile run() {
			final Profile p=(Profile)ConnectService.ofy().load().key(Key.create(Profile.class,email)).now();
			UserCounter u=(UserCounter)ConnectService.ofy().load().key(Key.create(UserCounter.class,"1")).now();
					long c=countData().counter+1;
					u.counter=--c;
					ConnectService.ofy().save().entity(p).now();
					ConnectService.ofy().save().entity(u).now();
					return p;
				}
				
			});
		
		return p;
	}
	@ApiMethod(name="getRecords",path="getRecords", httpMethod=HttpMethod.GET)
	public List<Profile> getRecords(){
		Query<Profile>q=ConnectService.ofy().load().type(Profile.class);
		return q.list();
	}
	
	/*
	 * @ApiMethod(name="getById",path="getById", httpMethod=HttpMethod.GET) public
	 * List<Profile> getById(@Named("pid")long pid){
	 * Query<Profile>q=ConnectService.ofy().load().type(Profile.class).filter("pid="
	 * ,pid); return q.list(); }
	 * 
	 * @ApiMethod(name="getByName",path="getByName", httpMethod=HttpMethod.GET)
	 * public List<Profile> getById(@Named("name")String name){
	 * Query<Profile>q=ConnectService.ofy().load().type(Profile.class).filter(
	 * "name=",name); return q.list(); }
	 * 
	 * @ApiMethod(name="getByPh",path="getByPh", httpMethod=HttpMethod.GET) public
	 * List<Profile> getByPh(@Named("cell")long cell){
	 * Query<Profile>q=ConnectService.ofy().load().type(Profile.class).filter(
	 * "cell=",cell); return q.list(); }
	 */
}
