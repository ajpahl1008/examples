package com.pahlsoft.examples.batch;

import javax.batch.api.AbstractBatchlet;
import javax.batch.runtime.context.JobContext;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.File;

@Dependent
@Named
public class ExampleBatchlet extends AbstractBatchlet {
    @Inject

    private JobContext jobContext;

    @Override
    public String process() throws Exception {
        String fileName = jobContext.getProperties().getProperty("output_file");
        System.out.println(" " + (new File(fileName)).length());
        return "COMPLETED";
    }

    public void stop() {

    }
}
