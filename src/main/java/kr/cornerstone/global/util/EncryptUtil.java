package kr.cornerstone.global.util;

import lombok.extern.slf4j.Slf4j;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

@Slf4j
public class EncryptUtil {

    public static String getEncMD5(String str){
        if(StringUtil.isNullOrBlank(str)) {
            return null;
        }
        String MD5 = "";
        try{
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes());
            byte byteData[] = md.digest();
            StringBuffer sb = new StringBuffer();
            for(int i = 0 ; i < byteData.length ; i++){
                sb.append(Integer.toString((byteData[i]&0xff) + 0x100, 16).substring(1));
            }
            MD5 = sb.toString();

        }catch(NoSuchAlgorithmException e){
            log.error("Error Occurs!!", e);
            MD5 = null;
        }
        return MD5;
    }

    public static String createRandomFileName(String str){
        String newFileName = "";
        if(!"".equals(str)) {
            String extension = str.substring(str.lastIndexOf("."), str.length());

            UUID uuid = UUID.randomUUID();
            newFileName = uuid.toString() + extension;
        }
        return newFileName;
    }
}