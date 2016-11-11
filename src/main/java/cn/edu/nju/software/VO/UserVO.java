package cn.edu.nju.software.VO;

import cn.edu.nju.software.entity.User;
import cn.edu.nju.software.enums.UserRole;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

/**
 * @author 丁峰
 * @date 2016/11/11 23:11
 * @see UserVO
 */
@Data
@NoArgsConstructor
public class UserVO
{
    public UserVO(User user)
    {
        this.setAccount(user.getAccount());
        this.setEmail(user.getEmail());
        this.setId(user.getId());
        this.role = UserRole.getDescription(user.getRole());
    }
    private Long   id;     //主键
    private String account; //账号
    private String email;  //邮箱
    private String role;  //角色
}
