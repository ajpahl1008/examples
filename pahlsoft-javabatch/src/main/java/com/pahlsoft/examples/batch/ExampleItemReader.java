package com.pahlsoft.examples.batch;

import javax.batch.api.chunk.AbstractItemReader;
import javax.batch.runtime.context.JobContext;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

@Dependent
@Named
public class ExampleItemReader extends AbstractItemReader {
    private ExampleCheckpoint checkpoint;
    private BufferedReader breader;

    @Inject
    private JobContext jobContext;


    public void open(Serializable ckpt) throws Exception {

        String fileName = jobContext.getProperties().getProperty("input_file");
        breader = new BufferedReader(new FileReader(fileName));


    }

    @Override
    public void close() throws Exception {
        breader.close();
    }


    @Override
    public String readItem() throws Exception {
        try {
            return breader.readLine();
        } catch (IOException ex) {
            Logger.getLogger(ExampleItemReader.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}