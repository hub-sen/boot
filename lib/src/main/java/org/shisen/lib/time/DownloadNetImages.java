package org.shisen.lib.time;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 图片下载
 */
public class DownloadNetImages {

    // 地址
    private static final String URL = "https://www.plmm.com.cn/";
    // 获取img标签正则
    private static final String IMGURL_REG = "<img.*src=(.*?)[^>]*?>";
    // 获取a标签正则
    private static final String A_REG = "<a href=\"//www.plmm.com.cn/(.*?).html\" target=\"_blank\">";
    // 获取src路径的正则
    private static final String IMGSRC_REG = "//img.plmm.com.cn/(.*?).jpg@!w210";

    private static final String HTMLSRC_REG = "//[^\\s]*html";


    public static void main(String[] args) {
        try {
            DownloadNetImages cm = new DownloadNetImages();
            //获得html文本内容
            String firstHtml = cm.getHtml(URL);
            List<String> htmlUrl = cm.getInfoUrl(A_REG, firstHtml);
            List<String> htmlSrc = cm.getInfoSrc(HTMLSRC_REG, htmlUrl);
            for (String s : htmlSrc) {
                s = "https:" + s;
                String html = cm.getHtml(s);
                //获取图片url标签
                List<String> imgUrl = cm.getInfoUrl(IMGURL_REG, html);
                //获取图片src地址
                List<String> imgSrc = cm.getInfoSrc(IMGSRC_REG, imgUrl);

                Download download = new Download(imgSrc);
                Thread thread = new Thread(download);
                thread.start();

/*                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File("E:\\xxxx\\imgsrc.txt"), true));
                for (String src : imgSrc) {
                    bufferedWriter.write(src);
                    bufferedWriter.newLine();
                    bufferedWriter.flush();
                }
                bufferedWriter.close();*/
            }

        } catch (Exception e) {
            System.out.println("发生错误");
        }

    }

    /**
     * 获取HTML内容
     *
     * @param urlStr
     * @return
     * @throws Exception
     */
    private String getHtml(String urlStr) throws Exception {
        java.net.URL url = new URL(urlStr);
        URLConnection connection = url.openConnection();
        InputStream in = connection.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(in));

        String line;
        StringBuilder sb = new StringBuilder();
        while (null != (line = br.readLine())) {
            sb.append(line, 0, line.length()).append('\n');
        }
        br.close();
        in.close();
        return sb.toString();
    }

    /**
     * 获取ImageUrl
     *
     * @param reg
     * @param html
     * @return
     */
    private List<String> getInfoUrl(String reg, String html) {
        Matcher matcher = Pattern.compile(reg).matcher(html);
        List<String> listImgUrl = new ArrayList<>();
        while (matcher.find()) {
            listImgUrl.add(matcher.group());
        }
        return listImgUrl;
    }

    /**
     * 获取ImageSrc地址
     *
     * @param reg
     * @param listimageurl
     * @return
     */
    private List<String> getInfoSrc(String reg, List<String> listimageurl) {
        List<String> listImageSrc = new ArrayList<>();
        for (String image : listimageurl) {
            Matcher matcher = Pattern.compile(reg).matcher(image);
            while (matcher.find()) {
                listImageSrc.add(matcher.group());
            }
        }
        return listImageSrc;
    }


    static class Download implements Runnable {

        List<String> listImgSrc;

        private Download(List<String> listImgSrc) {
            this.listImgSrc = listImgSrc;
        }

        /**
         * 执行下载,并保存
         * @param listImgSrc
         */
        private void download(List<String> listImgSrc){
            Thread thread = Thread.currentThread();
            FileOutputStream fo = null;
            InputStream in = null;
            try {
                //开始时间
                long beginSum = System.currentTimeMillis();
                for (String url : listImgSrc) {
                    url = "https:" + url;

                    url = url.substring(0, url.length() - 6);

                    //开始时间
                    long begin = System.currentTimeMillis();
                    String imageName = url.substring(url.lastIndexOf("/") + 1);
                    java.net.URL uri = new URL(url);
                    in = uri.openStream();
                    fo =  new FileOutputStream(new File("E:\\xxxx\\" + imageName));
                    byte[] buf = new byte[5120];
                    int length;
                    System.out.println(thread.getId() + "开始下载:" + url);
                    while (-1 != (length = in.read(buf, 0, buf.length))) {
                        fo.write(buf, 0, length);
                        fo.flush();
                    }
                    fo.close();
                    in.close();
                    //结束时间
                    long end = System.currentTimeMillis();
                    System.out.println(imageName + "下载完成\n"+thread.getId() + "耗时:" + (end - begin) + "ms");
                }
                long endSum = System.currentTimeMillis();
                System.out.println("总耗时:" + (endSum - beginSum) + "ms");
            } catch (Exception e) {
                if (fo != null) {
                    try {
                        fo.close();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
                if (in != null) {
                    try {
                        in.close();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
                System.out.println(thread.getId()+"下载失败");
            }
        }

        @Override
        public void run() {
           download(listImgSrc);
        }
    }
}