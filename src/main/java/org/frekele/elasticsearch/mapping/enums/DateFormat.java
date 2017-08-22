package org.frekele.elasticsearch.mapping.enums;

/**
 * In JSON documents, dates are represented as strings.
 * Elasticsearch uses a set of preconfigured formats to recognize and parse these strings into a long value representing milliseconds-since-the-epoch in UTC.
 *
 * @author frekele - Leandro Kersting de Freitas
 * @see <a href="https://www.elastic.co/guide/en/elasticsearch/reference/current/mapping-date-format.html#built-in-date-formats">Site Elasticsearch Reference Guide.</a>
 */
public enum DateFormat {

    /**
     * A formatter for the number of milliseconds since the epoch. Note, that this timestamp is subject to the limits of a Java Long.MIN_VALUE and Long.MAX_VALUE.
     */
    EPOCH_MILLIS("epoch_millis"),

    /**
     * A formatter for the number of seconds since the epoch. Note, that this timestamp is subject to the limits of a Java Long.MIN_VALUE and Long. MAX_VALUE divided by 1000 (the number of milliseconds in a second).
     */
    EPOCH_SECOND("epoch_second"),

    /**
     * A generic ISO datetime parser where the date is mandatory and the time is optional. Full details here.
     */
    DATE_OPTIONAL_TIME("date_optional_time"),
    /**
     * A generic ISO datetime parser where the date is mandatory and the time is optional. Full details here.
     */
    STRICT_DATE_OPTIONAL_TIME("strict_date_optional_time"),

    /**
     * A basic formatter for a full date as four digit year, two digit month of year, and two digit day of month: yyyyMMdd.
     */
    BASIC_DATE("basic_date"),

    /**
     * A basic formatter that combines a basic date and time, separated by a T: yyyyMMdd'T'HHmmss.SSSZ.
     */
    BASIC_DATE_TIME("basic_date_time"),

    /**
     * A basic formatter that combines a basic date and time without millis, separated by a T: yyyyMMdd'T'HHmmssZ.
     */
    BASIC_DATE_TIME_NO_MILLIS("basic_date_time_no_millis"),

    /**
     * A formatter for a full ordinal date, using a four digit year and three digit dayOfYear: yyyyDDD.
     */
    BASIC_ORDINAL_DATE("basic_ordinal_date"),

    /**
     * A formatter for a full ordinal date and time, using a four digit year and three digit dayOfYear: yyyyDDD'T'HHmmss.SSSZ.
     */
    BASIC_ORDINAL_DATE_TIME("basic_ordinal_date_time"),

    /**
     * A formatter for a full ordinal date and time without millis, using a four digit year and three digit dayOfYear: yyyyDDD'T'HHmmssZ.
     */
    BASIC_ORDINAL_DATE_TIME_NO_MILLIS("basic_ordinal_date_time_no_millis"),

    /**
     * A basic formatter for a two digit hour of day, two digit minute of hour, two digit second of minute, three digit millis, and time zone offset: HHmmss.SSSZ.
     */
    BASIC_TIME("basic_time"),

    /**
     * A basic formatter for a two digit hour of day, two digit minute of hour, two digit second of minute, and time zone offset: HHmmssZ.
     */
    BASIC_TIME_NO_MILLIS("basic_time_no_millis"),

    /**
     * A basic formatter for a two digit hour of day, two digit minute of hour, two digit second of minute, three digit millis, and time zone off set prefixed by T: 'T'HHmmss.SSSZ.
     */
    BASIC_T_TIME("basic_t_time"),

    /**
     * A basic formatter for a two digit hour of day, two digit minute of hour, two digit second of minute, and time zone offset prefixed by T: 'T'HHmmssZ.
     */
    BASIC_T_TIME_NO_MILLIS("basic_t_time_no_millis"),

    /**
     * A basic formatter for a full date as four digit weekyear, two digit week of weekyear, and one digit day of week: xxxx'W'wwe.
     */
    BASIC_WEEK_DATE("basic_week_date"),
    /**
     * A basic formatter for a full date as four digit weekyear, two digit week of weekyear, and one digit day of week: xxxx'W'wwe.
     */
    STRICT_BASIC_WEEK_DATE("strict_basic_week_date"),

