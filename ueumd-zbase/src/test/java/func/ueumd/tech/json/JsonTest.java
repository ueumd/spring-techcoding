package func.ueumd.tech.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JsonTest {

    @Test
    public void test() {
        User user = new User("root", "123456");
        String userJson = JSON.toJSONString(user);
        System.out.println(userJson);
        // {"password":"123456","username":"root"}

        //集合转json串
        User user1 = new User("zhangsan", "123456");
        User user2 = new User("lisi", "000");

        List<User> users = new ArrayList<>();
        users.add(user1);
        users.add(user2);

        String usersjson = JSON.toJSONString(users);
        System.out.println("集合转json串：" + usersjson);

        // [{"password":"123456","username":"zhangsan"},{"password":"000","username":"lisi"}]


        //复杂java类转换对象
        UserGroup userGroup = new UserGroup("userGroup", users);
        //调用toJSONString()
        String userGroupJson = JSON.toJSONString(userGroup);
        System.out.println("复杂java类转换json串：" + userGroupJson);

        /**
         {"password":"123456","username":"root"}
         集合转json串：[{"password":"123456","username":"zhangsan"},{"password":"000","username":"lisi"}]
         复杂java类转换json串：{"name":"userGroup","users":[{"password":"123456","username":"zhangsan"},{"password":"000","username":"lisi"}]}
         */
    }

    @Test
    public void test2() {
        String str= "{\"password\":\"123456\",\"username\":\"root\"}";
        JSONObject json = (JSONObject) JSON.parse(str);
        System.out.println(json);
        System.out.println(json.get("username"));
        System.out.println(json.get("password"));
    }

    @Test
    public void test3() {
        String str= "{\"password\":\"123456\",\"username\":\"root\"}";
        JSONObject json = JSON.parseObject(str);
        System.out.println(json);

        System.out.println(json.get("username"));
        System.out.println(json.get("password"));
    }
}
