package top.hueng.apm.util;

/**
 * ClassName: SSHHelper
 * Package: top.hueng.apm.util
 * Description:
 *
 * @Author: oceanLover
 * @Create: 2023/4/11 - 8:55
 * @Version: 1.0
 */

import java.io.BufferedInputStream;
import java.io.IOException;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;


@Data
@Component
@ConfigurationProperties(prefix = "ssh")
public class SSHHelper{
    private String host;
    private int port;
    private String username;
    private String password;

    private Session session;

    @PostConstruct
    public void init() throws JSchException {
        connect(host, port, username, password);
    }
    @PreDestroy
    public void destroy() {
        close();
    }
    //连接sftp服务器
    private Session connect(String host, Integer port, String user, String password) throws JSchException{
        try {
            JSch jsch = new JSch();
            if(port != null){
                session = jsch.getSession(user, host, port.intValue());
            }else{
                session = jsch.getSession(user, host);
            }
            session.setPassword(password);
            //设置第一次登陆的时候提示,可选值:(ask | yes | no)
            session.setConfig("StrictHostKeyChecking", "no");
            //5秒连接超时
            session.connect(5000);
        } catch (JSchException e) {
            e.printStackTrace();
            System.out.println("sftpUtil getConnect err");
            throw e;
        }
        return session;
    }


    public StringBuffer sendCmd(String command){
        StringBuffer strBuffer = new StringBuffer();
        Channel channel = null;
        BufferedInputStream bis = null;
        ChannelExec ssh = null;
        try {
            channel = session.openChannel("exec");
            ssh = (ChannelExec) channel;
            bis = new BufferedInputStream(ssh.getInputStream());
            ssh.setCommand(command);
            ssh.connect();
            byte[] bytes = new byte[1024];
            int length;
            //获取标准输出
            while((length = bis.read(bytes)) != -1){
                strBuffer.append(new String(bytes, 0, length));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            channel.disconnect();
            try {
                if (bis != null){
                    bis.close();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return strBuffer;
    }

    public void close(){
        if(session.isConnected())
            session.disconnect();
    }

}
