package com.alkemy.ar.service;

import java.io.IOException;

import org.springframework.stereotype.Service;

import com.alkemy.ar.error.ErrorMsg;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;

@Service
public class EmailService {

	private static String API_KEY ="";

	public void sendWelcomeEmail(String username) throws IOException{

		Email mailSender = new Email("pabloamado8@gmail.com");

		String subject = "Notificacion de actividad";

		Email mailReceiver = new Email(username);

		Content content = new Content("text/plain", "Bienvenido al challenge de Alkemy");

		Mail mail = new Mail(mailSender, subject, mailReceiver, content);

		SendGrid sg = new SendGrid(API_KEY);

		Request request = new Request();

		try {

			request.setMethod(Method.POST);

			request.setEndpoint("mail/send");

			request.setBody(mail.build());

			Response response = sg.api(request);

			System.out.println(response.getStatusCode());

			System.out.println(response.getBody());

			System.out.println(response.getHeaders());

		} catch (IOException e) {

			throw new IOException(ErrorMsg.ERROR_SENDING_EMAIL.toString(),e);

		}

	}

}
