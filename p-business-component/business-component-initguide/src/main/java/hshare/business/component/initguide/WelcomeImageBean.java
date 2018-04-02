package hshare.business.component.initguide;

import android.text.TextUtils;

import org.w3c.dom.Text;

import java.io.Serializable;

/**
 * Created by hcare on 2018/4/2.
 */

public class WelcomeImageBean implements Serializable {
    private int replaceRes;
    private int defaultWelcomeImageRes;
    private String localWelcomeImage;
    private String localAdImage;

    private String welcomeImageDownloadUrl;
    private String adImageDownloadUrl;

    public WelcomeImageBean(int replaceRes,int defaultWelcomeImageRes){
        this.replaceRes = replaceRes;
        this.defaultWelcomeImageRes = defaultWelcomeImageRes;
    }

    public boolean hasLocalImage(){
        if (TextUtils.isEmpty(getLocalImage())){
            return false;
        }
        return true;
    }

    public String getLocalImage(){
        if (!TextUtils.isEmpty(localAdImage)){
            return localAdImage;
        }else if (!TextUtils.isEmpty(localWelcomeImage)){
            return localWelcomeImage;
        }else {
            return "";
        }
    }

    public int getDefaultWelcomeImageRes() {
        return defaultWelcomeImageRes;
    }

    public void setDefaultWelcomeImageRes(int defaultWelcomeImageRes) {
        this.defaultWelcomeImageRes = defaultWelcomeImageRes;
    }

    public String getLocalWelcomeImage() {
        return localWelcomeImage;
    }

    public void setLocalWelcomeImage(String localWelcomeImage) {
        this.localWelcomeImage = localWelcomeImage;
    }

    public String getLocalAdImage() {
        return localAdImage;
    }

    public void setLocalAdImage(String localAdImage) {
        this.localAdImage = localAdImage;
    }

    public String getWelcomeImageDownloadUrl() {
        return welcomeImageDownloadUrl;
    }

    public void setWelcomeImageDownloadUrl(String welcomeImageDownloadUrl) {
        this.welcomeImageDownloadUrl = welcomeImageDownloadUrl;
    }

    public String getAdImageDownloadUrl() {
        return adImageDownloadUrl;
    }

    public void setAdImageDownloadUrl(String adImageDownloadUrl) {
        this.adImageDownloadUrl = adImageDownloadUrl;
    }

    public int getReplaceRes() {
        return replaceRes;
    }

    public void setReplaceRes(int replaceRes) {
        this.replaceRes = replaceRes;
    }
}
