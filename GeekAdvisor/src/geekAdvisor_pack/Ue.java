package geekAdvisor_pack;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

public class Ue {
	
	int id;
	String nom;
	String contenu;
	
	public Ue(){
		
	}
	
	public Ue(int id, String nom, String description){
		this.id = id;
		this.nom = nom;
		this.contenu = description;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getContenu() {
		return contenu;
	}

	public void setContenu(String contenu) {
		this.contenu = contenu;
	}
	
	public String getAllUE() throws Exception{
		String link = "http://"+Utilisateur.ip+"/geekAdvisorApi/UeApi.php";
					URL url = new URL(link);
				
	            HttpClient client = new DefaultHttpClient();
	            HttpGet request = new HttpGet();
	            request.setURI(new URI(link));
	            HttpResponse response = client.execute(request);
	            BufferedReader in = new BufferedReader
	           (new InputStreamReader(response.getEntity().getContent()));

	           StringBuffer sb = new StringBuffer("");
	           String line="";
	           while ((line = in.readLine()) != null) {
	              sb.append(line);
	              break;
	            }
	           //jObject = new JSONObject(sb.toString());
	           
	            in.close();
	            return sb.toString() ;
		
	}
	

}
