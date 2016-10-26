package schaffer.albumdemo.bean;

import java.util.List;

/**
 * Created by SchafferW on 2016/10/25.
 */

public class AlbumHomeBean {


    /**
     * message : OK
     * data : {"hotData":[{"id":16,"name":"Zita Robel","img":"http://git.ychlink.com/testimg/photo2.jpg","time":154760109,"artistname":"Ayla Price"},{"id":20,"name":"Lonzo Kihn III","img":"http://git.ychlink.com/testimg/photo4.jpg","time":137845294,"artistname":"Jordan Harvey"},{"id":5,"name":"Cayla Corkery","img":"http://git.ychlink.com/testimg/photo2.jpg","time":262283107,"artistname":"Ayla Price"},{"id":6,"name":"Mrs. Reanna Towne II","img":"http://git.ychlink.com/testimg/photo1.jpg","time":1426260628,"artistname":"Emile Larkin"},{"id":11,"name":"Mckayla Ernser I","img":"http://git.ychlink.com/testimg/photo4.jpg","time":422994823,"artistname":"Ayla Price"},{"id":27,"name":"Annamae Flatley","img":"http://git.ychlink.com/testimg/photo1.jpg","time":646792309,"artistname":"Emile Larkin"}],"newData":[{"id":60,"name":"Prof. Sebastian Champlin","img":"http://git.ychlink.com/testimg/photo1.jpg","time":181812264,"artistname":"Ayla Price"},{"id":59,"name":"Raymond Stokes","img":"http://git.ychlink.com/testimg/photo1.jpg","time":737347892,"artistname":"9999"},{"id":58,"name":"Merl Howell","img":"http://git.ychlink.com/testimg/photo4.jpg","time":884113566,"artistname":"1212"}]}
     * code : 0
     */

    private String message;
    private DataBean data;
    private int code;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public static class DataBean {
        /**
         * id : 16
         * name : Zita Robel
         * img : http://git.ychlink.com/testimg/photo2.jpg
         * time : 154760109
         * artistname : Ayla Price
         */

        private List<HotDataBean> hotData;
        /**
         * id : 60
         * name : Prof. Sebastian Champlin
         * img : http://git.ychlink.com/testimg/photo1.jpg
         * time : 181812264
         * artistname : Ayla Price
         */

        private List<NewDataBean> newData;

        public List<HotDataBean> getHotData() {
            return hotData;
        }

        public void setHotData(List<HotDataBean> hotData) {
            this.hotData = hotData;
        }

        public List<NewDataBean> getNewData() {
            return newData;
        }

        public void setNewData(List<NewDataBean> newData) {
            this.newData = newData;
        }

        public static class HotDataBean {
            private int id;
            private String name;
            private String img;
            private int time;
            private String artistname;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }

            public int getTime() {
                return time;
            }

            public void setTime(int time) {
                this.time = time;
            }

            public String getArtistname() {
                return artistname;
            }

            public void setArtistname(String artistname) {
                this.artistname = artistname;
            }
        }

        public static class NewDataBean {
            private int id;
            private String name;
            private String img;
            private int time;
            private String artistname;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }

            public int getTime() {
                return time;
            }

            public void setTime(int time) {
                this.time = time;
            }

            public String getArtistname() {
                return artistname;
            }

            public void setArtistname(String artistname) {
                this.artistname = artistname;
            }
        }
    }
}
