package func.ueumd.tech.json;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserGroup {
    private String name;
    private List<User> users = new ArrayList<>();
}
