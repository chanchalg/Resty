/**
 * 
 */
package us.monoid.web.auth;

import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Authenticator used for HTTP based authentication.
 * This class is not used directly, but through adding credentials using a Resty instance.
 * Also, this class is not useable for hundreds or thousands of credentials.
 * 
 * @author beders
 *
 */
public class RestyAuthenticator extends Authenticator {
	List<Site> sites = Collections.synchronizedList(new ArrayList<Site>());
	
	@Override
	protected PasswordAuthentication getPasswordAuthentication() {
		URL requestURL = getRequestingURL();
		try {
			String uri = requestURL.toURI().normalize().toString();
			Site site = findBestMatch(uri);
			if (site != null) {
				return new PasswordAuthentication(site.login, site.pwd);
			}
		} catch (URISyntaxException e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}
	
	/** Add or replace an authentication for a root URL aka site.
	 * 
	 * @param aRootUrl
	 * @param login
	 * @param pwd
	 */
	
	public void addSite(URI aRootUrl, String login, char[] pwd) {
		String rootUri = aRootUrl.normalize().toString();
		boolean replaced = false;
		// check if we already have a login/password for the root uri
		for (Site site : sites) {
			if (site.root.equals(rootUri)) { // TODO synchronisation
				site.login = login;
				site.pwd = pwd;
				replaced = true;
				break;
			}
		}
		if (!replaced) {
			Site s = new Site();
			s.root = rootUri;
			s.login = login;
			s.pwd = pwd;
			sites.add(s);
		}
	}
	
	private Site findBestMatch(String uri) {
		int max = 0;
		Site bestSite = null;
		for (Site s : sites) {
			int prefixLength = s.match(uri);
			if (prefixLength > max) {
				max = prefixLength;
				bestSite = s;
			}
		}
		return bestSite;
	}

	static class Site {
		String root;
		String login;
		char[] pwd;
		public int match(String uri) { // probably not correct. TODO look up better host matching
			if (uri.startsWith(root)) {
				return root.length();
			}
			return 0;
		}
	}
}
