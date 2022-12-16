import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Logger;

/**
 * 使用jdbc更新
 */
public class Update {
    private static final Logger log = Logger.getGlobal();

    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            log.severe(e.getMessage());
        }
        // 连接msql服务
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo", "root", "chen2983")) {

            PreparedStatement preparedStatement = connection.prepareStatement("update user set username = 'chenjiajie' ");
            int i = preparedStatement.executeUpdate();
            log.info("更新了" + i  + "条数据");

        } catch (SQLException e) {
            log.severe(e.getMessage());
        }
    }
}
