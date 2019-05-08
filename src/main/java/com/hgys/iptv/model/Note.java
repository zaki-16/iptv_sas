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
     * 模板类型，如查询cp、维度等不同类型的模板
     */
    @Column(name = "stateId",length = 11)
    private Integer typeId;

    private String userId;
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

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
