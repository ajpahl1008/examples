package com.pahlsoft.examples.batch;

import javax.batch.api.chunk.AbstractItemWriter;
import javax.batch.runtime.context.JobContext;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.Serializable;
import java.util.List;


@Dependent
@Named
public class ExampleItemWriter extends AbstractItemWriter {

    private BufferedWriter bwriter;
    @Inject
    private JobContext jobContext;

    @Override
    public void open(Serializable ckpt) throws Exception {
        String fileName = jobContext.getProperties().getProperty("output_file");
        bwriter = new BufferedWriter(new FileWriter(fileName));
    }

    @Override
    public void writeItems(List<Object> items) throws Exception {
        for (int i = 0; i < items.size(); i++) {
            String line = (String) items.get(i);
            bwriter.write(line);
            bwriter.newLine();
        }
    }

    public Serializable checkpointInfo() throws Exception {
        return new ExampleCheckpoint();
    }

    public void close() throws Exception {
        bwriter.close();
    }

}
