import com.bingo.qa.QaApplication;
import com.bingo.qa.service.SensitiveService;
import javafx.application.Application;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = QaApplication.class)

public class SensiticeTest {
    @Autowired
    SensitiveService sensitiveService;
    @Test
    public void testSensitive(){
        String result = sensitiveService.filter("hello,fuck you,大傻逼");
        System.out.println(result);


    }
}
