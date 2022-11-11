package sec;

import java.io.*;
import java.net.*;

public class TCP extends ServerSocket
{
    public TCP(int serverPort) throws IOException
    {
        super(serverPort);
        try
        {
            while (true)
            {
                Socket socket = accept();
                new ServerThread(socket);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            close();
        }
    }
    class ServerThread extends Thread       /*建立服务端线程*/
    {
        private Socket socket;
        private BufferedReader in;
        private PrintWriter out;
        public ServerThread(Socket s) throws IOException
        {
            this.socket = s;
            in = new BufferedReader(new InputStreamReader(
                    socket.getInputStream(), "GB2312"));        /*国标码*/
            out = new PrintWriter(socket.getOutputStream(),true);
            start();                /*开始线程*/
        }

        public void run()
        {
            try {
                while (true)
                {
                    String line = in.readLine();
                    if ("finish".equals(line))
                    {
                        System.out.println("服务器已停止监听");
                        break;
                    }
                    System.out.println("接收到的数据：" + line);
                    String msg = "'" + line + "'已传输到服务器端.";
                    out.println(msg);
                    out.flush();
                }
                out.close();
                in.close();
                socket.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }
    public static void main(String[] args) throws IOException
    {
        System.out.println("服务器开始运行......");
        new TCP(2333);       /*绑定端口号*/
    }

}