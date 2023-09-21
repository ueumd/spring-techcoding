package jdk8;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Description: TODO
 * Author: hsd
 * Date: 2023-06-11 17:43
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserBean {
    private int Userid;
    private String Workid;
    private String Username;
}
