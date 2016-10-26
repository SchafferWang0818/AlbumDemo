package schaffer.albumdemo.bean;

import java.util.List;

/**
 * Created by SchafferW on 2016/10/25.
 */

public class AlbumAllBean {

    /**
     * message : OK
     * data : {"allAlbum":[{"id":60,"name":"Prof. Sebastian Champlin","img":"http://git.ychlink.com/testimg/photo1.jpg","time":181812264,"artistname":"Ayla Price"},{"id":59,"name":"Raymond Stokes","img":"http://git.ychlink.com/testimg/photo1.jpg","time":737347892,"artistname":"9999"},{"id":58,"name":"Merl Howell","img":"http://git.ychlink.com/testimg/photo4.jpg","time":884113566,"artistname":"1212"},{"id":57,"name":"Mrs. Amanda Ritchie IV","img":"http://git.ychlink.com/testimg/photo2.jpg","time":294540984,"artistname":"444"},{"id":56,"name":"Roy Renner","img":"http://git.ychlink.com/testimg/photo4.jpg","time":503824260,"artistname":"1212"},{"id":55,"name":"Brenden Torp","img":"http://git.ychlink.com/testimg/photo2.jpg","time":1351742658,"artistname":"Ayla Price"},{"id":54,"name":"Pat Funk","img":"http://git.ychlink.com/testimg/photo3.jpg","time":561529064,"artistname":"Ayla Price"},{"id":53,"name":"Lance Kassulke V","img":"http://git.ychlink.com/testimg/photo3.jpg","time":621543129,"artistname":"9999"},{"id":52,"name":"Ms. Florida Vandervort DVM","img":"http://git.ychlink.com/testimg/photo2.jpg","time":728480884,"artistname":"3333"},{"id":51,"name":"Lonny Rau","img":"http://git.ychlink.com/testimg/photo2.jpg","time":535877616,"artistname":"444"},{"id":50,"name":"Davon Gislason","img":"http://git.ychlink.com/testimg/photo3.jpg","time":247658440,"artistname":"9999"},{"id":49,"name":"Mr. Webster Collins Jr.","img":"http://git.ychlink.com/testimg/photo2.jpg","time":948249940,"artistname":"3333"},{"id":48,"name":"Madyson Corkery V","img":"http://git.ychlink.com/testimg/photo4.jpg","time":615529175,"artistname":"444"},{"id":47,"name":"Eva Harber","img":"http://git.ychlink.com/testimg/photo4.jpg","time":874901323,"artistname":"22"},{"id":46,"name":"Ms. Aaliyah Anderson MD","img":"http://git.ychlink.com/testimg/photo2.jpg","time":254648540,"artistname":"Jordan Harvey"},{"id":45,"name":"Taylor Carroll","img":"http://git.ychlink.com/testimg/photo2.jpg","time":1292100833,"artistname":"22"},{"id":44,"name":"Ms. Arielle Cummerata III","img":"http://git.ychlink.com/testimg/photo1.jpg","time":457417871,"artistname":"9999"},{"id":43,"name":"Reina McLaughlin","img":"http://git.ychlink.com/testimg/photo3.jpg","time":1178522354,"artistname":"3333"},{"id":42,"name":"Vallie Huels","img":"http://git.ychlink.com/testimg/photo2.jpg","time":1432837602,"artistname":"22"},{"id":41,"name":"Meda Mohr","img":"http://git.ychlink.com/testimg/photo4.jpg","time":654148317,"artistname":"Jordan Harvey"},{"id":40,"name":"Vicente Deckow II","img":"http://git.ychlink.com/testimg/photo4.jpg","time":127765814,"artistname":"Jordan Harvey"}],"currentPage":"1"}
     * code : 0
     */

