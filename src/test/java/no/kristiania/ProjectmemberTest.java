package no.kristiania;

import org.flywaydb.core.Flyway;
import org.h2.jdbcx.JdbcDataSource;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.sql.SQLException;
import java.util.*;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class ProjectmemberTest {

    private JdbcDataSource jdbcDataSource;


    @BeforeEach
void testDataSource() {
        jdbcDataSource = new JdbcDataSource();
        jdbcDataSource.setURL("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1");
        Flyway.configure().dataSource(jdbcDataSource).load().migrate();


    }


    @Test
    void shouldRetriveProjectMemberNameH2() throws SQLException {

        ProjectMemberDao dao = new ProjectMemberDao(jdbcDataSource);



        String memberName = pickOne(new String[]{"Per", "Knut", "Arne", "Johannes"});

        dao.insertMember(memberName, "");
        assertThat(dao.listAll()).contains(memberName);
        System.out.println(dao.listAll());

    }


    @Test
    void shouldRetriveProjectMemberMailH2() throws SQLException {


        ProjectMemberDao dao = new ProjectMemberDao(jdbcDataSource);

        String memberMail = pickOne(new String[]{"Per@kristiania.no", "Knut@kristiania.no", "Arne@kristiania.no", "Johannes@kristiania.no"});

        dao.insertMember("", memberMail);
        assertThat(dao.listAll()).contains(memberMail);
        System.out.println(dao.listAll());


    }


    @Test
    void shouldRetriveProjectMemberNameandMailH2() throws SQLException {



        ProjectMemberDao dao = new ProjectMemberDao(jdbcDataSource);

        String memberName = pickOne(new String[]{"Per", "Knut", "Arne", "Johannes"});
        String memberMail = memberName + "@kristiania.no";


        dao.insertMember(memberName, memberMail);
        assertThat(dao.listAll()).contains(memberName);
        assertThat(dao.listAll()).contains(memberMail);
        System.out.println(dao.listAll());

    }


    private String pickOne(String[] strings) {

        return strings[new Random().nextInt(strings.length)];
    }

}
