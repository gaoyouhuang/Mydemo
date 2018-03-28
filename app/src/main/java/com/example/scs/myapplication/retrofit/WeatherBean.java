package com.example.scs.myapplication.retrofit;

import java.util.List;

/**
 * Created by scs on 18-3-8.
 */

public class WeatherBean {

    /**
     * date : 20180308
     * message : Success !
     * status : 200
     * city : 北京
     * count : 323
     * data : {"shidu":"63%","pm25":61,"pm10":82,"quality":"良","wendu":"-2","ganmao":"极少数敏感人群应减少户外活动","yesterday":{"date":"07日星期三","sunrise":"06:41","high":"高温 6.0℃","low":"低温 -2.0℃","sunset":"18:12","aqi":73,"fx":"北风","fl":"<3级","type":"阴","notice":"不要被阴云遮挡住好心情"},"forecast":[{"date":"08日星期四","sunrise":"06:40","high":"高温 8.0℃","low":"低温 -4.0℃","sunset":"18:13","aqi":57,"fx":"东北风","fl":"<3级","type":"晴","notice":"愿你拥有比阳光明媚的心情"},{"date":"09日星期五","sunrise":"06:38","high":"高温 10.0℃","low":"低温 -3.0℃","sunset":"18:14","aqi":106,"fx":"西南风","fl":"<3级","type":"多云","notice":"阴晴之间，谨防紫外线侵扰"},{"date":"10日星期六","sunrise":"06:37","high":"高温 12.0℃","low":"低温 -2.0℃","sunset":"18:15","aqi":64,"fx":"东南风","fl":"<3级","type":"晴","notice":"愿你拥有比阳光明媚的心情"},{"date":"11日星期日","sunrise":"06:35","high":"高温 11.0℃","low":"低温 -1.0℃","sunset":"18:16","aqi":72,"fx":"东南风","fl":"<3级","type":"多云","notice":"阴晴之间，谨防紫外线侵扰"},{"date":"12日星期一","sunrise":"06:34","high":"高温 14.0℃","low":"低温 1.0℃","sunset":"18:17","aqi":118,"fx":"南风","fl":"<3级","type":"晴","notice":"愿你拥有比阳光明媚的心情"}]}
     */

    private String date;
    private String message;
    private int status;
    private String city;
    private int count;
    private DataBean data;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * shidu : 63%
         * pm25 : 61
         * pm10 : 82
         * quality : 良
         * wendu : -2
         * ganmao : 极少数敏感人群应减少户外活动
         * yesterday : {"date":"07日星期三","sunrise":"06:41","high":"高温 6.0℃","low":"低温 -2.0℃","sunset":"18:12","aqi":73,"fx":"北风","fl":"<3级","type":"阴","notice":"不要被阴云遮挡住好心情"}
         * forecast : [{"date":"08日星期四","sunrise":"06:40","high":"高温 8.0℃","low":"低温 -4.0℃","sunset":"18:13","aqi":57,"fx":"东北风","fl":"<3级","type":"晴","notice":"愿你拥有比阳光明媚的心情"},{"date":"09日星期五","sunrise":"06:38","high":"高温 10.0℃","low":"低温 -3.0℃","sunset":"18:14","aqi":106,"fx":"西南风","fl":"<3级","type":"多云","notice":"阴晴之间，谨防紫外线侵扰"},{"date":"10日星期六","sunrise":"06:37","high":"高温 12.0℃","low":"低温 -2.0℃","sunset":"18:15","aqi":64,"fx":"东南风","fl":"<3级","type":"晴","notice":"愿你拥有比阳光明媚的心情"},{"date":"11日星期日","sunrise":"06:35","high":"高温 11.0℃","low":"低温 -1.0℃","sunset":"18:16","aqi":72,"fx":"东南风","fl":"<3级","type":"多云","notice":"阴晴之间，谨防紫外线侵扰"},{"date":"12日星期一","sunrise":"06:34","high":"高温 14.0℃","low":"低温 1.0℃","sunset":"18:17","aqi":118,"fx":"南风","fl":"<3级","type":"晴","notice":"愿你拥有比阳光明媚的心情"}]
         */

