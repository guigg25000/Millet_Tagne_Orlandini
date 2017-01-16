package com.example.yoann.localisation;


import android.os.Environment;

import com.amazonaws.auth.CognitoCachingCredentialsProvider;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferUtility;
import com.amazonaws.services.s3.AmazonS3Client;

import com.amazonaws.auth.CognitoCachingCredentialsProvider;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferUtility;
import com.amazonaws.services.s3.AmazonS3Client;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Guillaume on 12/01/2017.
 */



public class IContext {
    private List<String> fileList ;
    private static AmazonS3Client sS3Client;
    private static CognitoCachingCredentialsProvider sCredProvider;
    private static TransferUtility sTransferUtility;



    public IContext(){
        this.fileList= new ArrayList<String>();
    }

    public int ListDir(File f) {
        int i=0;
        File[] files = f.listFiles();
        fileList.clear();
        for (File file : files) {
            i++;
        }
        return i;
    }
    public int Fil(File F){
        File root = new File(Environment
                .getExternalStorageDirectory()
                .getAbsolutePath());


        int nb=ListDir(F);
        return nb;
    }
}
