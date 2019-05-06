package com.hgys.iptv.model;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * 结算类型-订购量表与CP关系表(cp_order_quantity)
 *
 * @author tjq
 * @version 1.0.0 2019-05-05
 */
@Entity
@Table(name="cp_order_quantity")
public class CpOrderQuantity implements java.io.Serializable{
    /** 版本号 */
    private static final long serialVersionUID = -1791188623421823689L;

    /** 主键 */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false, length = 11)
    private Integer id;

    /** 订购量结算名称*/
    @Column(name = "oqname", nullable = true, length = 50)
    private String oqname;

    /** CP名称*/
    @Column(name = "cpname", nullable = true, length = 50)
    private String cpname;

    @Column(name = "createtime", nullable = true, length = 50)
    /** 创建时间*/
    private Timestamp  createtime;

    /** 备用字段1 */
    @Column(name = "col1", nullable = true, length = 50)
    private String col1;

    /** 备用字段2 */
    @Column(name = "col2", nullable = true, length = 11)
    private Integer col2;

    /** 备用字段3 */
    @Column(name = "col3", nullable = true, length = 50)
    private String col3;

    /** 是否删除 */
    @Column(name = "isdelete", nullable = true, length = 2)
    private Integer isdelete;

    public Integer getId() {
        return id;
    }

    public String getOqname() {
        return oqname;
    }

    public String getCpname() {
        return cpname;
    }

    public Timestamp getCreatetime() {
        return createtime;
    }

    public String getCol1() {
        return col1;
    }

    public Integer getCol2() {
        return col2;
    }

    public String getCol3() {
        return col3;
    }

    public Integer getIsdelete() {
        return isdelete;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setOqname(String oqname) {
        this.oqname = oqname;
    }

    public void setCpname(String cpname) {
        this.cpname = cpname;
    }

    public void setCreatetime(Timestamp createtime) {
        this.createtime = createtime;
    }

    public void setCol1(String col1) {
        this.col1 = col1;
    }

    public void setCol2(Integer col2) {
        this.col2 = col2;
    }

    public void setCol3(String col3) {
        this.col3 = col3;
    }

    public void setIsdelete(Integer isdelete) {
        this.isdelete = isdelete;
    }
}
