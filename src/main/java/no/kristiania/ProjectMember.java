package no.kristiania;

import java.util.Objects;

public class ProjectMember {
    private String name;
    private String mail;

    public ProjectMember(String name, String mail) {
        this.name = name;
        this.mail = mail;
    }

    public String getName() {
        return name;
    }

    public String getMail() {
        return mail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProjectMember that = (ProjectMember) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(mail, that.mail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, mail);
    }

    @Override
    public String toString() {
        return "ProjectMember{" +
                "name='" + name + '\'' +
                ", mail='" + mail + '\'' +
                '}';
    }

}
