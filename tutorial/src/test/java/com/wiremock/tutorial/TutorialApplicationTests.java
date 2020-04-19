package com.wiremock.tutorial;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import jdk.jfr.Description;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TutorialApplicationTests {

	@Rule
	public WireMockRule wireMockRule = new WireMockRule(wireMockConfig().port(8089).httpsPort(8443));// No-args constructor defaults to port 8080
public String real = "https://jsonplaceholder.typicode.com";
	public String fakeHttps = "https://localhost:8443";
	public String fakeHttp = "http://localhost:8089";
public  String body = "{\n" +
		"  \"id\": 1,\n" +
		"  \"name\": \"Leanne Graham\",\n" +
		"  \"username\": \"Bret\",\n" +
		"  \"email\": \"Sincere@april.biz\",\n" +
		"  \"address\": {\n" +
		"    \"street\": \"Kulas Light\",\n" +
		"    \"suite\": \"Apt. 556\",\n" +
		"    \"city\": \"Gwenborough\",\n" +
		"    \"zipcode\": \"92998-3874\",\n" +
		"    \"geo\": {\n" +
		"      \"lat\": \"-37.3159\",\n" +
		"      \"lng\": \"81.1496\"\n" +
		"    }\n" +
		"  },\n" +
		"  \"phone\": \"1-770-736-8031 x56442\",\n" +
		"  \"website\": \"hildegard.org\",\n" +
		"  \"company\": {\n" +
		"    \"name\": \"Romaguera-Crona\",\n" +
		"    \"catchPhrase\": \"Multi-layered client-server neural-net\",\n" +
		"    \"bs\": \"harness real-time e-markets\"\n" +
		"  }\n" +
		"}";


	@Test
	@Description("test wiremock")
	public void exampleTest() {
		stubFor(get(urlEqualTo("/users/1"))
				.withHeader("Accept", equalTo("text/plain, application/json, application/*+json, */*"))
				.willReturn(aResponse()
						.withStatus(200)
						.withHeader("Content-Type", "application/json")
						.withBody(body)));

		RestTemplate restTemplate = new RestTemplate();
		String url = fakeHttp;
		ResponseEntity<String> response = restTemplate.getForEntity(url+"/users/1",String.class);

		assertNotNull(response);
		//System.out.println(response);
		/*verify(getRequestedFor(urlMatching("/users/[a-z0-9]+"))
				.withHeader("Content-Type", notMatching("application/json")));*/
	}

}
