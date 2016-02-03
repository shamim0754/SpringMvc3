package de.tr.model;

/**
 * The java bean class for {@link Twitters} objects. It specifies:
 * <ul>
 * <li>DB-Table</li>
 * <li>get-/set-methods</li>
 * <li>constructors</li>
 * </ul>
 * 
 * @author 
 */
public class Twitters {

	public String tweetsText;

	public String getTweetsText() {
		return tweetsText;
	}

	public void setTweetsText(String tweetsText) {
		this.tweetsText = tweetsText;
	}

}
