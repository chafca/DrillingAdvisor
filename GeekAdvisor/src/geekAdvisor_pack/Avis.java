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
	
	private int id;
	private String titre;
	private String contenu;

	public Avis() {
		// TODO Auto-generated constructor stub
	}
	
	public Avis(int idAvis, String titre, String contenu) {
		// TODO Auto-generated constructor stub
		this.id = idAvis;
		this.titre = titre;
		this.contenu = contenu;
	}
	
	public int getIdAvis() {
		return id;
	}

	public void setIdAvis(int idAvis) {
		this.id = idAvis;
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
	public String Coment(String membre,String contenu,int lieu ,float rate) throws Exception{
		String link = "http:"+Utilisateur.ip+"/geekAdvisorApi/avisApi.php?membre="
				    +URLEncoder.encode(membre)+"&contenu="+URLEncoder.encode(contenu)+"&lieu="+lieu+"&rate="+rate+"";
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
	public String listAvis(int lieu) throws Exception{
		String link = "http://"+Utilisateur.ip+"/geekAdvisorApi/avisApi.php?lieu="+lieu+"";
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
