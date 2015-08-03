package com.chat.client;

import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

/**
 * Created by alexey.ivlev on 03.08.2015.
 */
public class CommandLineValues {

    @Option(name="-t", required = true)
    private String type;

    @Option(name="-h", required = true)
    private String host;

    @Option(name="-p", required = true)
    private Integer port;

    public CommandLineValues(String[] args){
        CmdLineParser parser = new CmdLineParser(this);
        try {
            parser.parseArgument(args);
        } catch (CmdLineException e) {
            System.out.println("Wrong number or types of args. Required: Type[UPD | TCP], host, port");
            System.exit(1);
        }
    }

    public String getType(){
        return type;
    }

    public String getHost(){
        return host;
    }

    public Integer getPort(){
        return port;
    }

}
