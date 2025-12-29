package com.duoec.base.core.util;


import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.TemporalAdjusters;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author xuwenzhen
 * @since 2021/5/28 10:58 AM
 */
public class DateTimeUtils {
    public static final String YYYY_MM_DD = "yyyy-MM-dd";
    public static final String YYYYMMDD = "yyyyMMdd";
    public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
    public static final String ISO_8601_PATTERN = "yyyy-MM-dd'T'HH:mm:ss.SSS";
    public static final String TIME_RANGE_ALL = "all";
    public static final String TIME_RANGE_TODAY = "today";
    public static final String TIME_RANGE_WEEK = "week";
    public static final String TIME_RANGE_MONTH = "month";
    public static final String TIME_RANGE_OPTIONAL = "optional";
    public static final int MIN_VAL = 10;

    private DateTimeUtils() {
    }

    public static final ZoneId ZONE_ID = ZoneId.of("Asia/Shanghai");

    public static final DateTimeFormatter YYYY_MM_DD_DTF = DateTimeFormatter.ofPattern(YYYY_MM_DD);
    public static final DateTimeFormatter YYYY_MM_DD_HH_MM_SS_DTF = DateTimeFormatter.ofPattern(YYYY_MM_DD_HH_MM_SS);
    public static final DateTimeFormatter ISO_8601_DTF = DateTimeFormatter.ofPattern(ISO_8601_PATTERN);
    private static final Pattern X_DAY_AGO_PATTERN = Pattern.compile("^-(\\d+)d$");


    /**
     * 获取时间戳（毫秒粒度）
     *
     * @param time LocalDateTime时间
     * @return 时间戳（毫秒粒度），如果入参为null，则返回0
     */
    public static long getMilli(LocalDateTime time) {
        if (time == null) {
            return 0;
        }
        return time.atZone(ZONE_ID).toInstant().toEpochMilli();
    }

    /**
     * 格式化LocalDateTime时间
     *
     * @param time    时间
     * @param pattern 格式化模板
     * @return 格式化后字符串
     */
    public static String format(LocalDateTime time, String pattern) {
        DateTimeFormatter df;
        if (pattern == null || pattern.equals(YYYY_MM_DD)) {
            df = YYYY_MM_DD_DTF;
        } else {
            df = DateTimeFormatter.ofPattern(pattern);
        }

        return df.format(time);
    }

    public static String format(LocalDateTime time) {
        return format(time, YYYY_MM_DD);
    }

    /**
     * 将日期字符转换为时间戳（毫秒粒度）
     *
     * @param dateTimeStr 日期-时间字符串
     * @return 时间戳（毫秒粒度）
     */
    public static Long dateTimeStrToMilli(String dateTimeStr) {
        LocalDateTime dateTime = toLocalDateTime(dateTimeStr);
        if (dateTime == null) {
            return null;
        }
        return getMilli(dateTime);
    }

    /**
     * 将日期，转换为LocalDateTime
     *
     * @param dateStr 日期字符串
     * @return 如果入参为null，则返回null
     */
    public static LocalDate toLocalDate(String dateStr) {
        if (dateStr == null) {
            return null;
        }
        return LocalDate.parse(dateStr, YYYY_MM_DD_DTF);
    }

    /**
     * 将日期-时间字符，转换为LocalDateTime
     *
     * @param dateStartStr 日期字符串。日期格式，比如yyyy-MM-dd HH:mm:ss
     * @return 如果入参为null，则返回null，如果
     * @throws DateTimeParseException 如果不能匹配时，会抛出异常
     */
    public static LocalDateTime toLocalDateTime(String dateStartStr) {
        if (dateStartStr == null || dateStartStr.trim().equals("")) {
            return null;
        }
        return LocalDateTime.parse(dateStartStr, YYYY_MM_DD_HH_MM_SS_DTF);
    }

    /**
     * 通过时间范围描述，获取开始、结束时间
     *
     * @param timeRangeDesc 时间范围描述：all=所有 today=今天 week=本周（自然周） -xxd=近xx天（包含当天）month=本月
     * @return 时间范围
     */
    public static TimeRange getTimeRange(String timeRangeDesc) {
        return getTimeRange(timeRangeDesc, null, null);
    }

    /**
     * 通过时间范围描述，获取开始、结束时间
     *
     * @param timeRangeDesc 时间范围描述：all=所有 today=今天 week=本周（自然周） -xxd=近xx天（包含当天）month=本月  optional=随意时间（需要指定startDate和endDate）
     * @return 时间范围
     */
    public static TimeRange getTimeRange(String timeRangeDesc, String startDate, String endDate) {
        if (timeRangeDesc == null) {
            return null;
        }
        LocalDateTime start = null;
        LocalDateTime end = LocalDateTime.now();
        if (TIME_RANGE_ALL.equalsIgnoreCase(timeRangeDesc)) {
            //所有
            end = null;
        } else if (TIME_RANGE_TODAY.equalsIgnoreCase(timeRangeDesc)) {
            start = end.withHour(0).withMinute(0).withSecond(0).withNano(0);
        } else if (TIME_RANGE_WEEK.equalsIgnoreCase(timeRangeDesc)) {
            start = end.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY)).withHour(0).withMinute(0).withSecond(0).withNano(0);
        } else if (TIME_RANGE_MONTH.equalsIgnoreCase(timeRangeDesc)) {
            start = end.withDayOfMonth(1).withHour(0).withMinute(0).withSecond(0).withNano(0);
        } else if (TIME_RANGE_OPTIONAL.equalsIgnoreCase(timeRangeDesc)) {
            start = toLocalDateTime(startDate);
            if (endDate != null) {
                LocalDateTime localDateTime = toLocalDateTime(endDate);
                if (localDateTime != null) {
                    end = localDateTime.plusDays(1).minusNanos(-1L);
                }
            }
        } else {
            Matcher matcher = X_DAY_AGO_PATTERN.matcher(timeRangeDesc);
            if (!matcher.find()) {
                return null;
            }
            int date = Integer.parseInt(matcher.group(1));
            start = end.plusDays(-date);
        }

        return new TimeRange(start, end);
    }

    public static LocalDateTime millisToLocalDateTime(Long millis) {
        if (null == millis) {
            return null;
        }
        String millisStr = String.valueOf(millis);
        if (millisStr.length() <= MIN_VAL) {
            millis = millis * 1000;
        }
        ZoneId zone = ZoneId.systemDefault();
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(millis), zone);
    }

    public static class TimeRange {
        /**
         * 开始时间
         */
        private final LocalDateTime start;

        /**
         * 结束时间
         */
        private final LocalDateTime end;

        public TimeRange(LocalDateTime start, LocalDateTime end) {
            this.start = start;
            this.end = end;
        }

        public LocalDateTime getStart() {
            return start;
        }

        public LocalDateTime getEnd() {
            return end;
        }
    }

    public static String getTimeDisplay(long timeMillis) {
        long time = System.currentTimeMillis() - timeMillis;
        if (time < 1000) {
            return time + "ms";
        }
        if (time < 60 * 1000) {
            return time / 1000 + "." + time % 1000 + "s";
        }
        return time / 1000 / 60 + ":" + time / 1000 % 60 + "." + time % 1000 + "s";
    }
}
