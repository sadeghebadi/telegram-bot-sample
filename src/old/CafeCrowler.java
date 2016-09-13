package old;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class CafeCrowler {

	
	//important pkix error :)
	//   http://stackoverflow.com/questions/4062307/pkix-path-building-failed-unable-to-find-valid-certification-path-to-requested
	public static void main(String[] args) {
		try {
			new CafeCrowler().crowl();
		} catch (IOException e) {
		}
	}

	private HashMap<Category, ArrayList<App>> crowl() throws IOException {
		HashMap<Category, ArrayList<App>> all = new HashMap<Category, ArrayList<App>>();
		Document doc = Jsoup.connect("http://cafebazaar.ir").get();
		Elements es = doc.getElementsByClass("container-body");
		Element element = es.get(0);
		Elements rowElements = element.getElementsByClass("col-sm-12");
		for (Element row : rowElements) {
			Category category = new Category();
			if (row.getElementsByClass("msht-row-title").size() != 0) {

				category.setTitle(row.getElementsByClass("msht-row-title")
						.get(0).ownText());
				Elements appRowElements = row.getElementsByClass("msht-app");
				ArrayList<App> apps = new ArrayList<App>();
				for (Element appRow : appRowElements) {
					Element appitem = appRow.getElementsByAttribute("href")
							.get(0);
					App app = new App();
					app.url = appitem.attr("href");
					app.img = appitem.getElementsByTag("img").get(0)
							.attr("src");
					app.img = app.img.substring(2, app.img.length());
					app.img = app.img.replace("_128x128", "").replace("icons",
							"upload/icons");
					app.app_name = appitem.getElementsByClass("msht-app-name")
							.get(0).getElementsByTag("span").get(0).ownText();
					app.price = appitem.getElementsByClass("msht-app-price")
							.get(0).getElementsByTag("span").get(0).ownText();
				

					if (!new File("icons/"
							+ app.img.subSequence(app.img.lastIndexOf("/"),
									app.img.length())).exists()) {
						FileUtils.copyURLToFile(
								new URL("http://" + app.img),
								new File("icons/"
										+ app.img.subSequence(
												app.img.lastIndexOf("/"),
												app.img.length())));
						System.out.println("downloaded icon : "
								+ new File("icons/"
										+ app.img.subSequence(
												app.img.lastIndexOf("/"),
												app.img.length())).getName());
					}
					apps.add(app);
				}
				all.put(category, apps);
			}
		}
		System.out.println(all);
		return all;
	}

	public void saveApps() {
		try {
			// read database and check if is new add to db
			DB.saveData(crowl());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public App crawlApp(App app) throws Exception {
		String url = "https://cafebazaar.ir" + app.url;
		// System.out.println("https://cafebazaar.ir"+app.url);
		// System.setProperty("javax.net.ssl.trustStore",
		// "/home/sadegh/cafetest.jks");
		// HttpsURLConnection conn = (HttpsURLConnection) new
		// URL(url).openConnection();
		// String html = convertStreamToString(conn.getInputStream());
		// conn.disconnect();
		// Document doc = Jsoup.parse(html);
		Document doc = Jsoup.connect(url).ignoreContentType(true)
		           .userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:25.0) Gecko/20100101 Firefox/25.0")  
		           .referrer("http://www.google.com")   
		           .timeout(12000) 
		           .followRedirects(true)
		           .execute().parse();

		Element element = doc.getElementsByClass("col-sm-8").get(1);
		
		app.app_category = doc.getElementsByAttributeValue("itemprop","applicationSubCategory")
				.get(0).ownText();

		Element descriptoin = element.getElementsByClass("rtl").get(0);
		String html = descriptoin.html();
		html = html.replaceAll("\\<.*?\\>", "").replaceAll("&nbsp;", " ");
		if (html.contains("\\.")) {
			html = html.substring(0, html.indexOf("\\."));
		}
		app.description = html;
		return app;
	}

	private String convertStreamToString(InputStream is) {
		BufferedReader br = null;
		StringBuilder sb = new StringBuilder();

		String line;
		try {

			br = new BufferedReader(new InputStreamReader(is));
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return sb.toString();

	}

}
