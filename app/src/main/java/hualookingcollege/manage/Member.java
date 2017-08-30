package hualookingcollege.manage;

/**
 * Created by 13156 on 2017/8/17.
 */

public class Member {
    private String Title;
    private String SubTitle;
    private String Date;
    public Member(String Title,String SubTitle,String Date){
        this.Title=Title;
        this.SubTitle=SubTitle;
        this.Date=Date;
    }
    public String getTitle(){
        return Title;
    }

    public String getSubTitle() {
        return SubTitle;
    }
    public String getDate(){
        return Date;
    }
}
