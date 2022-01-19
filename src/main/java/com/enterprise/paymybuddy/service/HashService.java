package com.enterprise.paymybuddy.service;

import javax.xml.bind.DatatypeConverter;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 The hash service is use for encrypt a data of user.

 @version 0.1
 */
public class HashService {
  /**
   Hashes the character string with the sha-256 algorithm.

   @param stringToConvert The character string to convert
   @return                The character string hashed
   */
  public static String getHash(String stringToConvert){
    String hashValue="";

    try{
      MessageDigest messageDigest=MessageDigest.getInstance("sha-256");
      messageDigest.update(stringToConvert.getBytes(StandardCharsets.UTF_8));
      byte[] digestBytes= messageDigest.digest();
          hashValue=DatatypeConverter.printHexBinary(digestBytes).toLowerCase();
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    }
    return hashValue;

  }
}
