package no.kristiania;




import org.h2.jdbcx.JdbcDataSource;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.Random;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class ProjectmemberTest {

    @Test
    void shouldRetriveProjectMember() throws SQLException {
        JdbcDataSource dataSource = new JdbcDataSource();
        dataSource.setURL("jdbc:h2:mem:test");

        dataSource.getConnection().createStatement().executeUpdate(
                "create table projectMembers (name varchar (100))");

        ProjectMemberDao dao = new ProjectMemberDao(dataSource);
        String memberName = pickOne(new String[] {"Per", "Knut", "Arne", "Johannes"});
        dao.insertMember(memberName);
        assertThat(dao.listAll()).contains(memberName);


    }

    private String pickOne(String[] strings) {

        return strings[new Random().nextInt(strings.length)] ;
    }

}
