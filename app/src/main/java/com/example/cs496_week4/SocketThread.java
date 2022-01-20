package com.example.cs496_week4;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.widget.Toast;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class SocketThread extends Thread{

    Context context;
    String host; // 서버 IP
    String data; // 전송 데이터
    String response; //서버 응답

    Handler handler = new Handler(); // 토스트를 띄우기 위한 메인스레드 핸들러 객체 생성

    public SocketThread(Context context, String host, String data){
        this.host = host;
        this.data = data;
        this.context = context;
    }

    @Override
    public void run() {

        try{
            int port = 5555; //포트 번호는 서버측과 똑같이
            Socket socket = new Socket(host, port); // 소켓 열어주기
            ObjectOutputStream outstream = new ObjectOutputStream(socket.getOutputStream()); //소켓의 출력 스트림 참조
            outstream.writeObject(data); // 출력 스트림에 데이터 넣기
            outstream.flush(); // 출력

            ObjectInputStream instream = new ObjectInputStream(socket.getInputStream()); // 소켓의 입력 스트림 참조
            response = (String) instream.readObject(); // 응답 가져오기

            /* 토스트로 서버측 응답 결과 띄워줄 러너블 객체 생성하여 메인스레드 핸들러로 전달 */
            handler.post(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(context, "서버 응답 : " + response, Toast.LENGTH_LONG).show();
                }
            });

            socket.close(); // 소켓 해제

        }catch(Exception e){
            e.printStackTrace();
        }
    }
}