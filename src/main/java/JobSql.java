import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by bingoc on 16/4/27.
 */
public class JobSql implements Runnable{
    private static final String sqlurl = "jdbc:mysql://localhost:3306/51job?useUnicode=true&characterEncoding=utf-8";
    private static final String name = "root";
    private static final String password = "";

    private ArrayList<Job> jobs;
    private Connection connection;

    public ArrayList<Job> getJobs() {
        return jobs;
    }

    public void setJobs( ArrayList<Job> jobs) {
        this.jobs = jobs;
    }



    public void run() {
        try {
            Class.forName("com.mysql.jdbc.Driver");

            Connection connection = DriverManager.getConnection(sqlurl, name, password);
            Statement statement = connection.createStatement();

            int result = statement.executeUpdate("CREATE TABLE IF NOT EXISTS jobs(" +
                    "jobID char(20) PRIMARY KEY , jobTitle VARCHAR(100), jobSalary char(20), companyName VARCHAR(100), " +
                    "url TEXT, mydata char(20)) default character set utf8");

            if(result == 0){
                for(Job job:jobs) {
                    PreparedStatement preparedStatement = connection.prepareStatement("REPLACE INTO jobs VALUES (?,?,?,?,?,?)");
                    preparedStatement.setString(1, job.getJobID());
                    preparedStatement.setString(2, job.getJobTitle());
                    preparedStatement.setString(3, job.getSalary());
                    preparedStatement.setString(4, job.getCompanyName());
                    preparedStatement.setString(5, job.getJobDetailUrl());
                    preparedStatement.setString(6, job.getDate());
                    preparedStatement.executeUpdate();
                }
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
