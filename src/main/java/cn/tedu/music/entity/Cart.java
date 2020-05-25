package cn.tedu.music.entity;
/**
 * 购物车数据的实体类
 */

import java.util.Objects;

public class Cart extends BaseEntity{
    private Integer cid;
    private Integer uid;
    private Long gid;
    private Integer num;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cart cart = (Cart) o;
        return cid.equals(cart.cid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cid);
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Long getGid() {
        return gid;
    }

    public void setGid(Long gid) {
        this.gid = gid;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "cid=" + cid +
                ", uid=" + uid +
                ", gid=" + gid +
                ", num=" + num +
                '}';
    }
}
