package java.no.kristiania;


import no.kristiania.ProjectMemberDao;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class ProjectmemberTest {

    @Test
    void shouldRetriveProjectMember(){
        ProjectMemberDao dao = new ProjectMemberDao();
        String member = "Johannes";
        dao.insertMember(member);
        assertThat(dao.listAll()).contains(member);

    }

}
