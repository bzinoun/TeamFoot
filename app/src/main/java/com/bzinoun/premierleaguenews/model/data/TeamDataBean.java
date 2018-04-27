package com.bzinoun.premierleaguenews.model.data;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class TeamDataBean extends RealmObject {

    private String id;

    @PrimaryKey
    private String name;
    private String code;
    private String shortName;
    private String squadMarketValue;
    private String crestUrl;



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getSquadMarketValue() {
        return squadMarketValue;
    }

    public void setSquadMarketValue(String squadMarketValue) {
        this.squadMarketValue = squadMarketValue;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCrestUrl() {
        return crestUrl;
    }

    public void setCrestUrl(String crestUrl) {
        this.crestUrl = crestUrl;
    }

    public void setShortNameView(String shortName)
    {
        this.shortName = shortName;
    }

}