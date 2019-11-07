package com.netty.xidian.edu.cn.urlConnection;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class urlConnectionTest1 {

    public static void main(String[] args) {
        if (args.length > 0){
            try {
                URL url = new URL(args[0]);
                URLConnection urlConnection = url.openConnection();
                try (InputStream inputStream = urlConnection.getInputStream()){

                    InputStream buffer = new BufferedInputStream(inputStream);
                    Reader reader = new InputStreamReader(buffer);
                    int c;
                    while ((c = reader.read()) != -1){
                        System.out.println(c);
                    }
                }catch (MalformedURLException e){
                    e.printStackTrace();
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
}
