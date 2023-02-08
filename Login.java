import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class Login {
    public static void main(String[] args) throws IOException {
        // Define the login URL
        String loginUrl = "https://example.com/login";

        // Define the username and password
        String username = "your_username";
        String password = "your_password";

        // Send a POST request to the login URL with the username and password
        Document loginPage = Jsoup.connect(loginUrl)
            .data("username", username)
            .data("password", password)
            .post();

        // Extract the value of the reCAPTCHA v2 response token from the login page
        String recaptchaResponse = loginPage.select("#recaptcha-response").val();

        // Send a request to the reCAPTCHA v2 verification URL with the response token
        Document verifyPage = Jsoup.connect("https://www.google.com/recaptcha/api/siteverify")
            .data("secret", "your_recaptcha_secret_key")
            .data("response", recaptchaResponse)
            .post();

        // Check the response from the reCAPTCHA v2 verification to see if the login was successful
        if (verifyPage.select("success").text().equals("true")) {
            System.out.println("Login successful!");
        } else {
            System.out.println("Login failed. Please try again.");
        }
    }
}
