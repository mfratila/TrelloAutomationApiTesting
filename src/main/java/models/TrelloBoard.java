package models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({
        "id",
        "name",
        "desc",
        "idOrganization",
        "url",
        "shortUrl"
})

public class TrelloBoard {

    @JsonProperty("id")
    private String id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("desc")
    private String desc;
    @JsonProperty("idOrganization")
    private String idOrganization;
    @JsonProperty("url")
    private String url;
    @JsonProperty("shortUrl")
    private String shortUrl;

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("desc")
    public String getDesc() {
        return desc;
    }

    @JsonProperty("desc")
    public void setDesc(String desc) {
        this.desc = desc;
    }

    @JsonProperty("idOrganization")
    public String getIdOrganization() {
        return idOrganization;
    }

    @JsonProperty("idOrganization")
    public void setIdOrganization(String idOrganization) {
        this.idOrganization = idOrganization;
    }

    @JsonProperty("url")
    public String getUrl() {
        return url;
    }

    @JsonProperty("url")
    public void setUrl(String url) {
        this.url = url;
    }

    @JsonProperty("shortUrl")
    public String getShortUrl() {
        return shortUrl;
    }

    @JsonProperty("shortUrl")
    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }

}