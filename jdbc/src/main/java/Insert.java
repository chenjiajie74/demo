import java.sql.*;
import java.util.logging.Logger;

/**
 * 使用jdbc查询
 */
public class Insert {
    private static final Logger log = Logger.getGlobal();

    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            log.severe(e.getMessage());
        }
        // 连接msql服务
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo", "root", "chen2983")) {
            PreparedStatement preparedStatement = connection.prepareStatement("insert into user value (null, 'chenjiajie', '123456', '1', '1274448776@qq.com')", Statement.RETURN_GENERATED_KEYS);
            boolean execute = preparedStatement.execute();
            ResultSet keys = preparedStatement.getGeneratedKeys();
            // 获取自增id
            while (keys.next()) {
               log.info(String.valueOf(keys.getInt(1)));
            }

        } catch (SQLException e) {
            log.severe(e.getMessage());
        }
    }
}
