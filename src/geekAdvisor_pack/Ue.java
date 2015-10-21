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

import android.util.Log;

public class Ue {

	int id;
	String nom;
	String contenu;

	public Ue() {

	}

	public Ue(int id, String nom, String description) {
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

	// Méthode qui permet de récupéré les UEs de la BDD 
	public String getAllUE() throws Exception {
		String link = "http://" + Utilisateur.ip + "/geekAdvisorApi/UeApi.php";
		URL url = new URL(link);
		HttpClient client = new DefaultHttpClient();
		HttpGet request = new HttpGet();
		request.setURI(new URI(link));
		HttpResponse response = client.execute(request);
		BufferedReader in = new BufferedReader(new InputStreamReader(response
				.getEntity().getContent()));

		StringBuffer sb = new StringBuffer("");
		String line = "";
		while ((line = in.readLine()) != null) {
			sb.append(line);
			break;
		}
		// jObject = new JSONObject(sb.toString());
		Log.v("sidali", sb.toString());
		in.close();
		return sb.toString();

	}

	// Méthode qui permet de récupéré de la BDD les noms des UEs 
	public String getUEName(String nom) throws Exception {
		String link = "http://" + Utilisateur.ip
				+ "/geekAdvisorApi/nomUEApi.php?nom="
				+ URLEncoder.encode(nom, "UTF-8") + "";
		URL url = new URL(link);
		HttpClient client = new DefaultHttpClient();
		HttpGet request = new HttpGet();
		request.setURI(new URI(link));
		HttpResponse response = client.execute(request);
		BufferedReader in = new BufferedReader(new InputStreamReader(response
				.getEntity().getContent()));

		StringBuffer sb = new StringBuffer("");
		String line = "";
		while ((line = in.readLine()) != null) {
			sb.append(line);
			break;
		}
		in.close();
		return sb.toString();
	}

}
