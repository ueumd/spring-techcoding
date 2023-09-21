package func.ueumd.tech.tools;

import func.ueumd.tech.json.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;


@Data
@AllArgsConstructor
@NoArgsConstructor
class UserModel {
    private String username;
    private String password;
}

public class StudyTest {
    @Test
    public void test() {
        User user = new User();


        UserModel userModel  = new UserModel();
        userModel.setUsername("admin");
        userModel.setPassword("hello");

        // 把userModel 深度拷贝到user中
        BeanUtils.copyProperties(userModel, user);

        userModel.setPassword("1234");

        System.out.println(userModel);  // UserModel(username=admin, password=1234)
        System.out.println(user);       // User(username=admin, password=hello)
    }
}
