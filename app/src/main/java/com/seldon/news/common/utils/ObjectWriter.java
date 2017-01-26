package com.seldon.news.common.utils;

import java.io.File;
import java.io.IOException;

import fw.v6.core.utils.V6HardDisk;

public class ObjectWriter implements Runnable {

    private V6HardDisk hardDisk;
    private Object entity;
    private File file;

    public ObjectWriter(V6HardDisk hardDisk,
                  Object entity,
                  File file) {
        this.hardDisk = hardDisk;
        this.entity = entity;
        this.file = file;
    }

    @Override public void run() {
        synchronized (file) {
            hardDisk.write(entity, file);
        }
    }
}
