package project;

/*
 * �{������_�I
 */
public class Enter {
	public static void main(String[] args) {
		User user = new User();
		user.setName("Guest");
		user.setPassword("");
		user.setScore(0);
		new MainFrame(1,user); // �i�J���U�n�J����
	}
}