import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/**
 * 使用jdbc查询
 */
public class Query {
    private static final Logger log = Logger.getGlobal();
    public static void main(String[] args) {
        // 注册mysql驱动
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            log.severe(e.getMessage());
        }
        // 连接msql服务
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo", "root", "chen2983")) {
            // 获取到PreparedStatement
            PreparedStatement statement = connection.prepareStatement("select id, username, password, sex, email from user limit 10");
            // 执行sql获取到结果集
            ResultSet resultSet = statement.executeQuery();
            int maxRows = statement.getMaxRows();
            int maxFieldSize = statement.getMaxFieldSize();
            List<Map<String, Object>> list = new ArrayList<>(maxRows);
            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                int sex = resultSet.getInt("sex");
                String email = resultSet.getString("email");
                Map<String, Object> map = new HashMap<>(maxFieldSize);
                map.put("id", id);
                map.put("username", username);
                map.put("password", password);
                map.put("sex", sex);
                map.put("email", email);
                list.add(map);
            }
            log.info(list.toString());
        } catch (SQLException e) {
            log.severe(e.getMessage());
        }
    }

}
