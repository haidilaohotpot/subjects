package cn.ffcs.controller;

import cn.ffcs.bean.User;
import cn.ffcs.util.PagedGridResult;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * 返回的结果
 * @author xiezengcheng
 * @date 2020-08-06
 */
@XmlRootElement(name = "root")
public class JsonResult {

    private Integer code;

    private String msg;

    private List<User> data;

    // 当前页数
    private int page;
    // 总页数
    private int total;
    // 总记录数
    private long records;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public long getRecords() {
        return records;
    }

    public void setRecords(long records) {
        this.records = records;
    }

    public Integer getCode() {
        return code;
    }
    @XmlElement
    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }
    @XmlElement
    public void setMsg(String msg) {
        this.msg = msg;
    }

    @XmlElementWrapper(name="users")
    @XmlElement(name="user")
    public List<User> getData() {
        return data;
    }

    public void setData(List<User> data) {
        this.data = data;
    }

    public static JsonResult success(){
        JsonResult jsonResult = new JsonResult();
        jsonResult.setCode(0);
        jsonResult.setMsg("执行成功");
        return jsonResult;
    }

    public static JsonResult failed(){
        JsonResult jsonResult = new JsonResult();
        jsonResult.setCode(1);
        jsonResult.setMsg("执行失败");
        return jsonResult;
    }

    public static JsonResult success(PagedGridResult data){
        JsonResult jsonResult = new JsonResult();
        jsonResult.setCode(0);
        jsonResult.setMsg("执行成功");
        jsonResult.setData((List<User>) data.getRows());
        jsonResult.setPage(data.getPage());
        jsonResult.setRecords(data.getRecords());
        jsonResult.setTotal(data.getTotal());

        return jsonResult;
    }

}
