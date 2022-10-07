//package LumExpress.services.cloud;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.stereotype.Component;
//
//@Configuration
//@ConfigurationProperties()
//public class CloudinaryConfig {
//    private  String name;
//    private  String key;
//    private  String secret;
//
//    public CloudinaryConfig(@Value("${cloudinary.cloud.name}") String name,
//                            @Value("${cloudinary.cloud.key}") String key,
//                            @Value("${cloudinary.cloud.secret}") String secret) {
//        this.name = name;
//        this.key = key;
//        this.secret = secret;
//    }
//    public CloudinaryConfig(@Value("${host.url}") String url) {
//        return new CloudinaryConfig();
//
//}
//
//
