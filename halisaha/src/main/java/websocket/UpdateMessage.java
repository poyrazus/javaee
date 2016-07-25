///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//
//package websocket;
//
//import java.util.Date;
//import javax.websocket.DecodeException;
//import javax.websocket.Decoder;
//import javax.websocket.EncodeException;
//import javax.websocket.Encoder;
//import javax.websocket.EndpointConfig;
//
///**
// *
// * @author sitki.poyraz
// */
//public class UpdateMessage implements Decoder.Text<UpdateMessage>, Encoder.Text<UpdateMessage>{
//    private String message;
//    private String sender;
//    private Date received;
//
//    @Override
//    public UpdateMessage decode(String arg0) throws DecodeException {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//
//    @Override
//    public boolean willDecode(String arg0) {
//        return true;
//    }
//
//    @Override
//    public void init(EndpointConfig config) {
//    }
//
//    @Override
//    public void destroy() {
//    }
//
//    @Override
//    public String encode(UpdateMessage arg0) throws EncodeException {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//}
