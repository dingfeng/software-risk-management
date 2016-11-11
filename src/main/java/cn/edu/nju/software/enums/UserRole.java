package cn.edu.nju.software.enums;

/**
 * @author 丁峰
 * @date 2016/11/6 12:14
 * @see UserRole
 */
public enum  UserRole {
    SYSTEM_MANAGER,DIRECTOR,NORMAL;
    public static String getDescription(UserRole role)
    {
        String desc = null;
       switch (role)
       {
           case SYSTEM_MANAGER:
               desc = "系统管理员";
               break;
           case DIRECTOR:
               desc = "主管";
               break;
           case NORMAL:
               desc = "普通用户";
               break;
       }
       return desc;
    }
}
