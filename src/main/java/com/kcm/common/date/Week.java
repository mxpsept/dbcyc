package com.kcm.common.date;

public enum Week {
    /**
     * 星期一
     */
	MONDAY("星期一", "Monday", "Mon.", 1),
    /**
     * 星期二
     */
    TUESDAY("星期二", "Tuesday", "Tues.", 2),
    /**
     * 星期三
     */
    WEDNESDAY("星期三", "Wednesday", "Wed.", 3),
    /**
     * 星期四
     */
    THURSDAY("星期四", "Thursday", "Thur.", 4),
    /**
     * 星期五
     */
    FRIDAY("星期五", "Friday", "Fri.", 5),
    /**
     * 星期六
     */
    SATURDAY("星期六", "Saturday", "Sat.", 6),
    /**
     * 星期日
     */
    SUNDAY("星期日", "Sunday", "Sun.", 7);
      
    String nameCn;  
    String nameEn;  
    String nameEnShort;  
    int number;  
      
    Week(String nameCn, String nameEn, String nameEnShort, int number) {  
        this.nameCn = nameCn;  
        this.nameEn = nameEn;  
        this.nameEnShort = nameEnShort;  
        this.number = number;  
    }  
      
    public String getChineseName() {  
        return nameCn;  
    }  
  
    public String getName() {  
        return nameEn;  
    }  
  
    public String getShortName() {  
        return nameEnShort;  
    }  
  
    public int getNumber() {  
        return number;  
    }  
}