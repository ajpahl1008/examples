package com.pahlsoft.examples.batch;

import javax.batch.api.chunk.ItemProcessor;
import javax.enterprise.context.Dependent;
import javax.inject.Named;

@Dependent
@Named
public class ExampleItemProcessor implements ItemProcessor {
    @Override
    public Object processItem(Object item) throws Exception {
        int calc = Integer.parseInt(item.toString());
        calc = calc + 10;
        // Everyone gets 10 added to it.
        return Integer.toString(calc);
    }
}
