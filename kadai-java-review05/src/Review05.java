        
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Review05 {

    public static void main(String[] args) {
        
            Connection con = null;// 接続
            PreparedStatement pstmt = null;// 実行依頼
            ResultSet rs = null;// 結果取得
            
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            
                con = DriverManager.getConnection(
                        "jdbc:mysql://localhost/kadaidb?useSSL=false&allowPublicKeyRetrieval=true",
                        "root",
                        "Araimonotanomu"
                        );
        
                String sql = "SELECT * FROM person WHERE id = ?";
                pstmt = con.prepareStatement(sql);    
               
                   
                // 入力から検索
                System.out.print("idを入力してください>");
                int id1 = keyInNum();
                pstmt.setInt(1, id1);
                
                rs = pstmt.executeQuery();
                
                // 結果を表示
                while (rs.next()) {
                String name = rs.getString("name");// name列
                int age = rs.getInt("age");// age列
                System.out.println(name + age);
                }
        
                } catch (ClassNotFoundException e) {
                    System.err.println("JDBCドライバのロードに失敗しました。");
                    e.printStackTrace();
                } catch (SQLException e) {
                    System.err.println("データベースに異常が発生しました。");
                    e.printStackTrace();
                } finally {
                    // 8. 接続を閉じる
                    if( rs != null ) {
                        try {
                            rs.close();
                        } catch (SQLException e) {
                            System.err.println("ResultSetを閉じるときにエラーが発生しました。");
                        }
                    }  if( pstmt != null) {
                        try {
                            pstmt.close();
                        } catch (SQLException e) {
                            System.err.println("PreparedStetementを閉じるときにエラーが発生しました。");
                            e.printStackTrace();
                        }
                    }
                    if( con != null ) {
                        try {
                            con.close();
                        } catch (SQLException e) {
                            System.err.println("データベース切断時にエラーが発生しました。");
                            e.printStackTrace();
                        }
                    }
                }

}
                    private static String keyIn() {
                        String line = null;
                        try {
                            BufferedReader key = new BufferedReader(new InputStreamReader(System.in));
                            line = key.readLine();
                        } catch (IOException e) {
                            
                        }
                        return line;
                    }
                        
                    private static int keyInNum() {
                        int result = 0;
                        try {
                            result = Integer.parseInt(keyIn());
                        } catch (NumberFormatException e) {
                        }
                        return result;
                        
                    }
    
    }
