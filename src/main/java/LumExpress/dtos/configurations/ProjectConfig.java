package LumExpress.dtos.configurations;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProjectConfig {
    @Value("${cloudinary.api.name}")
    private String Cloud_name;
    @Value("${cloudinary.api.key}")
    private String Cloud_key;
    @Value("${cloudinary.api.secret}")
    private String Cloud_Secret;

    @Bean
    public ModelMapper modelMapper(){
       return new ModelMapper();
    }

    @Bean
    public Cloudinary cloudinary(){
       return new Cloudinary(ObjectUtils.asMap(
                       "cloud_name", Cloud_name,
                       "api_key",Cloud_key,
                       "api_secret",Cloud_Secret,
                       "secure",true)
       );

    }
}
