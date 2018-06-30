package com.lance.entity;

import javax.persistence.*;

/**
 * Created by lance on 2018/6/26.
 */
@Entity
@NamedQueries({
        @NamedQuery(name="TestEntity.getAllByNQ",query = "select test from TestEntity test"),
        @NamedQuery(name="TestEntity.getUserByNQ", query ="select test from TestEntity test where test.name=?1" )
})

@Table(name = "test", schema = "demo", catalog = "")
public class TestEntity {
    private String name;
    private String nickname;

    @Id
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "nickname")
    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TestEntity that = (TestEntity) o;

        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (nickname != null ? !nickname.equals(that.nickname) : that.nickname != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (nickname != null ? nickname.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "TestEntity{" +
                "name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
    public TestEntity(String name,String nickname){
        this.name=name;
        this.nickname=nickname;
    }
    public TestEntity(){}
}
