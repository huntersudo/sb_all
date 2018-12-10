package com.project.seed.scas;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.util.List;

/**
 * @author sml
 * created on  2018/12/5
 */
public class test {

    public static void main(String[] args)throws Exception{

        StringBuilder builder=new StringBuilder();

        String path="E:\\workspace2\\sb_all\\sb_seed\\src\\main\\ORDER_REFUND_INFO.sql";
        File file= new File(path);

        List<String> lines=FileUtils.readLines(file,"UTF-8");

        for (String line:lines){
            builder.append(line.split(" ")[0].replaceAll("`","\"")).append(",");
        }
        System.out.println(builder.toString());
    }
}
