package com.aditya.Service;

import com.aditya.dto.ContactRequest;
import com.sendgrid.*;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Value("${SENDGRID_API_KEY}")
    private String sendGridApiKey;

    public void sendContactMail(ContactRequest request) {

        try {
            Email from = new Email("adityarana1140@gmail.com"); // verified sender
            Email to = new Email("adityarana7780@gmail.com");   // receiver
            String subject = "📩 New Portfolio Contact Message";

            String htmlContent =
                    "<div style='font-family: Arial, Helvetica, sans-serif; background-color: #f4f6fb; padding: 30px;'>" +
                            "<div style='max-width: 600px; margin: auto; background: #ffffff; border-radius: 12px; overflow: hidden; box-shadow: 0 10px 30px rgba(0,0,0,0.1);'>" +

                            "<div style='background: linear-gradient(90deg, #7b5cff, #ff6fd8); padding: 20px; text-align: center; color: #ffffff;'>" +
                            "<h2 style='margin: 0;'>New Portfolio Contact</h2>" +
                            "</div>" +

                            "<div style='padding: 24px;'>" +
                            "<p style='font-size: 15px; color: #333;'>You have received a new message from your portfolio contact form.</p>" +

                            "<table style='width: 100%; border-collapse: collapse; margin-top: 20px; font-size: 14px;'>" +
                            "<tr><td style='padding: 10px; font-weight: bold; width: 120px;'>Name:</td>" +
                            "<td style='padding: 10px;'>" + request.getName() + "</td></tr>" +

                            "<tr><td style='padding: 10px; font-weight: bold;'>Email:</td>" +
                            "<td style='padding: 10px;'>" + request.getEmail() + "</td></tr>" +
                            "</table>" +

                            "<div style='margin-top: 20px; padding: 16px; background: #f7f7ff; border-radius: 8px; font-size: 14px; color: #444;'>" +
                            "<strong>Message:</strong>" +
                            "<p style='margin-top: 8px; line-height: 1.6;'>" + request.getMessage() + "</p>" +
                            "</div>" +
                            "</div>" +

                            "<div style='padding: 14px; text-align: center; font-size: 12px; color: #888; border-top: 1px solid #eee;'>" +
                            "This message was sent from your personal portfolio website." +
                            "</div>" +

                            "</div></div>";

            Content content = new Content("text/html", htmlContent);
            Mail mail = new Mail(from, subject, to, content);

            SendGrid sg = new SendGrid(sendGridApiKey);
            Request sgRequest = new Request();

            sgRequest.setMethod(Method.POST);
            sgRequest.setEndpoint("mail/send");
            sgRequest.setBody(mail.build());

            sg.api(sgRequest);

        } catch (Exception e) {
            throw new RuntimeException("Failed to send email using SendGrid", e);
        }
    }
}