    private String message;
    /**
     * allAlbum : [{"id":60,"name":"Prof. Sebastian Champlin","img":"http://git.ychlink.com/testimg/photo1.jpg","time":181812264,"artistname":"Ayla Price"},{"id":59,"name":"Raymond Stokes","img":"http://git.ychlink.com/testimg/photo1.jpg","time":737347892,"artistname":"9999"},{"id":58,"name":"Merl Howell","img":"http://git.ychlink.com/testimg/photo4.jpg","time":884113566,"artistname":"1212"},{"id":57,"name":"Mrs. Amanda Ritchie IV","img":"http://git.ychlink.com/testimg/photo2.jpg","time":294540984,"artistname":"444"},{"id":56,"name":"Roy Renner","img":"http://git.ychlink.com/testimg/photo4.jpg","time":503824260,"artistname":"1212"},{"id":55,"name":"Brenden Torp","img":"http://git.ychlink.com/testimg/photo2.jpg","time":1351742658,"artistname":"Ayla Price"},{"id":54,"name":"Pat Funk","img":"http://git.ychlink.com/testimg/photo3.jpg","time":561529064,"artistname":"Ayla Price"},{"id":53,"name":"Lance Kassulke V","img":"http://git.ychlink.com/testimg/photo3.jpg","time":621543129,"artistname":"9999"},{"id":52,"name":"Ms. Florida Vandervort DVM","img":"http://git.ychlink.com/testimg/photo2.jpg","time":728480884,"artistname":"3333"},{"id":51,"name":"Lonny Rau","img":"http://git.ychlink.com/testimg/photo2.jpg","time":535877616,"artistname":"444"},{"id":50,"name":"Davon Gislason","img":"http://git.ychlink.com/testimg/photo3.jpg","time":247658440,"artistname":"9999"},{"id":49,"name":"Mr. Webster Collins Jr.","img":"http://git.ychlink.com/testimg/photo2.jpg","time":948249940,"artistname":"3333"},{"id":48,"name":"Madyson Corkery V","img":"http://git.ychlink.com/testimg/photo4.jpg","time":615529175,"artistname":"444"},{"id":47,"name":"Eva Harber","img":"http://git.ychlink.com/testimg/photo4.jpg","time":874901323,"artistname":"22"},{"id":46,"name":"Ms. Aaliyah Anderson MD","img":"http://git.ychlink.com/testimg/photo2.jpg","time":254648540,"artistname":"Jordan Harvey"},{"id":45,"name":"Taylor Carroll","img":"http://git.ychlink.com/testimg/photo2.jpg","time":1292100833,"artistname":"22"},{"id":44,"name":"Ms. Arielle Cummerata III","img":"http://git.ychlink.com/testimg/photo1.jpg","time":457417871,"artistname":"9999"},{"id":43,"name":"Reina McLaughlin","img":"http://git.ychlink.com/testimg/photo3.jpg","time":1178522354,"artistname":"3333"},{"id":42,"name":"Vallie Huels","img":"http://git.ychlink.com/testimg/photo2.jpg","time":1432837602,"artistname":"22"},{"id":41,"name":"Meda Mohr","img":"http://git.ychlink.com/testimg/photo4.jpg","time":654148317,"artistname":"Jordan Harvey"},{"id":40,"name":"Vicente Deckow II","img":"http://git.ychlink.com/testimg/photo4.jpg","time":127765814,"artistname":"Jordan Harvey"}]
     * currentPage : 1
     */

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
        private String currentPage;
        /**
         * id : 60
         * name : Prof. Sebastian Champlin
         * img : http://git.ychlink.com/testimg/photo1.jpg
         * time : 181812264
         * artistname : Ayla Price
         */

        private List<AllAlbumBean> allAlbum;

        public String getCurrentPage() {
            return currentPage;
        }

        public void setCurrentPage(String currentPage) {
            this.currentPage = currentPage;
        }

        public List<AllAlbumBean> getAllAlbum() {
            return allAlbum;
        }

        public void setAllAlbum(List<AllAlbumBean> allAlbum) {
            this.allAlbum = allAlbum;
        }

        public static class AllAlbumBean {
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
