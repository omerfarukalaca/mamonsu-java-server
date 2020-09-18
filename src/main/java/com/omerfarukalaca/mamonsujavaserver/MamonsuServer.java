package com.omerfarukalaca.mamonsujavaserver;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

import com.google.gson.Gson;
import com.omerfarukalaca.mamonsujavaserver.model.MamonsuResponse;


public class MamonsuServer extends Thread {
    private Socket socket;

    public MamonsuServer(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {

        byte[] headerByte = new byte[4];
        byte[] versionByte = new byte[1];
        byte[] dataLenghtByte = new byte[8];
        boolean end = false;
        String dataString = "";
        try {
            DataInputStream in = new DataInputStream(socket.getInputStream());

            // Header Byte Array 
            for(int i = 0; i <4;i++){
                headerByte[i] = in.readByte(); 
            }
            String headerString = new String(headerByte, StandardCharsets.UTF_8);
            System.out.println("HEADER: " + headerString);


            // Version Byte Array 
            for(int i = 0; i <1;i++){
                versionByte[i] = in.readByte(); 
            }
            String versionString = new String(versionByte, StandardCharsets.UTF_8);
            System.out.println("VERSION: " + versionString);

            // Data Lenght Byte Array 
            for(int i = 7; i >=0;i--){
                dataLenghtByte[i] = in.readByte(); 
            }
            String hexString = encodeHexString(dataLenghtByte);
            int dataLengt=Integer.parseInt(hexString,16);  
            System.out.println("DATA LENGHT: " + dataLengt);  


            byte[] dataByte = new byte[dataLengt];
            while(!end)
            {
                int bytesRead = in.read(dataByte);
                dataString += new String(dataByte, 0, bytesRead);
                if (dataString.length() == dataLengt)
                {
                    end = true;
                }
            }

            Gson g = new Gson();
            MamonsuResponse mamonsuResponse = g.fromJson(dataString, MamonsuResponse.class);
            System.out.println("DATA: " + mamonsuResponse);


            final DataOutputStream stream = new DataOutputStream(socket.getOutputStream());
            String responseData = "failed: 0";
            stream.write(headerByte);
            stream.write(versionByte);
            byte[] responseLenght = ByteBuffer.allocate(8).putInt(responseData.length()).array();
            stream.write(responseLenght);
            stream.writeUTF(responseData);

        } catch(IOException e) {
            System.out.println("Oops: " + e.getMessage());
        } finally {
            try {
                socket.close();
            } catch(IOException e) {
                // Oh, well!
            }
        }

    }

    public String encodeHexString(byte[] byteArray) {
        StringBuffer hexStringBuffer = new StringBuffer();
        for (int i = 0; i < byteArray.length; i++) {
            hexStringBuffer.append(byteToHex(byteArray[i]));
        }
        return hexStringBuffer.toString();
    }

    public String byteToHex(byte num) {
        char[] hexDigits = new char[2];
        hexDigits[0] = Character.forDigit((num >> 4) & 0xF, 16);
        hexDigits[1] = Character.forDigit((num & 0xF), 16);
        return new String(hexDigits);
    }
}
