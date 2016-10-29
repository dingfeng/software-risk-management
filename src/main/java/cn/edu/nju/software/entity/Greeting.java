package cn.edu.nju.software.entity;

/**
 * @author 丁峰
 * @date 2016/10/23 23:00
 * @see Greeting
 */

public class Greeting {

    private final long id;
    private final String content;

    public Greeting(long id, String content) {
        this.id = id;
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }
}
