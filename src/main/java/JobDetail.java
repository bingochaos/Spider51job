/**
 * Created by bingoc on 16/4/26.
 */
public class JobDetail {

    private String jobID;
    private String workSpace;
    private String detail;

    @Override
    public String toString() {
        return "JobDetail{" +
                "jobID='" + jobID + '\'' +
                ", workSpace='" + workSpace + '\'' +
                ", detail='" + detail + '\'' +
                '}';
    }

    public String getJobID() {
        return jobID;
    }

    public void setJobID(String jobID) {
        this.jobID = jobID;
    }

    public String getWorkSpace() {
        return workSpace;
    }

    public void setWorkSpace(String workSpace) {
        this.workSpace = workSpace;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public JobDetail() {

    }
}
