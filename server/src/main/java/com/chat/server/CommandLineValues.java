package com.chat.server;

import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alexey.ivlev on 31.07.2015.
 */
public class CommandLineValues {

    @Option(name="-t", required = true)
    private String type;

    @Option(name="-p", required = true)
    private Integer port;

    public CommandLineValues(String[] args){
        CmdLineParser parser = new CmdLineParser(this);
        try {
            parser.parseArgument(args);
        } catch (CmdLineException e) {
            System.out.println("Wrong number or types of args. Required: Type[UPD | TCP], port");
            System.exit(1);
        }
    }

    public String getType(){
        return type;
    }

    public Integer getPort(){
        return port;
    }
}
