package java.no.kristiania;


import no.kristiania.ProjectMemberDao;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class ProjectmemberTest {

    @Test
     void shouldRetriveProjectMember(){
        ProjectMemberDao dao= new ProjectMemberDao;
        dao.insertMember("Johannes");
        assertThat(dao.listall()).contains("Johannes");

    }

}
