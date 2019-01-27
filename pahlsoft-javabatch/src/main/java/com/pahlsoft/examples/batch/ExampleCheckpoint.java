package com.pahlsoft.examples.batch;

import java.io.Serializable;

public class ExampleCheckpoint implements Serializable {
    private long lineNum = 0;
    public void increase() {lineNum++;}
    public long getLineNum() {return lineNum;}


}