    /**
     * A basic formatter that combines a basic weekyear date and time, separated by a T: xxxx'W'wwe'T'HHmmss.SSSZ.
     */
    BASIC_WEEK_DATE_TIME("basic_week_date_time"),
    /**
     * A basic formatter that combines a basic weekyear date and time, separated by a T: xxxx'W'wwe'T'HHmmss.SSSZ.
     */
    STRICT_BASIC_WEEK_DATE_TIME("strict_basic_week_date_time"),

    /**
     * A basic formatter that combines a basic weekyear date and time without millis, separated by a T: xxxx'W'wwe'T'HHmmssZ.
     */
    BASIC_WEEK_DATE_TIME_NO_MILLIS("basic_week_date_time_no_millis"),
    /**
     * A basic formatter that combines a basic weekyear date and time without millis, separated by a T: xxxx'W'wwe'T'HHmmssZ.
     */
    STRICT_BASIC_WEEK_DATE_TIME_NO_MILLIS("strict_basic_week_date_time_no_millis"),

    /**
     * A formatter for a full date as four digit year, two digit month of year, and two digit day of month: yyyy-MM-dd.
     */
    DATE("date"),
    /**
     * A formatter for a full date as four digit year, two digit month of year, and two digit day of month: yyyy-MM-dd.
     */
    STRICT_DATE("strict_date"),

    /**
     * A formatter that combines a full date and two digit hour of day: yyyy-MM-dd'T'HH.
     */
    DATE_HOUR("date_hour"),
    /**
     * A formatter that combines a full date and two digit hour of day: yyyy-MM-dd'T'HH.
     */
    STRICT_DATE_HOUR("strict_date_hour"),

    /**
     * A formatter that combines a full date, two digit hour of day, and two digit minute of hour: yyyy-MM-dd'T'HH:mm.
     */
    DATE_HOUR_MINUTE("date_hour_minute"),
    /**
     * A formatter that combines a full date, two digit hour of day, and two digit minute of hour: yyyy-MM-dd'T'HH:mm.
     */
    STRICT_DATE_HOUR_MINUTE("strict_date_hour_minute"),

    /**
     * A formatter that combines a full date, two digit hour of day, two digit minute of hour, and two digit second of minute: yyyy-MM-dd'T'HH:mm:ss.
     */
    DATE_HOUR_MINUTE_SECOND("date_hour_minute_second"),
    /**
     * A formatter that combines a full date, two digit hour of day, two digit minute of hour, and two digit second of minute: yyyy-MM-dd'T'HH:mm:ss.
     */
    STRICT_DATE_HOUR_MINUTE_SECOND("strict_date_hour_minute_second"),

    /**
     * A formatter that combines a full date, two digit hour of day, two digit minute of hour, two digit second of minute, and three digit fraction of second: yyyy-MM-dd'T'HH:mm:ss.SSS.
     */
    DATE_HOUR_MINUTE_SECOND_FRACTION("date_hour_minute_second_fraction"),
    /**
     * A formatter that combines a full date, two digit hour of day, two digit minute of hour, two digit second of minute, and three digit fraction of second: yyyy-MM-dd'T'HH:mm:ss.SSS.
     */
    STRICT_DATE_HOUR_MINUTE_SECOND_FRACTION("strict_date_hour_minute_second_fraction"),

    /**
     * A formatter that combines a full date, two digit hour of day, two digit minute of hour, two digit second of minute, and three digit fraction of second: yyyy-MM-dd'T'HH:mm:ss.SSS.
     */
    DATE_HOUR_MINUTE_SECOND_MILLIS("date_hour_minute_second_millis"),
    /**
     * A formatter that combines a full date, two digit hour of day, two digit minute of hour, two digit second of minute, and three digit fraction of second: yyyy-MM-dd'T'HH:mm:ss.SSS.
     */
    STRICT_DATE_HOUR_MINUTE_SECOND_MILLIS("strict_date_hour_minute_second_millis"),

    /**
     * A formatter that combines a full date and time, separated by a T: yyyy-MM-dd'T'HH:mm:ss.SSSZZ.
     */
    DATE_TIME("date_time"),
    /**
     * A formatter that combines a full date and time, separated by a T: yyyy-MM-dd'T'HH:mm:ss.SSSZZ.
     */
    STRICT_DATE_TIME("strict_date_time"),

    /**
     * A formatter that combines a full date and time without millis, separated by a T: yyyy-MM-dd'T'HH:mm:ssZZ.
     */
    DATE_TIME_NO_MILLIS("date_time_no_millis"),
    /**
     * A formatter that combines a full date and time without millis, separated by a T: yyyy-MM-dd'T'HH:mm:ssZZ.
     */
    STRICT_DATE_TIME_NO_MILLIS("strict_date_time_no_millis"),

