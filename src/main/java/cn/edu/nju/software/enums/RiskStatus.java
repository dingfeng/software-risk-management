package cn.edu.nju.software.enums;

/**
 * @author 丁峰
 * @date 2016/11/6 11:55
 * @see RiskStatus
 */
public enum RiskStatus {

    TODO(1,"待处理"),
    DOING(2, "正在处理"),
    DONE(3, "处理完毕"),
    REJECTED(3, "已拒绝");


    private Integer type;
    private String description;

    RiskStatus(Integer type, String desc) {
        this.type = type;
        this.description = desc;
    }

    public String getDescription() {
        return description;
    }

}
