package no.kristiania;

import org.h2.jdbcx.JdbcDataSource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.sql.Connection;

import java.sql.SQLException;
import java.util.*;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class ProjectmemberTest {

    private Connection connection;
    private ProjectMemberDao dao;

    @BeforeEach
    void dbSetup() throws SQLException {
        JdbcDataSource dataSource = new JdbcDataSource();
        dataSource.setURL("jdbc:h2:mem:test");

        connection = dataSource.getConnection();
        connection.createStatement().executeUpdate(
                "create table projectMembers (name varchar (100),email varchar(100))");

        dao = new ProjectMemberDao(dataSource);
    }


    @Test
    void shouldRetriveProjectMemberNameH2() throws SQLException {


        String memberName = pickOne(new String[]{"Per", "Knut", "Arne", "Johannes"});

        dao.insertMember(memberName, "");
        assertThat(dao.listAll()).contains(memberName);
        System.out.println(dao.listAll());
        connection.createStatement().executeUpdate(
                "drop table projectMembers");

    }


    @Test
    void shouldRetriveProjectMemberMailH2() throws SQLException {

        String memberMail = pickOne(new String[]{"Per@kristiania.no", "Knut@kristiania.no", "Arne@kristiania.no", "Johannes@kristiania.no"});

        dao.insertMember("", memberMail);
        assertThat(dao.listAll()).contains(memberMail);
        System.out.println(dao.listAll());
        connection.createStatement().executeUpdate(
                "drop table projectMembers");

    }


    @Test
    void shouldRetriveProjectMemberNameandMailH2() throws SQLException {


        String memberName = pickOne(new String[]{"Per", "Knut", "Arne", "Johannes"});
        String memberMail = memberName + "@kristiania.no";


        dao.insertMember(memberName, memberMail);
        assertThat(dao.listAll()).contains(memberName);
        assertThat(dao.listAll()).contains(memberMail);
        System.out.println(dao.listAll());
        connection.createStatement().executeUpdate(
                "drop table projectMembers");


    }


    private String pickOne(String[] strings) {

        return strings[new Random().nextInt(strings.length)];
    }

}
