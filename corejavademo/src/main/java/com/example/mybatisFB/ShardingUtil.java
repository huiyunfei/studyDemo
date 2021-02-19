package com.example.mybatisFB;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class ShardingUtil {

    /**
     * 获取日期年份
     *
     * @param date
     * @return
     */
    public static int getYear(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int year = c.get(Calendar.YEAR);
        return year;
    }

    /**
     * 获取日期月份
     *
     * @param date
     * @return
     */
    public static int getMonth(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int month = c.get(Calendar.MONTH) + 1;
        return month;
    }

    /**
     * 获取日期季度
     *
     * @param date
     * @return
     */
    public static int getSeason(Date date) {
        int season = 0;
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int month = c.get(Calendar.MONTH);
        switch (month) {
            case Calendar.JANUARY:
            case Calendar.FEBRUARY:
            case Calendar.MARCH:
                season = 1;
                break;
            case Calendar.APRIL:
            case Calendar.MAY:
            case Calendar.JUNE:
                season = 2;
                break;
            case Calendar.JULY:
            case Calendar.AUGUST:
            case Calendar.SEPTEMBER:
                season = 3;
                break;
            case Calendar.OCTOBER:
            case Calendar.NOVEMBER:
            case Calendar.DECEMBER:
                season = 4;
                break;
            default:
                break;
        }
        return season;
    }


    /**
     * 获取范围条件路由表
     *
     * @param logicTableName
     * @param availableTargetNames
     * @param lowerEndpoint
     * @param upperEndpoint
     * @return
     */
    public static List<String> getRouteTablesByRange(String logicTableName, Collection<String> availableTargetNames, String lowerEndpoint, String upperEndpoint){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        List<String> result = new ArrayList<>();
        String sYearSeason = null;
        String eYearSeason = null;
        try {
            Date lowerDate = sdf.parse(lowerEndpoint);
            Date upperDate = sdf.parse(upperEndpoint);
            sYearSeason = ShardingUtil.getYear(lowerDate) + "_" + ShardingUtil.getSeason(lowerDate);
            eYearSeason = ShardingUtil.getYear(upperDate) + "_" + ShardingUtil.getSeason(upperDate);
        } catch (ParseException e) {
        }

        if(sYearSeason == null){
            throw new RuntimeException("no start createTime!");
        }

        if(eYearSeason == null){
            throw new RuntimeException("no end createTime!");
        }

        System.out.println("sYearSeason=" + sYearSeason + ", eYearSeason=" + eYearSeason);

        for (String each : availableTargetNames) {
            String sActualTable = logicTableName + "_" + sYearSeason;
            String eActualTable = logicTableName + "_" + eYearSeason;
            if (each.compareTo(sActualTable) >= 0 && each.compareTo(eActualTable) <= 0) {
                result.add(each);
            }
        }
        return result;
    }
}