    /**
     * A formatter for a two digit hour of day: HH
     */
    HOUR("hour"),
    /**
     * A formatter for a two digit hour of day: HH
     */
    STRICT_HOUR("strict_hour"),

    /**
     * A formatter for a two digit hour of day and two digit minute of hour: HH:mm.
     */
    HOUR_MINUTE("hour_minute"),
    /**
     * A formatter for a two digit hour of day and two digit minute of hour: HH:mm.
     */
    STRICT_HOUR_MINUTE("strict_hour_minute"),

    /**
     * A formatter for a two digit hour of day, two digit minute of hour, and two digit second of minute: HH:mm:ss.
     */
    HOUR_MINUTE_SECOND("hour_minute_second"),
    /**
     * A formatter for a two digit hour of day, two digit minute of hour, and two digit second of minute: HH:mm:ss.
     */
    STRICT_HOUR_MINUTE_SECOND("strict_hour_minute_second"),

    /**
     * A formatter for a two digit hour of day, two digit minute of hour, two digit second of minute, and three digit fraction of second: HH:mm:ss.SSS.
     */
    HOUR_MINUTE_SECOND_FRACTION("hour_minute_second_fraction"),
    /**
     * A formatter for a two digit hour of day, two digit minute of hour, two digit second of minute, and three digit fraction of second: HH:mm:ss.SSS.
     */
    STRICT_HOUR_MINUTE_SECOND_FRACTION("strict_hour_minute_second_fraction"),

    /**
     * A formatter for a two digit hour of day, two digit minute of hour, two digit second of minute, and three digit fraction of second: HH:mm:ss.SSS.
     */
    HOUR_MINUTE_SECOND_MILLIS("hour_minute_second_millis"),
    /**
     * A formatter for a two digit hour of day, two digit minute of hour, two digit second of minute, and three digit fraction of second: HH:mm:ss.SSS.
     */
    STRICT_HOUR_MINUTE_SECOND_MILLIS("strict_hour_minute_second_millis"),

    /**
     * A formatter for a full ordinal date, using a four digit year and three digit dayOfYear: yyyy-DDD.
     */
    ORDINAL_DATE("ordinal_date"),
    /**
     * A formatter for a full ordinal date, using a four digit year and three digit dayOfYear: yyyy-DDD.
     */
    STRICT_ORDINAL_DATE("strict_ordinal_date"),

    /**
     * A formatter for a full ordinal date and time, using a four digit year and three digit dayOfYear: yyyy-DDD'T'HH:mm:ss.SSSZZ.
     */
    ORDINAL_DATE_TIME("ordinal_date_time"),
    /**
     * A formatter for a full ordinal date and time, using a four digit year and three digit dayOfYear: yyyy-DDD'T'HH:mm:ss.SSSZZ.
     */
    STRICT_ORDINAL_DATE_TIME("strict_ordinal_date_time"),

    /**
     * A formatter for a full ordinal date and time without millis, using a four digit year and three digit dayOfYear: yyyy-DDD'T'HH:mm:ssZZ.
     */
    ORDINAL_DATE_TIME_NO_MILLIS("ordinal_date_time_no_millis"),
    /**
     * A formatter for a full ordinal date and time without millis, using a four digit year and three digit dayOfYear: yyyy-DDD'T'HH:mm:ssZZ.
     */
    STRICT_ORDINAL_DATE_TIME_NO_MILLIS("strict_ordinal_date_time_no_millis"),

    /**
     * A formatter for a two digit hour of day, two digit minute of hour, two digit second of minute, three digit fraction of second, and time zone offset: HH:mm:ss.SSSZZ.
     */
    TIME("time"),
    /**
     * A formatter for a two digit hour of day, two digit minute of hour, two digit second of minute, three digit fraction of second, and time zone offset: HH:mm:ss.SSSZZ.
     */
    STRICT_TIME("strict_time"),

    /**
     * A formatter for a two digit hour of day, two digit minute of hour, two digit second of minute, and time zone offset: HH:mm:ssZZ.
     */
    TIME_NO_MILLIS("time_no_millis"),
    /**
     * A formatter for a two digit hour of day, two digit minute of hour, two digit second of minute, and time zone offset: HH:mm:ssZZ.
     */
    STRICT_TIME_NO_MILLIS("strict_time_no_millis"),

