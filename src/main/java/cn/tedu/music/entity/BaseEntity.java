package cn.tedu.music.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 实体类基类
 */
class BaseEntity implements Serializable {

    private String createUser;
    private Date createTime;
    private String modifiedUser;
    private Date modifiedTime;

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getModifiesUser() {
        return modifiedUser;
    }

    public void setModifiesUser(String modifiesUser) {
        this.modifiedUser = modifiesUser;
    }

    public Date getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(Date modifiedTime) {
        this.modifiedTime = modifiedTime;
    }
}
