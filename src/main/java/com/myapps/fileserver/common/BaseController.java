/**
 * @author 5zzang
 * Nov 15, 2012
 */
package com.myapps.fileserver.common;

import org.springframework.http.HttpHeaders;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


public abstract class BaseController {
	public Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").setPrettyPrinting().create();
	public HttpHeaders responseHeaders = new HttpHeaders();
}