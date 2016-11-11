package cn.edu.nju.software.enums;

/**
 * @author 丁峰
 * @date 2016/11/6 12:07
 * @see RiskPossibility
 */
public enum RiskPossibility {
    HIGH(1,"高"),
    MEDIUM(2, "中"),
    LOW(3, "低");


    private Integer type;
    private String description;

    RiskPossibility(Integer type, String desc) {
        this.type = type;
        this.description = desc;
    }

    public String getDescription() {
        return description;
    }
}