    /**
     * A formatter for a two digit hour of day, two digit minute of hour, two digit second of minute, three digit fraction of second, and time zone offset prefixed by T: 'T'HH:mm:ss.SSSZZ.
     */
    T_TIME("t_time"),
    /**
     * A formatter for a two digit hour of day, two digit minute of hour, two digit second of minute, three digit fraction of second, and time zone offset prefixed by T: 'T'HH:mm:ss.SSSZZ.
     */
    STRICT_T_TIME("strict_t_time"),

    /**
     * A formatter for a two digit hour of day, two digit minute of hour, two digit second of minute, and time zone offset prefixed by T: 'T'HH:mm:ssZZ.
     */
    T_TIME_NO_MILLIS("t_time_no_millis"),
    /**
     * A formatter for a two digit hour of day, two digit minute of hour, two digit second of minute, and time zone offset prefixed by T: 'T'HH:mm:ssZZ.
     */
    STRICT_T_TIME_NO_MILLIS("strict_t_time_no_millis"),

    /**
     * A formatter for a full date as four digit weekyear, two digit week of weekyear, and one digit day of week: xxxx-'W'ww-e.
     */
    WEEK_DATE("week_date"),
    /**
     * A formatter for a full date as four digit weekyear, two digit week of weekyear, and one digit day of week: xxxx-'W'ww-e.
     */
    STRICT_WEEK_DATE("strict_week_date"),

    /**
     * A formatter that combines a full weekyear date and time, separated by a T: xxxx-'W'ww-e'T'HH:mm:ss.SSSZZ.
     */
    WEEK_DATE_TIME("week_date_time"),
    /**
     * A formatter that combines a full weekyear date and time, separated by a T: xxxx-'W'ww-e'T'HH:mm:ss.SSSZZ.
     */
    STRICT_WEEK_DATE_TIME("strict_week_date_time"),

    /**
     * A formatter that combines a full weekyear date and time without millis, separated by a T: xxxx-'W'ww-e'T'HH:mm:ssZZ.
     */
    WEEK_DATE_TIME_NO_MILLIS("week_date_time_no_millis"),
    /**
     * A formatter that combines a full weekyear date and time without millis, separated by a T: xxxx-'W'ww-e'T'HH:mm:ssZZ.
     */
    STRICT_WEEK_DATE_TIME_NO_MILLIS("strict_week_date_time_no_millis"),

    /**
     * A formatter for a four digit weekyear: xxxx.
     */
    WEEKYEAR("weekyear"),
    /**
     * A formatter for a four digit weekyear: xxxx.
     */
    STRICT_WEEKYEAR("strict_weekyear"),

    /**
     * A formatter for a four digit weekyear and two digit week of weekyear: xxxx-'W'ww.
     */
    WEEKYEAR_WEEK("weekyear_week"),
    /**
     * A formatter for a four digit weekyear and two digit week of weekyear: xxxx-'W'ww.
     */
    STRICT_WEEKYEAR_WEEK("strict_weekyear_week"),

    /**
     * A formatter for a four digit weekyear, two digit week of weekyear, and one digit day of week: xxxx-'W'ww-e.
     */
    WEEKYEAR_WEEK_DAY("weekyear_week_day"),
    /**
     * A formatter for a four digit weekyear, two digit week of weekyear, and one digit day of week: xxxx-'W'ww-e.
     */
    STRICT_WEEKYEAR_WEEK_DAY("strict_weekyear_week_day"),

    /**
     * A formatter for a four digit year: yyyy.
     */
    YEAR("year"),
    /**
     * A formatter for a four digit year: yyyy.
     */
    STRICT_YEAR("strict_year"),

    /**
     * A formatter for a four digit year and two digit month of year: yyyy-MM.
     */
    YEAR_MONTH("year_month"),
    /**
     * A formatter for a four digit year and two digit month of year: yyyy-MM.
     */
    STRICT_YEAR_MONTH("strict_year_month"),

    /**
     * A formatter for a four digit year, two digit month of year, and two digit day of month: yyyy-MM-dd.
     */
    YEAR_MONTH_DAY("year_month_day"),
    /**
     * A formatter for a four digit year, two digit month of year, and two digit day of month: yyyy-MM-dd.
     */
    STRICT_YEAR_MONTH_DAY("strict_year_month_day");

    // /**
    // * Custom date formats, completely customizable date formats are supported. The syntax for these is explained in the Joda docs.
    // */
    // CUSTOM("custom");

    private String name;

    DateFormat(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
