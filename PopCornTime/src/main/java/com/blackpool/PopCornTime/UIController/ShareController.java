package com.blackpool.PopCornTime.UIController;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;

import com.blackpool.PopCornTime.App;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class ShareController {

	@FXML
	private TextField toText;

	@FXML
	private TextField subText;

	public static byte[] movieThumblin;
	public static String movieName;
	
	public static int releaseYear;
	public static double rating;
	@FXML
	void close(MouseEvent event) {
		App.mainBorderPain.setBorderPane(SetXml.setxml("movieInner"));
	}

	@FXML
	void send(MouseEvent event) {
		App.mainBorderPain.setBorderPane(SetXml.setxml("movieInner"));
		for (String i : toText.getText().split(";")) {
			new Thread(()->{
				shareMovie(i);
			}).start();
		}
	}

	private void shareMovie(String to) {
		
		
		final String username = "nishantprajapati2319@gmail.com";// change accordingly
		final String password = "";//TODO: change accordingly

		

		Properties props = new Properties();
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "465");

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {

			// Create a default MimeMessage object.
			Message message = new MimeMessage(session);
			

			// Set From: header field of the header.
			message.setFrom(new InternetAddress(username));

			// Set To: header field of the header.
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));

			// Set Subject: header field
			message.setSubject(subText.getText());

			// This mail has 2 part, the BODY and the embedded image
			MimeMultipart multipart = new MimeMultipart("related");

			// first part (the html)
			BodyPart messageBodyPart = new MimeBodyPart();
			String htmlText = "<img src=\"cid:image\" width=\"300\" height=\"400\"><H3>"+movieName+"</H3><H3>"+releaseYear+"</H3><H3>"+rating+"</H3>";
			messageBodyPart.setContent(htmlText, "text/html");
			// add it
			multipart.addBodyPart(messageBodyPart);
			
			

			// second part (the image)
			messageBodyPart = new MimeBodyPart();
//			DataSource fds = new FileDataSource("C:\\Users\\nisha\\Downloads\\car1.jpg");
			ByteArrayDataSource bd=new ByteArrayDataSource(movieThumblin, "text/html");

			messageBodyPart.setDataHandler(new DataHandler(bd));
			messageBodyPart.setHeader("Content-ID", "<image>");

			// add image to the multipart
			multipart.addBodyPart(messageBodyPart);

			// put everything together
			message.setContent(multipart);
			// Send message
	         Transport.send(message);

			System.out.println("Sent message successfully....");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}

}
