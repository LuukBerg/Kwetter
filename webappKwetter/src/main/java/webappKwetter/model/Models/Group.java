package webappKwetter.model.Models;

import java.util.List;

public class Group {
    private List<Role> roles;
    private String groupname;
    private Group parent;
    private List<Group> children;

    public Group() {
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public String getGroupname() {
        return groupname;
    }

    public void setGroupname(String groupname) {
        this.groupname = groupname;
    }

    public Group getParent() {
        return parent;
    }

    public void setParent(Group parent) {
        this.parent = parent;
    }

    public List<Group> getChildren() {
        return children;
    }

    public void setChildren(List<Group> children) {
        this.children = children;
    }
}
