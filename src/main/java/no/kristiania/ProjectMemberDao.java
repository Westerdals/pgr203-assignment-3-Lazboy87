package no.kristiania;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProjectMemberDao {

    private DataSource dataSource;

    public ProjectMemberDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void insertMember(String memberName) throws SQLException {

        try (Connection conn = dataSource.getConnection();) {
            PreparedStatement statement = conn.prepareStatement(
                    "insert into projectMembers (name) values (?)");
            statement.setString(1, memberName);
            statement.executeUpdate();
        }

    }

    public List<String> listAll() throws SQLException {
        try (Connection connection = dataSource.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(
                    "select * from projectMembers"
            )) {
                try (ResultSet resultSet = statement.executeQuery()) {
                    List<String> result = new ArrayList<>();

                    while (resultSet.next()) {
                        result.add(resultSet.getString("name"));
                    }
                    return result;
                }
            }
        }
    }
}


