package api.endpoints;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;
import org.testng.Assert;
import org.testng.annotations.Test;

public class RoleAPITest {

	@Test
	public void deleteTest() {
		throw new RuntimeException("Test not implemented");
	}

	@Test
	public void findTest() {
		try {
			HttpUriRequest request200 = new HttpGet("http://localhost:8080/group_3-0.0.1-SNAPSHOT/roles/1");
			HttpResponse response200 = HttpClientBuilder.create().build().execute(request200);
			Assert.assertEquals(response200.getStatusLine().getStatusCode(), HttpStatus.SC_OK);
			
			HttpUriRequest request404 = new HttpGet("http://localhost:8080/group_3-0.0.1-SNAPSHOT/roles/999");
			HttpResponse response404 = HttpClientBuilder.create().build().execute(request404);
			Assert.assertEquals(response404.getStatusLine().getStatusCode(), HttpStatus.SC_NOT_FOUND);
			
			HttpUriRequest request400 = new HttpGet("http://localhost:8080/group_3-0.0.1-SNAPSHOT/roles/abc");
			HttpResponse response400 = HttpClientBuilder.create().build().execute(request400);
			Assert.assertEquals(response400.getStatusLine().getStatusCode(), HttpStatus.SC_BAD_REQUEST);
			
		} catch (IOException e) {
			e.printStackTrace();
			Assert.fail();
		}

	}

	@Test
	public void getAllTest() {
		try {
			HttpUriRequest request = new HttpGet("http://localhost:8080/group_3-0.0.1-SNAPSHOT/roles");
			HttpResponse response = HttpClientBuilder.create().build().execute(request);
			Assert.assertEquals(response.getStatusLine().getStatusCode(), HttpStatus.SC_OK);
			
		} catch (IOException e) {
			e.printStackTrace();
			Assert.fail();
		}
	}

	@Test
	public void insertTest() {
		throw new RuntimeException("Test not implemented");
	}

	@Test
	public void replaceTest() {
		throw new RuntimeException("Test not implemented");
	}
}
