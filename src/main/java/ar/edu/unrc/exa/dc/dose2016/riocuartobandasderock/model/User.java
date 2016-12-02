package ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.model;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.DatatypeConverter;


/**
 * Title:   riocuartobandasderock.model.User<p>
 * Description: class which defines a user of bands of rock with the basic elements
 *              that are required for a user.<p>
 * 
 */

@Entity
@Table(name = "Users")
public class User {
	
	/** User name*/
	@Id
	@Column(name = "name")
	private String name;

	/** User password*/
	@Column(name = "password")
	private String password;
	
	public User() {}

	public User(String username, String pass) {
		name = username;
		password = encodePassword(pass);
	}

	public String name(){
		return name;
	}

	public String password(){
		return password;
	}

	public User name(String username){
		name = username;
		return this;
	}

	public User password(String pass){
		password = encodePassword(pass);
		return this;
	}

	public boolean equals(User other) {
		if (other == null)
			throw new IllegalArgumentException("Other can't be null");
		return other.name().equals(this.name());
	}
	
	public boolean repOk(){
		return name != null && name != "" && password != null && password != "";
	}
	

    /**
     * Use javax.xml.bind.DatatypeConverter class in JDK to convert byte array
     * to a hexadecimal string. Note that this generates hexadecimal in upper case.
     * @param SHA-256 hash in binary
     * @return the hash converted into hexadecimal
     */
    private static String bytesToHex(byte[] hash) {
        return DatatypeConverter.printHexBinary(hash);
    }

    /**
     * Returns a hexadecimal encoded SHA-256 hash for the input String.
     * @param the input string
     * @return a SHA-256 hash of the input string
     */
    public static String encodePassword(String data) {
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(data.getBytes("UTF-8"));
            return bytesToHex(hash);
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;
		}
    }
}
