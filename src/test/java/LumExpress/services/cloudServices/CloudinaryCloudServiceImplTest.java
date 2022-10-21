package LumExpress.services.cloudServices;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class CloudinaryCloudServiceImplTest {
    @Autowired
    private CloudService cloudService;
    private MultipartFile file;
    private MultipartFile file2;

    @BeforeEach
    void setUp() throws IOException {
        Path path = Paths.get("C:\\Users\\user\\IdeaProjects\\lumi-Express\\src\\main\\resources\\images\\milk.jpg");
        Path path2 = Paths.get("C:\\Users\\user\\IdeaProjects\\lumi-Express\\src\\main\\resources\\images\\GOLDEN-MORN-PUFFS-BRANDSPUR2.jpg");
         file = new MockMultipartFile("Peak", Files.readAllBytes(path));
         file2 = new MockMultipartFile("Golden Morn", Files.readAllBytes(path2));

    }

    @Test
    @DisplayName("cloudinary upload test")
    void uploadTest() throws IOException {
        try{
           String response =  cloudService.upload(file.getBytes(), null);
           assertThat(response).isNotNull();
        }catch(IOException ex){
            ex.printStackTrace();
        }
    }

    @Test
    @DisplayName("cloudinary upload test2")
    void uploadTest2() throws IOException {
        try{
            String response2 =  cloudService.upload(file2.getBytes(), null);
            assertThat(response2).isNotNull();
        }catch(IOException ex){
            ex.printStackTrace();
        }
    }

}