package org.openmrs.contrib.qaframework.helper;

import java.io.IOException;

import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;
import org.openmrs.contrib.qaframework.helper.TestData.JsonTestClass;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;

public class RestClient {

	private static final String REST_ROOT = "/ws/rest/v1/";

	public static JsonNode get(String restPath) {
		return get(restPath, null);
	}

	public static JsonNode get(String restPath, String columns) {
		return get(restPath, columns, null);
	}

	// columns is a comma separated list (or null)
	public static JsonNode get(String restPath, String columns,
			String searchQuery) {
		WebTarget target = newClient().target(getWebAppUrl()).path(
				REST_ROOT + restPath);
		if (columns != null) {
			target = target.queryParam("v", "custom:(" + columns + ")");
		}
		if (searchQuery != null) {
			target = target.queryParam("q", searchQuery);
		}
		String jsonString = target.request().get(String.class);
		try {
			return new ObjectMapper().readValue(jsonString, JsonNode.class);
		} catch (JsonParseException e) {
			log("GET " + restPath + " failed", e);
			return null;
		} catch (JsonMappingException e) {
			log("GET " + restPath + " failed", e);
			return null;
		} catch (IOException e) {
			log("GET " + restPath + " failed", e);
			return null;
		}
	}

	public static void delete(String restPath, Boolean purge) {
		WebTarget target = newClient().target(getWebAppUrl())
				.path(REST_ROOT + restPath).queryParam("purge", purge);
		try {
			String jsonString = target.request().delete(String.class);
			if (!jsonString.isEmpty()) {
				throw new RuntimeException(jsonString);
			}
		} catch (Exception e) {
			throw new IllegalStateException("Delete request failed: "
					+ target.getUri(), e);
		}
	}

	public static void delete(String restPath) {
		delete(restPath, null);
	}

	public static JsonNode post(String restPath, JsonTestClass object) {
		WebTarget target = newClient().target(getWebAppUrl()).path(
				REST_ROOT + restPath);
		try {
			String objectAsJson = object.asJson();
			Entity<String> entity = Entity.entity(objectAsJson,
					MediaType.APPLICATION_JSON_TYPE);
			String json = target.request(MediaType.APPLICATION_JSON_TYPE).post(
					entity, String.class);
			return new ObjectMapper().readValue(json, JsonNode.class);
		} catch (Exception e) {
			log("POST " + restPath + " failed", e);
			return null;
		}
	}

	private static Client newClient() {
		return ClientBuilder.newClient().register(
				HttpAuthenticationFeature.basic(getUsername(), getPassword()));
	}

	static String getUsername() {
		return TestProperties.instance().getUsername();
	}

	static String getPassword() {
		return TestProperties.instance().getPassword();
	}

	static String getWebAppUrl() {
		return TestProperties.instance().getWebAppUrl();
	}

	static void log(Object o) {
		System.out.println(o);
	}

	static void log(Object o, Exception e) {
		System.out.println(o);
		e.printStackTrace();
	}

	// Note this is not REST.
	public static String generatePatientIdentifier(String source) {
		Client client = newClient();
		WebTarget target = client.target(getWebAppUrl())
				.path("/module/idgen/generateIdentifier.form")
				.queryParam("source", source)
				.queryParam("username", getUsername())
				.queryParam("password", getPassword());
		String jsonString = target.request(MediaType.APPLICATION_JSON_TYPE)
				.get(String.class);
		JsonNode json;
		try {
			json = new ObjectMapper().readValue(jsonString, JsonNode.class);
		} catch (Exception e) {
			log("GET /module/idgen/generateIdentifier.form failed", e);
			return null;
		}
		return json.get("identifiers").get(0).asText();
	}

	public static void post(String restPath, TestPatient testPatient) {
		// TODO Auto-generated method stub

	}
}
