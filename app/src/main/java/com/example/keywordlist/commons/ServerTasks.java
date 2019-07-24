package com.example.keywordlist.commons;

import com.example.keywordlist.MainApplication;

public class ServerTasks {

    private static ServerTasks serverTasks = null;
    private Api api;

    private ServerTasks() {
    }

    public static ServerTasks getInstance() {
        if (serverTasks == null) {
            serverTasks = new ServerTasks();
        }

        return serverTasks;
    }

    public Api getApi() {
        if (api == null) {
            api = MainApplication.getRetrofit().create(Api.class);
        }

        return api;
    }

}
