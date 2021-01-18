package com.bh.dao.UserDaoImpl;

import com.bh.dao.UserDao.UserDao;
import com.bh.pojo.User;

import java.io.*;

public class UserDaoImpl implements UserDao {

    //随着类的加载而加载
    static {
        try {
            File file = new File("user.txt");
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 登录
     *
     * @param name
     * @param pass
     * @return
     */
    @Override
    public boolean Login(String name, String pass) {
        boolean flag = false;
        try {
            //创建高效字符输入流来读取数据
            BufferedReader br = new BufferedReader(new FileReader("user.txt"));
            String line;
            while ((line = br.readLine()) != null) {
                String[] s = line.split(":");
                if (s[0].equals(name.trim()) && s[1].equals(pass)) {
                    flag = true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return flag;
    }

    public boolean Login(String name) {
        boolean flag = false;
        try {
            //创建高效字符输入流来读取数据
            BufferedReader br = new BufferedReader(new FileReader("user.txt"));
            String line;
            while ((line = br.readLine()) != null) {
                String[] s = line.split(":");
                if (s[0].equals(name.trim())) {
                    flag = true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return flag;
    }


    /**
     * 注册
     */
    @Override
    public void Sign(User user) throws IOException {
        if (Login(user.getName())) {
            System.out.println("用户名已经注册,请直接登录");
        }else {
            //把用户信息存进文件里
            String info = user.getName() + ":" + user.getPassword();
            //创建高效字符输出流来给文件写入数据，创建了一个可以追加写入的FileWriter，避免了文件中之前的用户信息被覆盖
            try {
                BufferedWriter bw = new BufferedWriter(new FileWriter("user.txt", true));
                bw.write(info);

                //换行
                bw.newLine();

                //刷新缓冲区
                bw.flush();

                //关闭资源
                bw.close();
                System.out.println("注册成功！");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
