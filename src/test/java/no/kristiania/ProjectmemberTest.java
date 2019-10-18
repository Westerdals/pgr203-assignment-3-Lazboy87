package no.kristiania;

import org.h2.jdbcx.JdbcDataSource;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Member;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Random;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class ProjectmemberTest {

    @Test
    void shouldRetriveProjectMemberandMail() throws SQLException {
        JdbcDataSource dataSource = new JdbcDataSource();
        dataSource.setURL("jdbc:h2:mem:test");

        Connection connection = dataSource.getConnection();
        connection.createStatement().executeUpdate(
                "create table projectMembers (name varchar (100),email varchar(100))");

        ProjectMemberDao dao = new ProjectMemberDao(dataSource);
        String memberMail = pickOne(new String[] {"Per", "Knut", "Arne", "Johannes"});
        String memberName = pickOne(new String[] {"Per", "Knut", "Arne", "Johannes"});
        dao.insertMember(memberName,memberMail);
        assertThat(dao.listAll()).contains(memberName);
        System.out.println(dao.listAll());


    }



    private String pickOne(String[] strings) {

        return strings[new Random().nextInt(strings.length)] ;
    }

}
