package com.pahlsoft.watchdog;

import com.pahlsoft.watchdog.utility.CommandLineRunner;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/watchdog")
public class WatchDogEntryPoint {

    @GET
    @Path("all")
    @Produces(MediaType.TEXT_PLAIN)
    public String all() {

        JSONObject jo = new JSONObject();
        Collection<JSONObject> items = new ArrayList<JSONObject>();
            items.add(CommandLineRunner.execute("HostName","hostname"));
            items.add(CommandLineRunner.execute("Java Process Count", "ps -ef| grep java | grep -v grep | wc -l"));
            items.add(CommandLineRunner.execute("Java Process List", "jps -l |grep -v jps"));
            items.add(CommandLineRunner.execute("MemCached", "ps -ef|grep memcached | grep -v grep"));
            items.add(CommandLineRunner.execute("Memory Statistics:",  "free -m  |grep Mem | awk '{print \"Assigned:\"$2,\",Used:\"$3, \",Free:\" $4}'"));
        jo.put("WatchDogReport", new JSONArray().put(items));
        return jo.toString();
    }

    @GET
    @Path("memcached")
    @Produces(MediaType.TEXT_PLAIN)
    public String memcached() {
        return CommandLineRunner.execute("MemCached", "ps -ef|grep memcached | grep -v grep").toString();
    }

    @GET
    @Path("java")
    @Produces(MediaType.TEXT_PLAIN)
    public String java() {
        return CommandLineRunner.execute("Java Process Count", "ps -ef| grep java | grep -v grep | wc -l").toString();
    }

    @GET
    @Path("jps")
    @Produces(MediaType.TEXT_PLAIN)
    public String jps() {
        return CommandLineRunner.execute("Java Process List", "jps -l").toString();
    }

    @GET
    @Path("memory")
    @Produces(MediaType.TEXT_PLAIN)
    public String memory() {
        return CommandLineRunner.execute("Memory Statistics:",  "free -m  |grep Mem | awk '{print \"Assigned:\"$2,\",Used:\"$3, \",Free:\" $4}'").toString();
    }



}
