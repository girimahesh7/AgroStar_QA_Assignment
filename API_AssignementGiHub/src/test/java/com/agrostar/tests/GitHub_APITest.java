package com.agrostar.tests;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.agrostar.base.GitHub_Base;
import com.agrostar.gitoperation.RestOperation;
import com.agrostar.util.TestUtil;

public class GitHub_APITest extends GitHub_Base {
	GitHub_Base testBase;
	String getUrl;
	String postUrl;
	String starUrl;
	String vstarUrl;
	String newRepo;
	String desc;
	String deleteRepo;
	RestOperation restClient;
	CloseableHttpResponse closebaleHttpResponse;

	@BeforeMethod
	public void setUp() throws ClientProtocolException, IOException {
		testBase = new GitHub_Base();
		getUrl = prop.getProperty("GET_URL");
		postUrl = prop.getProperty("POST_URL");
		starUrl = prop.getProperty("STAR_URL");
		vstarUrl = prop.getProperty("V_STAR_URL");
		newRepo = prop.getProperty("NEW_REPO");
		desc = prop.getProperty("REPO_DESC");
		deleteRepo=prop.getProperty("DELETE_REPO");
	}

	@Test(priority = 0, enabled = true)
	public void createRepository() throws IOException {
		restClient = new RestOperation();
		HashMap<String, String> headerMap = new HashMap<String, String>();
		headerMap.put("Content-Type", "application/json");

		JSONObject jsonObject = new JSONObject();
		jsonObject.put("name", newRepo);
		jsonObject.put("description", desc);

		String input = jsonObject.toString();
		System.out.println(input);
		closebaleHttpResponse = restClient.post(postUrl, input);

		int statusCode = closebaleHttpResponse.getStatusLine().getStatusCode();
		Assert.assertEquals(statusCode, RESPONSE_STATUS_CODE_201,
				"Unable to create repository:Test failed with status code: " + statusCode);
		String responseString = EntityUtils.toString(closebaleHttpResponse.getEntity(), "UTF-8");
		JSONObject responseJson = new JSONObject(responseString);
		System.out.println("The response from API is:" + responseJson);

	}

	@Test(priority = 1, enabled = true)
	public void verifyRepository() throws ClientProtocolException, IOException {
		restClient = new RestOperation();
		closebaleHttpResponse = restClient.get(getUrl);

		int statusCode = closebaleHttpResponse.getStatusLine().getStatusCode();
		System.out.println("Status Code--->" + statusCode);

		Assert.assertEquals(statusCode, RESPONSE_STATUS_CODE_200, "Repository is not available");
		String responseString = EntityUtils.toString(closebaleHttpResponse.getEntity(), "UTF-8");

		JSONObject responseJson = new JSONObject(responseString);
		System.out.println("Response JSON from API---> " + responseJson);

		String repoName = TestUtil.getValueByJPath(responseJson, "/name");
		System.out.println("Repository name is -->" + repoName);
		Assert.assertEquals(repoName, newRepo, "RepositoryName is not matching :Found RepoName is " + repoName);
		String description = TestUtil.getValueByJPath(responseJson, "/description");
		System.out.println("Repoistory description is-->" + description);
		Assert.assertEquals(description, desc, "Repository Description is not matching exxpected :" + desc);
	}

	@Test(priority = 2, enabled = true)
	public void markStarRepository() throws ClientProtocolException, IOException {
		restClient = new RestOperation();
		closebaleHttpResponse = restClient.put(starUrl);
		int statusCode = closebaleHttpResponse.getStatusLine().getStatusCode();
		System.out.println("Status Code--->" + statusCode);
		Assert.assertEquals(statusCode, RESPONSE_STATUS_CODE_204, "Reoistory is not Stared:: Status code is not 204");

	}

	@Test(priority = 3, enabled = true)
	public void verifyStarRepository() throws ClientProtocolException, IOException {
		restClient = new RestOperation();
		closebaleHttpResponse = restClient.get(vstarUrl);

		int statusCode = closebaleHttpResponse.getStatusLine().getStatusCode();
		System.out.println("Status Code--->" + statusCode);
		Assert.assertEquals(statusCode, RESPONSE_STATUS_CODE_200, "Reoistory is not Starred:: Status code is not 200");
		String responseString = EntityUtils.toString(closebaleHttpResponse.getEntity(), "UTF-8");
		responseString = responseString.replaceAll("\\[", "").replaceAll("\\]", "");
		JSONObject responseJson = new JSONObject(responseString);
		System.out.println("Response JSON from API---> " + responseJson);

		String repoName = TestUtil.getValueByJPath(responseJson, "/name");
		System.out.println("Star Repo is-->" + repoName);
		Assert.assertEquals(repoName, newRepo, "Found RepoName is " + repoName);

		String description = TestUtil.getValueByJPath(responseJson, "/description");
		System.out.println("Repoistory description is-->" + description);
		Assert.assertEquals(description, desc, "Star Repo Description is not matching");
	}

	@Test(priority = 4, enabled = true)
	public void deleteRepository() throws ClientProtocolException, IOException {
		restClient = new RestOperation();
		closebaleHttpResponse = restClient.delete(deleteRepo);
		int statusCode = closebaleHttpResponse.getStatusLine().getStatusCode();
		System.out.println("Status Code--->" + statusCode);
		Assert.assertEquals(statusCode, RESPONSE_STATUS_CODE_204, "Reoistory is not Stared:: Status code is not 200");

	}
}