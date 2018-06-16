package bouns;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class JDBCByProperties {
	public static void main(String[] args) {

        // ֻ��Ҫ����ͬ���������������ļ��У��Ϳ���ʵ�����Ӳ�ͬ�����ݿ�
        try {
            // 1.ע������   
            Class.forName(PropertiesUtil.getDriverProperties("MySQL"));
            // 2. �������ݿ�
            Connection conn = DriverManager.getConnection(
                    PropertiesUtil.getUrlProperties("MySQL"),       PropertiesUtil.getUsernameProperties("MySQL"),              
                    PropertiesUtil.getPasswordProperties("MySQL"));
            System.out.println("ɵ��");
            System.out.println(PropertiesUtil.getUrlProperties("MySQL"));
            System.out.println("����");
        
            // 3. ����ִ�е�sql���, ����ʹ��PreparedStatement
            String sql = "select * from sale_todo where uniqueCode = ?";
            PreparedStatement prep = null;
            prep = (PreparedStatement) conn.prepareStatement(sql);
            prep.setInt(1, 1);
            // 4. ��ѯsql ���, ����һ�������
            ResultSet result = prep.executeQuery();
            // 5. ���������� �ͷ���Դ
            while (result.next()) {
                System.out.println(result.getInt("id"));
                System.out.println(result.getString("name"));
                System.out.println(result.getInt("age"));
                System.out.println(result.getString("salary"));
            }
            conn.close();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            System.out.println("����ע��δ�ɹ�");
            e.printStackTrace();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}