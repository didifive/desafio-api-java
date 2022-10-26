package com.gft.meuamigau.enums.constants.api.v1;

public final class MappingRoutesV1 {
	
	public static final String URI_BASE = "http://localhost";
			
	public static final String PATH_ROOT = "/api/v1";
	
	public static final String PATH_AUTH = PATH_ROOT+"/auth";
	
	public static final String PATH_USERS = PATH_ROOT+"/users";
	public static final String PATH_USERS_ID = "/{id}";
	public static final String PATH_USERS_PASS_USERNAME = "/pass/{username}";
	
	public static final String PATH_PEOPLE = PATH_ROOT+"/people";
	public static final String PATH_PEOPLE_ID = "/{id}";
	public static final String PATH_PEOPLE_HANDLE_USER_ID = "/{id}/handle-user/id/{newUserId}";
	public static final String PATH_PEOPLE_HANDLE_USER_USERNAME = "/{id}/handle-user/username/{username}";
	
	public static final String PATH_CLIENTS = PATH_ROOT+"/clients";
	public static final String PATH_CLIENTS_ID = "/{id}";
	
	public static final String PATH_VETS = PATH_ROOT+"/vets";
	public static final String PATH_VETS_ID = "/{id}";
	
	public static final String PATH_BREEDS = PATH_ROOT+"/breeds";
	public static final String PATH_BREEDS_ID = "/{id}";
	public static final String PATH_BREEDS_NAME = "/name/{name}";
	public static final String PATH_BREEDS_IMAGE_ID = "/images/{id}";
	
	public static final String PATH_DOGS = PATH_ROOT+"/dogs";
	public static final String PATH_DOGS_ID = "/{id}";
	public static final String PATH_DOGS_BY_CLIENTS_ID = "/clients/{id}";
	
	public static final String PATH_ATTENDANCES = PATH_ROOT+"/attendances";
	public static final String PATH_ATTENDANCES_ID = "/{id}";
	public static final String PATH_ATTENDANCES_BY_CLIENTS_ID = "/clients/{id}";
	public static final String PATH_ATTENDANCES_BY_DOGS_ID = "/dogs/{id}";
	public static final String PATH_ATTENDANCES_BY_VETS_ID = "/vets/{id}";

	private MappingRoutesV1() {
		throw new IllegalAccessError("Utility Class");
	}
	
}
