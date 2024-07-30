package day240729;

import day240729.dowloader.Downloader;
import day240729.dowloader.NopeDownloader;
import day240729.notificator.NopeNotificator;
import day240729.notificator.Notificator;
import day240729.parser.DefaultParser;
import day240729.parser.Parser;
import day240729.repository.NopeRepository;
import day240729.repository.Repository;

import java.util.List;


public class App {
    public static void main(String[] args) {

        //读取配置文件
        System.out.println("文件配置设置完成!");

        //=== === ===
        //以boss的视角，定义规范
        //定义具体的落地(黑盒子的思想):抽象类/接口

        //Downloader 下载
        //抽象类不能直接new出,写一个方法继承他实现
        Downloader downloader = new NopeDownloader();
        String html = downloader.download("http://bbs.xmfish.com/thread-htm-fid-55-search-all-orderway-postdate-asc-DESC-page-1.html");
        System.out.println(html);
        System.out.println("downloader - 下载完成");

        //Parser 解析
        System.out.println("Parser - 正在解析...");
        Parser parser = new DefaultParser();
        List<String> result = parser.parse(html);
        System.out.println(result);
        System.out.println("Parser - 解析完成");

        //Repository 解析
        System.out.println("Repository - 正在储存...");
        Repository repository = new NopeRepository();
        List<String> ls = repository.store(result);
        System.out.println("Repository - 储存完成");

        //Notificator 通知
        System.out.println("Notificator - 正在通知...");
        Notificator notificator = new NopeNotificator();
        notificator.notice(ls, "苹果，小米");
        System.out.println("Notificator - 通知完成！");

    }
}
