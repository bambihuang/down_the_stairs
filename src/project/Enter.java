package project;

/*
 * 程式執行起點
 */
public class Enter {
	public static void main(String[] args) {
		User user = new User();
		new MainFrame(1,user); // 進入註冊登入頁面
	}
}