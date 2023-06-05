package top.hueng.apm.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Process {
    private Integer id;
    private String user;
    private String pid;
    private String name;
    private String cpu;
    private String mem;
    private String status;
    private String start;
    private String time;
    private Date createdTime;

    public Process(String user, String pid, String name, String cpu, String mem, String status, String start, String time,Date createdTime) {
        this.user = user;
        this.pid = pid;
        this.name = name;
        this.cpu = cpu;
        this.mem = mem;
        this.status = status;
        this.start = start;
        this.time = time;
        this.createdTime = createdTime;
    }
}
