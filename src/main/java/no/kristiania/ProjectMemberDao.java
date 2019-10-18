package no.kristiania;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProjectMemberDao {

    private List<String> projectMembers = new ArrayList<>();
    private DataSource dataSource;

    public ProjectMemberDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void insertMember(String memberName) {
        projectMembers.add(memberName);

        try (Connection conn = dataSource.getConnection();) {
            PreparedStatement statement = conn.prepareStatement(
                    "insert into projectMembers (name) values (?)");
            statement.setString(1, memberName);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public List<String> listAll() {
        try (Connection connection = dataSource.getConnection()) {
            connection.prepareStatement("select * from projectMembers");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return projectMembers;
    }
}