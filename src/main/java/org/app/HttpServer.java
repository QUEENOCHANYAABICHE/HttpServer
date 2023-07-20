
package org.app;


import java.io.BufferedReader;

import java.io.InputStreamReader;

//import java.io.PrintWriter;

import java.net.Socket;


public class HttpServer extends Thread{

    Socket stk;


    public HttpServer(Socket stk) {

        this.stk = stk;

    }


    @Override

    public void run() {

        try {

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(stk.getInputStream()));

            String responseHeaderLine1 = bufferedReader.readLine();

            System.out.println(responseHeaderLine1);

            String endpoint = responseHeaderLine1.split(" ")[1];

            String method = responseHeaderLine1.split(" ")[0];

            System.out.println(endpoint);


            if (method.equals("GET")) {

                if (endpoint.equals("/")) {

                    stk.getOutputStream()

                            .write(("HTTP/1.1 200 OK" + "\r\n\r\n" + "<html>"+

                                    "<h1>"+

                                    "Welcome to Java"+

                                    "</h1>"+

                                    "<main>" +

                                    "<h2>"+

                                    "Network Socket communication." +

                                    "</h2>" +

                                    "<img alt='"+

                                    "diagram illustrating socket communication'" +

                                    "src='" +

                                    "https://images.idgesg.net/images/article/2019/05/java_binary_code_gears_programming_coding_development_by_bluebay2014_gettyimages-1040871468_2400x1600-100795798-large.jpg?auto=webp&quality=85,70'>"

                                    +"</main>"+

                                    "</html>").getBytes());



                } else if (endpoint.equals("/json")){

                    String responseHeader = "HTTP/1.1 200 OK\r\nContent-Type: application/json\r\n\r\n";
                    stk.getOutputStream().write(responseHeader.getBytes());
                    stk.getOutputStream().write("{\"name\":\"Server\",\"port\":\"3030\"}".getBytes());


                }

            }

            stk.close();

        } catch (Exception e) {

            System.out.println(e);

        }

    }

}

