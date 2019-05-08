package com.hgys.iptv.model;

import javax.persistence.*;

/**
 * @ClassName Note
 * @Auther: wangz
 * @Date: 2019/5/8 12:03
 * @Description: TODO
 */

@Entity
@Table(name="note")
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false, length = 11)
    private Integer id;

    /**
     * cp主键，可以不提供，查所有
     */
    @Column(name = "cpid",length = 11)
    private Integer cpid;

    /**
     * 备注内容
     */
    @Column(name = "content",length = 200)
    private String content;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCpid() {
        return cpid;
    }

    public void setCpid(Integer cpid) {
        this.cpid = cpid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
