package LumExpress.services.cloudServices;
import com.cloudinary.Cloudinary;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;

@Service
@AllArgsConstructor
public class CloudinaryCloudServiceImpl implements CloudService{

    private final Cloudinary cloudinary;
    @Override
    public String upload(byte[] imageBytes, Map<?, ?> map) throws IOException {

        var uploadResponse = cloudinary
                .uploader()
                .upload(imageBytes, com.cloudinary.utils.ObjectUtils.emptyMap());
        return (String) uploadResponse.get("url");
    }
}
