package no.kristiania;


import no.kristiania.ProjectMemberDao;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class ProjectmemberTest {

    @Test
    void shouldRetriveProjectMember(){
        ProjectMemberDao dao = new ProjectMemberDao();

        String memberName = pickOne(new String[] {"Per", "Knut", "Arne", "Johannes"});
        dao.insertMember(memberName);
        assertThat(dao.listAll()).contains(memberName);

    }

    private String pickOne(String[] strings) {
        return strings[new Random().nextInt(strings.length)];

    }

}
