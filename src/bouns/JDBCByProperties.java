package bouns;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class JDBCByProperties {
	public static void main(String[] args) {

        // 只需要将不同的驱动放在配置文件中，就可以实现连接不同的数据库
        try {
            // 1.注册驱动   
            Class.forName(PropertiesUtil.getDriverProperties("MySQL"));
            // 2. 连接数据库
            Connection conn = DriverManager.getConnection(
                    PropertiesUtil.getUrlProperties("MySQL"),       PropertiesUtil.getUsernameProperties("MySQL"),              
                    PropertiesUtil.getPasswordProperties("MySQL"));
            System.out.println("傻瓜");
            System.out.println(PropertiesUtil.getUrlProperties("MySQL"));
            System.out.println("笨蛋");
        
            // 3. 创建执行的sql语句, 建议使用PreparedStatement
            String sql = "select * from sale_todo where uniqueCode = ?";
            PreparedStatement prep = null;
            prep = (PreparedStatement) conn.prepareStatement(sql);
            prep.setInt(1, 1);
            // 4. 查询sql 语句, 返回一个结果集
            ResultSet result = prep.executeQuery();
            // 5. 处理结果集， 释放资源
            while (result.next()) {
                System.out.println(result.getInt("id"));
                System.out.println(result.getString("name"));
                System.out.println(result.getInt("age"));
                System.out.println(result.getString("salary"));
            }
            conn.close();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            System.out.println("驱动注册未成功");
            e.printStackTrace();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}