package com.agrostar.gitoperation;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

public class RestOperation {

	// GET Method
	public CloseableHttpResponse get(String url) throws ClientProtocolException, IOException {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet httpget = new HttpGet(url);
		CloseableHttpResponse closebaleHttpResponse = httpClient.execute(httpget);

		return closebaleHttpResponse;

	}

	// POST Method:
	public CloseableHttpResponse post(String url, String entityString) throws ClientProtocolException, IOException {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost httppost = new HttpPost(url);
		httppost.setEntity(new StringEntity(entityString));
		CloseableHttpResponse closebaleHttpResponse = httpClient.execute(httppost);
		return closebaleHttpResponse;

	}

	// PUT Method
	public CloseableHttpResponse put(String url) throws ClientProtocolException, IOException {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPut httpput = new HttpPut(url);
		CloseableHttpResponse closebaleHttpResponse = httpClient.execute(httpput);

		return closebaleHttpResponse;

	}

	// DELETE Method
	public CloseableHttpResponse delete(String url) throws ClientProtocolException, IOException {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpDelete httpdelete = new HttpDelete(url);
		CloseableHttpResponse closebaleHttpResponse = httpClient.execute(httpdelete);

		return closebaleHttpResponse;

	}

}