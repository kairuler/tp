package seedu.duke.secrets;

import seedu.duke.Backend;
import seedu.duke.exceptions.secrets.InvalidURLException;

/**
 * BasicPassword class represents a basic password entry that contains a username,
 * password, and URL.
 */
public class BasicPassword extends Secret{
    private String username;
    private String password;
    private String url;

    /**
     * Constructs a BasicPassword object with the specified name, username, password, and URL.
     *
     * @param name name of the basic password entry
     * @param username username of the basic password entry
     * @param password password of the basic password entry
     * @param url URL of the basic password entry
     * @throws InvalidURLException if the provided URL is not a valid URL
     */
    public BasicPassword(String name, String username,
                         String password, String url) throws InvalidURLException {
        super(name);
        this.password = password;
        this.username = username;
        if (url.contains(".") && (url.indexOf(".") == url.lastIndexOf("."))) {
            this.url = url;
        } else {
            throw new InvalidURLException();
        }
    }

    /**
     * Constructs a BasicPassword object with the specified name, folder name, username, password, and URL.
     *
     * @param name name of the basic password entry
     * @param folderName name of the folder that the basic password entry is stored in
     * @param username username of the basic password entry
     * @param password password of the basic password entry
     * @param url URL of the basic password entry
     * @throws InvalidURLException if the provided URL is not a valid URL
     */
    public BasicPassword(String name, String folderName, String username,
                            String password, String url) throws InvalidURLException {
        super(name, folderName);
        this.password = password;
        this.username = username;
        if (url.contains(".") && (url.indexOf(".") == url.lastIndexOf("."))) {
            this.url = url;
        } else {
            throw new InvalidURLException();
        }
    }

    /**
     * Returns the password of the basic password entry.
     *
     * @return password of the basic password entry
     */
    public String getPassword() {
        return password;
    }

    /**
     * Returns the URL of the basic password entry.
     *
     * @return URL of the basic password entry
     */
    public String getUrl() {
        return url;
    }

    /**
     * Returns a formatted string representation of the BasicPassword object for the database.
     *
     * @return formatted string representation of the BasicPassword object for the database
     */
    @Override
    public String toStringForDatabase() {
        String formattedString =  "Password," + super.toStringForDatabase() +
            "," + Backend.encode(this.username) + "," + Backend.encode(this.password) +
               "," + this.url;
        if (this.url.length() == 0) {
            formattedString += "empty";
        }
        return formattedString;
    }

    /**
     * Returns a string representation of the BasicPassword object for revealing the password.
     *
     * @return string representation of the BasicPassword object for revealing the password
     */
    @Override
    public String getRevealStr() {
        return String.format("Name: %s\n" +
                        "Url: %s\n" +
                        "Username: %s\n" +
                        "Password: %s",
                getName(), url, username, password);
    }

    /**
     * Returns the username associated with the password.
     *
     * @return the username of the password
     */
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUrl(String url) throws InvalidURLException {
        if (url.contains(".") && (url.indexOf(".") == url.lastIndexOf("."))) {
            this.url = url;
        } else {
            throw new InvalidURLException();
        }
    }
}
