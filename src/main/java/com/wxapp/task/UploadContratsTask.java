package com.wxapp.task;

import com.wxapp.api.contrats.UploadContratsApi;
import com.wxapp.entity.Contrat;

import java.util.concurrent.Callable;

public class UploadContratsTask implements Callable<String> {
    private UploadContratsApi uploadContratsApi;
    private Contrat contrat;

    public UploadContratsTask(UploadContratsApi uploadContratsApi, Contrat contrat) {
        this.uploadContratsApi = uploadContratsApi;
        this.contrat = contrat;
    }

    @Override
    public String call() throws Exception {
        String contratUploadResult = uploadContratsApi.uploadContrats(contrat);
        return contratUploadResult;
    }
}
