import java.sql.*;
import java.util.logging.Logger;

/**
 * 使用jdbc批量插入
 */
public class BatchInsert {
    private static final Logger log = Logger.getGlobal();

    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            log.severe(e.getMessage());
        }
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo?rewriteBatchedStatements=true", "root", "chen2983")) {
            PreparedStatement preparedStatement = connection.prepareStatement("insert into user(id, username, password, sex, email) values (null, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            connection.setAutoCommit(false);
            for (int i = 0; i < 10000; i++) {
                preparedStatement.setString(1, "chenjiajie");
                preparedStatement.setString(2, "123456");
                preparedStatement.setInt(3, 1);
                preparedStatement.setString(4, "1274448776@qq.com");

                // 将sql语句打包到容器中
                preparedStatement.addBatch();
                if (i % 1000 == 0) {
                    // 每一千条数据执行一次sql
                    preparedStatement.executeBatch();
                    // 亲空容器
                    preparedStatement.clearBatch();
                }
                // 防止sql漏提交，比如数据不是1000的倍数，最后一条数据就会漏提
                preparedStatement.executeBatch();
                preparedStatement.clearBatch();

            }
            connection.commit();

        } catch (SQLException e) {
            log.severe(e.getMessage());
        }
    }
}
