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
    void shouldRetriveProjectMemberName() throws SQLException {
        try {
            JdbcDataSource dataSource = new JdbcDataSource();
            dataSource.setURL("jdbc:h2:mem:test");

            Connection connection = dataSource.getConnection();
            connection.createStatement().executeUpdate(
                    "create table projectMembers (name varchar (100),email varchar(100))");

            ProjectMemberDao dao = new ProjectMemberDao(dataSource);
            String memberName = pickOne(new String[] {"Per", "Knut", "Arne", "Johannes"});

            dao.insertMember(memberName,"");
            assertThat(dao.listAll()).contains(memberName);
            System.out.println(dao.listAll());
            connection.createStatement().executeUpdate(
                    "drop table projectMembers");
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }


    @Test
    void shouldRetriveProjectMemberMail() throws SQLException {
        try {
            JdbcDataSource dataSource = new JdbcDataSource();
            dataSource.setURL("jdbc:h2:mem:test");

            Connection connection = dataSource.getConnection();
            connection.createStatement().executeUpdate(
                    "create table projectMembers (name varchar (100),email varchar(100))");

            ProjectMemberDao dao = new ProjectMemberDao(dataSource);
            String memberMail = pickOne(new String[] {"Per@kristiania.no", "Knut@kristiania.no", "Arne@kristiania.no", "Johannes@kristiania.no"});

            dao.insertMember("",memberMail);
            assertThat(dao.listAll()).contains(memberMail);
            System.out.println(dao.listAll());
            connection.createStatement().executeUpdate(
                    "drop table projectMembers");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    @Test
    void shouldRetriveProjectMemberNameandMail() throws SQLException {
        try {
            JdbcDataSource dataSource = new JdbcDataSource();
            dataSource.setURL("jdbc:h2:mem:test");

            Connection connection = dataSource.getConnection();
            connection.createStatement().executeUpdate(
                    "create table projectMembers (name varchar (100),email varchar(100))");

            ProjectMemberDao dao = new ProjectMemberDao(dataSource);

            String memberName = pickOne(new String[]{"Per", "Knut", "Arne", "Johannes"});
            String memberMail = "";
            if(memberName == "Per"){memberMail="Per@kristiania.no";}
            if(memberName == "Knut"){memberMail="Knut@kristiania.no";}
            if(memberName == "Arne"){memberMail="Arne@kristiania.no";}
            if(memberName == "Johannes"){memberMail="Johannes@kristiania.no";}

            dao.insertMember(memberName, memberMail);
            assertThat(dao.listAll()).contains(memberName);
            assertThat(dao.listAll()).contains(memberMail);
            System.out.println(dao.listAll());
            connection.createStatement().executeUpdate(
                    "drop table projectMembers");
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }


    private String pickOne(String[] strings) {

        return strings[new Random().nextInt(strings.length)] ;
    }

}
