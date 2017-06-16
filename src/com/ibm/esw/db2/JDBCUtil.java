package com.ibm.esw.db2;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;  
import java.sql.DriverManager;  
import java.sql.ResultSet;  
import java.sql.SQLException;  
import java.sql.Statement;  
import java.util.ArrayList;
import java.util.List;
  
/** 
 * @author yanghongyu
 * 
 * 
 */  
public class JDBCUtil {  
  
    private JDBCUtil() {  
    }  
  
  
    static {  
        try {  
            String driver = "com.ibm.db2.jcc.DB2Driver";  
            // 装载驱动  
            Class.forName(driver);  
        } catch (ClassNotFoundException e) {  
            throw new ExceptionInInitializerError(e);  
        } catch (Exception ex) {  
            throw new RuntimeException("database driver load error");  
        }  
    }  
  
    /** 
     * 获取数据库连接 
     * @param url url 
     * @param user 用户名 
     * @param password 密码 
     * @return 数据库连接 
     * @throws SQLException 
     */  
    public static Connection getConnection(String url, String user, String password) throws SQLException {  
  
        return DriverManager.getConnection(url, user, password);  
    }  
  
  
    /** 
     * 释放数据库相关对象 
     *  
     * @param rs 结果集 
     * @param st 声明 
     * @param conn 连接 
     */  
    public static void free(ResultSet rs, Statement st, Connection conn) {  
        try {  
            if (rs != null) {  
                rs.close();  
            }  
        } catch (SQLException e) {  
            e.printStackTrace();  
        } finally {  
            try {  
                if (st != null) {  
                    st.close();  
                }  
            } catch (SQLException e) {  
                e.printStackTrace();  
            } finally {  
                if (conn != null) {  
                    try {  
                        if (!conn.isClosed()) {  
                            conn.close();  
                        }  
                    } catch (SQLException e) {  
                        e.printStackTrace();  
                    }  
                }  
            }  
        }  
    } 
    public static List<ColumnModel> getTableStructure(Connection con,String tableName){  
        List<ColumnModel> columnModelList = new ArrayList<ColumnModel>();  
        try {  
            //TODO 表相关  
            //ResultSet tableSet = metaData.getTables(null, "%",tableName,new String[]{"TABLE"});   
            //TODO 字段相关  
            ResultSet columnSet = con.getMetaData().getColumns(null,"%",tableName,"%");  
            ColumnModel columnModel = null;  
            while(columnSet.next()){  
                columnModel = new ColumnModel();  
                columnModel.setColumnName(columnSet.getString("COLUMN_NAME"));  
                columnModel.setColumnSize(columnSet.getInt("COLUMN_SIZE"));  
                columnModel.setDataType(columnSet.getString("DATA_TYPE"));  
                columnModel.setRemarks(columnSet.getString("REMARKS"));  
                columnModel.setTypeName(columnSet.getString("TYPE_NAME"));  
                String columnClassName = ColumnTypeEnum.getColumnTypeEnumByDBType(columnModel.getTypeName());  
                String fieldName = getFieldName(columnModel.getColumnName());  
                //String fieldType = Class.forName(columnClassName).getSimpleName();  
                columnModel.setFieldName(fieldName);  
                columnModel.setColumnClassName(columnClassName);  
              //  columnModel.setFieldType(fieldType);  
                columnModelList.add(columnModel);  
                //System.out.println(columnModel.toString());  
            }  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return columnModelList;  
    }  
  
    /** 
     * 将数据库字段转换成bean属性 
     * @param columnName 
     * @return 
     */  
    private static String getFieldName(String columnName) {  
        char[]  columnCharArr = columnName.toLowerCase().toCharArray();  
        StringBuffer sb = new StringBuffer();  
        int ad = -1;  
        for (int i = 0; i < columnCharArr.length; i++) {  
              char cur = columnCharArr[i];  
              if(cur=='_'){  
                  ad = i;  
              }else{  
                  if((ad+1)==i&&ad!=-1){  
                      sb.append(Character.toUpperCase(cur));  
                  }else{  
                      sb.append(cur);  
                  }  
                  ad=-1;  
              }  
        }  
        return sb.toString();  
    }  
      
      
    public static String gernerateTableDetails(Connection conn, String tableName){
        List<ColumnModel> temp = getTableStructure(conn,tableName);
        
        StringBuilder sb = new StringBuilder();
        sb.append("TableName: "+tableName).append("\r\n");
        sb.append("Columns Details: ***************************************").append("\r\n");
        int count =0;
        System.out.println("Table Name: "+tableName);
        while(count<temp.size())
        {
//        	System.out.println("Column Name: "+temp.get(count).getColumnName()+"    "
//        			+"DtaType: "+temp.get(count).getTypeName()+"  Remarks:  "+((temp.get(count).getRemarks().trim().equals(""))?"None":temp.get(count).getRemarks())); 
//        	
        	//System.out.println(temp.get(count));
            sb.append(temp.get(count)).append("\r\n");
        	count ++;
        }
        //String sql = "select * from wlmdb2d.WLMCUS";  
       // rs = st.executeQuery(sql);  
       // while(rs.next()) {  
         //   System.out.println(rs.getString(1));  
       // }  
        System.out.println("-----------------------------------------------------------");
        sb.append("-----------------------------------------------------------").append("\r\n");
        System.out.println(sb.toString());
        return sb.toString();
    }
 
  
    public static void main(String[] args) throws IOException {  
        Connection conn = null;  
        Statement st = null;  
        ResultSet rs = null;  
          
        try {  
            conn = JDBCUtil.getConnection("jdbc:db2://mvss.mul.ie.ibm.com/SPCDB2D", "CN27235", "passw6rd");  
            int count =0;
            File inputFile=new File("src/Table.txt");
            File outputFile = new File("src/ColumsDeatil.txt");
            BufferedReader reader=null;
            BufferedWriter writer = null;
            String tempStr=null;  
                    reader = new BufferedReader(new FileReader(inputFile));
                    writer = new BufferedWriter(new FileWriter(outputFile));
                    while((tempStr=reader.readLine())!=null){
                    	writer.write(gernerateTableDetails(conn,tempStr));
                    	writer.flush();
                    }
                    writer.flush();
                    writer.close();

        } catch (SQLException e) {  
            // 添加日志记录该异常  
            e.printStackTrace();  
            System.out.println("test for connection exception");  
        } finally {  
            JDBCUtil.free(rs, st, conn);  
        }  
          
    }  
}  
