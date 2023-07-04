package com.example.pc04_miope.entity;

import com.google.gson.annotations.SerializedName;

public class JokeApiResponse {

    @SerializedName("formatVersion")
    private String formatVersion;

    @SerializedName("type")
    private String type;

    @SerializedName("flags")
    private Object flags;

    @SerializedName("joke")
    private String joke;

    @SerializedName("lang")
    private String lang;

    @SerializedName("category")
    private String category;

    // Agrega aquí cualquier otro campo adicional que desees mostrar

    public String getJoke() {
        return joke;
    }

    public String getCategory() {
        return category;
    }

    public String getFormatVersion() {
        return formatVersion;
    }

    public void setFormatVersion(String formatVersion) {
        this.formatVersion = formatVersion;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Object getFlags() {
        return flags;
    }

    public void setFlags(Object flags) {
        this.flags = flags;
    }

    public void setJoke(String joke) {
        this.joke = joke;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "JokeApiResponse{" +
                "formatVersion='" + formatVersion + '\'' +
                ", type='" + type + '\'' +
                ", flags=" + flags +
                ", joke='" + joke + '\'' +
                ", lang='" + lang + '\'' +
                ", category='" + category + '\'' +
                '}';
    }
}
