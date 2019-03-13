package myapp;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.xml.bind.DatatypeConverter;
import javax.net.ssl.HttpsURLConnection;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPasswordField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.InvalidParameterException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.awt.event.ActionEvent;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import java.awt.Font;

public class App extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private JPasswordField pwdField;
	private JLabel lblFinalResult;
	
	/**
	 * Calculates the SHA-1 hash of the given password.
	 * @param password the password to be hashed.
	 * @return the hexadecimal representation of the password's hash.
	 * @throws NoSuchAlgorithmException if the SHA-1 hashing algorithm could not be used.
	 */
	public static String calcHexSHA1(String password) throws NoSuchAlgorithmException {
		MessageDigest md = null;
		md = MessageDigest.getInstance("SHA-1");
		return DatatypeConverter.printHexBinary(md.digest(password.getBytes()));
	}
	
	
	/**
	 * Connects to the "haveibeenpwned" v2 API (check website {@link https://haveibeenpwned.com/API/v2})
	 * and gets the hashes that match the first 5 hash characters of the given password. Then it searches
	 * for the full hash match in the returned hashes list in order to check if the given password has been found.
	 * @param hexHashSHA1 the hexadecimal representation of the password's hash.
	 * @return returns the number of times the given password was leaked in the "haveibeenpwned" database or null
	 * if there are no matches.
	 * @throws MalformedURLException if the URL has an unexpected format.
	 * @throws IOException if an error occurs while reading the result.
	 */
	private static String getHashRangeAndCompare(String hexHashSHA1) throws MalformedURLException, IOException {
		URL url = new URL("https://api.pwnedpasswords.com/range/" + hexHashSHA1.substring(0, 5));
		HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
		con.addRequestProperty("User-Agent", "Java");
		
		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String line = in.readLine();
		while (line != null) {
			if (line.substring(0, 35).equals(hexHashSHA1.substring(5))) {
				return line.substring(36);
			}
			else
				line = in.readLine();
		}
		in.close();
		con.disconnect();

		return null;
	}

	/**
	 * Launches the application.
	 * @param if no params are given, the GUI will be launched. Otherwise, a console output will be shown.
	 */
	public static void main(String[] args) {
		if (args.length == 0) {
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						App frame = new App();
						frame.setVisible(true);
					} catch (Exception e) {
						System.out.println("An unexpected error has ocurred while loading the GUI :S");
					}
				}
			});
		} else if (args.length == 1) {
			try {
				String result = getHashRangeAndCompare(calcHexSHA1(args[0]));
				if (result == null) {
					System.out.println("Congratulations! Your password was not found! :D");
				} else {
					System.out.println("Your password was found " + result + " times! :(");
				}
			} catch (MalformedURLException e) {
				System.out.println("SHA-1 hashing algorithm could not be found! :(");
			} catch (NoSuchAlgorithmException e) {
				System.out.println("The URL has an unexpected format! :S");
			} catch (IOException e) {
				System.out.println("An unexpected error has ocurred while processing the result :(");
			}
		} else {
			throw new InvalidParameterException("An unexpected number of parameters were given. Only 0 or 1 is allowed!");
		}
	}
	
	/**
	 * Executes the needed procedures for the GUI interface to do it's thing.
	 */
	public void action() {
		lblFinalResult.setForeground(new Color(255, 150, 0));
		try {
			lblFinalResult.setText("Checking...");
			lblFinalResult.setVisible(true);
			
			String result = getHashRangeAndCompare(calcHexSHA1(new String(pwdField.getPassword())));
			
			if (result == null) {
				lblFinalResult.setForeground(new Color(0, 175, 0));
				lblFinalResult.setText("Congratulations! Your password was not found! :D");
			} else {
				lblFinalResult.setForeground(Color.RED);
				lblFinalResult.setText("Your password was found " + result + " times! :(");
			}
		} catch (NoSuchAlgorithmException e) {
			lblFinalResult.setText("SHA-1 hashing algorithm could not be found! :(");
		} catch (MalformedURLException e) {
			lblFinalResult.setText("The URL has an unexpected format! :S");
		} catch (IOException e) {
			lblFinalResult.setText("An unexpected error has ocurred while processing the result :(");
		}
		
		pwdField.setText(null);
		Runtime.getRuntime().gc();
	}
	
	/**
	 * Create the frame.
	 */
	public App() {
		setTitle("PwnedPasswordsChecker");
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e1) {
			e1.printStackTrace();
		}
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 160);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		pwdField = new JPasswordField();
		pwdField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				action();
			}
		});
		pwdField.setToolTipText("Enter your password here.");
		
		JLabel lblPassword = new JLabel("Your password:");
		
		JButton btnNewButton = new JButton("Check");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				action();
			}
		});
		
		JSeparator separator = new JSeparator();
		
		JLabel lblStatus = new JLabel("Result:");
		lblStatus.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblStatus.setHorizontalAlignment(SwingConstants.CENTER);
		
		lblFinalResult = new JLabel("<placeholder>");
		lblFinalResult.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblFinalResult.setVisible(false);
		lblFinalResult.setHorizontalAlignment(SwingConstants.CENTER);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblFinalResult, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 554, Short.MAX_VALUE)
						.addComponent(lblStatus, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 554, Short.MAX_VALUE)
						.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
							.addComponent(lblPassword)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(pwdField, GroupLayout.PREFERRED_SIZE, 380, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, 89, Short.MAX_VALUE))
						.addComponent(separator, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 554, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(pwdField, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblPassword)
						.addComponent(btnNewButton))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, 10, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblStatus)
					.addGap(11)
					.addComponent(lblFinalResult)
					.addContainerGap(51, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
