package cn.edu.nju.software.util;

/**
 * Created by zy118686 on 2016/11/6.
 * service层给controller层传递的结果
 */
public class ResultDTO<T> {

    private T data ;

    private boolean isSuccess ;

    private String errorMsg ;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
