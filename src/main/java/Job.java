/**
 * Created by bingoc on 16/4/26.
 */
public class Job {

    private String JobID;

    private String jobTitle;
    private String companyName;
    private String location;
    private String salary;
    private String date;
    private String jobDetailUrl;

    @Override
    public String toString() {
        return "Job{" +
                "JobID='" + JobID + '\'' +
                ", jobTitle='" + jobTitle + '\'' +
                ", companyName='" + companyName + '\'' +
                ", location='" + location + '\'' +
                ", salary='" + salary + '\'' +
                ", date='" + date + '\'' +
                ", jobDetailUrl='" + jobDetailUrl + '\'' +
                '}';
    }

    public String getJobID() {
        return JobID;
    }

    public void setJobID(String jobID) {
        JobID = jobID;
    }

    public String getJobDetailUrl() {
        return jobDetailUrl;
    }

    public void setJobDetailUrl(String jobDetailUrl) {
        this.jobDetailUrl = jobDetailUrl;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Job() {

    }
}
