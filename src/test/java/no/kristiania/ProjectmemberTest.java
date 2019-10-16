package java.no.kristiania;


import no.kristiania.ProjectMemberDao;
import org.junit.jupiter.api.Test;

public class ProjectmemberTest {

    @Test shouldRetriveProjectMember(){
        ProjectMemberDao dao= new ProjectMemberDao;
        dao.insertMember("Johannes");
        assertThat(dao.listall()).contains("Johannes");

    }

}
