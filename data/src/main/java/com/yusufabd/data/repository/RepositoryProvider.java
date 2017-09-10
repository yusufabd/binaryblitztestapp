package com.yusufabd.data.repository;

/**
 * Created by yusufabd on 9/8/2017.
 */

public class RepositoryProvider {

    private static Repository sRepository;

    public static Repository getRepository(){
        if (sRepository == null) {
            sRepository = new Repository();
        }
        return sRepository;
    }

}