        private String shidu;
        private int pm25;
        private int pm10;
        private String quality;
        private String wendu;
        private String ganmao;
        private YesterdayBean yesterday;
        private List<ForecastBean> forecast;

        public String getShidu() {
            return shidu;
        }

        public void setShidu(String shidu) {
            this.shidu = shidu;
        }

        public int getPm25() {
            return pm25;
        }

        public void setPm25(int pm25) {
            this.pm25 = pm25;
        }

        public int getPm10() {
            return pm10;
        }

        public void setPm10(int pm10) {
            this.pm10 = pm10;
        }

        public String getQuality() {
            return quality;
        }

        public void setQuality(String quality) {
            this.quality = quality;
        }

        public String getWendu() {
            return wendu;
        }

        public void setWendu(String wendu) {
            this.wendu = wendu;
        }

        public String getGanmao() {
            return ganmao;
        }

        public void setGanmao(String ganmao) {
            this.ganmao = ganmao;
        }

        public YesterdayBean getYesterday() {
            return yesterday;
        }

        public void setYesterday(YesterdayBean yesterday) {
            this.yesterday = yesterday;
        }

        public List<ForecastBean> getForecast() {
            return forecast;
        }

        public void setForecast(List<ForecastBean> forecast) {
            this.forecast = forecast;
        }

        public static class YesterdayBean {
            /**
             * date : 07日星期三
             * sunrise : 06:41
             * high : 高温 6.0℃
             * low : 低温 -2.0℃
             * sunset : 18:12
             * aqi : 73
             * fx : 北风
             * fl : <3级
             * type : 阴
             * notice : 不要被阴云遮挡住好心情
             */

            private String date;
            private String sunrise;
            private String high;
            private String low;
            private String sunset;
            private int aqi;
            private String fx;
            private String fl;
            private String type;
            private String notice;

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public String getSunrise() {
                return sunrise;
            }

            public void setSunrise(String sunrise) {
                this.sunrise = sunrise;
            }

            public String getHigh() {
                return high;
            }

            public void setHigh(String high) {
                this.high = high;
            }

            public String getLow() {
                return low;
            }

            public void setLow(String low) {
                this.low = low;
            }

            public String getSunset() {
                return sunset;
            }

            public void setSunset(String sunset) {
                this.sunset = sunset;
            }

            public int getAqi() {
                return aqi;
            }

            public void setAqi(int aqi) {
                this.aqi = aqi;
            }

            public String getFx() {
                return fx;
            }

            public void setFx(String fx) {
                this.fx = fx;
            }

            public String getFl() {
                return fl;
            }

            public void setFl(String fl) {
                this.fl = fl;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getNotice() {
                return notice;
            }

            public void setNotice(String notice) {
                this.notice = notice;
            }
        }

        public static class ForecastBean {
            /**
             * date : 08日星期四
             * sunrise : 06:40
             * high : 高温 8.0℃
             * low : 低温 -4.0℃
             * sunset : 18:13
             * aqi : 57
             * fx : 东北风
             * fl : <3级
             * type : 晴
             * notice : 愿你拥有比阳光明媚的心情
             */

            private String date;
            private String sunrise;
            private String high;
            private String low;
            private String sunset;
            private int aqi;
            private String fx;
            private String fl;
            private String type;
            private String notice;

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public String getSunrise() {
                return sunrise;
            }

            public void setSunrise(String sunrise) {
                this.sunrise = sunrise;
            }

            public String getHigh() {
                return high;
            }

            public void setHigh(String high) {
                this.high = high;
            }

            public String getLow() {
                return low;
            }

            public void setLow(String low) {
                this.low = low;
            }

            public String getSunset() {
                return sunset;
            }

            public void setSunset(String sunset) {
                this.sunset = sunset;
            }

            public int getAqi() {
                return aqi;
            }

            public void setAqi(int aqi) {
                this.aqi = aqi;
            }

            public String getFx() {
                return fx;
            }

            public void setFx(String fx) {
                this.fx = fx;
            }

            public String getFl() {
                return fl;
            }

            public void setFl(String fl) {
                this.fl = fl;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getNotice() {
                return notice;
            }

            public void setNotice(String notice) {
                this.notice = notice;
            }
        }
    }
}
