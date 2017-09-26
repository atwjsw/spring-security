package com.imooc.wiremock;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.springframework.core.io.ClassPathResource;

public class MockServer {
	public static void main(String[] args) throws IOException {
		configureFor(8062);
		removeAllMappings();	
		
		mock("/order/1", "01");
		mock("/order/2", "02");
		
		
	}
		
	private static void mock(String url, String file) throws IOException {
		ClassPathResource resource = new ClassPathResource("mock/response/" + file + ".txt");
		String content = FileUtils.readFileToString(resource.getFile(), "UTF-8");		
		stubFor(get(urlPathEqualTo(url))
				.willReturn(aResponse().withBody(content).withStatus(200)));
		
	}
	
}
