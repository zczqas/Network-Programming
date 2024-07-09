import java.net.*;

public class CookieStoreExample {
    public static void main(String[] args) {
        // Create a cookie manager
        CookieManager cookie_manager = new CookieManager();

        // Setting the default cookie manager
        CookieHandler.setDefault(cookie_manager);

        // Setting the cookie policy
        cookie_manager.setCookiePolicy(CookiePolicy.ACCEPT_ALL);

        // Getting the cookie store
        CookieStore cookie_store = cookie_manager.getCookieStore();

        try {
            URI uri = new URI("https://www.google.com");

            // create a cookies
            HttpCookie cookie_one = new HttpCookie("user", "john_doe");
            HttpCookie cookie_two = new HttpCookie("session", "xyz123");

            cookie_one.setDomain("www.google.com");
            cookie_two.setDomain("www.google.com");

            // Add the cookies to the cookie store
            cookie_store.add(uri, cookie_one);
            cookie_store.add(uri, cookie_two);

            // print the cookies to verify if they are added
            for (HttpCookie cookie : cookie_store.get(uri)) {
                System.out.println("Cookie: " + cookie);
            }

            // Remove the cookies from the cookie store
            cookie_store.remove(uri, cookie_one);
//            cookie_store.remove(uri, cookie_two);

            System.out.println("Cookie removed");

            // print the cookies to verify if they are removed
            for (HttpCookie cookie : cookie_store.get(uri)) {
                System.out.println("Cookie: " + cookie);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
