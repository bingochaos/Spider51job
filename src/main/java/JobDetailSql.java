import java.sql.*;
import java.util.ArrayList;

/**
 * Created by bingoc on 16/4/27.
 */
public class JobDetailSql implements Runnable{
    private static final String sqlurl = "jdbc:mysql://localhost:3306/51job?useUnicode=true&characterEncoding=utf-8";
    private static final String name = "root";
    private static final String password = "";

    private ArrayList<JobDetail> jobDetails;
    private Connection connection;

    public ArrayList<JobDetail> getJobDetails() {
        return jobDetails;
    }

    public void setJobDetails(ArrayList<JobDetail> jobDetails) {
        this.jobDetails = jobDetails;
    }

    public JobDetailSql() {

    }

    public void run() {
        try {
            Class.forName("com.mysql.jdbc.Driver");

            Connection connection = DriverManager.getConnection(sqlurl, name, password);
            Statement statement = connection.createStatement();

            int result = statement.executeUpdate("CREATE TABLE IF NOT EXISTS jobdetails(" +
                    "jobID char(20) PRIMARY KEY , workspace TEXT, jobdetail TEXT ) default character set utf8");

            if(result == 0){
                for(JobDetail jobDetail:jobDetails) {
                    PreparedStatement preparedStatement = connection.prepareStatement("REPLACE INTO jobdetails VALUES (?,?,?)");
                    preparedStatement.setString(1, jobDetail.getJobID());
                    preparedStatement.setString(2, jobDetail.getWorkSpace());
                    preparedStatement.setString(3, jobDetail.getDetail());
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
