

import com.pahlsoft.examples.batch.BatchTestHelper;
import com.pahlsoft.examples.batch.ExampleItemProcessor;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ArchivePaths;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.batch.operations.JobOperator;
import javax.batch.runtime.BatchRuntime;
import java.io.File;
import java.util.Properties;

@RunWith(Arquillian.class)
public class ExampleJobTest  {

    @Deployment
    public static WebArchive createDeployment() {
        WebArchive war = ShrinkWrap.create(WebArchive.class)
                .addPackage(ExampleItemProcessor.class.getPackage())
                .addAsWebInfResource(EmptyAsset.INSTANCE, ArchivePaths.create("beans.xml"))
                .addAsResource("META-INF/batch-jobs/FirstBatchJob.xml");

            System.out.println(war.toString(true));
       return war;
    }

    @Test
    public void testChunkSimple() {
        String jobName = "FirstBatchJob";
        JobOperator jo = BatchRuntime.getJobOperator();
        long jobId = jo.start(jobName, new Properties());
        System.out.println("Started Job: " + jobId);
    }

    @Test
    public void doingNothing() {
        System.out.println("Done.");
    }
}
