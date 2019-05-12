package com.fontys.kweetservice.Model.DTO;

import java.util.ArrayList;
import java.util.List;

public abstract class DTO {
    private List<Link> links;


    public DTO() {
        this.links = new ArrayList<>();
    }

    public void addLink(String url, String rel) {
        links.add(new Link(url, rel));
    }

    public List<Link> getLinks() {
        return links;
    }

    public void setLinks(List<Link> links) {
        this.links = links;
    }
}
