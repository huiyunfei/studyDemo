package com.example.esdemo.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Mapping;
import org.springframework.data.elasticsearch.annotations.Setting;

import java.util.List;

/**
 * @Auther: ZMJ
 * @Date: 2019/6/3 15:37
 * @Description:
 */
@Document(indexName = "coreqi",type = "user")
@Mapping(mappingPath = "user_mapping.json")//设置mapping
@Setting(settingPath = "elasticsearch_setting.json")//设置setting
public class EsUser {
    @Id //主键
    private String resumeId;  //ES中id不能定义为Long
    private String name;
    private String genderString;
    private String bornString;
    private Integer bornInteger;
    private List<EsPosion> intentionPosition;


public  EsUser(){

}

    public EsUser(String resumeId, String name, String genderString, String bornString, Integer bornInteger, List<EsPosion> intentionPosition) {
        this.resumeId = resumeId;
        this.name = name;
        this.genderString = genderString;
        this.bornString = bornString;
        this.bornInteger = bornInteger;
        this.intentionPosition = intentionPosition;
    }

    public String getResumeId() {
        return resumeId;
    }

    public void setResumeId(String resumeId) {
        this.resumeId = resumeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGenderString() {
        return genderString;
    }

    public void setGenderString(String genderString) {
        this.genderString = genderString;
    }

    public String getBornString() {
        return bornString;
    }

    public void setBornString(String bornString) {
        this.bornString = bornString;
    }

    public Integer getBornInteger() {
        return bornInteger;
    }

    public void setBornInteger(Integer bornInteger) {
        this.bornInteger = bornInteger;
    }

    public List<EsPosion> getIntentionPosition() {
        return intentionPosition;
    }

    public void setIntentionPosition(List<EsPosion> intentionPosition) {
        this.intentionPosition = intentionPosition;
    }
}
