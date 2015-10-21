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

public class Utilisateur {

	protected int id;
	protected String login;
	protected String password;
	protected String nom;
	protected String prenom;
	protected String email;

	public Utilisateur() {
		// TODO Auto-generated constructor stub
	}

	public Utilisateur(int id, String login, String password, String nom,
			String prenom) {
		// TODO Auto-generated constructor stub

		this.id = id;
		this.login = login;
		this.password = password;
		this.nom = nom;
		this.prenom = prenom;
	}

	public Utilisateur(String password, String email, String nom, String prenom) {
		// TODO Auto-generated constructor stub

		this.email = email;
		this.password = password;
		this.nom = nom;
		this.prenom = prenom;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	// 192.168.0.48 // 10.188.123.142 /10.50.33.246/geekadvisor.webege.com/
	// 172.30.235.186 // 172.30.98.140 
	public static String ip = "172.30.44.162";

	// Méthode qui permet de récupéré de la BDD les emails et le passwords des utilisateurs
	public String login(String email, String password) throws Exception {
		String link = "http://" + Utilisateur.ip
				+ "/geekAdvisorApi/loginApi.php?email=" + email + "&password="
				+ password;
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
		Log.v("sidali", sb.toString());
		return sb.toString();

	}

	// Méthode qui permet d'inséré dans la BDD le formulaire d'inscription des utilisateurs
	public String inscrire(Utilisateur m) throws Exception {
		String link = "http://" + Utilisateur.ip
				+ "/geekAdvisorApi/inscrireApi.php?nom="
				+ URLEncoder.encode(m.getNom()) + "&prenom="
				+ URLEncoder.encode(m.getPrenom()) + "&password="
				+ m.getPassword() + "&email=" + m.getEmail() + "";
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
	/* *****Méthode Pour Crypter le MDP***** */
	public String MD5(String md5) {
		try {
			java.security.MessageDigest md = java.security.MessageDigest
					.getInstance("MD5");
			byte[] array = md.digest(md5.getBytes());
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < array.length; ++i) {
				sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100)
						.substring(1, 3));
			}
			return sb.toString();
		} catch (java.security.NoSuchAlgorithmException e) {
		}
		return null;
	}

}
