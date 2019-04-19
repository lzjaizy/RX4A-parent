package com.rx.authPlat.tenant.entity;

/**
 * @PackageName com.rx.authPlat.tenant.entity
 * @ClassName PostDataEntity
 * @Description TODO
 * @Author MengQingJun
 * @Date 2019/4/17
 * @Version 2.0
 **/
public class PostDataEntity {
    private Integer id;
    private String name;
    private String age;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "PostDataEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age='" + age + '\'' +
                '}';
    }
}
