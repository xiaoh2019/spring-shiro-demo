package com.cyzs.shirodemo.dao;

import org.apache.shiro.session.Session;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


/**
 * @Author xiaoh
 * @create 2019-10-08 13:32
 */
public class SerializableUtils {

    public static String serialize(Session session){
        String s="";
        try{

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(session);
            s = byteArrayOutputStream.toString("UTF-8");

        }catch (Exception e){
            e.printStackTrace();
        }
        return s;
    }

    public static Session deSerialize(String sessionString){
        Session session=null;
        try{
            byte[] bytes = sessionString.getBytes("UTF-8");
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
            ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
            session= (Session) objectInputStream.readObject();

        }catch (Exception e){
            e.printStackTrace();
        }
        return session;
    }


}
