package no.kristiania;

import org.flywaydb.core.Flyway;
import org.postgresql.ds.PGSimpleDataSource;
import javax.sql.DataSource;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

public class ProjectMemberDao {

    private DataSource dataSource;

    public ProjectMemberDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void insertMember(ProjectMember member) throws SQLException {

        try (Connection conn = dataSource.getConnection();) {
            PreparedStatement statement = conn.prepareStatement(
                    "insert into projectmembers (name, email) values (? , ?)");
            statement.setString(1, member.getName());
            statement.setString(2, member.getMail());
            statement.executeUpdate();
        }

    }

    public List<ProjectMember> listAll() throws SQLException {

        try (Connection connection = dataSource.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(
                    "select * from projectmembers"
            )) {
                try (ResultSet resultSet = statement.executeQuery()) {
                    List<ProjectMember> result = new ArrayList<>();
                    ProjectMember projectMember;

                    while (resultSet.next()) {

                      result.add(projectMember = new ProjectMember(resultSet.getString("name"), resultSet.getString("email")));


                    }

                    return result;
                }
            }
        }
    }





    public static void main(String[] args) throws SQLException, IOException {
        System.out.println("enter a member name to insert: ");
        String projectName = new Scanner(System.in).nextLine();

        System.out.println("enter a email to "+projectName +": ");
        String projectMail = new Scanner(System.in).nextLine();

        Properties properties = new Properties();
        properties.load(new FileReader("task-manager.properties"));

        PGSimpleDataSource dataSource= new PGSimpleDataSource();
        dataSource.setURL(properties.getProperty("dataSource.url"));
        dataSource.setUser(properties.getProperty("dataSource.username"));
        dataSource.setPassword(properties.getProperty("dataSource.password"));
        Flyway.configure().dataSource(dataSource).load().migrate();
        ProjectMemberDao memberDao = new ProjectMemberDao(dataSource);
        memberDao.insertMember(new ProjectMember(projectName, projectMail));

        System.out.println(memberDao.listAll());
    }
}


