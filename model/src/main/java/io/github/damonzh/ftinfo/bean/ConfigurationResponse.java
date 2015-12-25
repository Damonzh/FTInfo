package io.github.damonzh.ftinfo.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Author:      ZhangYan
 * Date:        15/12/24
 * Description:
 */
public class ConfigurationResponse implements Serializable{

    /**
     * base_url : http://image.tmdb.org/t/p/
     * secure_base_url : https://image.tmdb.org/t/p/
     * backdrop_sizes : ["w300","w780","w1280","original"]
     * logo_sizes : ["w45","w92","w154","w185","w300","w500","original"]
     * poster_sizes : ["w92","w154","w185","w342","w500","w780","original"]
     * profile_sizes : ["w45","w185","h632","original"]
     * still_sizes : ["w92","w185","w300","original"]
     */

    private ImagesEntity images;
    /**
     * images : {"base_url":"http://image.tmdb.org/t/p/","secure_base_url":"https://image.tmdb.org/t/p/","backdrop_sizes":["w300","w780","w1280","original"],"logo_sizes":["w45","w92","w154","w185","w300","w500","original"],"poster_sizes":["w92","w154","w185","w342","w500","w780","original"],"profile_sizes":["w45","w185","h632","original"],"still_sizes":["w92","w185","w300","original"]}
     * change_keys : ["adult","also_known_as","alternative_titles","biography","birthday","budget","cast","character_names","crew","deathday","general","genres","homepage","images","imdb_id","name","original_title","overview","plot_keywords","production_companies","production_countries","releases","revenue","runtime","spoken_languages","status","tagline","title","trailers","translations"]
     */

    private List<String> change_keys;

    public void setImages(ImagesEntity images) {
        this.images = images;
    }

    public void setChange_keys(List<String> change_keys) {
        this.change_keys = change_keys;
    }

    public ImagesEntity getImages() {
        return images;
    }

    public List<String> getChange_keys() {
        return change_keys;
    }

    public static class ImagesEntity {
        private String base_url;
        private String secure_base_url;
        private List<String> backdrop_sizes;
        private List<String> logo_sizes;
        private List<String> poster_sizes;
        private List<String> profile_sizes;
        private List<String> still_sizes;

        public void setBase_url(String base_url) {
            this.base_url = base_url;
        }

        public void setSecure_base_url(String secure_base_url) {
            this.secure_base_url = secure_base_url;
        }

        public void setBackdrop_sizes(List<String> backdrop_sizes) {
            this.backdrop_sizes = backdrop_sizes;
        }

        public void setLogo_sizes(List<String> logo_sizes) {
            this.logo_sizes = logo_sizes;
        }

        public void setPoster_sizes(List<String> poster_sizes) {
            this.poster_sizes = poster_sizes;
        }

        public void setProfile_sizes(List<String> profile_sizes) {
            this.profile_sizes = profile_sizes;
        }

        public void setStill_sizes(List<String> still_sizes) {
            this.still_sizes = still_sizes;
        }

        public String getBase_url() {
            return base_url;
        }

        public String getSecure_base_url() {
            return secure_base_url;
        }

        public List<String> getBackdrop_sizes() {
            return backdrop_sizes;
        }

        public List<String> getLogo_sizes() {
            return logo_sizes;
        }

        public List<String> getPoster_sizes() {
            return poster_sizes;
        }

        public List<String> getProfile_sizes() {
            return profile_sizes;
        }

        public List<String> getStill_sizes() {
            return still_sizes;
        }
    }
}
