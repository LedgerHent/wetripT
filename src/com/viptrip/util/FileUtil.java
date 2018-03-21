package com.viptrip.util;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;

public class FileUtil {
	
	 /** 
     * 读取文件
     * @param filename 
     * @return 
     * @throws IOException 
     */  
	public static byte[] readFile(String filename)throws IOException{  
          
        FileChannel fc = null;  
        try{  
            fc = new RandomAccessFile(filename,"r").getChannel();  
            MappedByteBuffer byteBuffer = fc.map(MapMode.READ_ONLY, 0, fc.size()).load();  
            byte[] result = new byte[(int)fc.size()];  
            if (byteBuffer.remaining() > 0) {  
                byteBuffer.get(result, 0, byteBuffer.remaining());  
            }  
            return result;  
        }catch (IOException e) {  
            e.printStackTrace();  
            throw e;  
        }finally{  
            try{  
                fc.close();  
            }catch (IOException e) {  
                e.printStackTrace();  
            }  
        }  
    }

}
