package demo5;

import org.apache.shiro.authc.credential.DefaultPasswordService;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.codec.Hex;
import org.apache.shiro.crypto.AesCipherService;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.DefaultHashService;
import org.apache.shiro.crypto.hash.HashRequest;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.util.SimpleByteSource;
import org.junit.Assert;
import org.junit.Test;

import java.security.Key;

/**
 * Created by s on 2017/9/8.
 * 编码/散列/加密
 */
public class CodecAndCryptoTest {

    //编码和解码
    @Test
    public void testBase64(){
        String str = "hello";
        String base64Encoded = Base64.encodeToString(str.getBytes());
        String str2 = Base64.decodeToString(base64Encoded);
        System.out.println(base64Encoded);
        System.out.println(str2);
        Assert.assertEquals(str, str2);
    }
    @Test
    public  void testHex(){
        String str="hello";
        String encoded=Hex.encodeToString(str.getBytes());
        String str2=new String(Hex.decode(encoded));
        System.out.println(str2);
        Assert.assertEquals(str,str2);
    }


    //散列

    /**
     *
     * md5和sha
     *
     */
    @Test
    public void testHash(){
        String str="hello";
        String salt="ccy";
        String encoded= new Md5Hash(str,salt).toString();
        System.out.println(encoded);

        DefaultHashService defaultHashService=new DefaultHashService();
        defaultHashService.setHashAlgorithmName("MD5");//算法类型 默认SHA-512
        defaultHashService.setPrivateSalt(new SimpleByteSource("a"));//私盐
        defaultHashService.setGeneratePublicSalt(true);//是否生成公盐，默认false
        defaultHashService.setRandomNumberGenerator(new SecureRandomNumberGenerator());//用于生成公盐。默认就这个
        defaultHashService.setHashIterations(1);//生成Hash值的迭代次数

        HashRequest request = new HashRequest.Builder()
                .setAlgorithmName("MD5").setSource(ByteSource.Util.bytes("hello"))
                .setSalt(ByteSource.Util.bytes("123")).setIterations(2).build();
        String hex = defaultHashService.computeHash(request).toHex();
        System.out.println(hex);

    }

    /**
     * 加密/解密
     */
    @Test
    public void testAesCipherService(){
        AesCipherService aesCipherService=new AesCipherService();
        aesCipherService.setKeySize(128);
        //生成key
        Key key=aesCipherService.generateNewKey();
        String text="hello";

        //加密
        String encodeText=aesCipherService.encrypt(text.getBytes(),key.getEncoded()).toHex();

        //解密
        String text2 =
                new String(aesCipherService.decrypt(Hex.decode(encodeText), key.getEncoded()).getBytes());
        System.out.println(text);
        System.out.println(encodeText);
        System.out.println(text2);
        ;
    }

    /**
     * PasswordService/CredentialsMatcher
     */
    @Test
    public void service(){
        DefaultPasswordService defaultPasswordService=new DefaultPasswordService();
        String hello = defaultPasswordService.encryptPassword("hello");
        System.out.println(hello);
    }
}
