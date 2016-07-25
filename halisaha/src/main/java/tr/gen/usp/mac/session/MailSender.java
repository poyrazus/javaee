/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tr.gen.usp.mac.session;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Future;
import javax.ejb.AsyncResult;
import javax.ejb.Asynchronous;
import javax.ejb.Stateless;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

/**
 *
 * @author sitki.poyraz
 */
@Stateless
public class MailSender {

    /**
     * Send single mail.
     *
     * @param subject
     * @param body
     * @param from the recipient
     * @param to
     * @param cc
     * @return
     * @throws org.apache.commons.mail.EmailException
     */
    @Asynchronous
    public Future<String> sendSingleEmail(final String subject, final String body, final String from, String to, final List<InternetAddress> cc) throws EmailException {
        Email email = getLocalEmail();
        email.setSubject(subject);
        email.setMsg(body);
        email.setFrom(from);
        email.addTo(to);
        if (cc != null) {
            email.setCc(cc);
        }
//        String result = email.send();
        String result = "";
        return new AsyncResult<>(result);
    }

    private Email getLocalEmail() {
        Email email = new SimpleEmail();
        email.setHostName("10.1.37.37");
        email.setSmtpPort(25);
        // email.setAuthenticator(new DefaultAuthenticator("poyrazus", ""));
        // email.setSSLOnConnect(true);
        return email;
    }

    public static void main(String[] args) throws EmailException, AddressException {
        InternetAddress[] array = {new InternetAddress("sitki.poyraz@tubitak.gov.tr")};
        new MailSender().sendSingleEmail("Test subject", "Test body", "bte.b940@tubitak.gov.tr", "ebelgemsa@gmail.com", Arrays.asList(array));
    }

}
