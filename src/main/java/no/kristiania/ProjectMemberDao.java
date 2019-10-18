package no.kristiania;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProjectMemberDao {

    private List<String> members = new ArrayList<>();

    public void insertMember(String memberName) {
        members.add(memberName);

    }

    public List<String> listAll() {
        return members;
    }
}