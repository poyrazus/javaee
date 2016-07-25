/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tr.gen.usp.mac.jsf;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.mail.internet.InternetAddress;
import org.apache.commons.mail.EmailException;
import tr.gen.usp.mac.entity.Oyuncu;
import tr.gen.usp.mac.session.MailSender;
import tr.gen.usp.mac.session.OyuncuFacade;

/**
 *
 * @author sitki.poyraz
 */
@ApplicationScoped
public class AppController {
    @Inject
    private MailSender mailSender;
    @EJB
    private OyuncuFacade ejbFacade;
    private  List<InternetAddress> allMembersToEmail;

    @PostConstruct
    public void init() {
        initializeEmailMembers();
    }

    public void initializeEmailMembers() {
        allMembersToEmail = new ArrayList<>();
        List<Oyuncu> list = ejbFacade.findAll();
        for (Oyuncu oyuncu : list) {
            if (oyuncu != null && oyuncu.getEmail() != null && !oyuncu.getEmail().isEmpty()) {
                try {
                    InternetAddress addr = new InternetAddress(oyuncu.getEmail(), oyuncu.getAd());
                    allMembersToEmail.add(addr);
                } catch (UnsupportedEncodingException ex) {
                    Logger.getLogger(AppController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    public void sendEmail(){}

    void sendEmail(String subject) {
        try {
            Future<String> result = mailSender.sendSingleEmail(subject, subject, "bte.b940@tubitak.gov.tr", "sitki.poyraz@tubitak.gov.tr", allMembersToEmail);
            result.get();
        } catch (EmailException | ExecutionException | InterruptedException e) {
            Logger.getLogger(AppController.class.getName()).log(Level.SEVERE, null, e);
            e.printStackTrace();
        }
    }
}
