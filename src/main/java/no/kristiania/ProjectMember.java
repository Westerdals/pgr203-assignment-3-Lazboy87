package no.kristiania;

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
}
