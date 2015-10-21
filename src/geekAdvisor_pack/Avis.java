package geekAdvisor_pack;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;
import geekAdvisor_pack.Utilisateur;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

public class Avis {

	private int idAvis;
	private String titre;
	private String contenu;

	public Avis() {

	}

	public Avis(int idAvis, String titre, String contenu) {

		this.idAvis = idAvis;
		this.titre = titre;
		this.contenu = contenu;
	}

	public int getIdAvis() {
		return idAvis;
	}

	public void setIdAvis(int idAvis) {
		this.idAvis = idAvis;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public String getContenu() {
		return contenu;
	}

	public void setContenu(String contenu) {
		this.contenu = contenu;
	}

	// Méthode qui permet d'insére dans la BDD un avis donner par un utilisateur
	public String Coment(String utilisateur, String contenu, int idSupport,
			float rate) throws Exception {
		String link = "http://" + Utilisateur.ip
				+ "/geekAdvisorApi/avisApi.php?utilisateur="
				+ URLEncoder.encode(utilisateur) + "&contenu="
				+ URLEncoder.encode(contenu) + "&support=" + idSupport
				+ "&note=" + rate + "";
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

	// Méthode qui permet d'afficher les avis donner par les utilisateurs pour
	// un support précis
	public String listAvis(int support) throws Exception {
		String link = "http://" + Utilisateur.ip
				+ "/geekAdvisorApi/avisApi.php?id=" + support + "";
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
