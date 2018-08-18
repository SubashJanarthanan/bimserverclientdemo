package bimserverclientdemo;

import org.bimserver.client.BimServerClient;
import org.bimserver.client.json.JsonBimServerClientFactory;
import org.bimserver.interfaces.objects.SProject;
import org.bimserver.shared.ChannelConnectionException;
import org.bimserver.shared.UsernamePasswordAuthenticationInfo;
import org.bimserver.shared.exceptions.BimServerClientException;
import org.bimserver.shared.exceptions.PublicInterfaceNotFoundException;
import org.bimserver.shared.exceptions.ServiceException;
import org.bimserver.shared.reflector.ReflectorException;

public class Main {
	public static void main(String[] args) {
		try {
			createNewProject();
		} catch (ReflectorException e) {
			System.out.println("ReflectorException-Connection refused: Check the connection URL");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void createNewProject() throws ReflectorException {
		try {
			JsonBimServerClientFactory clientFactory = new JsonBimServerClientFactory("http://localhost:8082");
			BimServerClient client = clientFactory
					.create(new UsernamePasswordAuthenticationInfo("subashjanarthanan@gmail.com", "Makesecrets@123"));
			SProject sampleProject = client.getServiceInterface().addProject("sample", "ifc2x3tc1");
			long oid = sampleProject.getOid();
			System.out.println("Project O_ID: " + oid);
		} catch (BimServerClientException e) {
			e.printStackTrace();
		} catch (ServiceException e) {
			e.printStackTrace();
		} catch (ChannelConnectionException e) {
			e.printStackTrace();
		} catch (PublicInterfaceNotFoundException e) {
			e.printStackTrace();
		}
	}
}
