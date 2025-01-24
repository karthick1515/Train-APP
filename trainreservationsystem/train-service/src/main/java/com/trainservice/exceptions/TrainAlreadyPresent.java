package com.trainservice.exceptions;

public class TrainAlreadyPresent extends  Exception{
    public TrainAlreadyPresent() {
        super();
    }
    public TrainAlreadyPresent(String msg) {
        super(msg);
    }
}
