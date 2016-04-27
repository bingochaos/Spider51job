import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by bingoc on 16/4/26.
 */
public class Main {

    public static String url = "http://search.51job.com/list/010000%252C00,%2B,%2B,%2B,%2B,%2B,java,0,%2B.html?lang=c&stype=1&image_x=0&image_y=0&specialarea=00";

    public static void main(String[] args) throws IOException {
        Document document = Jsoup.connect(url)
                .userAgent("ie7：mozilla/4.0 (compatible; msie 7.0b; windows nt 6.0)")
                .timeout(3000)
                .cookie("guide", "1")
                .followRedirects(false)
                .execute().parse();



        Elements elements = document.getElementsByClass("dw_table");
        Elements elements1 = elements.get(0).getElementsByClass("el");
        elements1.remove(0);

        ArrayList<Job> jobs = getJobListFromSearchPage(elements1);
        JobSql jobSql = new JobSql();
        jobSql.setJobs(jobs);
        new Thread(jobSql).start();

        ArrayList<JobDetail> jobDetails = getJobDetailFromJobPage(jobs);
        JobDetailSql jobDetailSql = new JobDetailSql();
        jobDetailSql.setJobDetails(jobDetails);
        new Thread(jobDetailSql).start();


    }

    private static ArrayList<JobDetail> getJobDetailFromJobPage(ArrayList<Job> jobs) {
        ArrayList<JobDetail> jobDetails = new ArrayList<JobDetail>();

        for(Job job:jobs)
        {
            jobDetails.add(getJobDetail(job));
        }
        return jobDetails;
    }

    private static JobDetail getJobDetail(Job job) {
        if(job.getJobID() != null)
        {
            try {
                Document document = Jsoup.connect(job.getJobDetailUrl())
                        .userAgent("ie7：mozilla/4.0 (compatible; msie 7.0b; windows nt 6.0)")
                        .timeout(3000)
                        .cookie("guide", "1")
                        .followRedirects(false)
                        .execute().parse();
                Elements elements = document.getElementsByClass("tCompanyPage");
                Element element = elements.first().getElementsByClass("bmsg").first();
                JobDetail jobDetail = new JobDetail();
                jobDetail.setJobID(job.getJobID());
                jobDetail.setDetail(element.ownText());

                Element element1 = null;
                if(elements.first().getElementsByClass("bmsg").size()>1) {
                    element1 = elements.first().getElementsByClass("bmsg").get(1);
                    jobDetail.setWorkSpace(element1.getElementsByClass("fp").first().ownText());
                }
                return jobDetail;
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }
        return null;
    }

    private static ArrayList<Job> getJobListFromSearchPage(Elements elements) {

        ArrayList<Job> jobs = new ArrayList<Job>();

        for (Element element:elements)
        {
            Job job = new Job();
            job.setJobID(element.getElementsByClass("checkbox").first().attr("value"));
            Element t1 = element.getElementsByClass("t1").first();
            job.setJobTitle(t1.getElementsByTag("a").attr("title"));
            job.setJobDetailUrl(t1.getElementsByTag("a").attr("href"));
            Element t2 = element.getElementsByClass("t2").first();
            job.setCompanyName(t2.getElementsByTag("a").attr("title"));
            job.setLocation(element.getElementsByClass("t3").text());
            job.setSalary(element.getElementsByClass("t4").text());
            job.setDate(element.getElementsByClass("t5").text());
            jobs.add(job);
           // System.out.println(job.toString());

        }
        return jobs;
    }
}
