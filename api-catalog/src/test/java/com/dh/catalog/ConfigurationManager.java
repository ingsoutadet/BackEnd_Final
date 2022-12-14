package com.dh.catalog;

public final class ConfigurationManager {
    private ConfigurationManager(){
    }

    public static Configuration getConfiguration(){return ConfigCache.getOrCreate(Configuration.class);}
}
