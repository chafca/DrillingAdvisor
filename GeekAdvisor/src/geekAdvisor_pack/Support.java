package geekAdvisor_pack;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

public class Support {
	
	int idSupport;
	String nom;
	String description;
	String titre;
	String lien;
	String type;
	
	public Support() {}
	
	public Support(int idSupport, String nom, String description, String titre,
			String lien) {
		super();
		this.idSupport = idSupport;
		this.nom = nom;
		this.description = description;
		this.titre = titre;
		this.lien = lien;
	}

	public int getIdSupport() {
		return idSupport;
	}

	public void setIdSupport(int idSupport) {
		this.idSupport = idSupport;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public String getLien() {
		return lien;
	}

	public void setLien(String lien) {
		this.lien = lien;
	}

	public String getSupport(String nom) throws Exception{
		String link = "http://"+Utilisateur.ip+"/geekAdvisorApi/supportApi.php?nom="+URLEncoder.encode(nom, "UTF-8")+"";
				URL url = new URL(link.replace(" ", "%20"));
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
	            in.close();
	            return sb.toString();
	}
	
	public String getSupportName(String nom) throws Exception{
		String link = "http://"+Utilisateur.ip+"/geekAdvisorApi/nomSupportApi.php?nom="+URLEncoder.encode(nom, "UTF-8")+"";
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
	            in.close();
	            return sb.toString();
	}

}
