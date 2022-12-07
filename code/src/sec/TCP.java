package sec;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;

public class TCP extends ServerSocket {
    public TCP(int serverPort) throws IOException {
        super(serverPort);
        try {
            while (true) {
                Socket socket = accept();
                new ServerThread(socket);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            close();
        }
    }

    class ServerThread extends Thread       /*建立服务端线程*/ {
        private Socket socket;
        private BufferedReader in;
        private PrintWriter out;

        public ServerThread(Socket s) throws IOException {
            this.socket = s;

            in = new BufferedReader(new InputStreamReader(
                    socket.getInputStream(), "GB2312"));        /*国标码*/
            out = new PrintWriter(socket.getOutputStream(), true);
            start();                /*开始线程*/
        }

        public void run() {
            try {
                System.out.println(socket.toString());
                byte[] b = new byte[2048];
                while (true) {
                    InputStream is = socket.getInputStream();
                    is.read(b);
//                    char c = Character.toChars();
                    System.out.println(Arrays.toString(b));
                    String line = in.readLine();
                    if ("finish".equals(line)) {
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
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        System.out.println("服务器开始运行......");
//        new TCP(2333);       /*绑定端口号*/
        t();
    }

    static void t(){
        byte[] b = {83, 84, 65, 82, 84, 13, 10, 10, 32, 32, 32, 32, 32, 32, 49, 32, 32, 32, 97, 13, 10, 32, 32, 32, 32, 32, 32, 50, 32, 32, 32, 98, 13, 10, 32, 32, 32, 32, 32, 32, 51, 32, 32, 32, 99, 13, 10, 32, 32, 32, 32, 32, 32, 52, 32, 32, 32, 51, 53, 54, 13, 10, 32, 32, 32, 32, 32, 32, 53, 32, 32, 32, 55, 55, 56, 13, 10, 32, 32, 32, 32, 32, 32, 54, 32, 32, 32, 115, 12, 69, 78, 68};
        StringBuilder sb = new StringBuilder();
        for (byte bb : b) {
            char[] c = Character.toChars(bb);
            for (char c1 : c) {
                sb.append(Character.toString(c1));
            }
        }
        System.out.println(sb.toString());
    }


}